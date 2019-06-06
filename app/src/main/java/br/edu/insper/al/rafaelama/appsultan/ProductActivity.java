    package br.edu.insper.al.rafaelama.appsultan;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;

public class ProductActivity extends AppCompatActivity {


    ImageView imagem;
    TextView desc;
    TextView preco;
    ImageButton perfil, carrinho, pedidos;
    ImageButton zapzap, backButton1;
    Button addCar;
    private double userProfit;
    private double sharedPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);


        imagem = findViewById(R.id.imageView2);
        desc = findViewById(R.id.desc);
        preco = findViewById(R.id.preco);
        perfil = findViewById(R.id.buttonProfile);
        carrinho = findViewById(R.id.buttonCart);
        pedidos = findViewById(R.id.buttonRequests);
        zapzap = findViewById(R.id.zapshare);
        addCar = findViewById(R.id.addCarrinho);
        backButton1 = findViewById(R.id.backButton1);

        Bundle mBundle = getIntent().getExtras();

        if (mBundle != null) {
            String pName = mBundle.getString("name");
            String pDesc = mBundle.getString("desc");
            String pImagePath = mBundle.getString("imagePath");
            double pPrice = mBundle.getDouble("price");
            int pMinQuant = mBundle.getInt("minQuant");

            Produto produto = new Produto(pName, pDesc, pImagePath, pPrice, pMinQuant);

            int id = this.getResources().getIdentifier(mBundle.getString("imagePath"), "drawable", "br.edu.insper.al.rafaelama.appsultan");

            imagem.setImageResource(id);
            desc.setText(produto.getDesc());
            preco.setText(produto.getPriceString());

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

            zapzap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                Intent share = new Intent(Intent.ACTION_SEND);

                int id = imagem.getContext().getResources().getIdentifier(produto.getImagePath(), "drawable", "br.edu.insper.al.rafaelama.appsultan");

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

                }
            });


            perfil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent perfil = new Intent(ProductActivity.this, PerfilActivity.class);
                    startActivity(perfil);
                }
            });
            pedidos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent perfil = new Intent(ProductActivity.this, MainActivity.class);
                    startActivity(perfil);
                }
            });

            carrinho.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent carrinho = new Intent(ProductActivity.this, CarrinhoActivity.class);
                    startActivity(carrinho);

                }
            });

            addCar.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                    String uid = currentFirebaseUser.getUid();
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                    DatabaseReference uidRef = databaseReference.child("users").child(uid);
                    DatabaseReference carRef = uidRef.child("carrinho");
                    carRef.push().setValue(produto);
                    Toast.makeText(ProductActivity.this, "Produto adicionado ao carrinho!", Toast.LENGTH_SHORT).show();
                }
            });

            backButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent returnIntent = new Intent(ProductActivity.this, MainActivity.class);
                    startActivityForResult(returnIntent, 1);
                }
            });

        }
    }
}
