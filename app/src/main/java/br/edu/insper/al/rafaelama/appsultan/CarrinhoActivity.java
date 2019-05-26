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
import android.widget.Toast;

public class CarrinhoActivity extends AppCompatActivity {

    protected Button buttonCancel,buttonConfirm,endereco,fabrica;
    protected ImageButton backButton, botao_perfil,botao_pedidos,botao_catalogo,botao_carrinho;
    protected String localEnvio;
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
//        fabrica = findViewById(R.id.buttonFabrica);
//        endereco = findViewById(R.id.buttonEndereco);

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
                Intent intento = new Intent(CarrinhoActivity.this,PaymentActivity.class);
                intento.putExtra("envio",localEnvio);
                startActivity(intento);
                finish();
            }
        });
        fabrica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                localEnvio = "Fabrica";
                Toast.makeText(CarrinhoActivity.this,"Local de entrega definida como fábrica",Toast.LENGTH_LONG).show();
            }
        });
        endereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                localEnvio = "Endereço";
                Toast.makeText(CarrinhoActivity.this,"Local de entrega definida como seu endereço",Toast.LENGTH_LONG).show();
            }
        });

        //Adapter adapter = new Adapter(CarrinhoActivity.this, nomeProduto, imagemProduto);


        //Adapter adapter = new Adapter(CarrinhoActivity.this, nomeProduto, imagemProduto);

        //produtos.setAdapter(adapter);

    }
}
