package br.edu.insper.al.rafaelama.appsultan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SignUpActivity extends AppCompatActivity {

    protected Button buttonCancela;
    protected Button buttonInscreve;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        buttonCancela = findViewById(R.id.buttonCancel);
        buttonInscreve = findViewById(R.id.buttonConfirm);

        buttonCancela.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                
            }
        });
        buttonCancela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
}
}
