package lv.rvt;

import java.io.IOException;


public class Main 
{
    public static void main( String[] args ) throws IOException
    {
        UserManager.users(); //Add users
    
        // ProductManager.productsByCategorie(); //get products by categorie

        // UserManager.login(); //Login 

        ProductManager.productsByCategorie(); 

    }
}
