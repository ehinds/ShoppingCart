package COP4331;

import java.util.LinkedList;
import java.util.Observable;

/**
 *
 * @author 
 */
public class SellerHomepageModel extends Observable
{
    private User user = new User();
    private LinkedList<Product> products = new LinkedList<>();
    
    private boolean registerEnabled = false;
    private String errorMessage = "";
    
    public SellerHomepageModel(User loadUser)
    {
        user = loadUser;
    }
    
    public void setUser(User updatedUser)
    {
        System.out.println("Model receiving user data:");
        System.out.println("Username: " + updatedUser.username);
        System.out.println("Products: " + updatedUser.products.toString());
        user = updatedUser;
        
        update();
    }
    
    public void setProducts(LinkedList<Product> updatedProducts)
    {
        products = updatedProducts;
        update();
    }
    
    public void addProduct(Product updatedProduct)
    {
        products.add(updatedProduct);
        update();
    }
    
    public void removeProduct(String title)
    {
        for (int i = 0; i < products.size(); i++) 
        {
            if(products.get(i).title.compareTo(title) == 0)
            {
                System.out.println("Found one: " + products.remove(i));
            }
        }
        update();
    }
    
    public LinkedList<Product> getProducts()
    {
        return products;
    }

    public User getUser()
    {
        return user;
    }
    
    public void setRegisterButtonEnable(boolean enabled)
    {
        registerEnabled = enabled;
        update();
    }
    
    public boolean getRegisterEnable()
    {
        return registerEnabled;
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
