package lv.rvt;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    public static void seeAllProducts() throws IOException{
        products = new ArrayList<>();
        getProductlist();
        Scanner scanner = new Scanner(System.in);
        int productNumber = 1;
        ConsoleManeger.clearScreen();
        ConsoleManeger.title();

        System.out.println();
        System.out.println("\u001B[97m---------------------------------------------------------------------------");
        System.out.printf(" %-5s|", "No.");
        System.out.printf(" %-15s |", "Categorie");
        System.out.printf(" %-30s |", "Name");
        System.out.printf(" %-5s |", "Price");
        System.out.printf(" %-6s |", "Weight");
        System.out.println();

        for(Products p : products){
            System.out.println("---------------------------------------------------------------------------");
            System.out.printf(" %-4s |", productNumber);
            System.out.printf(" %-15s | ", p.getCategorie());
            System.out.printf("%-30s | ", p.getName());
            System.out.printf("%-5s | ", p.getPrice());
            System.out.printf("%-6s | ", p.getWeight());
            System.out.println();

            productNumber ++;
        }
        System.out.println("---------------------------------------------------------------------------");
        System.out.println();
        System.out.println("[C] - fillter by category           [S] - search by name         [A] - add to cart      [ ] - back");
        String answer = scanner.nextLine();
        if(answer.equalsIgnoreCase("C")){
            ConsoleManeger.clearScreen();
            ConsoleManeger.title();
            System.out.println("Please select an option: ");
            productsByCategorie();
        } else if(answer.equalsIgnoreCase("S")){
            ConsoleManeger.clearScreen();
            ConsoleManeger.title();
            getProductsBySearch();
        } else if(answer.equalsIgnoreCase("A")){
            Cart.addToCart(products);
        }
    }

    public static ArrayList<Products> getProductsByCategorie(String userInput) throws IOException {
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
        products = new ArrayList<>();
        getProductlist();
        while (true) {
            System.out.println();
            System.out.println("\u001B[97mOptions: ");
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
                System.out.println("\u001B[97mCategories:");
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
                    System.out.println();
                    System.out.println("\u001B[97mSort the items by: ");
                    System.out.println("[D] - price ↑           [U] - price ↓           [A] A-Z            [R]Z-A             [ ] - none");
                    System.out.println();
                    String sort = scanner.nextLine();

                    if(sort.equalsIgnoreCase("D")){
                        sortByPriceDown(filteredProducts);
                    }else if(sort.equalsIgnoreCase("U")){
                        sortByPriceUp(filteredProducts);
                    }else if(sort.equalsIgnoreCase("A")){
                        sortByNameA(filteredProducts);
                    }else if(sort.equalsIgnoreCase("R")){
                        sortByNameR(filteredProducts);
                    }

                    System.out.println("---------------------------------------------------------------------------");
                    System.out.printf(" %-5s|", "No.");
                    System.out.printf(" %-15s |", "Categorie");
                    System.out.printf(" %-30s |", "Name");
                    System.out.printf(" %-5s |", "Price");
                    System.out.printf(" %-6s |", "Weight");
                    System.out.println();
                    for (int i = 0; i<filteredProducts.size(); i++) {
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
                    System.out.println("\u001B[31mSorry, no items in this category! Please select a category from the list.");
                    System.out.println();
                }
            }
        }
    } 

    private static ArrayList<Products> searchProducts(String search) throws IOException {
        ArrayList<Products> productsBySearch = new ArrayList<>();
        
        for (Products product : products) {
            String name = product.getName();
            if (name != null && name.toLowerCase().contains(search.toLowerCase())) {
                boolean alreadyAdded = false;
                for (Products p : productsBySearch) {
                    if (p.getName().equalsIgnoreCase(name)) {
                        alreadyAdded = true;
                        break;
                    }
                }
                if (!alreadyAdded) {
                    productsBySearch.add(product);
                }
            }
        }
        return productsBySearch;
    }

    public static void getProductsBySearch() throws IOException{
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("\u001B[97mSearch items: ");
        String search = scanner.nextLine();

        int productNumber = 1;

        ArrayList<Products> searchProducts = searchProducts(search);

        if(!searchProducts.isEmpty()){
            System.out.println("---------------------------------------------------------------------------");
            System.out.printf(" %-4s |", productNumber);
            System.out.printf(" %-15s |", "Categorie");
            System.out.printf(" %-30s |", "Name");
            System.out.printf(" %-5s |", "Price");
            System.out.printf(" %-6s |", "Weight");
            System.out.println();

            for(int i = 0; i < searchProducts.size(); i++){
                System.out.println("---------------------------------------------------------------------------");
                System.out.printf(" %-4s |", productNumber);
                System.out.printf(" %-15s | ", searchProducts.get(i).getCategorie());
                System.out.printf("%-30s | ", searchProducts.get(i).getName());
                System.out.printf("%-5s | ", searchProducts.get(i).getPrice());
                System.out.printf("%-6s | ", searchProducts.get(i).getWeight());
                System.out.println();

                productNumber++;
            }
            System.out.println("---------------------------------------------------------------------------");
            System.out.println();
            Cart.addToCart(searchProducts);
        } else {
            ConsoleManeger.clearScreen();

            System.out.println();
            System.out.println("\3u001B[31mSorry, no items found! Please try again!");
            System.out.println();
        }
    }
    

    private static void sortByPriceDown(ArrayList<Products> items){
        Collections.sort(items, new Comparator<Products>() {
            public int compare(Products p1, Products p2) {
                return Double.compare(p1.price, p2.price);
            }
        });
    }

    
    private static void sortByPriceUp(ArrayList<Products> items){
        Collections.sort(items, new Comparator<Products>() {
            public int compare(Products p1, Products p2) {
                return Double.compare(p2.price, p1.price);
            }
        });
    }

    private static void sortByNameA(ArrayList<Products> items){
        Collections.sort(items, new Comparator<Products>() {
            public int compare(Products p1, Products p2) {
                return p1.getName().compareTo(p2.getName());
            }
        });
    }

    private static void sortByNameR(ArrayList<Products> items){
        Collections.sort(items, new Comparator<Products>() {
            public int compare(Products p1, Products p2) {
                return p2.getName().compareTo(p1.getName());
            }
        });
    }

}
