package br.edu.insper.al.rafaelama.appsultan;

import java.util.ArrayList;
import java.util.LinkedList;

public class User {
    public String name;
    public String email;
    public String address;
    public String cep;
    public String cpf;
    public String celular;
    public LinkedList<Produto> carrinho;
    public String id;
    public ArrayList<Produto> userCarrinho;

    public User(String name, String email, String address, String cep, String cpf, String celular, String id) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.cep = cep;
        this.cpf = cpf;
        this.celular = celular;
        this.id = id;
        this.userCarrinho = new ArrayList<>();
    }

    public User() {

    }

    public ArrayList<Produto> getCarrinho() {
        return this.userCarrinho;
    }

    public void addOnCarrinho(Produto produto) {
        userCarrinho.add(produto);
    }

    public void limpaCarrinho() {
        userCarrinho.clear();
    }

    public void removeCarrinhoItem(Produto produto) {
        for (Produto p : userCarrinho) {
            if (p.getName().equals(produto.getName())) {
                userCarrinho.remove(produto);
            }
        }
    }

}
