
package lv.rvt;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import lv.rvt.tools.Helper;

public class UserManager extends User {
   
    public UserManager(String username, String email) {
        super(username, email);
    }

    public static final String DELIMITER = ", ";
    public static ArrayList<User> UserList = new ArrayList<>();
  
    // get array list with users
    public static ArrayList<User> getUserlist() throws IOException{
        BufferedReader reader = Helper.getReader("users.csv");
        String line;

        reader.readLine();       
        while ((line = reader.readLine()) != null) {
            String[] parts = line.trim().split(DELIMITER);

            if (parts.length >= 2) {
                String username = parts[0];
                String email = parts[1];
    
                User user = new User(username, email);
                UserList.add(user);
            }
        }
        return UserList;
    }

    // make new file for user
    private static void addUser(String username, String email) throws IOException{
        Scanner scanner = new Scanner(System.in);

        User newUser = new User(username, email);

        BufferedWriter writer = Helper.getWriter("users.csv", StandardOpenOption.APPEND);
        writer.write(newUser.toCsvRow());
        writer.newLine(); 
        writer.close();
        
        Cart.makeCart();
    }

    // user exists deny Sign up
    private static boolean userVerificationSignIn(String username, String email) throws IOException{
        getUserlist();
        
        for (User user : UserList) {
            if (user.getUsername().equalsIgnoreCase(username) || user.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
            return false;
    }

    // user exists allow logIn
    private static boolean userVerificationLognIn(String username, String email) throws IOException{
        getUserlist();
        
        for (User user : UserList) {
            if (user.getUsername().equals(username) && user.getEmail().equals(email)) {
                return true;
            }
        }
            return false;
    }

    // pattern for email
    public static boolean patternMatches(String emailAddress) {
        String emailPattern = "^(.+)@(\\S+)$";
        return Pattern.compile(emailPattern)
        .matcher(emailAddress)
        .matches();
    }
    
    //Sign in
    public static Boolean users() throws IOException{
        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.println("Please fill out this form ( *fill out)");
        System.out.println("--------------------------------------------------------------------------------");

        System.out.println();
        System.out.println("Firstname: ");
        scanner.nextLine();
        System.out.println("--------------------------------------------------------------------------------");

        System.out.println();
        System.out.println("Surname: ");
        scanner.nextLine();
        System.out.println("--------------------------------------------------------------------------------");

        System.out.println();
        System.out.println("* Please enter your username (Username must have atleast 4 charecter *): ");
        String username = scanner.nextLine();
        System.out.println("--------------------------------------------------------------------------------");

        System.out.println();
        System.out.println("* Please enter your email *: ");
        String email = scanner.nextLine();
        System.out.println("--------------------------------------------------------------------------------");

        if(username.length() < 4){
                System.out.println("\u001B[31mInvalid username must be atleast 4 charecters!");
                return false;
        }
        if(patternMatches(email)){
            if(userVerificationSignIn(username, email)){
                System.out.println("\u001B[31mUser exists in the system. Try a different one!");
                return false;
            }else {
                User.setCurrentUsername(username);
                addUser(username, email);
                ConsoleManeger.clearScreen();
                ConsoleManeger.title();
                System.out.println();
                System.out.println("\u001B[92mSign in sucssesfull!");
                return true;
            }
        }else{
            System.out.println("\u001B[31mInvalid email!");
            return false;
        }
    }

    // logIn
    public static Boolean login() throws IOException{
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Please enter your username: ");
        String username = scanner.nextLine();

        System.out.println("Please enter your email: ");
        String email = scanner.nextLine();


        if (userVerificationLognIn(username, email)) {
            User.setCurrentUsername(username);
            return true;
        } else {
            System.out.println("\u001B[31mUser dosen't exists in the system. Try a different one!");
            return false;
        }
    }
}
