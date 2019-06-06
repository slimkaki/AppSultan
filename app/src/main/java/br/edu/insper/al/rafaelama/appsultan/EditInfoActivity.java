package br.edu.insper.al.rafaelama.appsultan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditInfoActivity extends AppCompatActivity {

    private EditText nameInfo, emailInfo, cpfInfo, celphoneInfo, cepInfo, addressInfo, numberInfo;
    private Button saveButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editinfo);

        saveButton = (Button) findViewById(R.id.save_info);
        cancelButton = (Button) findViewById(R.id.buttonCancel);

        nameInfo = (EditText) findViewById(R.id.name_info);
        emailInfo = (EditText) findViewById(R.id.email_info);
        cpfInfo = (EditText) findViewById(R.id.cpf_info);
        celphoneInfo = (EditText) findViewById(R.id.celphone_info);
        cepInfo = (EditText) findViewById(R.id.cep_info);
        addressInfo = (EditText) findViewById(R.id.address_info);
        numberInfo = (EditText) findViewById(R.id.number_info);

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = currentFirebaseUser.getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference uidRef = databaseReference.child("users").child(uid);

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("name").getValue(String.class);
                String email = dataSnapshot.child("email").getValue(String.class);
                String cpf = dataSnapshot.child("cpf").getValue(String.class);
                String phone = dataSnapshot.child("celular").getValue(String.class);
                String cep = dataSnapshot.child("cep").getValue(String.class);
                String address = dataSnapshot.child("address").getValue(String.class);
                String number = dataSnapshot.child("number").getValue(String.class);

                nameInfo.setHint(name);
                emailInfo.setHint(email);
                cpfInfo.setHint(cpf);
                celphoneInfo.setHint(phone);
                cepInfo.setHint(cep);
                addressInfo.setHint(address);
                numberInfo.setHint(number);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        uidRef.addListenerForSingleValueEvent(valueEventListener);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValueEventListener valueEventListener1 = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (!nameInfo.getText().toString().equals("")) {
                            String newName = nameInfo.getText().toString();
                            uidRef.child("name").setValue(newName);
                        }
                        if (!emailInfo.getText().toString().equals("")) {
                            String newEmail = emailInfo.getText().toString();
                            uidRef.child("email").setValue(newEmail);
                        }
                        if (!cpfInfo.getText().toString().equals("")) {
                            String newCpf = cpfInfo.getText().toString();
                            uidRef.child("cpf").setValue(newCpf);
                        }
                        if (!celphoneInfo.getText().toString().equals("")) {
                            String newPhone = celphoneInfo.getText().toString();
                            uidRef.child("celular").setValue(newPhone);
                        }
                        if (!cepInfo.getText().toString().equals("")) {
                            String newCep = cepInfo.getText().toString();
                            uidRef.child("cep").setValue(newCep);
                        }
                        if (!addressInfo.getText().toString().equals("")) {
                            String newAddress = addressInfo.getText().toString();
                            uidRef.child("address").setValue(newAddress);
                        }
                        if (!numberInfo.getText().toString().equals("")) {
                            String newNumber = numberInfo.getText().toString();
                            uidRef.child("number").setValue(newNumber);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                };
                uidRef.addListenerForSingleValueEvent(valueEventListener1);
                Toast.makeText(EditInfoActivity.this, "Informações atualizadas!", Toast.LENGTH_SHORT).show();

                Intent back = new Intent(EditInfoActivity.this, PerfilActivity.class);
                startActivity(back);

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(EditInfoActivity.this, PerfilActivity.class);
                startActivity(back);
            }
        });
    }

}