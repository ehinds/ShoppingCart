/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package COP4331;

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

/**
 *
 * @author Brownie
 */
public class CustomerHomepageController implements ActionListener, KeyListener, MouseListener
{
    private final Database database = new Database();
    
    private final CustomerHomepageModel model;
    private final CustomerHomepageView view;

    public CustomerHomepageController(CustomerHomepageModel customerHomepageModel, CustomerHomepageView customerHomepageView)
    {
        System.out.println("CustomerHomepageController constructor being called");
        
        model = customerHomepageModel;
        view = customerHomepageView;
        view.addMouseEventListener(this);
    }

    public void displayCustomerHomepage()
    {
        model.setRandomProducts(getRandomProducts());
        System.out.println(getCategories());
        
        model.setCategories( getCategories() );
        
        view.setLayout();
        view.update(model, null);
        view.addObservers();
        addListeners();
        
        view.setVisible(true);
    }
    
    public void displayProductPage(String category)
    {
        view.removeAll();
        view.dispose();
        
        ProductPageModel productPageModel = new ProductPageModel(model.getUser(), category);
        ProductPageView productPageView = new ProductPageView(productPageModel);
        ProductPageController productPageController = new ProductPageController(productPageModel, productPageView);
        productPageController.displayProductPage();
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
            products.add(database.getProduct(productString));
        }
        return products;
    }
    
    public void popupClosed(User updatedUser)
    {
        model.setUser(updatedUser);
        displayCustomerHomepage();
    }
    
    void addListeners()
    {
        view.addLogoutButtonListener(this);
        view.addViewAccountButtonListener(this);
        //view.addMerchantAnalyticsButtonListener(this);
        //view.addAddNewProductButtonListener(this);
        //view.addRemoveListingListener(this);
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

   
    public void displayAddNewProductPopUp(User user)
    {
        AddNewProductPopUpModel addNewProductPopUpModel = new AddNewProductPopUpModel(user);
        AddNewProductPopUpView addNewProductPopUpView = new AddNewProductPopUpView(addNewProductPopUpModel);
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
    
    @Override
    public void actionPerformed(ActionEvent event) 
    {
        String command = event.getActionCommand();
        Object source = event.getSource();
        
        System.out.println(command + "Action Performed on " + source);
        
        switch (command) 
        {
            case "Logout":
                displayLoginPage();
                break;
            case "View Account":
                displayAccountPage();
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
         System.out.println("Controller heard mouse click!");
         System.out.println(((CategoryCard)e.getSource()).getCategory());
         
         displayProductPage(((CategoryCard)e.getSource()).getCategory());
         
         
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
