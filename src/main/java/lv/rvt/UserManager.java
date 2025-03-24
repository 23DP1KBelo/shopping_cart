package lv.rvt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

import lv.rvt.tools.Helper;

public class UserManager extends  User {

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
        System.out.println("User added succsesfully!");
        
        Cart.makeCart();
    }

    private static boolean userVerification(String username, String email) throws IOException{
        getUserlist();
        
        for (User user : UserList) {
            if (user.getUsername().equalsIgnoreCase(username) && user.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
            return false;
    }
        
    public static void users() throws IOException{
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Please enter your username: ");
        String username = scanner.nextLine();
        User.setCurrentUsername(username);

        System.out.println("Please enter your email: ");
        String email = scanner.nextLine();
        
        if (userVerification(username, email)) {
            System.out.println("User exists in the system. Try a different one!");
            users();
        } else {
            addUser(username, email); 
        }
    }

    public static void login() throws IOException{
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Please enter your username: ");
        String username = scanner.nextLine();

        System.out.println("Please enter your email: ");
        String email = scanner.nextLine();

        User user = new User (username, email);

        if (userVerification(username, email)) {
            System.out.println("Welcome "+ username + " to your account!");
            User.setCurrentUsername(username);

        } else {
            System.out.println("User dosen't exists in the system. Try a different one!");
        }
    }
}
