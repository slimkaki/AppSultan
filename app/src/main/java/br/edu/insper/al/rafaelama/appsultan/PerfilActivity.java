package br.edu.insper.al.rafaelama.appsultan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class PerfilActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        Button btaddress = findViewById(R.id.AddressButton);
        Button btedit = findViewById(R.id.EditButton);
        Button btrequests = findViewById(R.id.RequestsButton);
        Button btlucro = findViewById(R.id.LucroButton);

        Button voltar = findViewById(R.id.voltar);

        btaddress.setOnClickListener(AddressHandler);
        btedit.setOnClickListener(EditHandler);
        btrequests.setOnClickListener(RequestsHandler);
        btlucro.setOnClickListener(LucroHandler);

        voltar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilActivity.this, MainActivity.class);
                setResult(2, intent);
                finish();
            }
        });


    }
    View.OnClickListener AddressHandler = new View.OnClickListener(){
        public void onClick(View v){

        }
    };
    View.OnClickListener EditHandler = new View.OnClickListener(){
        public void onClick(View v){

        }
    };
    View.OnClickListener RequestsHandler = new View.OnClickListener(){
        public void onClick(View v){

        }
    };
    View.OnClickListener LucroHandler = new View.OnClickListener(){
        public void onClick(View v){

        }
    };
}
