package br.edu.insper.al.rafaelama.appsultan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ForgotPasswordActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        Button cancelButton = findViewById(R.id.cancel_button);
        Button continueButton = findViewById(R.id.continue_button);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                startActivityForResult(returnIntent, 1);
            }
        });

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                startActivityForResult(returnIntent, 1);
            }
        });
    }
}
