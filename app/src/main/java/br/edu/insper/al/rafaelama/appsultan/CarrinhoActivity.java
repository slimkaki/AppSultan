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

public class CarrinhoActivity extends AppCompatActivity {

    protected Button buttonCancel,buttonConfirm;
    protected ImageButton backButton, botao_perfil,botao_pedidos,botao_carrinho;
    protected TextView priceText, totalText, profitText, fretePrice;
    private static final String TAG = "MUSTAFAR";
    private ListView listView;
    List<Produto> productsList;
    private int productCount;
    private double calcTotal;
    private double profitNumber;
    private double freteCounter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);
        listView = (ListView) findViewById(R.id.prodCarrinho);

        backButton = findViewById(R.id.backButton);
        buttonCancel = findViewById(R.id.buttonCancel);
        buttonConfirm = findViewById(R.id.buttonConfirm);
        botao_perfil = findViewById(R.id.buttonProfile);
        botao_carrinho = findViewById(R.id.buttonCart);
        botao_pedidos = findViewById(R.id.buttonRequests);
        priceText = (TextView) findViewById(R.id.produto_preco);
        totalText = findViewById(R.id.total_preco);
        profitText = findViewById(R.id.lucro_preco);
        fretePrice = findViewById(R.id.frete_preco);
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = currentFirebaseUser.getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference uidRef = databaseReference.child("users").child(uid);
        DatabaseReference carRef = uidRef.child("carrinho");
        DatabaseReference profitRef = uidRef.child("profit");
        productsList = new ArrayList<Produto>();

        ValueEventListener valueEventListenerProfit = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                profitNumber = dataSnapshot.getValue(Double.class);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("MUSTAFAR", "Failed to read value.", error.toException());
            }
        };

        profitRef.addListenerForSingleValueEvent(valueEventListenerProfit);

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
                priceText.setText(String.valueOf(productCount));
                totalText.setText(String.valueOf(calcTotal));
                fretePrice.setText(String.valueOf(freteCounter));
                profitText.setText(String.valueOf(calcTotal * profitNumber / 100.00));


                CartInfoAdapter cartInfoAdapter = new CartInfoAdapter(CarrinhoActivity.this, productsList);
                listView.setAdapter(cartInfoAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, databaseError.getMessage());
            }
        };

        carRef.addListenerForSingleValueEvent(valueEventListener);

        botao_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perfil = new Intent(CarrinhoActivity.this, PerfilActivity.class);
                startActivity(perfil);
            }
        });
        botao_pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perfil = new Intent(CarrinhoActivity.this, MainActivity.class);
                startActivity(perfil);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CarrinhoActivity.this, MainActivity.class);
                setResult(2, intent);
                finish();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carRef.removeValue();
                Toast.makeText(CarrinhoActivity.this, "Itens removidos com sucesso!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CarrinhoActivity.this, MainActivity.class);
                setResult(2, intent);
                finish();
            }
        });

        buttonConfirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intento = new Intent(CarrinhoActivity.this, PedidoActivity.class);
                startActivity(intento);
                finish();
            }
        });

    }
}
