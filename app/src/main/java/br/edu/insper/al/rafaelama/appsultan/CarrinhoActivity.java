package br.edu.insper.al.rafaelama.appsultan;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

public class CarrinhoActivity extends AppCompatActivity {

    protected ImageButton backButton;
    protected Button buttonCancel;
    protected Button buttonConfirm;
    protected ImageButton botao_perfil;
    protected ImageButton botao_catalogo;
    protected ImageButton botao_carrinho;
    protected ImageButton botao_pedidos;

    protected ListView produtos;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);

        backButton = findViewById(R.id.backButton);
        buttonCancel = findViewById(R.id.buttonCancel);
        buttonConfirm = findViewById(R.id.buttonConfirm);
        botao_perfil = findViewById(R.id.buttonProfile);
        botao_catalogo = findViewById(R.id.buttonCat);
        botao_carrinho = findViewById(R.id.buttonCart);
        botao_pedidos = findViewById(R.id.buttonRequests);

        produtos = findViewById(R.id.prodCarrinho);

        botao_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perfil = new Intent(CarrinhoActivity.this, PerfilActivity.class);
                startActivity(perfil);
            }
        });
        botao_pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perfil = new Intent(CarrinhoActivity.this, MainActivity.class);
                startActivity(perfil);
            }
        });

        botao_catalogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perfil = new Intent(CarrinhoActivity.this, MainActivity.class);
                startActivity(perfil);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CarrinhoActivity.this, MainActivity.class);
                setResult(2, intent);
                finish();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CarrinhoActivity.this, MainActivity.class);
                setResult(2, intent);
                finish();
            }
        });

        buttonConfirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

            }
        });

        //Adapter adapter = new Adapter(CarrinhoActivity.this, nomeProduto, imagemProduto);

        //produtos.setAdapter(adapter);

    }
}
