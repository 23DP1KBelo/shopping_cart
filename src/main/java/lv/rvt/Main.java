package lv.rvt;

import java.util.Scanner;

public class Main 
{
    @SuppressWarnings("resource")
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your username: ");
        String username = scanner.nextLine();

        System.out.println("Enter your email: ");
        String email = scanner.nextLine();

        user person = new user(username, email);
        person.addUser();
    }
}
