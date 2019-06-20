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
    protected String local, items,nome,celular, endereco,numero;
    private TextView profitText;
    protected Button finalizar;
    ImageButton perfil, pedidos,botao_carrinho;
    private static final String TAG = "MUSTAFAR";
    private ListView listView;
    List<Produto> productsList;
    private double calcTotal;
    private double productCount;
    private double profitNumber;

    @Override
    public void onCreate(Bundle Saved){
        super.onCreate(Saved);
        setContentView(R.layout.activity_pedido_confirmado);
        profitText = findViewById(R.id.lucroArea);
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
        DatabaseReference NameRef = uidRef.child("name");
        DatabaseReference CelRef = uidRef.child("celular");
        DatabaseReference EndRef = uidRef.child("address");
        DatabaseReference NumRef = uidRef.child("number");
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
                    calcTotal += produto.getMinQuant() * produto.getPrice();
                    items += produto.getName() + "\n";
                }
                productCount = Math.round(productCount*100)/100;
                calcTotal = Math.round(calcTotal*100.00)/100.00;
                profitText.setText(String.valueOf(calcTotal * profitNumber / 100.00));

                CartInfoAdapter cartInfoAdapter = new CartInfoAdapter(EndingActivity.this, productsList);
                listView.setAdapter(cartInfoAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, databaseError.getMessage());
            }
        };
        carRef.addListenerForSingleValueEvent(valueEventListener);
        NameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try{
                    nome=dataSnapshot.getValue(String.class);
                }catch (Exception e){
                    e.fillInStackTrace();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        CelRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try{
                    celular=dataSnapshot.getValue(String.class);
                }catch (Exception e){
                    e.fillInStackTrace();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });EndRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try{
                    endereco=dataSnapshot.getValue(String.class);
                }catch (Exception e){
                    e.fillInStackTrace();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });NumRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try{
                    numero=dataSnapshot.getValue(String.class);
                }catch (Exception e){
                    e.fillInStackTrace();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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
                finish();
            }
        });
        pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(EndingActivity.this, MainActivity.class);
                startActivity(intento);
                finish();
            }
        });
        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Getting content for email
                String email = "mchauki.orra@gmail.com";
                String subject = "Pedido teste";
                String message = "";
                message += "Cliente: "+nome+"\n";
                message += "Contato: "+celular+"\n";
                message += "Endereço: "+endereco+"  Número:"+numero+"\n";
                message += "Preço: "+calcTotal+"\n";
                message += "Local de retirada: " + local + "\n";
                message += "Produtos" + items + "\n";
                message += "Pagamento: \n";
                for (String s:pagamento){
                    message += s + "\n";
                }

                //Creating SendMail object
                SendMail sm = new SendMail(EndingActivity.this, email, subject, message);

                //Executing sendmail to send email
                sm.execute();

                carRef.removeValue();
                Intent intent = new Intent(EndingActivity.this, MainActivity.class);
                startActivity(intent);
            }});
        }
    }
