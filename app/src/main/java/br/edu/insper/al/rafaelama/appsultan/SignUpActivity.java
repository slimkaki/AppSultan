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
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUpActivity extends AppCompatActivity {

    private EditText emailSign;
    private EditText passwordSign;
    private EditText passwordSignConf;
    private EditText nameSign;
    private EditText cpfSign;
    private EditText cepSign;
    private EditText celphoneSign;
    private EditText addressSign;
    private EditText numberSign;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        emailSign = findViewById(R.id.email_signup);
        passwordSign = findViewById(R.id.password_signup);
        passwordSignConf = findViewById(R.id.password_signup_confirmation);
        nameSign = findViewById(R.id.name_signup);
        cpfSign = findViewById(R.id.cpf_signup);
        cepSign = findViewById(R.id.cep_signup);
        celphoneSign = findViewById(R.id.celphone_signup);
        addressSign = findViewById(R.id.address_signup);
        numberSign = findViewById(R.id.number_signup);

        Button cancel = findViewById(R.id.buttonCancel);
        Button register = findViewById(R.id.signup_button);

        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailSign.getText().toString().trim();
                String password = passwordSign.getText().toString().trim();
                String passwordConf = passwordSignConf.getText().toString().trim();
                String address = addressSign.getText().toString().trim();
                String name = nameSign.getText().toString().trim();
                String cep = cepSign.getText().toString().trim();
                String cpf = cpfSign.getText().toString().trim();
                String celphone = celphoneSign.getText().toString().trim();
                String number = numberSign.getText().toString().trim();


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
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(SignUpActivity.this, "Nome inválido", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(address)) {
                    Toast.makeText(SignUpActivity.this, "Endereço inválido", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(cep)) {
                    Toast.makeText(SignUpActivity.this, "Cep inválido", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(cpf)) {
                    Toast.makeText(SignUpActivity.this, "CPF inválido", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(celphone)) {
                    Toast.makeText(SignUpActivity.this, "Telefone inválido", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(number)) {
                    Toast.makeText(SignUpActivity.this, "Número inválido", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.equals(passwordConf)) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(name).build();

                                user.updateProfile(profileUpdates);

                                String id = user.getUid();

                                writeNewUser(name, email, address, cep, cpf, celphone, id, number);

                                startActivity(new Intent(getApplicationContext(),ProfitActivity.class));
                                Toast.makeText(SignUpActivity.this, "Conta criada",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SignUpActivity.this, "Falha ao criar a conta", Toast.LENGTH_SHORT).show();
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

    private void writeNewUser(String name, String email, String address, String cep, String cpf, String celular, String id, String number) {
        User user = new User(name, email, address, cep, cpf, celular, id, number);

        mDatabase.child("users").child(id).setValue(user);
    }
}
