package br.edu.insper.al.rafaelama.appsultan;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Toast;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ImageButton botao_perfil;
    ImageButton botao_carrinho;
    ImageButton botao_pedidos;
    ImageButton botao_cat;

    private FirebaseDatabase database;
    private static final String TAG = "MUSTAFAR";
    private Context mContext;

    private SwipeMenuListView listView;
    DatabaseReference databaseReference;
    List<Produto> productsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        listView = (SwipeMenuListView) findViewById(R.id.list_view);

        botao_perfil = findViewById(R.id.buttonProfile);

        botao_cat = findViewById(R.id.buttonCat);

        botao_pedidos = findViewById(R.id.buttonRequests);

        botao_carrinho = findViewById(R.id.buttonCart);

        databaseReference = FirebaseDatabase.getInstance().getReference("Produtos");
        productsList = new ArrayList<Produto>();

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                productsList.clear();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Produto produto = child.getValue(Produto.class);
                    productsList.add(produto);
                }
                ProductInfoAdapter productInfoAdapter = new ProductInfoAdapter(MainActivity.this, productsList);
                listView.setAdapter(productInfoAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, databaseError.getMessage());
            }
        };

        databaseReference.addListenerForSingleValueEvent(valueEventListener);

        botao_carrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem whats = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                whats.setBackground(new ColorDrawable(Color.rgb(0x25,
                        0xd3, 0x66)));
                // set item width
                whats.setWidth(400);
                // set a icon
                whats.setIcon(R.drawable.ic_share);
                // add to menu
                menu.addMenuItem(whats);

                SwipeMenuItem to_cart = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                to_cart.setBackground(new ColorDrawable(Color.rgb(0xFF,
                        0xFF, 0xFF)));
                // set item width
                to_cart.setWidth(400);
                // set a icon
                to_cart.setIcon(R.drawable.ic_cart);
                // add to menu
                menu.addMenuItem(to_cart);
            }
        };

        listView.setMenuCreator(creator);

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        Intent mIntent = new Intent(MainActivity.this, ProductActivity.class);
                        Produto produto = (Produto) listView.getItemAtPosition(position);

                        mIntent.putExtra("minQuant", produto.getMinQuant());
                        mIntent.putExtra("name", produto.getName());
                        mIntent.putExtra("imagePath", produto.getImagePath());
                        mIntent.putExtra("desc", produto.getDesc());
                        mIntent.putExtra("price", produto.getPrice());

                        Bundle mBundle = getIntent().getExtras();

                        Intent share = new Intent(Intent.ACTION_SEND);
                        share.setType("text/plain");

                        String shareTitle = mBundle.getString("name");
                        String shareBody = mBundle.getString("desc");
                        String sharePrice = mBundle.getString("price");

                        share.putExtra(Intent.EXTRA_COMPONENT_NAME, shareTitle);
                        share.putExtra(Intent.EXTRA_TEXT, shareBody + sharePrice);

                        startActivity(Intent.createChooser(share, "sharing..."));

                        break;

                    case 1:
                        Produto produtoAdd = (Produto) listView.getItemAtPosition(position);
                        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                        String uid = currentFirebaseUser.getUid();
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                        DatabaseReference uidRef = databaseReference.child("users").child(uid);
                        DatabaseReference carRef = uidRef.child("carrinho");
                        carRef.push().setValue(produtoAdd);
                        Toast.makeText(MainActivity.this, "Produto adicionado ao carrinho!", Toast.LENGTH_SHORT).show();
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent mIntent = new Intent(MainActivity.this, ProductActivity.class);
                Produto produto = (Produto) listView.getItemAtPosition(position);

                mIntent.putExtra("minQuant", produto.getMinQuant());
                mIntent.putExtra("name", produto.getName());
                mIntent.putExtra("imagePath", produto.getImagePath());
                mIntent.putExtra("desc", produto.getDesc());
                mIntent.putExtra("price", produto.getPrice());

                startActivity(mIntent);
            }
        });
    }
}
