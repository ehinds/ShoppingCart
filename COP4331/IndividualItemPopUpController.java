/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package COP4331;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

/**
 *
 * @author ASUS
 */
public class IndividualItemPopUpController implements ActionListener{
      private final Database database = new Database();
    final JFileChooser fc = new JFileChooser();
    
    private final ProductPageController parent;
    private final IndividualItemPopUpModel model;
    private final IndividualItemPopUpView view;

    public IndividualItemPopUpController(ProductPageController parentWindow, IndividualItemPopUpModel individualItemPopUpModel, IndividualItemPopUpView individualItemPopUpView)
    {
        parent = parentWindow;
        model = individualItemPopUpModel;
        view = individualItemPopUpView;
    }

    public void displayIndividualItemPopUp()
    {
        view.setLayout();
        view.update(model, null);
        view.addObservers();
        addListeners();
        view.setVisible(true);
    }
    
    void addListeners()
    {
        view.addUpdateProductButtonListener(this);
    }
    
    /*public static BufferedImage resize(BufferedImage img, int newW, int newH) 
    { 
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }*/ 
    
    @Override
    public void actionPerformed(ActionEvent event) 
    {
        String command = event.getActionCommand();
        
        switch (command) 
        {
            /*case "Upload Image":
                try
                {
                    //System.out.println("Working Directory = " + System.getProperty("user.dir"));
                    BufferedImage originalImage;
                    int returnVal = fc.showOpenDialog(view);
                    if (returnVal == JFileChooser.APPROVE_OPTION) 
                    {
                        File file = fc.getSelectedFile();
                        //This is where a real application would open the file.
                        System.out.println("Opening: " + file.getName() + ".");
                        originalImage = ImageIO.read(file);
                        
                        originalImage = resize(originalImage, view.getImagePanelWidth(), view.getImagePanelHeight());
                        ImageIcon image = new ImageIcon(originalImage);
                        model.setImage(image);
                    } 
                    else 
                    {
                        System.out.println("Open command cancelled by user.");
                    }
                }
                catch(IOException ex)
                {
                    Logger.getLogger(IndividualItemPopUpController.class.getName()).log(Level.SEVERE, null, ex);
                }
            
                break;
                
            case "Update Product":
                Product product = view.getProduct();
                
                if(database.updateProduct(product))
                {
                    model.linkUserProduct(product.title);

                    database.updateUserProductLink(model.getUser());

                    System.out.println("Updated product in database");


                    model.setUser(database.getUserData(model.getUser()));

                    parent.popupClosed(model.getUser());

                    view.removeAll();
                    view.dispose();
                    
                }
                else
                {
                    System.out.println("Failed to add product to database");
                }
                //model.image = ImageIO.read(new File("Strawberry.jpg"));
                
                //view.imagePanel.drawImage(img, 50, 50, null);
                break;*/
            default:
                parent.popupClosed(model.getUser());
                view.removeAll();
                view.dispose();
                break;
        }
    }
}
