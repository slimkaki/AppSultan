package br.edu.insper.al.rafaelama.appsultan;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements ValueEventListener {

    protected Toolbar barra;
    protected LinearLayout procura;
    protected ListView catalogo;
    protected Button botao_perfil;
    protected Button botao_carrinho;
    protected Adapter adapter;
    private String text;
    public ArrayList<String> list;



    private String[] nomeProduto = {
            "Sofá",
            "Tapete",
            "Toalha",
            "Toalha de mesa",
            "Copo",
            "Prato"};

    int[] imagemProduto = {
            R.drawable.blackmetal,
            R.drawable.mine,
            R.drawable.arveres,
            R.drawable.bunito,
            R.drawable.lofi_capa,
            R.drawable.lvanda
    };
    private String[] descri = {
            "Lorem ipsum dolor sit amet consectetur adipiscing elit. Nulla a nulla ac tellus gravida sagittis consectetur ac quam. Laririri ipsum dolor sit amet consectetur adipiscing elit. Nulla a nulla ac tellus gravida sagittis consectetur ac quam. ",
            "Laura ipsum dolor sit amet consectetur adipiscing elit. Nulla a nulla ac tellus gravida sagittis consectetur ac quam. Laririri ipsum dolor sit amet consectetur adipiscing elit. Nulla a nulla ac tellus gravida sagittis consectetur ac quam. ",
            "Lorenzo ipsum dolor sit amet consectetur adipiscing elit. Nulla a nulla ac tellus gravida sagittis consectetur ac quam. Laririri ipsum dolor sit amet consectetur adipiscing elit. Nulla a nulla ac tellus gravida sagittis consectetur ac quam. ",
            "Laurinha ipsum dolor sit amet consectetur adipiscing elit. Nulla a nulla ac tellus gravida sagittis consectetur ac quam. Laririri ipsum dolor sit amet consectetur adipiscing elit. Nulla a nulla ac tellus gravida sagittis consectetur ac quam. ",
            "Lororo ipsum dolor sit amet consectetur adipiscing elit. Nulla a nulla ac tellus gravida sagittis consectetur ac quam. Laririri ipsum dolor sit amet consectetur adipiscing elit. Nulla a nulla ac tellus gravida sagittis consectetur ac quam. ",
            "Laririri ipsum dolor sit amet consectetur adipiscing elit. Nulla a nulla ac tellus gravida sagittis consectetur ac quam. Laririri ipsum dolor sit amet consectetur adipiscing elit. Nulla a nulla ac tellus gravida sagittis consectetur ac quam. "
    };

    private String[] preco = {
            "R$120,00",
            "R$13,00",
            "R$130,00",
            "R$1300,00",
            "R$13000,00",
            "R$130000,00"


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> list;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference productsList = database.getReference();

        productsList.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnap : dataSnapshot.getChildren()) {
                    //childSnap.child("price").getValue();
                    //Log.v("tmz",""+ childSnap.getKey()); //displays the key for the node
                    //Log.v("tmz",""+ childSnap.child("price").getValue());   //gives the value for given keyname
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        text = productsList.toString();

        barra = findViewById(R.id.barra);

        procura = (LinearLayout) findViewById(R.id.searches);

        catalogo = (ListView) findViewById(R.id.catalogo);

        botao_perfil = findViewById(R.id.buttonProfile);

        botao_carrinho = findViewById(R.id.buttonCart);


        botao_carrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG).show();
                Intent perfil = new Intent(MainActivity.this, CarrinhoActivity.class);
                startActivity(perfil);
            }
        });

        botao_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perfil = new Intent(MainActivity.this, PerfilActivity.class);
                startActivity(perfil);
            }
        });

        adapter = new Adapter(MainActivity.this, nomeProduto, imagemProduto);

        catalogo.setAdapter(adapter);

        catalogo.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent mIntent = new Intent(MainActivity.this, ProductActivity.class);

                mIntent.putExtra("Nome", nomeProduto[position]);
                mIntent.putExtra("Imagem", imagemProduto[position]);
                mIntent.putExtra("descri", descri[position]);
                mIntent.putExtra("preco", preco[position]);

                startActivity(mIntent);
            }
        });
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        this.list = new ArrayList<String>();
        for(DataSnapshot childSnap : dataSnapshot.getChildren()) {
            String uid = childSnap.child("price").getValue().toString();
            list.add(uid);
        }
        Map<String, String> map = dataSnapshot.getValue(Map.class);
        String name = map.get("name");
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
