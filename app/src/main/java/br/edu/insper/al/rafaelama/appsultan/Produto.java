package br.edu.insper.al.rafaelama.appsultan;

public class Produto {
    private String name;
    private String desc;
    private String imagePath;
    private double price;

    public Produto() {

    }

    public Produto(String name, String desc, String imagePath, double price) {
        this.name = name;
        this.desc = desc;
        this.imagePath = imagePath;
        this.price = price;
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

    public void setPrice(double price) {
        this.price = price;
    }
}
