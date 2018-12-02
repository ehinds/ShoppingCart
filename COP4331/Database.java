package COP4331;
//package net.sqlitetutorial;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Brownie
 */
public class Database implements java.io.Serializable 
{
    String url = "jdbc:sqlite:shoppingCart.db";

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
                + "	cost integer,\n"
                + "	quantity integer,\n"
                + "	image blob,\n"
                + "     category text\n"

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
        //ByteArrayOutputStream bos = new ByteArrayOutputStream();
        //bos.
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


            pstmt.setBytes(10, objectToByteArray(user.products));

            
            pstmt.executeUpdate();
            return true;
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public boolean deleteProduct(String title) 
    {
        String sql = "DELETE FROM products WHERE title = ?";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) 
        {
            // set the corresponding param
            pstmt.setString(1, title);
            // execute the delete statement
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
            String sql = "INSERT INTO products(title, summary, description, price, cost, quantity, image, category) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

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
                //pstmt.setObject(7, product.image);
                pstmt.setBytes(7, objectToByteArray(product.image));
                pstmt.setObject(8, product.category);
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
    
           public boolean updateProduct(Product product)
    {
        {
            String sql = "UPDATE products SET summary = ?, description = ?, price = ?, cost = ?, quantity = ?, image = ? WHERE title = ?";

            System.out.println("Updating product");
            try(
                    Connection connection = DriverManager.getConnection(url);
                    PreparedStatement pstmt = connection.prepareStatement(sql)
                ) 
            {
                pstmt.setObject(1, product.summary);
                pstmt.setObject(2, product.description);
                pstmt.setObject(3, product.price);
                pstmt.setObject(4, product.invoiceCost);
                pstmt.setObject(5, product.quantity);
                //pstmt.setObject(7, product.image);
                pstmt.setBytes(6, objectToByteArray(product.image));
                pstmt.setObject(7, product.title);
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
                
                user.email = (String) resultSet.getObject("email");
                user.phone = (String) resultSet.getObject("phone");
                user.address = (String) resultSet.getObject("address");
                user.DOB = (String) resultSet.getObject("DOB");
                
                user.creditCard = (String) resultSet.getObject("creditCard");
                user.bankAccount = (String) resultSet.getObject("bankAccount");

                user.products = (LinkedList<String>)byteArrayToObject(resultSet.getBinaryStream("products"));
                
                for(int i = 0; i < user.products.size(); i++)
                {
                    if(isNullProduct(user.products.get(i)))
                    {
                        
                    }
                    else
                    {
                        System.out.println("Found null user: " + user.products.remove(i));  
                    }
                }

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
    
    public Object byteArrayToObject(InputStream inputStream)
    {
        Object object = null;
        try(ObjectInputStream ois = new ObjectInputStream(inputStream))
        {
            object = ois.readObject();
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        } 
        catch (ClassNotFoundException ex) 
        {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return object;
    }
    
    public Product getProduct(String productTitle)
    {
        String sql = "SELECT * FROM products WHERE title = ?";
        Product product = new Product();
        
        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement preparedStatement  = connection.prepareStatement(sql))
        {
             preparedStatement.setString(1, productTitle);
             ResultSet resultSet  = preparedStatement.executeQuery();

            while (resultSet.next()) 
            {
                product.title = (String)resultSet.getObject("title");
                product.summary = (String)resultSet.getObject("summary");
                product.description = (String)resultSet.getObject("description");
                product.price = (int)resultSet.getObject("price");
                product.quantity = (int)resultSet.getObject("quantity");
                product.invoiceCost = (int)resultSet.getObject("cost");
                //product.image = (ImageIcon)resultSet.getObject("image");
                
                product.image = (ImageIcon)byteArrayToObject(resultSet.getBinaryStream("image"));
                product.category = (String)resultSet.getObject("category");

                return product;
            }
            return product;
        } catch (SQLException e) 
        {
            System.out.println(e.getMessage());
            System.out.println("Database error while attempting to retreive product.");
            return product;
        }
    }
    
     public void updatePassword(User user)
   {
       String sql = "UPDATE users SET password = ? WHERE username = ?";

       try
       (
           Connection conn = this.connect();
           PreparedStatement pstmt = conn.prepareStatement(sql)
       )
       {

           pstmt.setString(1, user.password);
            pstmt.setString(2, user.username);
           pstmt.executeUpdate();
            System.out.println("Updated database information for " + user.username);
       } 
       catch (SQLException e) 
       {
           System.out.println(e.getMessage());
       }
   }
    
    public byte[] objectToByteArray(Object object)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) 
        {
            oos.writeObject(object);
            oos.flush();
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
            return baos.toByteArray();
        }
            
        return baos.toByteArray();
    }

    public LinkedList<String> findAllCategories()
    {
        LinkedList<String> categories = new LinkedList<>();

        String sql = "SELECT * FROM products";
        
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql))
        {
            while (resultSet.next()) 
            {
                categories.add((String)resultSet.getObject("category"));
            }
            
            return categories;
        } catch (SQLException e) 
        {
            System.out.println(e.getMessage());
            return categories;
        }
    }
    
    public LinkedList<String> getProductsByCategory(String category)
    {
        String sql = "SELECT * FROM products WHERE category = \"" + category + "\"";
        LinkedList<String> products = new LinkedList<>();
                
                
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql))
        {
            while (resultSet.next()) 
            {
                products.add(((String)resultSet.getObject("title")));
                
            }
            return products;
        } catch (SQLException e) 
        {
            System.out.println(e.getMessage());
            System.out.println("EMPTY RESULT");
            return products;
        }
    }
    
    public String getRandomProduct(String category)
    {
        String sql = "SELECT * FROM products WHERE category = \"" + category + "\" ORDER BY RANDOM() LIMIT 1";
        String result = "";
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql))
        {
            while (resultSet.next()) 
            {
                if(result != "")
                {
                    System.out.println("RESULT ALREADY SET");
                }
                result = ((String)resultSet.getObject("title"));
                
            }
            return result;
        } catch (SQLException e) 
        {
            System.out.println(e.getMessage());
            System.out.println("EMPTY RESULT");
            return "";
        }
    }
    
    
    public void updateUserProductLink(User user)
    {
        String sql = "UPDATE users SET products = ? WHERE username = ?";
 
        try
        (
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        )
        {

            pstmt.setBytes(1, objectToByteArray(user.products));

            pstmt.setString(2, user.username);
            // update 
            pstmt.executeUpdate();
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
    }
    ///String sql = "INSERT INTO users(username, password, accountType, email, phone, address, DOB, creditCard, bankAccount, products) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public void updateUserPersonalInformation(User user)
    {
        String sql = "UPDATE users SET email = ?, phone = ?, address = ?, DOB = ? WHERE username = ?";
 
        try
        (
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        )
        {

            pstmt.setString(1, user.email);
            pstmt.setString(2, user.phone);
            pstmt.setString(3, user.address);
            pstmt.setString(4, user.DOB);
            pstmt.setString(5, user.username);
            pstmt.executeUpdate();
            System.out.println("Updated database information for " + user.username);
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
    }
    
    public void updateUserBillingInformation(User user)
    {
        String sql = "UPDATE users SET creditCard = ?, bankAccount = ? WHERE username = ?";
 
        try
        (
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        )
        {

            if(user.accountType)
            {
                user.creditCard = "";
            }
            else
            {
                user.bankAccount = "";
            }
            
            pstmt.setString(1, user.creditCard);
            pstmt.setString(2, user.bankAccount);
            pstmt.setString(3, user.username);
            pstmt.executeUpdate();
            System.out.println("Updated database information for " + user.username);
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
    }
    
    public boolean isNullProduct(String item)
    {
        if(getProduct(item).title == null)
        {
            return false;
        }
        else
        {
            return true;
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