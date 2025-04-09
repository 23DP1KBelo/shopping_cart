package lv.rvt;

public class Products {
    public Double price;
    public String name;
    public String categorie;
    public String weight;
    public Integer quantity;

    public Products(String categories,String name, Double price, String weight, Integer quantity){
        this.categorie = categories;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.quantity = quantity;
    }

    public Products(String categories, String name, Double price, String weight){
        this.categorie = categories;
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    public Products(String name){
        this.name = name;
    }

    public String toCsvRow() {
        return this.categorie + ";" + this.name + ";" + this.price + ";" + this.weight;
    }

    public String getCategorie(){
        return this.categorie;
    }

    public String getName(){
        return this.name;
    }

    public Double getPrice(){
        return this.price;
    }

    public Integer getQuantity(){
        return this.quantity;
    }

    public String getWeight(){
        return this.weight;
    }
    public static Double priceOfproduct(Double price, Integer quantity){
        Double sum;
        sum =price * quantity;
        return sum;
    }

    @Override
    public String toString(){
        return this.categorie + " " + this.name + " " + this.price + " " + this.weight + "g";
    }
}
