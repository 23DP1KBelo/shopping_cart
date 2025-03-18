package lv.rvt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

import lv.rvt.tools.Helper;

public class UserManager {

    private static final String DELIMITER = ", ";
    public static ArrayList<User> UserList = new ArrayList<>();
    public void run() {
        
    }

    public static ArrayList<User> getUserlist() throws IOException{
        BufferedReader reader = Helper.getReader("users.csv");
        String line;

        reader.readLine();       
        while ((line = reader.readLine()) != null) {
            String[] parts = line.trim().split(UserManager.DELIMITER);

            String username = parts[0];
            String email = parts[1];

            User user = new User(username, email);
            UserList.add(user);
        }
        return UserList;
    }

    public static void addUser() throws IOException{
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your username again: ");
        String username = scanner.nextLine();

        System.out.println("Please enter your email again: ");
        String email = scanner.nextLine();

        User newUser = new User(username, email);

        BufferedWriter writer = Helper.getWriter("users.csv", StandardOpenOption.APPEND);
        writer.write(newUser.toCsvRow());
        writer.newLine(); 
        writer.close();
    }

    public static boolean userVerification(String username, String email) throws IOException{
                getUserlist();
        
                for (User user : UserList) {
                    if (user.getUsername().equalsIgnoreCase(username) || user.getEmail().equalsIgnoreCase(email)) {
                        return true;
                    }
                }
                return false;
            }
        
    public static void users() throws IOException{
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Please enter your username: ");
        String username = scanner.nextLine();
        
        System.out.println("Please enter your email: ");
        String email = scanner.nextLine();
        
        if (userVerification(username, email)) {
            System.out.println("User exists in the system. Try a different one!");
            users();
        } else {
            addUser(); 
        }
    }
}
