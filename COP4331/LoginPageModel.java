/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package COP4331;

import java.util.Observable;

/**
 *
 * @author Brownie
 */
public class LoginPageModel extends Observable
{
    private boolean loginEnabled = false;
    private String errorMessage = "";
    
    public LoginPageModel()
    {

    }
    
    public void setLoginButtonEnable(boolean enabled)
    {
        loginEnabled = enabled;
        setChanged();
        notifyObservers();
    }
    
    public boolean getLoginEnable()
    {
        return loginEnabled;
    }
    
    public void setErrorMessage(String message)
    {
        errorMessage = message;
        setChanged();
        notifyObservers();
    }
    
    public String getErrorMessage()
    {
        return errorMessage;
    }
    
    
}
