package COP4331;

import java.util.Observable;

/**
 *
 * @author Brownie
 */
public class SellerHomepageModel extends Observable
{
    public User user = new User();
    
    private boolean registerEnabled = false;
    private String errorMessage = "";
    
    public SellerHomepageModel(User loadUser)
    {
        user = loadUser;
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
    
    public String getUsername()
    {
        return errorMessage;
    }
    
    public void update()
    {
        setChanged();
        notifyObservers();
    }
}
