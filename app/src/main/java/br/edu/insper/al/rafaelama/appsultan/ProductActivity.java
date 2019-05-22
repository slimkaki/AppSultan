    package br.edu.insper.al.rafaelama.appsultan;

import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

    public class ProductActivity extends AppCompatActivity implements ValueEventListener {

    protected Toolbar barra2;
    protected ImageView imagem;
    protected TextView desc;
    protected TextView preco;
    protected Button botao_perfil;
    protected Button voltar;
    protected Button carrinho;
    private String text;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://appsultanproducts.firebaseio.com");
        DatabaseReference productsList = database.getReference();
        productsList.addValueEventListener(ProductActivity.this);
        text = productsList.toString();

        barra2 = findViewById(R.id.barra2);
        imagem = findViewById(R.id.imageView2);
        desc = findViewById(R.id.desc);
        preco = findViewById(R.id.preco);
        botao_perfil = findViewById(R.id.perfil);
        voltar = findViewById(R.id.voltar);
        carrinho = findViewById(R.id.carrinho);


        Bundle mBundle = getIntent().getExtras();

        if (mBundle != null){

            barra2.setTitle(mBundle.getString("Nome"));
            imagem.setImageResource(mBundle.getInt("Imagem"));
            desc.setText(mBundle.getString("descri"));
            preco.setText(mBundle.getString("preco"));

        }


        botao_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.out.println(" ");
                //System.out.println(text);
                //System.out.println(" ");
                System.out.println(list);
                Intent perfil = new Intent(ProductActivity.this, PerfilActivity.class);
                startActivity(perfil);
            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent voltar = new Intent(ProductActivity.this, MainActivity.class);
                startActivity(voltar);
            }
        });

        carrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent carrinho = new Intent(ProductActivity.this, CarrinhoActivity.class);
                startActivity(carrinho);

            }
        });

    }

        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            this.list = new ArrayList<String>();
            for(DataSnapshot ds : dataSnapshot.getChildren()) {
                String uid = ds.getKey();
                list.add(uid);
            }
            System.out.println(list);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            //Log.d(databaseError.getMessage());
        }
    }
