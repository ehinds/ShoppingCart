package COP4331;

import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author 
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
    

    LinkedList<CartItem> cartItems = new LinkedList<>();
    LinkedList<String> products = new LinkedList<>();
    
    public User() 
    {
        
    }
    
    public void addProduct(String title)
    {
        //  TODO: Prevent
        System.out.println("User products: " + products);
         System.out.println("User: Adding title");
         System.out.println(title);
         products.add(title);
         System.out.println("User: Title done");
         System.out.println("User products: " + products);
    }
    
    public void addItemToCart(String name, int count)
    {
        CartItem cartItem = new CartItem();
        cartItem.title = name;
        cartItem.count = count;
        cartItems.add(cartItem);
    }
    
    public class CartItem
    {
        String title;
        int count;
    }

    public String serializeProducts()
    {
         //System.out.println(resultSet.getObject(user.products.toString()));
        return "";
    }
}