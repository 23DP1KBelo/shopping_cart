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
            System.out.println();
            System.out.println("Options: ");
            System.out.println("[A] - add to cart                 [X] - stop adding products");
    
            String programmRun = scanner.nextLine().trim();
            ConsoleManeger.clearScreen();
            ConsoleManeger.title();

            if (programmRun.equalsIgnoreCase("X")) {
                System.out.println();
                return false; 
            }

            if (programmRun.equalsIgnoreCase("A")) {

                System.out.println();
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

                ConsoleManeger.clearScreen();
                ConsoleManeger.title();

                if (!filteredProducts.isEmpty()) {
                    System.out.println("---------------------------------------------------------------------------");
                    System.out.printf(" %-5s|", "No.");
                    System.out.printf(" %-15s |", "Categorie");
                    System.out.printf(" %-30s |", "Name");
                    System.out.printf(" %-5s |", "Price");
                    System.out.printf(" %-6s |", "Weight");
                    System.out.println();
                    for (int i = 0; i<10; i++) {
                        System.out.println("---------------------------------------------------------------------------");
                        System.out.printf(" %-4s |", productNumber);
                        System.out.printf(" %-15s | ", filteredProducts.get(i).getCategorie());
                        System.out.printf("%-30s | ", filteredProducts.get(i).getName());
                        System.out.printf("%-5s | ", filteredProducts.get(i).getPrice());
                        System.out.printf("%-6s | ", filteredProducts.get(i).getWeight());
                        System.out.println();
    
                        productNumber++;
                    }
                    System.out.println("---------------------------------------------------------------------------");
                    System.out.println();
                    Cart.addToCart(filteredProducts);
                } else {
                    ConsoleManeger.clearScreen();

                    System.out.println();
                    System.out.println("Sorry, no items in this category! Please select a category from the list.");
                    System.out.println();
                }
            }
        }
    } 
}
