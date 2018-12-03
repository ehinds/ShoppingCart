/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package COP4331;

import java.util.Observable;
import javax.swing.ImageIcon;

/**
 *
 * @author 
 */
public class IndividualItemPopUpModel extends Observable{
     private User user = new User();
    public Product product = new Product();
    //public BufferedImage image = null;
    public ImageIcon imageIcon = null;
    
    private String errorMessage = "";
    
    public User getUser()
    {
        return user;
    }
    
     public Product getProduct()
    {
        return product;
    }
    
    public void setUser(User updatedUser)
    {
        user = updatedUser;
        update();
    }
    
        public void setProduct(Product product)
    {
        this.product = product;
        update();
    }
    
    public IndividualItemPopUpModel(User loadUser, Product product)
    {
        user = loadUser;
        this.product = product;
    }
    
    public void linkUserProduct(String title)
    {
        //user.addProduct(title);
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
