package br.edu.insper.al.rafaelama.appsultan;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CartInfoAdapter extends ArrayAdapter<Produto>{
    private Activity context;
    private List<Produto> productsList;

    public CartInfoAdapter(Activity context, List<Produto> productsList) {
        super(context, R.layout.listview_cart, productsList);
        this.context = context;
        this.productsList = productsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listView = inflater.inflate(R.layout.listview_cart, null, true);

        TextView productName = (TextView) listView.findViewById(R.id.textViewName);
        TextView productPrice = (TextView) listView.findViewById(R.id.textViewPrice);
        ImageView productImage = (ImageView) listView.findViewById(R.id.imageViewSultan);
        TextView productMinQuant = (TextView) listView.findViewById(R.id.textViewMinQuant);
        Button buttonRemove = (Button) listView.findViewById(R.id.buttonRemove);

        Produto produto = productsList.get(position);
        productName.setText(produto.getName());
        productPrice.setText("R$ "+ String.valueOf(produto.getPrice()) + "0");
        if (produto.getMinQuant() == 1) {
            productMinQuant.setText("Qmm: " + String.valueOf(produto.getMinQuant()) + " Peça");
        } else {
            productMinQuant.setText("Qmm: " + String.valueOf(produto.getMinQuant()) + " Peças");
        }
        //productImage.setImageDrawable();
        int id = productImage.getContext().getResources().getIdentifier(produto.getImagePath(), "drawable", "br.edu.insper.al.rafaelama.appsultan");
        productImage.setImageResource(id);
        productImage.setMaxHeight(100);
        productImage.setMaxWidth(100);

        buttonRemove.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                String uid = currentFirebaseUser.getUid();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                DatabaseReference uidRef = databaseReference.child("users").child(uid);
                DatabaseReference carRef = uidRef.child("carrinho");

                ValueEventListener valueEventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot child : dataSnapshot.getChildren()) {
                            Produto deleteProduct = child.getValue(Produto.class);
                            if (deleteProduct.getName().equals(produto.getName())) {
                                child.getRef().removeValue();
                                break;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.d("MUSTAFAR","Deu erro!");
                    }
                };
                carRef.addListenerForSingleValueEvent(valueEventListener);
                Toast.makeText(context, "O item foi removido", Toast.LENGTH_SHORT).show();
            }
        });

        return listView;
    }
}
