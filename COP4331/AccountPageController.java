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
        view.addRegisterButtonListener(this);
        view.addUserInputListener(this); 
        
    }
    
    public void updatePersonalInformation()
    {
        try 
        {
            User user = view.getUserData();
            System.out.println("Generated new user: " + user.products);
            
            boolean userExists = database.userExists(user.username);
            if(userExists)
            {
                model.setErrorMessage("Username already exists");
                return;
            }
            
            if(database.insertUser(user))
            {
                System.out.println("Inserted user");
                user = database.getUserData(user);
                System.out.println("Getting user: " + user.products);
                displayLoginPage();
            }
            else
            {
                model.setErrorMessage("Error creating new user");
            }
        }
        catch (NumberFormatException nfex) 
        {
            model.setErrorMessage("Login error");
        }
    }

   public void displayLoginPage()
    {
        view.removeAll();
        view.dispose();
        
        LoginPageModel loginPageModel = new LoginPageModel();
        LoginPageView loginPageView = new LoginPageView(loginPageModel);
        LoginPageController loginPageController = new LoginPageController(loginPageModel, loginPageView);
        loginPageController.displayLoginPage();
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
        if(command.equals("Register"))
        {
            System.out.println("Control opening register");
            updatePersonalInformation();
        }
        else if(command.equals("Login"))
        {
            displaySellerHomepage(model.getUser());
        }
    }
    
    public void userListener()
    {
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
