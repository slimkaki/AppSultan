package br.edu.insper.al.rafaelama.appsultan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class CarrinhoActivity extends AppCompatActivity {

    protected ImageButton backButton;
    protected Button buttonCancel;
    protected Button buttonConfirm;
    protected Button botao_perfil;
    protected Button botao_catalogo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);

        backButton = findViewById(R.id.backButton);
        buttonCancel = findViewById(R.id.buttonCancel);
        buttonConfirm = findViewById(R.id.buttonConfirm);
        botao_perfil = findViewById(R.id.buttonProfile);
        botao_catalogo = findViewById(R.id.buttonCat);

        botao_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perfil = new Intent(CarrinhoActivity.this, PerfilActivity.class);
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
    }
}
