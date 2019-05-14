    package br.edu.insper.al.rafaelama.appsultan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

    public class ProductActivity extends AppCompatActivity {

    Toolbar barra2;
    ImageView imagem;
    TextView desc;
    TextView preco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        barra2 = findViewById(R.id.barra2);
        imagem = findViewById(R.id.imageView2);
        desc = findViewById(R.id.desc);
        preco = findViewById(R.id.preco);

        Bundle mBundle = getIntent().getExtras();

        if (mBundle != null){

            barra2.setTitle(mBundle.getString("Nome"));
            imagem.setImageResource(mBundle.getInt("Imagem"));
            desc.setText(mBundle.getString("descri"));
            preco.setText(mBundle.getString("preco"));

        }

    }
}
