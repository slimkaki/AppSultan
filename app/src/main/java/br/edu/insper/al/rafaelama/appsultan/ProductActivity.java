    package br.edu.insper.al.rafaelama.appsultan;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

    public class ProductActivity extends AppCompatActivity {


    ImageView imagem;
    TextView desc;
    TextView preco;
    ImageButton perfil, carrinho, pedidos, catalogo;
    ImageButton zapzap;
    Button addCar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);


        imagem = findViewById(R.id.imageView2);
        desc = findViewById(R.id.desc);
        preco = findViewById(R.id.preco);
        perfil = findViewById(R.id.buttonProfile);
        carrinho = findViewById(R.id.buttonCart);
        catalogo = findViewById(R.id.buttonCat);
        pedidos = findViewById(R.id.buttonRequests);
        zapzap = findViewById(R.id.zapshare);
        addCar = findViewById(R.id.addCarrinho);

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


            zapzap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                Intent share = new Intent(Intent.ACTION_SEND);

                share.setType("image/jpeg");

                Uri imagemUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                       "://" + getApplicationContext().getResources().getResourcePackageName(id)
                       + '/' + getApplicationContext().getResources().getResourceTypeName(id)
                       + '/' + getApplicationContext().getResources().getResourceEntryName(id) );

                share.putExtra(Intent.EXTRA_STREAM, imagemUri);
                share.putExtra(Intent.EXTRA_TEXT, produto.getShareProduct());
                startActivity(Intent.createChooser(share, "sharing..."));

                }
            });


            perfil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent perfil = new Intent(ProductActivity.this, PerfilActivity.class);
                    startActivity(perfil);
                }
            });
            catalogo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent perfil = new Intent(ProductActivity.this, MainActivity.class);
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

        }
    }
}
