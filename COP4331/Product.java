package COP4331;

import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
    
    BufferedImage image = null;
    
    public Product()
    {
        
    }
}
