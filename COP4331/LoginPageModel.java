package COP4331;

import java.util.Observable;

/**
 *
 * @author 
 */
public class LoginPageModel extends Observable
{
    private boolean loginEnabled = false;
    private String errorMessage = "";
    
    public LoginPageModel()
    {
        //update();
    }
    
    public void setLoginButtonEnable(boolean enabled)
    {
        loginEnabled = enabled;
        update();
    }
    
    public boolean getLoginEnable()
    {
        return loginEnabled;
    }
    
    public void setErrorMessage(String message)
    {
        errorMessage = message;
        update();
    }
    
    public String getErrorMessage()
    {
        return errorMessage;
    }
    
    public void update()
    {
        setChanged();
        notifyObservers();
    }
}
