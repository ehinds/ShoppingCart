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
public class LoginPageController implements ActionListener, KeyListener 
{
    private final Database database = new Database();
    private final LoginPageModel model;
    private final LoginPageView view;

    public LoginPageController(LoginPageModel loginPageModel, LoginPageView loginPageView)
    {
        model = loginPageModel;
        view = loginPageView;
    }
    
    public void displayLoginPage()
    {
        view.setLayout();
        view.addObservers();
        view.update(model, null);
        
        addListeners();
                
        view.setVisible(true);
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
    
    void addListeners()
    {
        view.addLoginButtonListener(this);
        view.addRegisterButtonListener(this);
        view.addUserInputListener(this); 
    }

    @Override
    public void actionPerformed(ActionEvent event) 
    {
        String command = event.getActionCommand();
        if("Login".equals(command))
        {
            login();
        }
        else if("Register".equals(command))
        {
            System.out.println("Control opening register");
            displayRegistrationPage();
        }
    }
    
    public void login()
    {
        try 
        {
            User user = view.getUser();

            boolean userExists = database.userExists(user.username);

            if(userExists)
            {
                boolean loginSuccess = database.verifyPassword(user.username, user.password);
                if(loginSuccess)
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
                    model.setErrorMessage("Incorrect username or password");
                }
            }
            else
            {
                model.setErrorMessage("Username does not exist");
            }  
        }
        catch (NumberFormatException nfex) 
        {
            model.setErrorMessage("Login error");
        }
    }

    public void displayRegistrationPage()
    {
        view.removeAll();
        view.dispose();
        
        RegistrationPageModel registrationPageModel = new RegistrationPageModel();
        RegistrationPageView registrationPageView = new RegistrationPageView(registrationPageModel);
        RegistrationPageController registrationPageController = new RegistrationPageController(registrationPageModel, registrationPageView);
        registrationPageController.displayRegistrationPage();
    }
    
    public void userListener()
    {
        User user = view.getUser();

        try 
        {
            boolean readyToLogin = (user.username.length() >= 5 && user.password.length() >= 5);

            model.setLoginButtonEnable(readyToLogin);
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
        if (e.getKeyCode()==KeyEvent.VK_ENTER)
        {
            login();
        }
        
    } 
}
