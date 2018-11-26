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
public class RegistrationPageController implements ActionListener, KeyListener 
{
    private final Database database = new Database();
    private final RegistrationPageModel model;
    private final RegistrationPageView view;

    public RegistrationPageController(RegistrationPageModel registrationPageModel, RegistrationPageView registrationPageView)
    {
        model = registrationPageModel;
        view = registrationPageView;
    }

    public void displayRegistrationPage()
    {
        view.setLayout();
        view.addObservers();
        view.update(model, null);
        
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
        view.addBackButtonListener(this);
        view.addRegisterButtonListener(this);
        view.addUserInputListener(this); 
        
    }
    
    public void register()
    {
        try 
        {
            User user = view.getUser();

            boolean userExists = database.userExists(user.username);
            if(userExists)
            {
                model.setErrorMessage("Username already exists");
                return;
            }
            
            if(database.insertUser(user))
            {
                user = database.getUserData(user);
                if(user.accountType)
                {
                    displaySellerHomepage(user);
                }
                else
                {
                    //displayCustomerHomepage();
                }
            }
            else
            {
                model.setErrorMessage("Error creating new user");
            }
        }
        catch (NumberFormatException nfex) 
        {
            model.setErrorMessage("Login error");
        }
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
   
    public void displaySellerHomepage(User user)
    {
        view.removeAll();
        view.dispose();
        
        SellerHomepageModel sellerHomepageModel = new SellerHomepageModel(user);
        SellerHomepageView sellerHomepageView = new SellerHomepageView(sellerHomepageModel);
        SellerHomepageController sellerHomepageController = new SellerHomepageController(sellerHomepageModel, sellerHomepageView);
        sellerHomepageController.displaySellerHomepage();
    }
    
    @Override
    public void actionPerformed(ActionEvent event) 
    {
        System.out.println("Action performed");
        String command = event.getActionCommand();
        if(command.equals("Register"))
        {
            System.out.println("Control opening register");
            register();
        }
        else if(command.equals("Login"))
        {
            System.out.println("Controller opening login page");
            displayLoginPage();
        }
    }
    
    public void userListener()
    {
        System.out.println("User listener called");
        User user = view.getUser();
        boolean readyToRegister = (user.username.length() >= 5 && user.password.length() >= 5 && user.email.length() >= 5 && user.address.length() >= 5 && user.phone.length() >= 5 && user.DOB.length() >= 5);

        try 
        {
            model.setRegisterButtonEnable(readyToRegister);
        }
        catch (NumberFormatException nfex) 
        {
            
        }
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
        userListener();
    } 
}
