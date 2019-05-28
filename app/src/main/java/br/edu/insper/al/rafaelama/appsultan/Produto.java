package br.edu.insper.al.rafaelama.appsultan;

public class Produto {
    private String name; // Nome do produto
    private String desc; // Descrição do produto
    private String imagePath; // Nome do arquivo da imagem (ela deve estar tbm em app -> res -> drawable)
    private double price; // Preço do produto
    private int minQuant; // Quantindade mínima de compra do produto

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
}
