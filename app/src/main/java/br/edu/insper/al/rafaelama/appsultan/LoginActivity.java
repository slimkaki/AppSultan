package br.edu.insper.al.rafaelama.appsultan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    private String username;
    private String password;

    private boolean loged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loged = false;
        super.onCreate(savedInstanceState);
        if(loged){
            Intent returnIntent = new Intent(LoginActivity.this, MainActivity.class);
            startActivityForResult(returnIntent, 1);
        }
        setContentView(R.layout.activity_login);

        Button loginButton = findViewById(R.id.login_button);
        Button singupButton = findViewById(R.id.singup_button);
        Button forgotPasswordButton = findViewById(R.id.forgot_password_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent(LoginActivity.this, MainActivity.class);
                startActivityForResult(returnIntent, 1);
            }
        });

        singupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent(LoginActivity.this, MainActivity.class);
                startActivityForResult(returnIntent, 1);
            }
        });

        forgotPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivityForResult(returnIntent, 1);
            }
        });
    }
}
