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
    public Integer sessonId;
    public static final String DELIMITER = ";";

    public Cart(Integer sessonId,String categories, String name, Double price, String weight, Integer quantity) {
        super(categories, name, price, weight, quantity);
        this.sessonId = sessonId;
    }

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

    @Override
    public String toCsvRow() {
        return this.sessonId + ";" + this.categorie + ";" + this.name + ";" + this.price + ";" + this.weight + ";" + this.quantity;
    }

    public Integer getSessionId(){
        return this.sessonId;
    }

    // gets how many times user has shopped
    public static Integer getLastSessionId() throws IOException {
        String username = User.getCurrentUsername();
        String fileName = "data/user_shoppng_cart/" + username + ".csv";
        File file = new File(fileName);

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        Integer lastSessionId = 0;

        reader.readLine();
        while ((line = reader.readLine()) != null) {
            String[] parts = line.trim().split(DELIMITER);
            Integer sessionId = Integer.valueOf(parts[0].trim());
            lastSessionId = sessionId;
        }
        reader.close();

        if(lastSessionId == null){
            lastSessionId = 0;
        }
        return lastSessionId+1;
    }

    // gets file name for user
    public static String getCartFileName() {
        String username = User.getCurrentUsername();  
        return "data/user_shoppng_cart/" + username + ".csv";
    }

    // makes a new file for userCart
    public static void makeCart() throws IOException {
        cart.clear();
        String fileName = getCartFileName();
        BufferedWriter Newfile = new BufferedWriter(new FileWriter(fileName));
        Newfile.write("session number;categorie;name;price;weight;quantity");
        Newfile.newLine();
        Newfile.close();
    }

    // get all items that are purchesed
    public static ArrayList<Cart> getCart(String username) throws IOException {
        String fileName = "data/user_shoppng_cart/" + username + ".csv";
        File file = new File(fileName);

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        ArrayList<Cart> allChecks = new ArrayList<>();
    
        reader.readLine();
        while ((line = reader.readLine()) != null) {
            String[] parts = line.trim().split(DELIMITER);
            
            Integer sessionId = Integer.valueOf(parts[0].trim());
            String categorie = parts[1].trim();
            String name = parts[2].trim();
            Double price = Double.valueOf(parts[3].trim());
            String weight = parts[4].trim();
            Integer quantity = Integer.valueOf(parts[5].trim());
    
            Cart cartItem = new Cart(sessionId,categorie, name, price, weight, quantity);
            allChecks.add(cartItem);
        }
        reader.close();
        return allChecks;
    }

    // returns arraylist with products of this session
    public static ArrayList<Cart> getCurrentCart() throws IOException{
        return cart;
    } 

    // save Cart to user csv file
    public static void saveCartToFile() throws IOException {
        String username = User.getCurrentUsername();
        String fileName = "data/user_shoppng_cart/" + username + ".csv";
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
        for (Cart cartItem : cart) {
            writer.write(cartItem.toCsvRow());
            writer.newLine();
        }
        writer.close();
    }

    // add items to cart
    public static void addToCart(ArrayList<Products> array) throws IOException {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Please enter the number of the product you want to add to your cart (1, 2, etc.): ");
        Integer productNumber = Integer.valueOf(scanner.nextLine());
        if (productNumber < 1 || productNumber > array.size()) {
            System.out.println("Invalid product selection.");
            return;
        }
    
        Products selectedProduct = array.get(productNumber - 1);
        
        System.out.println("Enter the quantity you want to add to the cart: ");
        Integer quantity = Integer.valueOf(scanner.nextLine());
        if(quantity % 2 != 0){
            System.out.println("Invalid input.");
            return;
        }
    
        Integer sessionId = Cart.getLastSessionId();
        Cart newCartItem = new Cart(sessionId, selectedProduct.getCategorie(), selectedProduct.getName(), selectedProduct.getPrice() * quantity, selectedProduct.getWeight(), quantity);
        Cart.cart.add(newCartItem);
    
        System.out.println("Product/s added to your cart!");

        ConsoleManeger.clearScreen();
        ConsoleManeger.title();
    }

    // remove items from cart
    public static void removeFromCart() throws IOException{
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Please enter the number of the product you want to remove (1, 2, etc.): ");
        Integer productNumber = Integer.valueOf(scanner.nextLine());

        if (productNumber < 1 || productNumber > cart.size()) {
            System.out.println("Invalid product selection.");
            return;
        }
        Cart.cart.remove(productNumber -1);
    }

    // see all purcheses
    public static void showAllPurchases() throws IOException {
        ConsoleManeger.clearScreen();
        ConsoleManeger.title();

        String username = User.getCurrentUsername();
        ArrayList<Cart> cartItems = Cart.getCart(username);
    
        if (cartItems.isEmpty()) {
            System.out.println("\u001B[91mNo purchases found for user: " + username);
            return;
        }
    
        System.out.println("\u001B[37m-------------------------------------------------------------------------------------");
        System.out.printf(" %-2s |", "No.");
        System.out.printf(" %-15s |", "Categorie");
        System.out.printf(" %-30s |", "Name");
        System.out.printf(" %-5s |", "Price");
        System.out.printf(" %-6s |", "Weight");
        System.out.printf(" %-5s |", "Quantity");
        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------");
    
        int productNumber = 1;
        int currentSessionId = -1;  
    
        for (Cart cartItem : cartItems) {
            if (cartItem.getSessionId() != currentSessionId) {
                if (currentSessionId != -1) {
                    System.out.println("\u001B[37m\n-------------------------------------------------------------------------------------");
                }
                System.out.println();
                System.out.println("\u001B[92mSession " + cartItem.getSessionId() + " Purchases:");
                System.out.println("-------------------------------------------------------------------------------------");
                productNumber = 1; 
                currentSessionId = cartItem.getSessionId();
            }
    
            System.out.println("\u001B[37m-------------------------------------------------------------------------------------");
            System.out.printf(" %-3s |","No.");
            System.out.printf(" %-15s |", cartItem.getCategorie());
            System.out.printf(" %-30s |", cartItem.getName());
            System.out.printf(" %-5.2f |", cartItem.getPrice());
            System.out.printf(" %-6s |", cartItem.getWeight());
            System.out.printf(" %-8s |", cartItem.getQuantity());
            System.out.println();
    
            productNumber++;
        }
    
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println();
    }

    // see session added products
    public static void cartSummary() throws IOException{
        ConsoleManeger.clearScreen();
        ConsoleManeger.title();

        Scanner scanner = new Scanner(System.in);

        if (cart.isEmpty()) {
            System.out.println("\u001B[31mNo puroducts in your cart!");
            return;
        }

        Integer lastSessionId = cart.get(cart.size() - 1).getSessionId();

        System.out.println();
        System.out.println("\u001B[37m-------------------------------------------------------------------------------------");
        System.out.printf(" %-2s |", "No.");
        System.out.printf(" %-15s |", "Categorie");
        System.out.printf(" %-30s |", "Name");
        System.out.printf(" %-5s |", "Price");
        System.out.printf(" %-6s |", "Weight");
        System.out.printf(" %-5s |", "Quantity");
        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------");

        Integer productNumber = 1;
        Double sum =  0.0;
        for (Cart cartItem : cart) {
            if (cartItem.getSessionId().equals(lastSessionId)) {
                System.out.println("-------------------------------------------------------------------------------------");
                System.out.printf(" %-3s |", productNumber);
                System.out.printf(" %-15s |", cartItem.getCategorie());
                System.out.printf(" %-30s |", cartItem.getName());
                System.out.printf(" %-5.2f |", cartItem.getPrice());
                System.out.printf(" %-6s |", cartItem.getWeight());
                System.out.printf(" %-8s |", cartItem.getQuantity());
                System.out.println();

                sum += cartItem.getPrice();

                productNumber++;
            }
        }

        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("\u001B[32mTotal: " + sum);

        System.out.println("\u001B[37m[R] - Remove product            [C] - checkout            [P] - discount code             [ ] - back ");
        String remove = scanner.nextLine();

        if(remove.equalsIgnoreCase("R")){
            removeFromCart();
            cartSummary();
            System.out.println("\u001B[32mProduct removed succesfuly!");
            System.out.println();
        }else if(remove.equalsIgnoreCase("C")){
            checkout();
        } else if(remove.equalsIgnoreCase("P")){
            System.out.println("\u001B[37mEnter promocode: ");
            String answer = scanner.nextLine();
            if(answer.equals("PROMO")){
                System.out.printf("\u001B[32mTotal with discount:  %-5.2f", (sum - promocode(sum, 15)));
            } else {
                System.out.println("\u001B[31mWrong promo code!");
            }
        }
    }

    // save items to cart
    public static void checkout() throws IOException{
        String username = User.getCurrentUsername();
    
        if (cart.isEmpty()) {
            System.out.println();
            System.out.println("\u001B[31mNo items found for: " + username);
        }else{
            System.out.println("\u001B[32mCheckout sucsessful!");
            saveCartToFile();
            cart.clear();
        }
    }

    public static double promocode(double total, int discount) {
        return (total * discount) / 100;
    }

}
