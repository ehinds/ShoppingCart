package COP4331;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

/**
 *
 * @author Brownie
 */
public class AccountPageController implements ActionListener, KeyListener 
{
    private final Database database = new Database();
    private final AccountPageModel model;
    private final AccountPageView view;

    public AccountPageController(AccountPageModel accountPageModel, AccountPageView accountPageView)
    {
        model = accountPageModel;
        view = accountPageView;
    }

    public void displayAccountPage()
    {
        view.setLayout();
        view.addObservers();
        view.update(model, null);
        
        addListeners();
                
        view.setVisible(true);
    }
    
    public void displayOtherPage()
    {
        view.removeAll();
        view.dispose();
        
        //AccountPageModel model = new AccountPageModel();
        //AccountPageView accountPageView = new AccountPageView(model);
        //AccountPageController controller = new AccountPageController(model, view);
        //controller.displayLoginPage();
    }
    
    void addListeners()
    {
        view.addBackButtonListener(this);
        
        view.addEditPersonalInformationButtonListener(this);
        
        view.addUserInputListener(this);
        
        view.addEditBillingInformationButtonListener(this);
        view.addEditPasswordButtonListener(this);
        
        view.addSaveButtonListener(this);
    }
    
    public void updatePersonalInformation()
    {
        database.updateUserPersonalInformation(view.getUserPersonalData());

        model.setUser(database.getUserData(model.getUser()));
        
        displaySellerHomepage(model.getUser());
    }
   
    void activatePersonalInformation()
    {
        model.setIsEditing(true);
        model.setPersonalDataEnabled(true);
    }
    
    void activateBillingInformation()
    {
        model.setIsEditing(true);
        model.setBillingInformationEnabled(true);
    }
    
    void activatePassword()
    {
        model.setIsEditing(true);
        model.setAccountInformationEnabled(true);
    }
    
    void cancelInformationUpdate()
    {
        model.setIsEditing(false);
        model.setPersonalDataEnabled(false);
        model.setBillingInformationEnabled(false);
        model.setAccountInformationEnabled(false);
    }
    
    
    public void displaySellerHomepage(User user)
    {
        view.removeAll();
        view.dispose();
        
        SellerHomepageModel sellerHomepageModel = new SellerHomepageModel(user);
        SellerHomepageView sellerHomepageView = new SellerHomepageView(sellerHomepageModel);
        SellerHomepageController sellerHomepageController = new SellerHomepageController(sellerHomepageModel, sellerHomepageView);
        sellerHomepageController.displaySellerHomepage();
    }
    
    @Override
    public void actionPerformed(ActionEvent event) 
    {
        System.out.println("Action performed");
        String command = event.getActionCommand();
        
        if(command.equals("Edit Personal Information"))
        {
            System.out.println("Edit Personal Information");
            activatePersonalInformation();
        }
        else if(command.equals("Edit Billing Information"))
        {
            System.out.println("Edit Billing Information");
            activateBillingInformation();
        }
        else if(command.equals("Edit Password"))
        {
            System.out.println("Edit Password");
            activatePassword();
        }
        else if(command.equals("Back"))
        {
            System.out.println("Back");
            displaySellerHomepage(model.getUser());
        }
        else if(command.equals("Save"))
        {
            System.out.println("Save");
            if(model.getPersonalDataEnabled())
            {
                
            }
            updatePersonalInformation();
            //getUserBillingInfo()
        }
        else if(command.equals("Cancel"))
        {
            System.out.println("Cancel");
            cancelInformationUpdate();
        }
    }
    
    public void userListener()
    {
        /*
        System.out.println("User listener called");
        User user = view.getUserData();
        boolean readyToRegister = (user.username.length() >= 5 && user.password.length() >= 5 && user.email.length() >= 5 && user.address.length() >= 5 && user.phone.length() >= 5 && user.DOB.length() >= 5);

        try 
        {
            model.setRegisterButtonEnable(readyToRegister);
        }
        catch (NumberFormatException nfex) 
        {
            
        }
*/
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
        userListener();
    } 
}
