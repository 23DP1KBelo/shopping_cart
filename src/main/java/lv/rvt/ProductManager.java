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
            String[] parts = line.trim().split(DELIMITER);

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

    public static void productsByCategorie() throws IOException {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                           Categories:

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

        if (!filteredProducts.isEmpty()) {
            System.out.println("--------------------------------------------------------------------");
            System.out.printf(" %-15s |", "Categorie");
            System.out.printf(" %-30s |", "Name");
            System.out.printf(" %-5s |", "Price");
            System.out.printf(" %-6s |", "Weight");
            System.out.println();
            for (Products product : filteredProducts) {
                System.out.println("--------------------------------------------------------------------");
                System.out.printf(" %-15s | ", product.getCategorie());
                System.out.printf("%-30s | ", product.getName());
                System.out.printf("%-5s | ", product.getPrice());
                System.out.printf("%-6s | ", product.getWeight() + "g");
                System.out.println();
            }
            System.out.println("--------------------------------------------------------------------");
            addToCart(filteredProducts);
        } else {
            System.out.println();
            System.out.println("""
                               Sorry, no items in this category!
                               Please select a category from the list!""");
            System.out.println();

            productsByCategorie();
        }
    }

    public static void addToCart(ArrayList<Products> filteredProducts) throws IOException {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the number of the product you want to add to your cart (1, 2, etc.): ");
        int productNumber = scanner.nextInt();

        if (productNumber < 1 || productNumber > filteredProducts.size()) {
            System.out.println("Invalid product selection.");
            return;
        }

        Products selectedProduct = filteredProducts.get(productNumber - 1);
        
        System.out.println("Enter the quantity you want to add to the cart: ");
        int quantity = scanner.nextInt();
        
        Cart newCartItem = new Cart(selectedProduct.getCategorie(), selectedProduct.getName(), selectedProduct.getPrice(), selectedProduct.getWeight(), quantity, "username");  // Replace with actual username
        Cart.cart.add(newCartItem);

        UserManager.saveCartToFile();

        System.out.println("Product added to your cart!");
    }
}
