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
    
    void addListeners()
    {
        view.addLoginListener(this);
        view.addRegisterListener(this);
        view.addUsernamePasswordListener(this); 
    }

    @Override
    public void actionPerformed(ActionEvent event) 
    {
        String command = event.getActionCommand();
        if("Login".equals(command))
        {
            LoginListener();
        }
        else if("Register".equals(command))
        {
            System.out.println("Control opening register");
            RegisterListener();
        }
    }
    
    public void LoginListener()
    {
        try 
        {
            User user = view.getLoginInformation();

            boolean userExists = database.userExists(user.username);

            if(userExists)
            {
                boolean loginSuccess = database.verifyPassword(user.username, user.password);
                if(loginSuccess)
                {
                    model.setErrorMessage("");
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
    
    public void RegisterListener()
    {
        view.removeAll();
        view.dispose();

        RegistrationPageView registrationPageView = new RegistrationPageView();
        registrationPageView.setVisible(true);
    }
    
    public void UsernamePasswordListener()
    {
        User user = view.getLoginInformation();

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
        UsernamePasswordListener();
    } 
}
