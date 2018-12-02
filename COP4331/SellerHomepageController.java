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
import java.util.LinkedList;
import javax.swing.JButton;

/**
 *
 * @author Brownie
 */
public class SellerHomepageController implements ActionListener, KeyListener 
{
    private final Database database = new Database();
    
    private final SellerHomepageModel model;
    private final SellerHomepageView view;

    public SellerHomepageController(SellerHomepageModel sellerHomepageModel, SellerHomepageView sellerHomepageView)
    {
        model = sellerHomepageModel;
        view = sellerHomepageView;
    }

    public void displaySellerHomepage()
    {
        model.setProducts( getUserProducts());
        
        view.setLayout();
        view.update(model, null);
        view.addObservers();
        addListeners();
        
        view.setVisible(true);
    }
    
    public LinkedList<Product> getUserProducts()
    {
        LinkedList<Product> products = new LinkedList<>();
        
        model.getUser().products.stream().map((product) -> 
        {
            System.out.println(product);
            return product;
        }).forEachOrdered((product) -> 
        {
            if(database.getProduct(product).title == null)
            {
                System.out.println("Found null product");
                model.removeProduct(product);
                database.updateUserProductLink(model.getUser());
            }
            else
            {
                products.add(database.getProduct(product));
            }
            
        });
        
        return products;
    }
    
    public void displayOtherPage()
    {
        view.removeAll();
        view.dispose();
        
        //RegistrationPageModel model = new RegistrationPageModel();
        //RegistrationPageView registrationPageView = new RegistrationPageView(model);
        //RegistrationPageController controller = new RegistrationPageController(model, view);
        //controller.displayLoginPage();
    }
    
    public void popupClosed(User updatedUser)
    {
        System.out.println("Popup closed! Got new user data");
        System.out.println("Username: " + updatedUser.username);
        System.out.println("Products: " + updatedUser.products.toString());
        model.setUser(updatedUser);
        model.setProducts( getUserProducts());
    }
    
    void addListeners()
    {
        view.addLogoutButtonListener(this);
        view.addViewAccountButtonListener(this);
        view.addMerchantAnalyticsButtonListener(this);
        view.addAddNewProductButtonListener(this);
        view.addRemoveListingListener(this);
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
   
    public void displayMerchantAnalytics()
    {
        //view.removeAll();
        //view.dispose();
        
       // LoginPageModel loginPageModel = new LoginPageModel();
        //LoginPageView loginPageView = new LoginPageView(loginPageModel);
        //LoginPageController loginPageController = new LoginPageController(loginPageModel, loginPageView);
        //loginPageController.displayLoginPage();
    }
   
    public void displayAddNewProductPopUp(User user)
    {
        AddNewProductPopUpModel addNewProductPopUpModel = new AddNewProductPopUpModel(user);
        AddNewProductPopUpView addNewProductPopUpView = new AddNewProductPopUpView(addNewProductPopUpModel);
        AddNewProductPopUpController addNewProductPopUpController = new AddNewProductPopUpController(this, addNewProductPopUpModel, addNewProductPopUpView);
        addNewProductPopUpController.displayAddNewProductPopUp();
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
            case "Merchant Analytics":
                break;
            case "Add New Product":
                displayAddNewProductPopUp(model.getUser());
                break;
            case "Remove Listing":
                JButton button;
                button = (JButton)source;
                
                if(database.deleteProduct(button.getName()))
                {
                    model.removeProduct(button.getName());
                    
                    database.updateUserProductLink(model.getUser());
                    System.out.println("Successfully deleted " + button.getName());
                    //view.update(model, null);
                }
                else
                {
                    System.out.println("Error occured while deleting  " + button.getName());
                }
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
}
