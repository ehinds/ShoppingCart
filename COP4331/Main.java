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
    public static void main(String[] args) 
    {
        System.out.println("Welcome to the shopping cart program!\n");
        
        LoginPageView loginPageView = new LoginPageView();
        loginPageView.setVisible(true);
        
        Database database = new Database();
        database.connect();
        database.createNewTable();

        database.insertUser();
        database.selectAll();
        
        
        //loginPageView.update();
        //loginPageView.show();


        

        //genericView.pack();

        //genericView.add(loginPageView, BorderLayout.CENTER);
        
        //genericView.pack();
        
        
        
        
        
    }
}
