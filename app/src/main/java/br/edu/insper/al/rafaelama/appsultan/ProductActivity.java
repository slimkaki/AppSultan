    package br.edu.insper.al.rafaelama.appsultan;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

    public class ProductActivity extends AppCompatActivity {


    ImageView imagem;
    TextView desc;
    TextView preco;
    ImageButton perfil, carrinho, pedidos, catalogo;
    ImageButton zapzap;


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


        Bundle mBundle = getIntent().getExtras();

        if (mBundle != null){

            imagem.setImageResource(mBundle.getInt("Imagem"));
            desc.setText(mBundle.getString("descri"));
            preco.setText(mBundle.getString("preco"));

        }

        zapzap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent share = new Intent(Intent.ACTION_SEND);
//                share.setType("text/plain");
//
//                String shareTitle = mBundle.getString("Nome");
//                String shareBody = mBundle.getString("descri");
//                String sharePrice = mBundle.getString("preco");
//
//                share.putExtra(Intent.EXTRA_COMPONENT_NAME, shareTitle);
//                share.putExtra(Intent.EXTRA_TEXT,shareBody + sharePrice);
//
//                startActivity(Intent.createChooser(share, "sharing..."));


//                Bitmap b = BitmapFactory.decodeResource();
//
//                Intent share = new Intent(Intent.ACTION_SEND);
//
//                share.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//
//                share.setType("image/*");
//
//                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//
//                b.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//
//                String path = MediaStore.Images.Media.insertImage(getContentResolver(), b, mBundle.getString("Nome"), mBundle.getString("descri"));
//                Uri imageUri =  Uri.parse(path);
//
////                grantUriPermission(path, imageUri, );
//
//                share.putExtra(Intent.EXTRA_STREAM, imageUri);
//                startActivity(Intent.createChooser(share, "Select"));


//                Uri imageUri = Uri.parse("android.resource://" + getPackageName() + "/drawable/" + "ic_launcher");
//
//                Intent intent = new Intent(Intent.ACTION_SEND);
//
//                intent.putExtra(Intent.EXTRA_TEXT, mBundle.getString("descri"));
//
//                intent.putExtra(Intent.EXTRA_STREAM, imageUri);
//
//                intent.setType("*/*");
//                startActivity(intent);

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

    }
}
