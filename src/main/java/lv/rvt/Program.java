package lv.rvt;

import java.io.IOException;
import java.util.Scanner;

public class Program {
    public static void programm() throws IOException{

        ConsoleManeger.title();
        System.out.println();
        System.out.println("Welcome to freshly!");
        Boolean exit = false;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Log in [L] if you don't have an account, Sign in [S] to exit [X]");
            String logIn = scanner.nextLine();
        
            if (logIn.equalsIgnoreCase("L")) {
                if(UserManager.login() == true){
                    break;
                }
            } else if (logIn.equalsIgnoreCase("S")) {
                if(UserManager.users()== true){
                    break;
                }
            } else if(logIn.equalsIgnoreCase("X")) {
                exit = true;
                break;
            }else{
                System.out.println("Invalid input. Please enter 'L' for log in or 'S' for sign in.");
            }}

            if(exit == false){
                while (true) {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Select from the list: ");
                    System.out.println("""
                        Add to cart - A
                        Last purcheses - S
                        Cart - C
                        Exit - X
                    """);
        
                    String answer = scanner.nextLine();
        
                    if(answer.equalsIgnoreCase("A")){
                        ProductManager.productsByCategorie();
                    }else if(answer.equalsIgnoreCase("S")){
                        Cart.showAllPurchases();
                    }else if(answer.equalsIgnoreCase("C")){
                        Cart.cartSum();
                    }else if(answer.equalsIgnoreCase("x")){
                        System.out.println();
                        System.out.println("See you soon!");
                        System.out.println();
                        ConsoleManeger.title();
                        break;
                    }else{
                        System.out.println("Invalid input! Select from the list: ");
                        System.out.println("""
                            Add to cart - A
                            See last purcheses - S
                            See sum of products - W
                            Exit - X
                        """);
                    }
                }
            }else{
                System.out.println();
                System.out.println("See you soon!");
                System.out.println();
                ConsoleManeger.title();  
            }
        }
    }
