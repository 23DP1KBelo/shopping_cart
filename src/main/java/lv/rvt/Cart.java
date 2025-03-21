package lv.rvt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Cart extends Products{
    public static ArrayList<Cart> cart = new ArrayList<>();
    public String username;
    public static BufferedWriter Newfile;
    public static final String DELIMITER = ";";

    public Cart(String categories, String name, Double price, String weight, Integer quantity, String username) {
        super(categories, name, price, weight, quantity);
        this.username = username;
    }

    public Cart(String categories, String name, Double price, String weight, Integer quantity) {
            super(categories, name, price, weight, quantity);
    }

    public Cart(String categories, String name, Double price, String weight) {
        super(categories, name, price, weight);
    }

    public String toCsvRow() {
        return this.categorie + ";" + this.name + ";" + this.price + ";" + this.weight + ";" + this.quantity;
    }

    public static String getCartFileName() {
        String username = User.getCurrentUsername();  
        return "data/user_shoppng_cart/" + username + ".csv";
    }

    public static void makeCart() throws IOException {
        String fileName = getCartFileName();
        BufferedWriter Newfile = new BufferedWriter(new FileWriter(fileName));
        Newfile.write("categorie;name;price;weight;quantity");
        Newfile.newLine();
        Newfile.close();
    }

    public static ArrayList<Cart> getCart(String username) throws IOException {
        String fileName = "data/user_shoppng_cart/" + username + ".csv";
        File file = new File(fileName);

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;

        reader.readLine();

        while ((line = reader.readLine()) != null) {
            String[] parts = line.trim().split(DELIMITER);

            String categorie = parts[0].trim();
            String name = parts[1].trim();
            Double price = Double.valueOf(parts[2].trim());
            String weight = parts[3].trim();
            Integer quantity = Integer.valueOf(parts[4].trim());

            Cart cartItem = new Cart(categorie, name, price, weight, quantity, username);
            cart.add(cartItem);
        }
        reader.close();
        return cart;
    }

    public static Cart addProducts(String categories, String name, Double price, String weight){
        Scanner scanner = new Scanner(System.in);
        Integer quantity = Integer.valueOf(scanner.nextLine());

        Cart newProduct = new Cart(categories, name, price, weight, quantity);
        return newProduct;
    }
}
