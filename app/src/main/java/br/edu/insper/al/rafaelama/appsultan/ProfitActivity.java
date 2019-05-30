package br.edu.insper.al.rafaelama.appsultan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfitActivity extends AppCompatActivity {

    private EditText profitSign;
    private FirebaseUser user;
    private DatabaseReference mDatabase;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profit);

        ImageButton perfil = findViewById(R.id.buttonProfile);
        ImageButton carrinho = findViewById(R.id.buttonCart);
        ImageButton catalogo = findViewById(R.id.buttonCat);
        ImageButton pedidos = findViewById(R.id.buttonRequests);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        profitSign = findViewById(R.id.LucroEdit);

        Button go = findViewById(R.id.DefineLucroButton);

        user = FirebaseAuth.getInstance().getCurrentUser();
        id = user.getUid();

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String profit = profitSign.getText().toString().trim();
                if(profit.equals("")){
                    mDatabase.child("users").child(id).child("profit").setValue(0);
                }
                else{
                    mDatabase.child("users").child(id).child("profit").setValue(Double.valueOf(profit));
                }
              
                Intent returnIntent = new Intent(ProfitActivity.this, MainActivity.class);
                startActivityForResult(returnIntent, 1);
            }
        });

        carrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perfil = new Intent(ProfitActivity.this, CarrinhoActivity.class);
                startActivity(perfil);
            }
        });

        catalogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perfil = new Intent(ProfitActivity.this, MainActivity.class);
                startActivity(perfil);
            }
        });

        pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perfil = new Intent(ProfitActivity.this, MainActivity.class);
                startActivity(perfil);
            }
        });

        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent perfil = new Intent(ProfitActivity.this, PerfilActivity.class);
                startActivity(perfil);
            }
        });

    }
}
