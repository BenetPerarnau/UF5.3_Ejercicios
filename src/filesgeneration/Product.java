package filesgeneration;

import java.io.Serializable;

public class Product implements Serializable{
    
    private String cod;
    private String desc;
    private int stock;
    private double price;

    public Product(String cod, String desc, int stock, double price) {
        this.cod = cod;
        this.desc = desc;
        this.stock = stock;
        this.price = price;
    }

    public String getCod() {return cod;}
    public String getDesc() {return desc;}
    public int getStock() {return stock;}
    public double getPrice() {return price;}

    public void setCod(String cod) {this.cod = cod;}
    public void setDesc(String desc) {this.desc = desc;}
    public void setStock(int stock) {this.stock = stock;}
    public void setPrice(double price) {this.price = price;}

    @Override
    public String toString() {
        return "Product{" + "cod=" + cod + ", desc=" + desc + ", stock=" + stock + ", price=" + price + '}';
    }
    
    
    
    
}
