package COP4331;

import java.util.Observable;

/**
 *
 * @author Brownie
 */
public class AccountPageModel extends Observable
{
    private boolean registerEnabled = false;
    private String errorMessage = "";
    
    private User user = new User();
    
    public AccountPageModel(User loadUser)
    {
        user = loadUser;
    }
    
    public User getUser()
    {
        return user;
    }
    
    public void setRegisterButtonEnable(boolean enabled)
    {
        registerEnabled = enabled;
        update();
    }
    
    public boolean getRegisterEnable()
    {
        return registerEnabled;
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
