/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package COP4331;

import COP4331.User.CartItem;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JComboBox;

/**
 *
 * @author 
 */
public class ProductPageController implements ActionListener, KeyListener, MouseListener
{
    private final Database database = new Database();
    
    private final ProductPageModel model;
    private final ProductPageView view;

    public ProductPageController(ProductPageModel productPageModel, ProductPageView productPageView)
    {
        System.out.println("ProductPageViewController constructor being called");
        
        model = productPageModel;
        view = productPageView;
    }

    public void displayProductPage()
    {
        model.setProducts(getProductsByCategory(model.getCategory()));
        
        view.setLayout();
        view.update(model, null);
        view.addObservers();
        addListeners();
        
        view.setVisible(true);
    }
    
    public LinkedList<Product> getProductsByCategory(String category)
    {
        LinkedList<Product> categoryProducts = new LinkedList<>();
        
        LinkedList<String> productStrings =  database.getProductsByCategory(category);
        for(int i = 0; i < productStrings.size(); i++)
        {
            categoryProducts.add(database.getProduct(productStrings.get(i)));
        }
        model.setNumberOfItems(productStrings.size());
        
        return categoryProducts;
    }
    
    
    public LinkedList<String> getCategories()
    {
        return database.findAllCategories();
    }
    
    public LinkedList<Product> getRandomProducts()
    {
        LinkedList<String> allCats = database.findAllCategories();
        allCats = new LinkedList<>(new HashSet<>(allCats));
        
        LinkedList<Product> products = new LinkedList<>();
        for(int i = 0; i < allCats.size(); i++)
        {
            String productString = database.getRandomProduct(allCats.get(i));
            System.out.println("productString" + productString);
            products.add(database.getProduct(productString));
            System.out.println("products.add(database.getProduct(productString)" + database.getProduct(productString).title);
            System.out.println("products.add(database.getProduct(productString)" + database.getProduct(productString).category);
        }
        return products;
    }
    
    public void popupClosed(User updatedUser)
    {
        System.out.println("Popup closed! Got new user data");
        System.out.println("Username: " + updatedUser.username);
        System.out.println("Products: " + updatedUser.products.toString());
        model.setUser(updatedUser);
        //model.setProducts( getUserProducts());
        //view.addRemoveListingListener(this);
        displayProductPage();
    }
    
    void addListeners()
    {
        view.addLogoutButtonListener(this);
        view.addViewAccountButtonListener(this);
        view.addViewCustomerHomepageButtonListener(this);
        view.addShoppingCartEventListener(this);
        view.addAddToCartListener(this);
        view.addSortEventListener(this);
        view.addViewProductListener(this);
        view.addUserInputListener(this); 
    }

   public void displayLoginPage()
    {
        view.removeAll();
        view.dispose();
        
        LoginPageModel loginPageModel = new LoginPageModel();
        LoginPageView loginPageView = new LoginPageView(loginPageModel);
        LoginPageController loginPageController = new LoginPageController(loginPageModel, loginPageView);
        loginPageController.displayLoginPage();
    }
   
    public void displayAccountPage()
    {
        view.removeAll();
        view.dispose();
        
        AccountPageModel accountPageModel = new AccountPageModel(model.getUser());
        AccountPageView accountPageView = new AccountPageView(accountPageModel);
        AccountPageController accountPageController = new AccountPageController(accountPageModel, accountPageView);
        accountPageController.displayAccountPage();
    }
    
        public void displayCustomerHomepage()
    {
        view.removeAll();
        view.dispose();
        
        CustomerHomepageModel customerHomepageModel = new CustomerHomepageModel(model.getUser());
        CustomerHomepageView customerHomepageView = new CustomerHomepageView(customerHomepageModel);
       CustomerHomepageController customerHomepageController = new CustomerHomepageController(customerHomepageModel, customerHomepageView);
        customerHomepageController.displayCustomerHomepage();
    }
    
    @Override
    public void actionPerformed(ActionEvent event) 
    {
        String command = event.getActionCommand();
        Object source = event.getSource();
        
        System.out.println(command + "Action Performed on " + source);

        if (source instanceof javax.swing.JComboBox) 
        {
            command = (String) ((JComboBox) source).getSelectedItem();
        }
        
        switch (command) 
        {
            case "Logout":
                displayLoginPage();
                break;
            case "View Account":
                displayAccountPage();
                break;
            case "View Homepage":
                displayCustomerHomepage();
                break;
            case "Add to Cart":
                System.out.println("Add to cart");
                JButton button;
                button = (JButton)source;
                //productSummaryListItem.get(i).getAddToCartButton().addActionListener(actionListener);
                
                //int count = (int)(((JButton)source.getParent())).getCountSpinner().getValue();
                model.getUser().addItemToCart(button.getName(), 1);
                view.addAddToCartListener(this);           
                
                break;
            case "View Product":
                JButton button1 = (JButton)source;
                IndividualItemPopUpModel individualItemPopUpModel = new IndividualItemPopUpModel(model.getUser(), database.getProduct(button1.getName()));
                IndividualItemPopUpView individualItemPopUpView = new IndividualItemPopUpView(individualItemPopUpModel);
                IndividualItemPopUpController individualItemPopUpController = new IndividualItemPopUpController(this, individualItemPopUpModel, individualItemPopUpView);
                individualItemPopUpController.displayIndividualItemPopUp();
                break;
            case "Shopping Cart":
                System.out.println("Shopping Cart");
                break;
                
            case "Alphabetical  ↑":
                view.displayCurrentInventory("AlphabeticalUp");
                break;
            case "Alphabetical  ↓":
                view.displayCurrentInventory("AlphabeticalDown");
                break;
            case "Price              ↑":
                view.displayCurrentInventory("PriceUp");
                break;
            case "Price              ↓":
                view.displayCurrentInventory("PriceDown");
                break;

                
            default:
                break;
        }
    }
    
    public void userInputListener()
    {
        System.out.println("User listener called");
    }

    @Override
    public void keyTyped(KeyEvent e) 
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
        System.out.println(e);
        userInputListener();
    } 

    @Override
    public void mouseClicked(MouseEvent e) 
    {

    }

    @Override
    public void mousePressed(MouseEvent e) 
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) 
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) 
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) 
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
