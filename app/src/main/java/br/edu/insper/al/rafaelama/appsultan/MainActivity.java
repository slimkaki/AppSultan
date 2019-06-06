package br.edu.insper.al.rafaelama.appsultan;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.SearchView;
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
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ImageButton botao_perfil;
    ImageButton botao_carrinho;
    ImageButton botao_pedidos;
    ImageButton botao_cat;
    SearchView searchView;

    private FirebaseDatabase database;
    private static final String TAG = "MUSTAFAR";
    private Context mContext;
    private double userProfit;
    private double sharedPrice;

    private SwipeMenuListView listView;
    DatabaseReference databaseReference;
    DatabaseReference databaseReferenceP;
    List<Produto> productsList;
    List<Produto> searchedProductsList;
    FirebaseCatalogoInfo firebaseCatalogoInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        listView = (SwipeMenuListView) findViewById(R.id.list_view);

        searchView = (SearchView) findViewById(R.id.search_view);

        botao_perfil = findViewById(R.id.buttonProfile);

        botao_pedidos = findViewById(R.id.buttonRequests);

        botao_carrinho = findViewById(R.id.buttonCart);

        databaseReference = FirebaseDatabase.getInstance().getReference("Produtos");
        productsList = new ArrayList<Produto>();

        firebaseCatalogoInfo = new FirebaseCatalogoInfo(MainActivity.this);
        firebaseCatalogoInfo.getCatalogoList(databaseReference, listView, productsList);

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

        // Salvando o dado de lucro do usuário na variável userProfit
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = currentFirebaseUser.getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference uidRef = databaseReference.child("users").child(uid);
        DatabaseReference profitRef = uidRef.child("profit");

        profitRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    userProfit = dataSnapshot.getValue(Double.class);
                } catch (Exception e) {
                    e.fillInStackTrace();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        // Controle deslizante dos itens
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
                        Produto produto = (Produto) listView.getItemAtPosition(position);

                        int id = listView.getContext().getResources().getIdentifier(produto.getImagePath(), "drawable", "br.edu.insper.al.rafaelama.appsultan");

                        Intent share = new Intent(Intent.ACTION_SEND);

                        Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), id);

                        int check = ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);

                        if (check == PackageManager.PERMISSION_GRANTED) {

                            sharedPrice = Math.round(produto.getPrice()*(1.0+(userProfit/100.0))*100)/100;
                            String b = "*_Produto:_* " + produto.getName();
                            String a = produto.getDesc();
                            String p = "*_Preço:_* " + sharedPrice;
                            String send = b + "\n" +"\n" + a + "\n" +"\n" + p;

                            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                            String path = MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(), bitmap, "Produto", null);
                            Uri imageUri = Uri.parse(path);

                            share.setType("*/*");
                            share.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            share.putExtra(Intent.EXTRA_STREAM, imageUri);
                            share.setPackage("com.whatsapp");
                            share.putExtra(Intent.EXTRA_TEXT, send);
                            startActivity(Intent.createChooser(share, "Compartilhar produto"));

                        } else {
                            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1024);
                        }

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

        // Botão de busca a partir do nome do produto
        databaseReferenceP = FirebaseDatabase.getInstance().getReference("Produtos");
        searchView.setInputType(InputType.TYPE_CLASS_TEXT);
        searchedProductsList  = new ArrayList<Produto>();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                String cap = s.substring(0, 1).toUpperCase() + s.substring(1);
                firebaseCatalogoInfo.getSearchCatalogoList(databaseReferenceP, listView, searchedProductsList, cap);
                onSearchRequested();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
//                databaseReferenceP.addListenerForSingleValueEvent(valueEventListener);
                firebaseCatalogoInfo.getCatalogoList(databaseReference, listView, productsList);
                return false;
            }
        });

    }
}
