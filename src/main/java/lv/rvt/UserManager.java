
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

    private static void addUser(String username, String email) throws IOException{
        Scanner scanner = new Scanner(System.in);

        User newUser = new User(username, email);

        BufferedWriter writer = Helper.getWriter("users.csv", StandardOpenOption.APPEND);
        writer.write(newUser.toCsvRow());
        writer.newLine(); 
        writer.close();
        
        Cart.makeCart();
    }

    private static boolean userVerificationSignIn(String username, String email) throws IOException{
        getUserlist();
        
        for (User user : UserList) {
            if (user.getUsername().equalsIgnoreCase(username) || user.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
            return false;
    }

    private static boolean userVerificationLognIn(String username, String email) throws IOException{
        getUserlist();
        
        for (User user : UserList) {
            if (user.getUsername().equalsIgnoreCase(username) && user.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
            return false;
    }


    public static boolean patternMatches(String emailAddress) {
        String emailPattern = "^(.+)@(\\S+)$";
        return Pattern.compile(emailPattern)
        .matcher(emailAddress)
        .matches();
    }
        
    public static Boolean users() throws IOException{
        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.println("Please fill out this form ( * need to fill out)");
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

        if(patternMatches(email)){
            if(  userVerificationSignIn(username, email)){
                System.out.println("User exists in the system. Try a different one!");
                return false;
            } else if(username.length() < 3){
                System.out.println("Invalid username must be atleast 4 charecters!");
                return false;
            } else {
                ConsoleManeger.clearScreen();
                ConsoleManeger.title();
                System.out.println();
                System.out.println("Sign in sucssesfull!");
                return true;
            }
        }else{
            System.out.println("Invalid email!");
            return false;
        }
    }

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
            System.out.println("User dosen't exists in the system. Try a different one!");
            return false;
        }
    }
}
