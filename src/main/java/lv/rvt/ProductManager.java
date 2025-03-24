package lv.rvt;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import lv.rvt.tools.Helper;

public class ProductManager extends Products {  

    public ProductManager(String categories, String name, Double price, String weight) {
        super(categories, name, price, weight);
    }

    public static final String DELIMITER = ";";

    public static ArrayList<Products> products = new ArrayList<>();

    public static ArrayList<Products> getProductlist() throws IOException {
        BufferedReader reader = Helper.getReader("products.csv");
        String line;

        reader.readLine();
        while ((line = reader.readLine()) != null) {
            String[] parts = line.trim().split(ProductManager.DELIMITER);

            String categorie = parts[0].trim();
            String name = parts[1];
            Double price = Double.valueOf(parts[2].trim());
            String weight = parts[3];

            Products product = new Products(categorie, name, price, weight);
            products.add(product);
        }
        return products;
    }

    public static ArrayList<Products> getProductsByCategorie(String userInput) throws IOException {
        getProductlist();
        ArrayList<Products> productsByCategorie = new ArrayList<>();

        userInput = userInput.trim();

        for (Products product : products) {
            if (product.getCategorie().equalsIgnoreCase(userInput)) {
                productsByCategorie.add(product);
            }
        }
        return productsByCategorie;
    }

    public static boolean productsByCategorie() throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please write 'add Items' to add products to your cart or 'exit' to quit:");
    
            String programmRun = scanner.nextLine().trim();
    
            if (programmRun.equalsIgnoreCase("exit")) {
                Cart.saveCartToFile();
                System.out.println("Exiting the program...");
                return false; 
            }
    
            if (programmRun.equalsIgnoreCase("add Items")) {
                System.out.println("Categories:");
                System.out.println("""
                           Vegetables
                           Fruits
                           Flour products
                           Drinks
                           Dairy products
                           Meat
                           """);
    
                System.out.println("Please put in a category you want to find: ");
                String userInput = scanner.nextLine().trim();
    
                ArrayList<Products> filteredProducts = getProductsByCategorie(userInput);
    
                int productNumber = 1;
                if (!filteredProducts.isEmpty()) {
                    System.out.println("---------------------------------------------------------------------------");
                    System.out.printf(" %-5s|", "No.");
                    System.out.printf(" %-15s |", "Categorie");
                    System.out.printf(" %-30s |", "Name");
                    System.out.printf(" %-5s |", "Price");
                    System.out.printf(" %-6s |", "Weight");
                    System.out.println();
                    for (Products product : filteredProducts) {
                        System.out.println("---------------------------------------------------------------------------");
                        System.out.printf(" %-4s |", productNumber);
                        System.out.printf(" %-15s | ", product.getCategorie());
                        System.out.printf("%-30s | ", product.getName());
                        System.out.printf("%-5s | ", product.getPrice());
                        System.out.printf("%-6s | ", product.getWeight());
                        System.out.println();
    
                        productNumber++;
                    }
                    System.out.println("---------------------------------------------------------------------------");
                    Cart.addToCart(filteredProducts);
                } else {
                    System.out.println();
                    System.out.println("Sorry, no items in this category! Please select a category from the list.");
                    System.out.println();
                }
            }
        }
    } 
}
