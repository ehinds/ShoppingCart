package COP4331;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Brownie
 */
public class ProductPageView extends GenericView implements Observer
{
    private ProductPageModel model;
    LinkedList<ProductSummaryListItem> productSummaryListItems = new LinkedList<>();
    
    MouseListener controllerMouseListener;

    /**
     * Creates new form RegistrationPageView
     * @param customerHomepageModel
     */
    public ProductPageView(ProductPageModel customerHomepageModel)  
    {
        model = customerHomepageModel;
    }
    
    void showErrorMessage(String error) 
    {
        errorLabel.setText(error);
    }
    
    void welcomeUser(String username) 
    {
        welcomeUserLabel.setText("Hello, " + username);
    }
    
    void addObservers()
    {
        model.addObserver(this);
    }
    
    void addLogoutButtonListener(ActionListener actionListener) 
    {
        System.out.println("Login listener added\n");
        logoutButton.addActionListener(actionListener);
    }
    
    void addMouseEventListener(MouseListener mouseListener) 
    {
        System.out.println("Mouse listener added\n");
        controllerMouseListener = mouseListener;
    }
    
    void addViewAccountButtonListener(ActionListener actionListener) 
    {
        System.out.println("Login listener added\n");
        viewAccountButton.addActionListener(actionListener);
    }
    
    void addSortEventListener(ActionListener actionListener) 
    {
        System.out.println("Sort listener added\n");
        sortBox.addActionListener(actionListener);
    }
    
    void addShoppingCartEventListener(ActionListener actionListener) 
    {
        System.out.println("Add to cart listener added\n");
        shoppingCartButton.addActionListener(actionListener);
    }
    
    void addAddToCartListener(ActionListener actionListener) 
    {
        for (int i = 0; i < productSummaryListItems.size(); i++) 
        {
            productSummaryListItems.get(i).getAddToCartButton().addActionListener(actionListener);
            //int value = (int)((ProductSummaryListItem)(productSummaryListItems.get(i).getAddToCartButton().getParent())).getCountSpinner().getValue();

        }
    }

    void displayCurrentInventory(String sort)
    {
        LinkedList<Product> products =  model.getProducts();
        
        Iterator<Product> prodIterator = products.iterator(); 
  
        System.out.println("Before Sorting: " + sort); 
        while (prodIterator.hasNext()) 
        { 
            System.out.println(prodIterator.next().title); 
        } 
  
        switch (sort) 
        {
            case "AlphabeticalUp":
                Collections.sort(products, new SortProducts.SortName());
                break;
            case "AlphabeticalDown":
                Collections.sort(products, new SortProducts.SortNameReverse());
                break;
            case "PriceUp":
                Collections.sort(products, new SortProducts.SortPrice());
                break;
            case "PriceDown":
                Collections.sort(products, new SortProducts.SortPriceReverse());
                break;
            default:
                Collections.sort(products, new SortProducts.SortName());
                break; 
        }
        productList.removeAll();
        productList.setLayout(new GridLayout(products.size(), 2));

        System.out.println("\n\nAfter Sorting:\n"); 
        
        for(int i = 0; i < products.size(); i++)
        {
            //LinkedList<ProductSummaryListItem> productSummaryListItems = new LinkedList<>();
            //System.out.println(p.title);
            productSummaryListItems = new LinkedList<>();
            ProductSummaryListItem productSummaryListItem = new ProductSummaryListItem();
            productSummaryListItem.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            addUIMouseOverAnimations(productSummaryListItem);     // Local listener
            productSummaryListItem.addMouseListener(controllerMouseListener);   //  Controller listener
            //productSummaryListItem.remove(productSummaryListItem.getRemoveButton());
            productSummaryListItem.getAddToCartButton().setVisible(true);
            productSummaryListItem.getCountSpinner().setVisible(true);
            
            System.out.println("Adding: " ); 
            productSummaryListItems.add(productSummaryListItem);
            //productSummaryListItems.addFirst(productSummaryListItem);// For inverse sorting
            productList.add(productSummaryListItem.generateLayout(products.get(i), false));
            productList.updateUI();
        }
    }
    
    void addUserInputListener(KeyListener keyListener) 
    {

    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        welcomeUserLabel = new javax.swing.JLabel();
        logoutButton = new javax.swing.JButton();
        viewAccountButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        shoppingCartButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        errorLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        productList = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        productsPageSubTitle = new javax.swing.JLabel();
        sortBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Seller Homepage");

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        welcomeUserLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        welcomeUserLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        welcomeUserLabel.setText("Hello Username");

        logoutButton.setText("Logout");
        logoutButton.setMargin(new java.awt.Insets(2, 4, 2, 4));

        viewAccountButton.setText("View Account");

        shoppingCartButton.setText("Shopping Cart");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(welcomeUserLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(logoutButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(shoppingCartButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(viewAccountButton)
                        .addGap(6, 6, 6))
                    .addComponent(jSeparator1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(logoutButton)
                    .addComponent(welcomeUserLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewAccountButton)
                    .addComponent(shoppingCartButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Products Page");

        errorLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        errorLabel.setForeground(new java.awt.Color(255, 0, 0));
        errorLabel.setText("[Error Message]");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(errorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(errorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        productList.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        productList.setName(""); // NOI18N

        javax.swing.GroupLayout productListLayout = new javax.swing.GroupLayout(productList);
        productList.setLayout(productListLayout);
        productListLayout.setHorizontalGroup(
            productListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 701, Short.MAX_VALUE)
        );
        productListLayout.setVerticalGroup(
            productListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 354, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(productList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(productList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        productsPageSubTitle.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 18)); // NOI18N
        productsPageSubTitle.setForeground(new java.awt.Color(0, 51, 153));
        productsPageSubTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productsPageSubTitle.setText("[Number] [Category] Listings");

        sortBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Alphabetical  ↑", "Alphabetical  ↓", "Price              ↑", "Price              ↓" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(productsPageSubTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sortBox, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(productsPageSubTitle)
                .addComponent(sortBox, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel errorLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton logoutButton;
    private javax.swing.JPanel productList;
    private javax.swing.JLabel productsPageSubTitle;
    private javax.swing.JButton shoppingCartButton;
    private javax.swing.JComboBox<String> sortBox;
    private javax.swing.JButton viewAccountButton;
    private javax.swing.JLabel welcomeUserLabel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setLayout() 
    {
        initComponents();
        getContentPane().setBackground(Color.white);
    }

    @Override
    public void enableScrollbar() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void addUIMouseOverAnimations(ProductSummaryListItem productSummaryListItem)
    {
        productSummaryListItem.addMouseListener(new java.awt.event.MouseAdapter() 
        {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) 
            {
                Color color = new Color(0xffE7F3FF);

                productSummaryListItem.setBackground(color);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) 
            {
                Color color = new Color(0xffF0F0F0);

                productSummaryListItem.setBackground(color);
            }
            
            @Override
            public void mouseClicked(MouseEvent e)  
            {  

            }  
        });
    }
    
    @Override
    public void update(Observable observable, Object arg) 
    {
        model = (ProductPageModel)observable;
        
        showErrorMessage(model.getErrorMessage());
        welcomeUser(model.getUser().username);
        
        if(model.getNumberOfItems() == 1)
        {
            productsPageSubTitle.setText(Integer.toString(model.getNumberOfItems()) + " listing for " + model.getCategory() + ".");
        }
        else
        {
            productsPageSubTitle.setText(Integer.toString(model.getNumberOfItems()) + " listings for " + model.getCategory() + ".");
        }
        

        displayCurrentInventory("sort");

        productList.updateUI();
        
        this.displayFrame();
    }

}
