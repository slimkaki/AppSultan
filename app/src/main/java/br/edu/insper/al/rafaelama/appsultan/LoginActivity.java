package br.edu.insper.al.rafaelama.appsultan;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {


    private EditText emailn;
    private EditText passwordn;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        emailn = (EditText)findViewById(R.id.email);
        passwordn = (EditText)findViewById(R.id.password);
        firebaseAuth = FirebaseAuth.getInstance();

        Button loginButton = findViewById(R.id.login_button);
        Button singupButton = findViewById(R.id.singup_button);
        Button forgotPasswordButton = findViewById(R.id.forgot_password_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailn.getText().toString().trim();
                String password = passwordn.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(LoginActivity.this,"campo email invalido", Toast.LENGTH_SHORT);
                    return;
                }
                if (TextUtils.isEmpty(password)){

                    Toast.makeText(LoginActivity.this,"campo senha invalido", Toast.LENGTH_SHORT);
                    return;
                }
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                } else {
                                    Toast.makeText(LoginActivity.this,"Login falhou ou usuario invalido", Toast.LENGTH_SHORT);

                                }
                            }
                        });


            }
        });

        singupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivityForResult(returnIntent, 1);
            }
        });

        forgotPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivityForResult(returnIntent, 1);
            }
        });
    }
}