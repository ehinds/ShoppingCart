package COP4331;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author Brownie
 */

public class Main 
{
    Database database = new Database();

    public static void main(String[] args) 
    {
        System.out.println("Welcome to the shopping cart program!\n");
        
        Database database = new Database();
        database.connect();
        database.createNewUsersTable();
        database.insertUser(new User());
        database.selectAll();
        
        LoginPageModel loginPageModel = new LoginPageModel();
        LoginPageView loginPageView = new LoginPageView(loginPageModel);
        LoginPageController loginPageController = new LoginPageController(loginPageModel, loginPageView);
        loginPageView.setVisible(true);
        
        
        
        


        //http://www.fredosaurus.com/notes-java/GUI/structure/40mvc.html
        
        //loginPageView.update();
        //loginPageView.show();


        

        //genericView.pack();

        //genericView.add(loginPageView, BorderLayout.CENTER);
        
        //genericView.pack();
        
        
        
        
        
    }
}
