package COP4331;

import java.awt.image.BufferedImage;
import java.util.Observable;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Brownie
 */
public class AddNewProductPopUpModel extends Observable
{
    public User user = new User();
    public Product product = new Product();
    //public BufferedImage image = null;
    public ImageIcon imageIcon = null;
    
    private String errorMessage = "";
    
    public AddNewProductPopUpModel(User loadUser)
    {
        user = loadUser;
    }
    
    public void addProduct(String title)
    {
        user.addProduct(title);
    }
    
    public void setErrorMessage(String message)
    {
        errorMessage = message;
        update();
    }
    
    public void setImage(ImageIcon icon)
    {
        System.out.println("Setting model image");
        imageIcon = icon;
        update();
    }
    
    public ImageIcon getImage()
    {
        return imageIcon;
    }
    
    public String getErrorMessage()
    {
        return errorMessage;
    }
    
    public void update()
    {
        setChanged();
        notifyObservers();
    }
}
