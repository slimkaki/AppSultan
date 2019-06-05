package br.edu.insper.al.rafaelama.appsultan;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Produto {
    private String name; // Nome do produto
    private String desc; // Descrição do produto
    private String imagePath; // Nome do arquivo da imagem (ela deve estar tbm em app -> res -> drawable)
    private double price; // Preço do produto
    private int minQuant; // Quantindade mínima de compra do produto
    private double userProfit;

    public Produto() {

    }

    public Produto(String name, String desc, String imagePath, double price, int minQuant) {
        this.name = name;
        this.desc = desc;
        this.imagePath = imagePath;
        this.price = price;
        this.minQuant  = minQuant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public double getPrice() {
        return price;
    }

    public String getPriceString(){
        String p = Double.toString(this.price);
        return "R$ " + p;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMinQuant() {
        return minQuant;
    }

    public void setMinQuant(int minQuant) {
        this.minQuant = minQuant;
    }

    public String getShareProduct(){
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = currentFirebaseUser.getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference uidRef = databaseReference.child("users").child(uid);
        DatabaseReference profitRef = uidRef.child("profit");

        profitRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    userProfit = dataSnapshot.getValue(Double.class);
                } catch (Exception e) {
                    e.fillInStackTrace();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        double sharedPrice = getPrice()*(1.0+(userProfit/100.0));

        String b = "*_Produto:_* " + getName();
        String a = getDesc();
        String p = "*_Preço:_* " + String.valueOf(sharedPrice);

        return b + "\n" +"\n" + a + "\n" +"\n" + p;
    }
}
