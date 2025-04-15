package lv.rvt;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    public static void programm() throws IOException{
        ConsoleManeger.clearScreen();
        ConsoleManeger.title();

        System.out.println();
        System.out.println("Welcome to freshly!");
        Boolean exit = false;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println();
            System.out.println("Select an option: ");
            System.out.println("[L] - Log in                 [S] - Sign up                 [X] - exit");
            String logIn = scanner.nextLine();
        
            if (logIn.equalsIgnoreCase("L")) {
                if (UserManager.login() == true){
                    break;
                }
            } else if (logIn.equalsIgnoreCase("S")) {
                if(UserManager.users()== true){
                    System.out.println();
                    System.out.println("Please log in: ");
                    if(UserManager.login() == true){
                       break;
                    }
                }
            } else if (logIn.equalsIgnoreCase("X")) {
                exit = true;
                break;
            } else {
                System.out.println("Invalid input.");
            }
        }
            if (exit == false){
                ConsoleManeger.clearScreen();
                ConsoleManeger.title();
                while (true) {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println();
                    System.out.println("Options: ");
                    System.out.println(" [A] - add to cart      [S] - see last purcheses     [C] - Cart     [O] - Checkout     [P] - Search      [X] - Exit");
        
                    String answer = scanner.nextLine();

                    ConsoleManeger.clearScreen();
                    ConsoleManeger.title();
                    if (answer.equalsIgnoreCase("A")){
                        ProductManager.productsByCategorie();
                    } else if (answer.equalsIgnoreCase("S")){
                        Cart.showAllPurchases();
                    } else if (answer.equalsIgnoreCase("P")){
                        ProductManager.getProductsBySearch();
                    } else if (answer.equalsIgnoreCase("C")){
                        Cart.cartSummary();
                    } else if (answer.equalsIgnoreCase("X")){
                        ArrayList<Cart> cartItems = Cart.getCurrentCart();
                        if (cartItems.isEmpty()){
                            ConsoleManeger.clearScreen();
                            ConsoleManeger.title();
                            System.out.println();
                            System.out.println("See you soon!");
                            System.out.println();
                            ConsoleManeger.title();
                            break;
                        } else {
                            System.out.println("Are you sure you want to leave before checkout? [Y/N]");
                            String checkoutAnswer = scanner.nextLine();
                            if (checkoutAnswer.equalsIgnoreCase("N")){
                                ConsoleManeger.clearScreen();
                                ConsoleManeger.title();
                                Cart.checkout();
                                System.out.println();
                                System.out.println("See you soon!");
                                System.out.println();
                                break;
                            } else {
                                ConsoleManeger.clearScreen();
                                ConsoleManeger.title();
                                System.out.println();
                                System.out.println("See you soon!");
                                System.out.println();
                                break;
                            }
                        }
                    } else if (answer.equalsIgnoreCase("O")){
                        Cart.checkout();
                    } else {
                        System.out.println();
                        System.out.println("Invalid input!");
                    }
                }
            } else {
                ConsoleManeger.clearScreen();
                ConsoleManeger.title();
                System.out.println();
                System.out.println("See you soon!");
                System.out.println();
            }
        }
    }
