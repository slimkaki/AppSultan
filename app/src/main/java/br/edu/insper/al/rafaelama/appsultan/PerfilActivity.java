package br.edu.insper.al.rafaelama.appsultan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PerfilActivity extends AppCompatActivity {

    TextView nameText, emailText, addressText;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        Button btEdit = findViewById(R.id.editButton);
        Button btLucro = findViewById(R.id.lucroButton);
        Button voltar = findViewById(R.id.voltar);

        ImageButton perfil = findViewById(R.id.buttonProfile);
        ImageButton carrinho = findViewById(R.id.buttonCart);
        ImageButton catalogo = findViewById(R.id.buttonCat);
        ImageButton pedidos = findViewById(R.id.buttonRequests);

        btEdit.setOnClickListener(EditHandler);
        btLucro.setOnClickListener(LucroHandler);

        nameText = findViewById(R.id.nameText);
        emailText = findViewById(R.id.emailText);
        addressText = findViewById(R.id.addressButton);

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = currentFirebaseUser.getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference uidRef = databaseReference.child("users").child(uid);

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("name").getValue(String.class);
                String email = dataSnapshot.child("email").getValue(String.class);
                String address = dataSnapshot.child("address").getValue(String.class);

                nameText.setText(name);
                emailText.setText(email);
                addressText.setText(address);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        uidRef.addListenerForSingleValueEvent(valueEventListener);

        voltar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilActivity.this, MainActivity.class);
                setResult(2, intent);
                finish();
            }
        });
        carrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perfil = new Intent(PerfilActivity.this, CarrinhoActivity.class);
                startActivity(perfil);
            }
        });
        catalogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perfil = new Intent(PerfilActivity.this, MainActivity.class);
                startActivity(perfil);
            }
        });
        pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perfil = new Intent(PerfilActivity.this, MainActivity.class);
                startActivity(perfil);
            }
        });


    }

    View.OnClickListener EditHandler = new View.OnClickListener(){
        public void onClick(View v){
            // abre a intent de edição de infos
        }
    };

    View.OnClickListener LucroHandler = new View.OnClickListener(){
        public void onClick(View v){
            // Intent pra activity de profit
           // Intent editLucro = new Intent(PerfilActivity.this, )
        }
    };
}
