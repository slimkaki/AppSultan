package br.edu.insper.al.rafaelama.appsultan;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ProductInfoAdapter extends ArrayAdapter<Produto> {
    private Activity context;
    private List<Produto> productsList;

    public ProductInfoAdapter(Activity context, List<Produto> productsList) {
        super(context, R.layout.listview_item, productsList);
        this.context = context;
        this.productsList = productsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listView = inflater.inflate(R.layout.listview_item, null, true);

        TextView productName = (TextView) listView.findViewById(R.id.textViewName);
        TextView productPrice = (TextView) listView.findViewById(R.id.textViewPrice);
        TextView productDesc = (TextView) listView.findViewById(R.id.textViewDesc);
        ImageView productImage = (ImageView) listView.findViewById(R.id.imageViewSultan);
        TextView productMinQuant = (TextView) listView.findViewById(R.id.textViewMinQuant);

        Produto produto = productsList.get(position);
        productName.setText(produto.getName());
        productDesc.setText(produto.getDesc());
        productPrice.setText("R$ "+ String.valueOf(produto.getPrice()) + "0");
        if (produto.getMinQuant() == 1) {
            productMinQuant.setText("Qmm: " + String.valueOf(produto.getMinQuant()) + " Peça");
        } else {
            productMinQuant.setText("Qmm: " + String.valueOf(produto.getMinQuant()) + " Peças");
        }
        //productImage.setImageDrawable();
        int id = productImage.getContext().getResources().getIdentifier(produto.getImagePath(), "drawable", "br.edu.insper.al.rafaelama.appsultan");
        productImage.setImageResource(id);
        return listView;
    }
}
