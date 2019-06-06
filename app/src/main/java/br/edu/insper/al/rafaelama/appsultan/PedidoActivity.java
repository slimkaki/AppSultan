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

public class PedidoActivity extends AppCompatActivity {
    protected ImageButton perfil, carrinho, pedidos;
    protected Button fab, enderco, confirmar;
    protected TextView qtdtxt, fretetxt, totaltxt, profitText;
    protected String localEnvio = "Fábrica";
    private static final String TAG = "MUSTAFAR";
    List<Produto> productsList;
    private int productCount;
    private double calcTotal;
    private double profitNumber;
    private double freteCounter;

    @Override
    public void onCreate(Bundle saved){
        super.onCreate(saved);
        setContentView(R.layout.activity_pedido);

        perfil = findViewById(R.id.buttonProfile);
        carrinho = findViewById(R.id.buttonCart);
        pedidos = findViewById(R.id.buttonRequests);
        fab = findViewById(R.id.buttonFabrica);
        enderco = findViewById(R.id.buttonEndereco);
        confirmar = findViewById(R.id.buttonConfirm);
        qtdtxt = findViewById(R.id.qtd);
        fretetxt = findViewById(R.id.frete);
        totaltxt = findViewById(R.id.total);
        profitText = findViewById(R.id.lucro);

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = currentFirebaseUser.getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference uidRef = databaseReference.child("users").child(uid);
        DatabaseReference carRef = uidRef.child("carrinho");
        DatabaseReference profitRef = uidRef.child("profit");
        productsList = new ArrayList<Produto>();


        profitRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    profitNumber = dataSnapshot.getValue(Double.class);
                } catch (Exception e) {
                    e.fillInStackTrace();
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                productsList.clear();

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Produto produto = child.getValue(Produto.class);
                    productsList.add(produto);
                    productCount += produto.getMinQuant();
                    calcTotal += produto.getMinQuant() * produto.getPrice();
                }
                productCount = Math.round(productCount*100)/100;
                calcTotal = Math.round(calcTotal*100.00)/100.00;
                freteCounter = Math.round(0.04 * calcTotal*100.00)/100.00;
                qtdtxt.setText(String.valueOf(productCount));
                totaltxt.setText(String.valueOf(calcTotal));
                fretetxt.setText(String.valueOf(freteCounter));
                profitText.setText(String.valueOf(calcTotal * profitNumber / 100.00));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, databaseError.getMessage());
            }
        };
        carRef.addListenerForSingleValueEvent(valueEventListener);

        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(PedidoActivity.this,PerfilActivity.class);
                startActivity(intento);
                finish();
            }
        });
        carrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(PedidoActivity.this, CarrinhoActivity.class);
                startActivity(intento);
                finish();
            }
        });
        pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perfil = new Intent(PedidoActivity.this, MainActivity.class);
                startActivity(perfil);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                localEnvio = "Fábrica";
                Toast.makeText(PedidoActivity.this,"Destino atualizado",Toast.LENGTH_SHORT).show();
            }
        });
        enderco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                localEnvio = "Endereço";
                Toast.makeText(PedidoActivity.this,"Destino atualizado",Toast.LENGTH_SHORT).show();
            }
        });
        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(PedidoActivity.this, PaymentActivity.class);
                intento.putExtra("local",localEnvio);
                startActivity(intento);
                finish();
            }
        });
    }
}
