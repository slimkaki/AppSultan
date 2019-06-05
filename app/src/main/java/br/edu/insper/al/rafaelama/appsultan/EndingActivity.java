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
    protected String local, items,nome;
    private TextView lucro;
    protected Button finalizar;
    ImageButton perfil, catalogo, pedidos,botao_carrinho;
    private static final String TAG = "MUSTAFAR";
    private FirebaseDatabase database;
    private ListView listView;
    List<Produto> productsList;
    private double calcTotal;
    private double productCount,freteCounter;
    private TextView qtdtxt,totaltxt,fretetxt;
    private double profitNumber;

    @Override
    public void onCreate(Bundle Saved){
        super.onCreate(Saved);
        setContentView(R.layout.activity_pedido_confirmado);
        lucro = findViewById(R.id.lucroArea);
        pagamento = getIntent().getStringArrayExtra("pagamentos");
        local = getIntent().getStringExtra("envio");
        finalizar = findViewById(R.id.buttonFazerPedido);
        perfil = findViewById(R.id.buttonProfile);
        botao_carrinho = findViewById(R.id.buttonCart);
        listView = findViewById(R.id.itemPedido);
        pedidos = findViewById(R.id.buttonRequests);
        items = "";

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
                nome = dataSnapshot.child("name").getValue(String.class);
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Produto produto = child.getValue(Produto.class);
                    productsList.add(produto);
                    productCount += produto.getMinQuant();
                    calcTotal += produto.getMinQuant() * produto.getPrice();
                    items += produto.getName() + "\n";
                }
                productCount = Math.round(productCount*100)/100;
                calcTotal = Math.round(calcTotal*100.00)/100.00;
                freteCounter = Math.round(0.04 * calcTotal*100.00)/100.00;
                qtdtxt.setText(String.valueOf(productCount));
                totaltxt.setText(String.valueOf(calcTotal));
                fretetxt.setText(String.valueOf(freteCounter));
                lucro.setText(String.valueOf(Math.round(calcTotal*profitNumber)));
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
        botao_carrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perfil = new Intent(EndingActivity.this, CarrinhoActivity.class);
                startActivity(perfil);
            }
        });
        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Getting content for email
                String email = "joaomeirelles575@gmail.com";
                String subject = "Pedido teste";
                String message = "";
                message += "Cliente: "+nome+"\n";
                message += "Preço: "+String.valueOf(calcTotal)+"\n";
                message += "Produtos" + items;

                //Creating SendMail object
                SendMail sm = new SendMail(EndingActivity.this, email, subject, message);

                //Executing sendmail to send email
                sm.execute();
            }});
        }
    }
