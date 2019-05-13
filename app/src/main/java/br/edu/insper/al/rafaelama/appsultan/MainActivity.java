package br.edu.insper.al.rafaelama.appsultan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    Toolbar barra;
    ListView catalogo;

    String[] nomeProduto = {"Sofá", "Tapete", "Toalha", "Toalha de mesa", "Copo", "Prato"};
    int[] imagemProduto = {
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_foreground
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        barra = (Toolbar) findViewById(R.id.barra);
        catalogo = (ListView) findViewById(R.id.catalogo);

    }
}
