    package br.edu.insper.al.rafaelama.appsultan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

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
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");

                String shareTitle = mBundle.getString("Nome");
                String shareBody = mBundle.getString("descri");
                String sharePrice = mBundle.getString("preco");

                share.putExtra(Intent.EXTRA_SUBJECT, shareTitle);
                share.putExtra(Intent.EXTRA_TEXT, shareBody + sharePrice);

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

    }
}
