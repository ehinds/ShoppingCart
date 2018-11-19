/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package COP4331;

/**
 *
 * @author Brownie
 */
public class LoginPageModel 
{
    private boolean loginButtonEnabled = false;
    private String errorMessage = "";
    
    public LoginPageModel()
    {

    }
    
    public void setLoginButtonEnable(boolean enabled)
    {
        loginButtonEnabled = enabled;
    }
    
    public boolean getLoginButtonEnable()
    {
        return loginButtonEnabled;
    }
    
    public void setErrorMessage(String message)
    {
        errorMessage = message;
    }
    
    public String getErrorMessage()
    {
        return errorMessage;
    }
    
    
}
