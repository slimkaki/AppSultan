package br.edu.insper.al.rafaelama.appsultan;

import android.content.Intent;
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
        buttonInscreve = findViewById(R.id.singup_button);

        buttonInscreve.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivityForResult(returnIntent, 1);
            }
        });
        buttonCancela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivityForResult(returnIntent, 1);
            }
        });
}
}
