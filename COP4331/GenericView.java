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
}
