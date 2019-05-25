package br.edu.insper.al.rafaelama.appsultan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.nio.file.attribute.AclEntryFlag;

public class EndingActivity extends AppCompatActivity {
    String[] pagamento;
    String local;
    Button finalizar;
    ImageButton perfil, catalogo, pedidos, carrinho;

    @Override
    public void onCreate(Bundle Saved){
        super.onCreate(Saved);
        setContentView(R.layout.activity_pedido_confirmado);
        pagamento = getIntent().getStringArrayExtra("pagamentos");
        local = getIntent().getStringExtra("envio");
        finalizar = findViewById(R.id.buttonOkPedido);
        perfil = findViewById(R.id.buttonProfile);
        catalogo = findViewById(R.id.buttonCat);
        carrinho = findViewById(R.id.buttonCart);
        pedidos = findViewById(R.id.buttonRequests);
        Bundle mBundle = getIntent().getExtras();

        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(EndingActivity.this,PerfilActivity.class);
                startActivity(intento);
                finish();
            }
        });
        catalogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento =  new Intent(EndingActivity.this, MainActivity.class);
                startActivity(intento);
                finish();
            }
        });
        carrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(EndingActivity.this, CarrinhoActivity.class);
                startActivity(intento);
                finish();
            }
        });
        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");

                String shareTitle = "Pedido";
                String shareBody = "";
                for (String s:pagamento){
                    shareBody += " " + s;
                }

                String sharePrice = "Talvez mais coisas";

                share.putExtra(Intent.EXTRA_COMPONENT_NAME, shareTitle);
                share.putExtra(Intent.EXTRA_TEXT,shareBody + sharePrice);
                share.putExtra("jid", PhoneNumberUtils.stripSeparators("551198051435")+"@s.whatsapp.net");

                startActivity(Intent.createChooser(share, "sharing..."));


                Intent intento = new Intent(EndingActivity.this, MainActivity.class);
                startActivity(intento);
                finish();
            }
        });
    }
}
