package COP4331;

import java.util.Observable;

/**
 *
 * @author 
 */
public class AccountPageModel extends Observable
{
    private boolean personalDataEnabled = false;
    private boolean billingInformationEnabled = false;
    private boolean accountInformationEnabled = false;
    
    private boolean isEditing = false;
    
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
    
    public void setUser(User updatedUser)
    {
        System.out.println("Account Model receiving user data:");
        System.out.println("Username: " + updatedUser.username);
        System.out.println("Products: " + updatedUser.products.toString());
        System.out.println("Email: " + updatedUser.email);
        user = updatedUser;
        
        update();
    }
    
    public void setIsEditing(boolean enabled)
    {
        isEditing = enabled;
        update();
    }
    
    public boolean getIsEditing()
    {
        return isEditing;
    }
    
    public void setPersonalDataEnabled(boolean enabled)
    {
        personalDataEnabled = enabled;
        update();
    }
    
    public boolean getPersonalDataEnabled()
    {
        return personalDataEnabled;
    }
    
    public void setBillingInformationEnabled(boolean enabled)
    {
        billingInformationEnabled = enabled;
        update();
    }
    
    public boolean getBillingInformationEnabled()
    {
        return billingInformationEnabled;
    }
    
    public void setAccountInformationEnabled(boolean enabled)
    {
        accountInformationEnabled = enabled;
        update();
    }
    
    public boolean getAccountInformationEnabled()
    {
        return accountInformationEnabled;
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
