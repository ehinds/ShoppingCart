package COP4331;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.ImageIcon;

/**
 *
 * @author Brownie
 */
public class Product 
{
    public String title;
    String summary;
    String description;
    int price;
    int quantity;
    int invoiceCost;
    
    ImageIcon image = null;
    
    public Product()
    {
        
    }
    
    public ImageIcon getImage(int x, int y)
    {
        Image imageIcon = image.getImage(); // transform it 
        Image newimg = imageIcon.getScaledInstance(x, y,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        return (new ImageIcon(newimg));  // transform it back
    }
}
