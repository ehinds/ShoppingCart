package COP4331;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Observable;
import javax.swing.ImageIcon;

/**
 *
 * @author 
 */
public class CustomerHomepageModel extends Observable
{
    private User user = new User();
    private LinkedList<String> categories = new LinkedList<>();
    private LinkedList<Product> randomProducts = new LinkedList<>();
    
    private boolean registerEnabled = false;
    private String errorMessage = "";
    
    public CustomerHomepageModel(User loadUser)
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

    public LinkedList<String> getCategories()
    {
        System.out.println("(model)getCategories()");
        return new LinkedList<>(new HashSet<>(categories));
    }
    
    public void setCategories(LinkedList<String> updatedCategories)
    {
        categories = updatedCategories;
        update();
    }   
    
    public ImageIcon getCategoryImage(String category)
    {
        System.out.println("getCategoryImage(" + category + ")");
        System.out.println("randomProducts.size() == " + randomProducts.size());
        
        ImageIcon image = new ImageIcon();
        for(int i = 0; i < randomProducts.size(); i++)
        {
            System.out.println("randomProducts.get(" + randomProducts.get(i).category + ")");
            
            if(randomProducts.get(i).category.compareTo(category) == 0)
            {
                System.out.println("image == " + randomProducts.get(i).title + ".getImage(150, 150);");
                image = randomProducts.get(i).getImage(150, 150);
            }
        }
        return image;
    }
    
    public void setRandomProducts(LinkedList<Product> products)
    {
        randomProducts = products;
        update();
    }   

    public int countCategory(String category)
    {
        System.out.println("countCategory(String category)" + categories);
        System.out.println("categories.size()" + categories.size());
        int count = 0;
        
        for(int i = 0; i < categories.size(); i++)
        {
            if(categories.get(i).compareTo(category) == 0)
            {
                System.out.println("count++;");
                count++;
            }
        }
        System.out.println("countCategory(complete)");
        return count;
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
