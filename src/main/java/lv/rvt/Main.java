package lv.rvt;

import java.io.IOException;
import java.util.Scanner;


public class Main 
{
    public static void main( String[] args ) throws IOException
    {
        Scanner scanner = new Scanner(System.in);

        User user = new User(scanner.nextLine(), scanner.nextLine());
        UserManager.addUser(user);
    }
}
