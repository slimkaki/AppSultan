package br.edu.insper.al.rafaelama.appsultan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Path;

public class SignUpActivity extends AppCompatActivity implements ValueEventListener {

    protected Button buttonCancela;
    protected Button buttonInscreve;

    private TextView name, email, cpf, password, password_confirmation, celular, cep, address, numero;

    private int id;

    private void showToast(String text) {
        // Constrói uma bolha de duração curta.
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_LONG);

        // Mostra essa bolha.
        toast.show();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        id = 1;

        name = findViewById(R.id.name_signup);
        email = findViewById(R.id.email_signup);
        cpf = findViewById(R.id.cpf_signup);
        password = findViewById(R.id.password_signup);
        password_confirmation = findViewById(R.id.password_signup_confirmation);
        celular = findViewById(R.id.celphone_signup);
        cep = findViewById(R.id.cep_signup);
        address = findViewById(R.id.address_signup);
        numero = findViewById(R.id.number_signup);

        FirebaseApp.initializeApp(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference idNumber = database.getReference("users/id");

        idNumber.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                id = dataSnapshot.getValue(int.class);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });




        buttonCancela = findViewById(R.id.buttonCancel);
        buttonInscreve = findViewById(R.id.signup_button);

        buttonInscreve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference referenceA_name = database.getReference("users/"+String.valueOf(id)+"/name");
                DatabaseReference referenceA_email = database.getReference("users/"+String.valueOf(id)+"/email");
                DatabaseReference referenceA_cpf = database.getReference("users/"+String.valueOf(id)+"/cpf");
                DatabaseReference referenceA_password = database.getReference("users/"+String.valueOf(id)+"/password");
                DatabaseReference referenceA_celphone = database.getReference("users/"+String.valueOf(id)+"/celphone");
                DatabaseReference referenceA_cep = database.getReference("users/"+String.valueOf(id)+"/cep");
                DatabaseReference referenceA_address = database.getReference("users/"+String.valueOf(id)+"/address");

                String nameString = name.getText().toString();
                String emailString = email.getText().toString();
                String cpfString = cpf.getText().toString();
                String passwordString = password.getText().toString();
                String passwordConfirmationString = password_confirmation.getText().toString();
                String celularString = celular.getText().toString();
                String cepString = cep.getText().toString();
                String addressString = address.getText().toString();

                if(!nameString.equals("") && !emailString.equals("") && !cpfString.equals("") && !passwordString.equals("") && !celularString.equals("") && !cepString.equals("") && !addressString.equals("")){
                    if(passwordConfirmationString.equals(passwordString)){
                        referenceA_name.setValue(nameString);
                        referenceA_email.setValue(emailString);
                        referenceA_cpf.setValue(cpfString);
                        referenceA_password.setValue(passwordString);
                        referenceA_celphone.setValue(celularString);
                        referenceA_cep.setValue(cepString);
                        referenceA_address.setValue(addressString);

                        id++;
                        idNumber.setValue(id);

                        Intent main = new Intent(SignUpActivity.this, MainActivity.class);
                        startActivity(main);
                    }
                    else {
                        showToast("Senhas diferentes");
                    }
                }
                else{
                    showToast("Campos vazios");
                }


            }
        });
        buttonCancela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivityForResult(returnIntent, 1);
            }
        });
}

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
