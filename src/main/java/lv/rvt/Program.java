package lv.rvt;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    public static void programm() throws IOException{
        ConsoleManeger.clearScreen();
        ConsoleManeger.title();

        System.out.println();
        System.out.println("\u001B[97mWelcome to freshly!");
        Boolean exit = false;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println();
            System.out.println("\u001B[97mSelect an option: ");
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
                System.out.println("\u001B[31mInvalid input.");
            }
        }
            if (exit == false){
                ConsoleManeger.clearScreen();
                ConsoleManeger.title();
                while (true) {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println();
                    System.out.println("\u001B[97mOptions: ");
                    System.out.println("[S] - see all products      [A] - see all purchases     [C] - cart      [O] - checkout      [X] - exit");
        
                    String answer = scanner.nextLine();
                    ConsoleManeger.clearScreen();
                    ConsoleManeger.title();
                    if (answer.equalsIgnoreCase("S")){
                        ProductManager.seeAllProducts();
                    } else if (answer.equalsIgnoreCase("A")){
                        Cart.showAllPurchases();
                    } else if (answer.equalsIgnoreCase("C")){
                        Cart.cartSummary();
                    } else if (answer.equalsIgnoreCase("X")){
                        ArrayList<Cart> cartItems = Cart.getCurrentCart();
                        if (cartItems.isEmpty()){
                            ConsoleManeger.clearScreen();
                            ConsoleManeger.title();
                            System.out.println();
                            System.out.println("\u001B[97mSee you soon!");
                            break;
                        } else {
                            System.out.println("\u001B[31mAre you sure you want to leave before checkout? [Y/N]");
                            String checkoutAnswer = scanner.nextLine();
                            if (checkoutAnswer.equalsIgnoreCase("N")){
                                ConsoleManeger.clearScreen();
                                ConsoleManeger.title();
                                Cart.checkout();
                                System.out.println();
                                System.out.println("\u001B[97mSee you soon!");
                                System.out.println();
                                break;
                            } else {
                                ConsoleManeger.clearScreen();
                                ConsoleManeger.title();
                                System.out.println();
                                System.out.println("\u001B[97mSee you soon!");
                                System.out.println();
                                break;
                            }
                        }
                    } else if (answer.equalsIgnoreCase("O")){
                        Cart.checkout();
                    } else {
                        System.out.println();
                        System.out.println("\u001B[31mInvalid input!");
                    }
                }
            } else {
                ConsoleManeger.clearScreen();
                ConsoleManeger.title();
                System.out.println();
                System.out.println("\u001B[97mSee you soon!");
                System.out.println();
            }
        }
    }
