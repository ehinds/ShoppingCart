package COP4331;

import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Brownie
 */
public class User 
{
    String username;
    String password;
    
    boolean accountType;
    
    String email;
    String phone;
    String address;
    String DOB;
    String creditCard;
    String bankAccount;
    
    public LinkedList products = new LinkedList();
    
    public User() 
    {
        
    }
    
    public void addProduct(String title)
    {
         System.out.println("User: Adding title");
         System.out.println(title);
        products.add(title);
         System.out.println("User: Title done");
    }
}