package br.edu.insper.al.rafaelama.appsultan;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;


public class MainActivity extends AppCompatActivity {

    protected ImageButton procura;
    protected ImageButton filtro;
    protected SwipeMenuListView catalogo;
    protected ImageButton botao_perfil;
    protected ImageButton botao_carrinho;
    protected ImageButton botao_pedidos;
    protected ImageButton botao_cat;

    private String[] nomeProduto = {
            "Sofá",
            "Tapete",
            "Toalha",
            "Toalha de mesa",
            "Copo",
            "Prato"};

    int[] imagemProduto = {
            R.drawable.blackmetal,
            R.drawable.mine,
            R.drawable.arveres,
            R.drawable.bunito,
            R.drawable.lofi_capa,
            R.drawable.lvanda
    };
    private String[] descri = {
            "Lorem ipsum dolor sit amet consectetur adipiscing elit. Nulla a nulla ac tellus gravida sagittis consectetur ac quam. Laririri ipsum dolor sit amet consectetur adipiscing elit. Nulla a nulla ac tellus gravida sagittis consectetur ac quam. ",
            "Laura ipsum dolor sit amet consectetur adipiscing elit. Nulla a nulla ac tellus gravida sagittis consectetur ac quam. Laririri ipsum dolor sit amet consectetur adipiscing elit. Nulla a nulla ac tellus gravida sagittis consectetur ac quam. ",
            "Lorenzo ipsum dolor sit amet consectetur adipiscing elit. Nulla a nulla ac tellus gravida sagittis consectetur ac quam. Laririri ipsum dolor sit amet consectetur adipiscing elit. Nulla a nulla ac tellus gravida sagittis consectetur ac quam. ",
            "Laurinha ipsum dolor sit amet consectetur adipiscing elit. Nulla a nulla ac tellus gravida sagittis consectetur ac quam. Laririri ipsum dolor sit amet consectetur adipiscing elit. Nulla a nulla ac tellus gravida sagittis consectetur ac quam. ",
            "Lororo ipsum dolor sit amet consectetur adipiscing elit. Nulla a nulla ac tellus gravida sagittis consectetur ac quam. Laririri ipsum dolor sit amet consectetur adipiscing elit. Nulla a nulla ac tellus gravida sagittis consectetur ac quam. ",
            "Laririri ipsum dolor sit amet consectetur adipiscing elit. Nulla a nulla ac tellus gravida sagittis consectetur ac quam. Laririri ipsum dolor sit amet consectetur adipiscing elit. Nulla a nulla ac tellus gravida sagittis consectetur ac quam. "
    };

    private String[] preco = {
            "R$120,00",
            "R$13,00",
            "R$130,00",
            "R$1300,00",
            "R$13000,00",
            "R$130000,00"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        procura = findViewById(R.id.buttonSearch);

        filtro = findViewById(R.id.buttonFiltro);

        catalogo = findViewById(R.id.catalogo);

        botao_perfil = findViewById(R.id.buttonProfile);

        botao_cat = findViewById(R.id.buttonCat);

        botao_pedidos = findViewById(R.id.buttonRequests);

        botao_carrinho = findViewById(R.id.buttonCart);

        botao_carrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perfil = new Intent(MainActivity.this, CarrinhoActivity.class);
                startActivity(perfil);
            }
        });

        botao_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perfil = new Intent(MainActivity.this, PerfilActivity.class);
                startActivity(perfil);
            }
        });
        botao_pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perfil = new Intent(MainActivity.this, PerfilActivity.class);
                startActivity(perfil);
            }
        });

        Adapter adapter = new Adapter(MainActivity.this, nomeProduto, imagemProduto);

        catalogo.setAdapter(adapter);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem whats = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                whats.setBackground(new ColorDrawable(Color.rgb(0x25,
                        0xd3, 0x66)));
                // set item width
                whats.setWidth(400);
                // set a icon
                whats.setTitle("Whatsapp");
                whats.setTitleSize(18);
                whats.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(whats);

                SwipeMenuItem to_cart = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                to_cart.setBackground(new ColorDrawable(Color.rgb(0xFF,
                        0xFF, 0xFF)));
                // set item width
                to_cart.setWidth(400);
                // set a icon
                to_cart.setIcon(R.drawable.ic_cart);
                // add to menu
                menu.addMenuItem(to_cart);
            }
        };

        catalogo.setMenuCreator(creator);

        catalogo.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:

                        //zapzap share

                        break;

                    case 1:

                        //Adiciona ao carrinho

                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });

        catalogo.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent mIntent = new Intent(MainActivity.this, ProductActivity.class);

                mIntent.putExtra("Nome", nomeProduto[position]);
                mIntent.putExtra("Imagem", imagemProduto[position]);
                mIntent.putExtra("descri", descri[position]);
                mIntent.putExtra("preco", preco[position]);

                startActivity(mIntent);
            }
        });
    }
}
