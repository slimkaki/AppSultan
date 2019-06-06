package br.edu.insper.al.rafaelama.appsultan;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.List;

public class FirebaseCatalogoInfo {
    private Activity context;
    private double profit;

    public FirebaseCatalogoInfo(Activity context) {
        this.context = context;
    }

    public void getCatalogoList(DatabaseReference databaseReference, ListView listView, List<Produto> productsList) {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                productsList.clear();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Produto produto = child.getValue(Produto.class);
                    productsList.add(produto);
                }
                ProductInfoAdapter productInfoAdapter = new ProductInfoAdapter(context, productsList);
                listView.setAdapter(productInfoAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("MUSTAFAR", databaseError.getMessage());
            }
        };

        databaseReference.addListenerForSingleValueEvent(valueEventListener);
    }

    public void getSearchCatalogoList(DatabaseReference databaseReference, ListView listView, List<Produto> productsList, String cap) {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                productsList.clear();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Produto produto = child.getValue(Produto.class);
                    if (produto.getName().contains(cap)) {
                        productsList.add(produto);
                    }
                }
                ProductInfoAdapter productInfoAdapter = new ProductInfoAdapter(context, productsList);
                listView.setAdapter(productInfoAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("MUSTAFAR", databaseError.getMessage());
            }
        };
        databaseReference.addListenerForSingleValueEvent(valueEventListener);
    }
}
