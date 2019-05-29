package br.edu.insper.al.rafaelama.appsultan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;

public class PaymentActivity extends AppCompatActivity {
    protected String local;
    protected Button continuar;
    protected ImageButton perfil, pedidos, catalogo, carrinho;
    protected CheckBox boleto, credito, debito, tranferencia;

    @Override
    public void onCreate(Bundle Saved){
        super.onCreate(Saved);
        setContentView(R.layout.activity_pagamento);
        local = getIntent().getStringExtra("local");

        perfil = findViewById(R.id.buttonProfile);
        catalogo = findViewById(R.id.buttonCat);
        carrinho = findViewById(R.id.buttonCart);
        pedidos = findViewById(R.id.buttonRequests);
        boleto = findViewById(R.id.cb_bb);
        credito = findViewById(R.id.cb_cc);
        debito = findViewById(R.id.cb_cd);
        tranferencia = findViewById(R.id.cb_tb);
        continuar = findViewById(R.id.bt_continuar);

        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(PaymentActivity.this, PerfilActivity.class);
                startActivity(intento);
                finish();
            }
        });
        catalogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento =  new Intent(PaymentActivity.this, MainActivity.class);
                startActivity(intento);
                finish();
            }
        });
        carrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(PaymentActivity.this,CarrinhoActivity.class);
                startActivity(intento);
                finish();
            }
        });
        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] pagamento = new String[4];
                if (boleto.isChecked()){pagamento[0] = "boleto";}
                if (credito.isChecked()){pagamento[1] = " crédito";}
                if (debito.isChecked()){pagamento[2] = " debito";}
                if (tranferencia.isChecked()){pagamento[3] = " tranferência";}
                Intent intento = new Intent(PaymentActivity.this, EndingActivity.class);
                intento.putExtra("pagamentos", pagamento);
                intento.putExtra("envio", local);
                startActivity(intento);
                finish();
            }
        });


    }
}
