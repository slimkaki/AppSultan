package br.edu.insper.al.rafaelama.appsultan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfitActivity extends AppCompatActivity {

    private EditText profitSign;
    private FirebaseUser user;
    private DatabaseReference mDatabase;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profit);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        profitSign = findViewById(R.id.LucroEdit);

        Button go = findViewById(R.id.DefineLucroButton);

        user = FirebaseAuth.getInstance().getCurrentUser();
        id = user.getUid();

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String profit = profitSign.getText().toString().trim();
                if(profit.equals("")){
                    mDatabase.child("users").child(id).child("profit").setValue(0);
                }
                else{
                    mDatabase.child("users").child(id).child("profit").setValue(Double.valueOf(profit));
                }
                Intent returnIntent = new Intent(ProfitActivity.this, MainActivity.class);
                startActivityForResult(returnIntent, 1);
            }
        });
    }
}
