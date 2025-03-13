package lv.rvt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

import lv.rvt.tools.Helper;

public class UserManager {

    private static final String DELIMITER = ", ";

    public void run() {
        
    }

    public static ArrayList<User> getUserlist() throws IOException{
        BufferedReader reader = Helper.getReader("users.csv");
        ArrayList<User> UserList = new ArrayList<>();
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

    public static void addUser(User user) throws IOException{
        BufferedWriter writer = Helper.getWriter("users.csv", StandardOpenOption.APPEND);
        writer.write(user.toCsvRow());
        writer.newLine(); 
        writer.close();
    }

}
