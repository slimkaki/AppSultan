package br.edu.insper.al.rafaelama.appsultan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Toast;

public class PaymentActivity extends AppCompatActivity {
    protected String local;
    protected Button continuar;
    protected ImageButton perfil, pedidos, carrinho;
    protected CheckBox boleto, credito, debito, tranferencia;

    @Override
    public void onCreate(Bundle Saved){
        super.onCreate(Saved);
        setContentView(R.layout.activity_pagamento);
        local = getIntent().getStringExtra("local");

        perfil = findViewById(R.id.buttonProfile);
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
        carrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(PaymentActivity.this,CarrinhoActivity.class);
                startActivity(intento);
                finish();
            }
        });
        pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perfil = new Intent(PaymentActivity.this, MainActivity.class);
                startActivity(perfil);
            }
        });
        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int it = 0;
                String[] pagamento = new String[4];
                pagamento[0]="";
                pagamento[1]="";
                pagamento[2]="";
                pagamento[3]="";
                if (boleto.isChecked()){pagamento[0] = "boleto";it++;}
                if (credito.isChecked()){pagamento[1] = " crédito";it++;}
                if (debito.isChecked()){pagamento[2] = " debito";it++;}
                if (tranferencia.isChecked()){pagamento[3] = " tranferência";it++;}
                if (it!=0) {
                    Intent intento = new Intent(PaymentActivity.this, EndingActivity.class);
                    intento.putExtra("pagamentos", pagamento);
                    intento.putExtra("envio", local);
                    startActivity(intento);
                    finish();
                }else {
                    Toast.makeText(PaymentActivity.this,"Nenhum método selecionado",Toast.LENGTH_SHORT);}
            }
        });
    }
}
