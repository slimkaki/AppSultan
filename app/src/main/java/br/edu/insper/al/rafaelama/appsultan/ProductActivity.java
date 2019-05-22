    package br.edu.insper.al.rafaelama.appsultan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

    public class ProductActivity extends AppCompatActivity {

    Toolbar barra2;
    ImageView imagem;
    TextView desc;
    TextView preco;
    Button botao_perfil;
    Button voltar;
    Button carrinho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        barra2 = findViewById(R.id.barra2);
        imagem = findViewById(R.id.imageView2);
        //desc = findViewById(R.id.desc);
        preco = findViewById(R.id.preco);
        botao_perfil = findViewById(R.id.buttonProfile);
        voltar = findViewById(R.id.voltar);
        carrinho = findViewById(R.id.buttonCart);


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
}
