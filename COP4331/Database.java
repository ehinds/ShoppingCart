package COP4331;
//package net.sqlitetutorial;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Brownie
 */
public class Database 
{
    String url = "jdbc:sqlite:C:/sqlite/db/shoppingCart.db";
     /**
     * Connect to a sample database
     */
    public void connect() 
    {
        Connection conn = null;
        try 
        {
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        } finally 
        {
            try 
            {
                if (conn != null) 
                {
                    conn.close();
                }
            } catch (SQLException ex) 
            {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public void createNewTable() 
    {
        // SQL statement for creating a new table
        
        String sql = "CREATE TABLE IF NOT EXISTS users (\n"
                + "	username text PRIMARY KEY,\n"
                + "	password text NOT NULL,\n"
                
                + "	accountType boolean,\n"
                + "	email text,\n"
                + "	phone text,\n"
                + "	address text,\n"
                + "	DOB text,\n"
                + "	creditCard text,\n"
                + "	bankAccount,\n"
                + "	products text\n"
                + ");";

        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) 
        {
            // create a new table
            stmt.execute(sql);
            System.out.println("Made table");
        } catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
    }

    public void insertUser() 
    {
        User user = new User();
        user.username = "Michael";
        user.password = "Password";
        user.accountType = true;
        user.email = "Something@email.com";
        user.phone = "12345";
        user.address = "myAddress";
        user.DOB = "56/45/34";
        user.creditCard = "1233345";
        user.bankAccount = "34534545";
        user.products = null;
        
        String sql = "INSERT INTO users(username, password, accountType, email, phone, address, DOB, creditCard, bankAccount, products) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
 
        System.out.println("Inserting user");
        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(sql)) 
        {
            
            pstmt.setObject(1, "Michael" + Double.toString(Math.random()));
            pstmt.setObject(2, user.password);
            pstmt.setObject(3, user.accountType);
            pstmt.setObject(4, user.email);
            pstmt.setObject(5, user.phone);
            pstmt.setObject(6, user.address);
            pstmt.setObject(7, user.DOB);
            pstmt.setObject(8, user.creditCard);
            pstmt.setObject(9, user.bankAccount);
            pstmt.setObject(10, user.products);
            
            //pstmt.setDouble(2, capacity);
            pstmt.executeUpdate();
        } catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
    }
    
    public void selectAll()
    {
        String sql = "SELECT * FROM users";
        
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql))
        {
            
            // loop through the result set
            while (rs.next()) 
            {
                System.out.println(rs.getObject("username"));
            }
        } catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
    }
}