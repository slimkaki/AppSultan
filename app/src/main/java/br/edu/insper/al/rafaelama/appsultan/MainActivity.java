package br.edu.insper.al.rafaelama.appsultan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    protected Toolbar barra;
    protected ListView catalogo;

    private String[] nomeProduto = {
            "Sofá",
            "Tapete"
    };//, "Toalha", "Toalha de mesa", "Copo", "Prato"};

    int[] imagemProduto = {
            R.drawable.blackmetal,
            R.drawable.mine
    };
    private String[] descri = {
            "muito doido",
            "deveras doido"
    };

    private String[] preco = {
            "R$120,00",
            "R$13,00"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        barra = findViewById(R.id.barra);
        catalogo = (ListView) findViewById(R.id.catalogo);

        barra.setTitle("Catálogo");

        Adapter adapter = new Adapter(MainActivity.this, nomeProduto, imagemProduto);

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
}
