package COP4331;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;

/**
 *
 * @author 
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
