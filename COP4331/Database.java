package COP4331;
//package net.sqlitetutorial;
import java.util.List;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.LinkedList;

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
    
    public Connection connect() 
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
        }
        return connection;
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
                + "	bankAccount text,\n"
                + "	products blob\n"
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

    public void createNewProductsTable() 
    {
        String sql = "CREATE TABLE IF NOT EXISTS products (\n"
                + "	title text PRIMARY KEY,\n"
                + "	summary text,\n"
                
                + "	description text,\n"
                + "	price integer,\n"
                + "	cost text,\n"
                + "	quantity integer,\n"
                + "	image blob\n"

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

    public boolean insertUser(User user) 
    {
        String sql = "INSERT INTO users(username, password, accountType, email, phone, address, DOB, creditCard, bankAccount, products) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        System.out.println("Inserting user");
        try(
                Connection connection = DriverManager.getConnection(url);
                PreparedStatement pstmt = connection.prepareStatement(sql)
            ) 
        {
            
            pstmt.setObject(1, user.username);
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
            return true;
        } catch (SQLException e) 
        {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public boolean addProduct(Product product)
    {
        {
            String sql = "INSERT INTO products(title, summary, description, price, cost, quantity, image) VALUES(?, ?, ?, ?, ?, ?, ?)";

            System.out.println("Inserting product");
            try(
                    Connection connection = DriverManager.getConnection(url);
                    PreparedStatement pstmt = connection.prepareStatement(sql)
                ) 
            {

                pstmt.setObject(1, product.title);
                pstmt.setObject(2, product.summary);
                pstmt.setObject(3, product.description);
                pstmt.setObject(4, product.price);
                pstmt.setObject(5, product.invoiceCost);
                pstmt.setObject(6, product.quantity);
                pstmt.setObject(7, product.image);

                //pstmt.setDouble(2, capacity);
                pstmt.executeUpdate();
                return true;
            } catch (SQLException e) 
            {
                System.out.println(e.getMessage());
                return false;
            }
        }
    }
    
    //  'statement' doesn't allow parameters, use 'prepareStatement'
    public void selectAllUsers()
    {
        String sql = "SELECT * FROM users";
        
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql))
        {
            while (resultSet.next()) 
            {
                System.out.println(resultSet.getObject("username"));
                System.out.println(resultSet.getObject("products"));
            }
        } catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
    }
    
    public User getUserData(User user)
    {
        System.out.println("Getting all data for " + user.username);
        String sql = "SELECT * FROM users WHERE username = ?";
        
        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement preparedStatement  = connection.prepareStatement(sql))
        {
             preparedStatement.setString(1, user.username);
             ResultSet resultSet  = preparedStatement.executeQuery();

            while (resultSet.next()) 
            {
                System.out.println(resultSet.getObject("username"));
                user.username = (String) resultSet.getObject("username");
                user.password = (String) resultSet.getObject("password");
                
                if((int)resultSet.getObject("accountType") == 1)
                {
                    user.accountType = true;
                }
                else
                {
                    user.accountType = false;
                }
                //user.accountType = (boolean) resultSet.getObject("accountType");
                
                user.email = (String) resultSet.getObject("email");
                user.phone = (String) resultSet.getObject("phone");
                user.address = (String) resultSet.getObject("address");
                user.DOB = (String) resultSet.getObject("DOB");
                
                user.creditCard = (String) resultSet.getObject("creditCard");
                user.bankAccount = (String) resultSet.getObject("bankAccount");
                
                //TODO Fix reading this back
                System.out.println(resultSet.getObject("products"));

                String newString = ((String)(resultSet.getObject("products"))).replace("[", "").replace("]", "").replace(" ", "");
                String[] output = {};
                if(!newString.equalsIgnoreCase(""))
                {
                    output = newString.split(",");
                }
                else
                {

                }
                //String[] output = ((String)resultSet.getObject("products")).replace("[", "").replace("]", "").replace(" ", "").split(",");
                //LinkedList<String> temp = new LinkedList<> (Arrays.asList(output));
                System.out.println(user.products);
                //System.out.println((new LinkedList<> (Arrays.asList(output))).size());
                int size = output.length;
                System.out.println("Size:" + size);
                System.out.println("Products Size:" + user.products.size());
                user.products = new LinkedList<>();

                //output.
                for(int i = 0; i < size; i++)
                {
                    System.out.println("Adding: " + output[i]);
                    user.products.add(output[i]);
                }
                System.out.println(user.products);
                System.out.println("Products Size:" + user.products.size());
                //user.products = (LinkedList) (Arrays.asList(output));
                
                //des = resultSet.getObject("products");
                //user.products = (LinkedList) output;
                
                return user;
            }
            return user;
        } catch (SQLException e) 
        {
            System.out.println("UserExists() error.");
            System.out.println(e.getMessage());
            return user;
        }
    }
    
    public void updateUserProducts(User user)
    {
        String sql = "UPDATE users SET products = ? WHERE username = ?";
 
        try
        (
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        )
        {
            pstmt.setObject(1, user.products);
            pstmt.setString(2, user.username);
            // update 
            pstmt.executeUpdate();
        } 
        catch (SQLException e) 
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