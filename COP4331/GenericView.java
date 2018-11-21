/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package COP4331;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;

/**
 *
 * @author Brownie
 */
public abstract class GenericView extends javax.swing.JFrame
{
    //public JFrame frame;
    
    public GenericView()
    {

    }
    
    public void displayFrame()
    {
        this.pack();
        this.setVisible(true);
    }
    
    abstract public void setLayout();
    abstract public void enableScrollbar();
    
    public void switchView(Object lp, Container container)
    {
        System.out.println(lp);
        lp = null;
        //frame.getContentPane().removeAll();
        //frame.setContentPane(container);
        //frame.pack();
        //frame.setVisible(true);
        System.out.println(lp);
    }
    

}
