package br.edu.insper.al.rafaelama.appsultan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class EndingActivity extends AppCompatActivity {
    protected String[] pagamento;
    protected String local;
    private TextView lucro, total;
    protected Button finalizar;
    ImageButton perfil, catalogo, pedidos, carrinho;
    private static final String TAG = "MUSTAFAR";
    private FirebaseDatabase database;
    private ListView listView;
    List<Produto> productsList;
    private double calcTotal;

    @Override
    public void onCreate(Bundle Saved){
        super.onCreate(Saved);
        setContentView(R.layout.activity_pedido_confirmado);
        total = findViewById(R.id.lucroArea);
        pagamento = getIntent().getStringArrayExtra("pagamentos");
        local = getIntent().getStringExtra("envio");
        finalizar = findViewById(R.id.buttonFazerPedido);
        perfil = findViewById(R.id.buttonProfile);
        catalogo = findViewById(R.id.buttonCat);
        carrinho = findViewById(R.id.buttonCart);
        listView = findViewById(R.id.itemPedido);
        pedidos = findViewById(R.id.buttonRequests);

        Bundle mBundle = getIntent().getExtras();

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = currentFirebaseUser.getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference uidRef = databaseReference.child("users").child(uid);
        DatabaseReference carRef = uidRef.child("carrinho");
        productsList = new ArrayList<Produto>();

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                productsList.clear();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Produto produto = child.getValue(Produto.class);
                    productsList.add(produto);
                    calcTotal += produto.getPrice();

                    total.setText(String.valueOf(calcTotal) + "0");
                }

                ProductInfoAdapter productInfoAdapter = new ProductInfoAdapter(EndingActivity.this, productsList);
                listView.setAdapter(productInfoAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, databaseError.getMessage());
            }
        };

        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(EndingActivity.this,PerfilActivity.class);
                startActivity(intento);
                finish();
            }
        });
        catalogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento =  new Intent(EndingActivity.this, MainActivity.class);
                startActivity(intento);
                finish();
            }
        });
        carrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(EndingActivity.this, CarrinhoActivity.class);
                startActivity(intento);
                finish();
            }
        });
        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Getting content for email
                String email = "joaomeirelles575@gmail.com";
                String subject = "Pedido teste";
                String message = "Aqui é o que tem que ter no e-mail";

                //Creating SendMail object
                SendMail sm = new SendMail(EndingActivity.this, email, subject, message);

                //Executing sendmail to send email
                sm.execute();
            }});
        }
    }
