package COP4331;

import java.util.Observable;

/**
 *
 * @author Brownie
 */
public class RegistrationPageModel extends Observable
{
    private boolean registerEnabled = false;
    private String errorMessage = "";
    
    public RegistrationPageModel()
    {
        //update();
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
