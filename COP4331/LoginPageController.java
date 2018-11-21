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
    //private final LoginPage view;
    private final LoginPageView view;
    //GenericView genericView;
    //public String test = "test";
    //args here
    public LoginPageController(LoginPageModel loginPageModel, LoginPageView loginPageView)
    {
        System.out.println("Control init");
        //genericView = gv;
        model = loginPageModel;
        view = loginPageView;
        
        view.addLoginListener(this);
        view.addRegisterListener(this);
        view.addUsernamePasswordListener(this); 
    }

    @Override
    public void actionPerformed(ActionEvent event) 
    {
        String command = event.getActionCommand();
        if(command == "Login")
        {
            LoginListener();
        }
        else if(command == "Register")
        {
            System.out.println("Control opening register");
            RegisterListener();
        }
    }
    
    public void LoginListener()
    {
        String username = "";
        String password = "";
        boolean userExists = false;
        boolean loginSuccess = false;

        try 
        {
            username = view.getUsername();

            userExists = database.userExists(username);

            if(userExists)
            {
                password = view.getPassword();
                loginSuccess = database.verifyPassword(username, password);
                if(loginSuccess)
                {
                    model.setErrorMessage("");
                    //view.showError(model.getErrorMessage());
                }
                else
                {
                    model.setErrorMessage("Incorrect username or password");
                    //view.showError(model.getErrorMessage());
                }
            }
            else
            {
                model.setErrorMessage("Username does not exist");
                //view.showError(model.getErrorMessage());
            }  
        }
        catch (NumberFormatException nfex) 
        {
            model.setErrorMessage("Login error");
            //view.showError(model.getErrorMessage());
        }
    }
    
    public void RegisterListener()
    {
        view.removeAll();
        view.dispose();
        
        //LoginPageModel model = new LoginPageModel();
        //LoginPageView view = new LoginPageView(model);
        RegistrationPageView registrationPageView = new RegistrationPageView();
        registrationPageView.setVisible(true);
        //v.switchView(this, new RegistrationPage());
        System.out.println("Testing to see if I'm still alive.");
        //gg.loginPage = view;
        //gg.frame.setContentPane(view);
        //gg.frame.pack();
        //gg.frame.setVisible(true);
    }
    
    public void UsernamePasswordListener()
    {
        //System.out.println(event.KeyEvent);

        //System.out.println("User/Pass Listener\n");
        String username = "";
        String password = "";
        boolean readyToLogin = false;

        try 
        {
            username = view.getUsername();
            password = view.getPassword();
            readyToLogin = (username.length() >= 5 && password.length() >= 5);

            model.setLoginButtonEnable(readyToLogin);
            //view.setLoginEnabled(model.getLoginEnable());
        }
        catch (NumberFormatException nfex) 
        {
            //m_view.showError("Bad input: '" + username + "'");
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
