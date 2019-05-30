package br.edu.insper.al.rafaelama.appsultan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PedidoActivity extends AppCompatActivity {
    protected ImageButton perfil, catalogo, carrinho, pedidos;
    protected Button fab, enderco, confirmar;
    protected TextView qtdtxt, desctxt, fretetxt, totaltxt, lucrotxt;
    protected String localEnvio = "Fábrica";
    protected int qtd, desconto, frete, total, lucro;

    @Override
    public void onCreate(Bundle saved){
        super.onCreate(saved);
        setContentView(R.layout.activity_pedido);

        perfil = findViewById(R.id.buttonProfile);
        catalogo = findViewById(R.id.buttonCat);
        carrinho = findViewById(R.id.buttonCart);
        pedidos = findViewById(R.id.buttonRequests);
        fab = findViewById(R.id.buttonFabrica);
        enderco = findViewById(R.id.buttonEndereco);
        confirmar = findViewById(R.id.buttonConfirm);
        qtdtxt = findViewById(R.id.qtd);
        qtd = (int) getIntent().getIntExtra("quantidade",0);
        qtdtxt.setText(String.valueOf(qtd));
        //desc = findViewById(R.id.desc);
       // frete = findViewById(R.id.frete);
        totaltxt = findViewById(R.id.total);
        total = getIntent().getIntExtra("total",0);
        totaltxt.setText(String.valueOf(total));
        //lucro = findViewById(R.id.lucro);
        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(PedidoActivity.this,PerfilActivity.class);
                startActivity(intento);
                finish();
            }
        });
        catalogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(PedidoActivity.this, MainActivity.class);
                startActivity(intento);
                finish();
            }
        });
        carrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(PedidoActivity.this, CarrinhoActivity.class);
                startActivity(intento);
                finish();
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                localEnvio = "Fábrica";
                Toast.makeText(PedidoActivity.this,"Destino atualizado",Toast.LENGTH_SHORT).show();
            }
        });
        enderco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                localEnvio = "Endereço";
                Toast.makeText(PedidoActivity.this,"Destino atualizado",Toast.LENGTH_SHORT).show();
            }
        });
        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(PedidoActivity.this, PaymentActivity.class);
                intento.putExtra(localEnvio,"local");
                startActivity(intento);
                finish();
            }
        });


    }

}
