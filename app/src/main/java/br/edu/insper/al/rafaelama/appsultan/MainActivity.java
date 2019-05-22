package br.edu.insper.al.rafaelama.appsultan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    protected ImageButton procura;
    protected ListView catalogo;
    protected Button botao_perfil;
    protected Button botao_carrinho;

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


        procura = findViewById(R.id.buttonSearch);

        catalogo = (ListView) findViewById(R.id.catalogo);

        botao_perfil = findViewById(R.id.buttonProfile);

        botao_carrinho = findViewById(R.id.buttonCart);

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
