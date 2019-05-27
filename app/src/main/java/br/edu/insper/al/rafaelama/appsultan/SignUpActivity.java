package br.edu.insper.al.rafaelama.appsultan;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SignUpActivity extends AppCompatActivity {

    private EditText emailSign;
    private EditText passwordSign;
    private EditText passwordSignConf;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        emailSign = findViewById(R.id.email_signup);
        passwordSign = findViewById(R.id.password_signup);
        passwordSignConf = findViewById(R.id.password_signup_confirmation);


        Button cancel = findViewById(R.id.buttonCancel);
        Button register = findViewById(R.id.signup_button);

        firebaseAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailSign.getText().toString().trim();
                String password = passwordSign.getText().toString().trim();
                String passwordConf = passwordSignConf.getText().toString().trim();
                String password1 = passwordSign.getText().toString();
                String password2 = passwordSignConf.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(SignUpActivity.this, "Email inválido", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {

                    Toast.makeText(SignUpActivity.this, "Senha inválida", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(passwordConf)) {

                    Toast.makeText(SignUpActivity.this, "Senhas incompatíveis", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6) {

                    Toast.makeText(SignUpActivity.this, "Senha muito curta", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password1.equals(password2)) {
                    firebaseAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = firebaseAuth.getCurrentUser();

                                     } else {

                    Toast.makeText(SignUpActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();

                }
                                }
                            });
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivityForResult(returnIntent, 1);
            }
        });
    }
}
