package COP4331;

import java.awt.Color;
import java.awt.Font;
import javafx.scene.Parent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;

/**
 *
 * @author 
 */
public class ProductSummaryListItem extends javax.swing.JPanel 
{
    
    /**
     * Creates new form productSummaryListItem
     */
    public ProductSummaryListItem() 
    {
        initComponents();
    }
    
    public ProductSummaryListItem generateLayout(Product product, boolean seller)
    {
        getRemoveButton().setVisible(false);
        getUpdateButton().setVisible(false);
        getAddToCartButton().setVisible(false);
        getCountSpinner().setVisible(false);
        getViewProductButton().setVisible(false);
        
        if(seller)
        {
            getRemoveButton().setVisible(true);
            getUpdateButton().setVisible(true);
        }
        else
        {
            getAddToCartButton().setVisible(true);
            getCountSpinner().setVisible(true);
            getViewProductButton().setVisible(true);
        }

        
        System.out.println("Generating product layout for " + product.title);
        imageLabel.setText("");
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setIcon(product.getImage(150, 150));

        titleLabel.setText(product.title);
        removeListing.setName(product.title);
        updateListing.setName(product.title);
        addToCartButton.setName(product.title);
        viewProductButton.setName(product.title);
        countSpinner.setName(product.title);

        priceLabel.setText("$" + String.valueOf(product.price / 100.00));
        summaryLabel.setText("<html>" + product.summary + "</html>");
        
        return this;
    }
    
    public JButton getRemoveButton()
    {
        return removeListing;
    }
    
        public JButton getUpdateButton()
    {
        return updateListing;
    }
    
    public JButton getAddToCartButton()
    {
        return addToCartButton;
    }
    
    public JButton getViewProductButton()
    {
        return viewProductButton;
    }
    
    public JSpinner getCountSpinner()
    {
        return countSpinner;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imageLabel = new javax.swing.JLabel();
        titleLabel = new javax.swing.JLabel();
        priceLabel = new javax.swing.JLabel();
        summaryLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        removeListing = new javax.swing.JButton();
        countSpinner = new javax.swing.JSpinner();
        addToCartButton = new javax.swing.JButton();
        updateListing = new javax.swing.JButton();
        viewProductButton = new javax.swing.JButton();

        imageLabel.setText("[imageLabel]");

        titleLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titleLabel.setText("[titleLabel]");

        priceLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        priceLabel.setText("[priceLabel]");

        summaryLabel.setText("[summaryLabel]");

        removeListing.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        removeListing.setText("Remove Listing");

        countSpinner.setValue(1);

        addToCartButton.setText("Add to Cart");

        updateListing.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        updateListing.setText("Update Listing");

        viewProductButton.setText("View Product");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(updateListing, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(priceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(countSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(removeListing, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addToCartButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(summaryLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1))
                        .addGap(18, 18, 18)
                        .addComponent(viewProductButton, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(titleLabel)
                            .addComponent(removeListing)
                            .addComponent(updateListing))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(priceLabel)
                                .addComponent(addToCartButton))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(countSpinner)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(summaryLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(viewProductButton))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addToCartButton;
    private javax.swing.JSpinner countSpinner;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JButton removeListing;
    private javax.swing.JLabel summaryLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JButton updateListing;
    private javax.swing.JButton viewProductButton;
    // End of variables declaration//GEN-END:variables
}
