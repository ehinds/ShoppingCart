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
public class LoginPageController
{
    private final Database database = new Database();
    private final LoginPageModel model;
    private final LoginPageView view;

    public LoginPageController(LoginPageModel loginPageModel, LoginPageView loginPageView)
    {
        model = loginPageModel;
        view = loginPageView;
        
        view.addLoginListener(new LoginListener());
        view.addUsernamePasswordListener(new UsernamePasswordListener()); 
        //loginPageView.setVisible(true);
        //jButton3.addActionListener(actionPerformed);
    }
    
    class LoginListener implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
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
                        view.showError(model.getErrorMessage());
                    }
                    else
                    {
                        model.setErrorMessage("Incorrect username or password");
                        view.showError(model.getErrorMessage());
                    }
                }
                else
                {
                    model.setErrorMessage("Username does not exist");
                    view.showError(model.getErrorMessage());
                }  
            }
            catch (NumberFormatException nfex) 
            {
                model.setErrorMessage("Login error");
                view.showError(model.getErrorMessage());
            }
        }
    }
    
    class UsernamePasswordListener implements KeyListener 
    {
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
                view.setLoginEnabled(model.getLoginButtonEnable());
            }
            catch (NumberFormatException nfex) 
            {
                //m_view.showError("Bad input: '" + username + "'");
            }
        }
    }
    
}
