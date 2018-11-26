/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package COP4331;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
        view.setLayout();
        view.update(model, null);
        view.addObservers();
        addListeners();
        view.setVisible(true);
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
    
    void addListeners()
    {
        view.addLogoutButtonListener(this);
        view.addViewAccountButtonListener(this);
        view.addMerchantAnalyticsButtonListener(this);
        view.addAddNewProductButtonListener(this);
        
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
        AddNewProductPopUpController addNewProductPopUpController = new AddNewProductPopUpController(addNewProductPopUpModel, addNewProductPopUpView);
        addNewProductPopUpController.displayAddNewProductPopUp();
    }
    
    @Override
    public void actionPerformed(ActionEvent event) 
    {
        String command = event.getActionCommand();
        
        switch (command) 
        {
            case "Logout":
                break;
            case "View Account":
                break;
            case "Merchant Analytics":
                break;
            case "Add New Product":
                displayAddNewProductPopUp(model.user);
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
