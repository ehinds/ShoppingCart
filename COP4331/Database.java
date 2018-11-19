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
    String url = "jdbc:sqlite:shoppingCart.db";
    //String url = "jdbc:sqlite:C:/sqlite/db/shoppingCart.db";
     /**
     * Connect to a sample database
     */
    public Database() 
    {
        
    }
    
    public void connect() 
    {
        Connection connection = null;
        try 
        {
            // create a connection to the database
            connection = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        } finally 
        {
            try 
            {
                if (connection != null) 
                {
                    connection.close();
                }
            } catch (SQLException ex) 
            {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public void createNewUsersTable() 
    {
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

        try (Connection connection = DriverManager.getConnection(url);
                Statement statement = connection.createStatement()) 
        {
            statement.execute(sql);
            //System.out.println("Made table");
        } catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
    }

    public void insertUser(User user) 
    {
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
        try(
                Connection connection = DriverManager.getConnection(url);
                PreparedStatement pstmt = connection.prepareStatement(sql)) 
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
    
    //  'statement' doesn't allow parameters, use 'prepareStatement'
    public void selectAll()
    {
        String sql = "SELECT * FROM users";
        
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql))
        {
            while (resultSet.next()) 
            {
                System.out.println(resultSet.getObject("username"));
            }
        } catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
    }
    
    public boolean userExists(String username)
    {
        System.out.println("Checking if " + username + " exists");
        String sql = "SELECT username FROM users WHERE username = ?";
        
        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement preparedStatement  = connection.prepareStatement(sql))
        {
             preparedStatement.setString(1, username);
             ResultSet resultSet  = preparedStatement.executeQuery();

            while (resultSet.next()) 
            {
                System.out.println(resultSet.getObject("username"));
                return true;
            }
            return false;
        } catch (SQLException e) 
        {
            System.out.println("UserExists() error.");
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public boolean verifyPassword(String username, String password)
    {
        String sql = "SELECT username FROM users WHERE username = ? and password = ?";
        
        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement preparedStatement  = connection.prepareStatement(sql))
        {
             preparedStatement.setString(1, username);
             preparedStatement.setString(2, password);
             ResultSet resultSet  = preparedStatement.executeQuery();

            while (resultSet.next()) 
            {
                System.out.println(resultSet.getObject("username"));
                return true;
            }
            return false;
        } catch (SQLException e) 
        {
            System.out.println(e.getMessage());
            return false;
        }
    }
}