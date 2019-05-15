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

    public Adapter(Context context, String[] nomeProduto, int[] imageProduto ) {
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

        ViewHolder mViewHolder = new ViewHolder();

        if (convertView == null) {

            LayoutInflater mInflator = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = mInflator.inflate(R.layout.listview_item, parent, false);

            mViewHolder.imagem = (ImageView) convertView.findViewById(R.id.imageView);
            mViewHolder.nome = (TextView) convertView.findViewById(R.id.textView);

            convertView.setTag(mViewHolder);
        }

        else{
            mViewHolder = (ViewHolder)convertView.getTag();
        }

        mViewHolder.imagem.setImageResource(imagens[position]);
        mViewHolder.nome.setText(nomes[position]);

        return convertView;
    }

    static class ViewHolder{
        ImageView imagem;
        TextView nome;

    }
}
