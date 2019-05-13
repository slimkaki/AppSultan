package br.edu.insper.al.rafaelama.appsultan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adapter extends ArrayAdapter<String> {

    private String[] nomes;
    private int[] imagens;
    private Context mContext;

    public Adapter(Context context, String[] nomeProduto, int[] imageProduto) {
        super(context, R.layout.listview_item);
        this.nomes = nomeProduto;
        this.imagens = imageProduto;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return nomes.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mInflator = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = mInflator.inflate(R.layout.listview_item, parent, false);

        ImageView imagem = (ImageView) convertView.findViewById(R.id.imageView);
        TextView nome = (TextView) convertView.findViewById(R.id.textView);

        imagem.setImageResource(imagens[position]);
        nome.setText(nomes[position]);

        return convertView;
    }
}
