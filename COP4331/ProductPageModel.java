package COP4331;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Observable;
import javax.swing.ImageIcon;

/**
 *
 * @author 
 */
public class ProductPageModel extends Observable
{
    private User user = new User();
    private String category;
    private int numberOfItems;
    private LinkedList<Product> products = new LinkedList<>();
    
    private String errorMessage = "";
    
    public ProductPageModel(User loadUser, String loadCategory)
    {
        user = loadUser;
        category = loadCategory;
    }
    
    public String getCategory()
    {
        return category;
    }
    
    public void setNumberOfItems(int count)
    {
        numberOfItems = count;
    }
    
    public int getNumberOfItems()
    {
        return numberOfItems;
    }
    
    public void setUser(User updatedUser)
    {
        System.out.println("Model receiving user data:");
        System.out.println("Username: " + updatedUser.username);
        System.out.println("Products: " + updatedUser.products.toString());
        user = updatedUser;
        
        update();
    }

    public User getUser()
    {
        return user;
    }
    
    public LinkedList<Product> getProducts()
    {
        return products;
    }
    
    public void setProducts(LinkedList<Product> newProducts)
    {
        products = newProducts;
        update();
    }
    
    public void setErrorMessage(String message)
    {
        errorMessage = message;
        update();
    }
    
    public String getErrorMessage()
    {
        return errorMessage;
    }
    
    public String getUsername()
    {
        return errorMessage;
    }
    
    public void update()
    {
        setChanged();
        notifyObservers();
    }
}
