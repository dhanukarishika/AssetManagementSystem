/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author dhanukarishika
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.text.SimpleDateFormat;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JLabel;
import javax.swing.JTable;

public class AssetRegisterScreen extends javax.swing.JFrame {

    /**
     * Creates new form AssetRegisterScreen
     */
    String userRole;

public AssetRegisterScreen(String role) {
        initComponents();
        loadCategories();
        loadAssets();
        setTableColumnWidths();
        this.userRole = role;

    }
    private void setTableColumnWidths() {
    tblAssets.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    tblAssets.getColumnModel().getColumn(0).setPreferredWidth(60);
    tblAssets.getColumnModel().getColumn(1).setPreferredWidth(150);
    tblAssets.getColumnModel().getColumn(2).setPreferredWidth(100);
    tblAssets.getColumnModel().getColumn(3).setPreferredWidth(100);
    tblAssets.getColumnModel().getColumn(4).setPreferredWidth(100);
    tblAssets.getColumnModel().getColumn(5).setPreferredWidth(80);
    tblAssets.getColumnModel().getColumn(6).setPreferredWidth(120);
    tblAssets.getColumnModel().getColumn(7).setPreferredWidth(100);
    tblAssets.getColumnModel().getColumn(8).setPreferredWidth(120);
    tblAssets.getColumnModel().getColumn(9).setPreferredWidth(100);
    tblAssets.getColumnModel().getColumn(10).setPreferredWidth(80);
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
centerRenderer.setHorizontalAlignment(JLabel.CENTER);

// Apply to columns you want centered (example: Serial No, Units, Status)
tblAssets.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // Serial
tblAssets.getColumnModel().getColumn(5).setCellRenderer(centerRenderer); // Units
tblAssets.getColumnModel().getColumn(10).setCellRenderer(centerRenderer); // Status

}

    public void loadAssets() {
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(
            "jdbc:oracle:thin:@localhost:1521/XEPDB1", "asset", "asset");

        String keyword = txtSearch.getText().trim().toLowerCase();
        PreparedStatement pst;

        if (keyword.isEmpty()) {
            pst = con.prepareStatement("SELECT * FROM asset_register");
        } else {
            pst = con.prepareStatement(
                "SELECT * FROM asset_register WHERE LOWER(asset_name) LIKE ? OR LOWER(category) LIKE ?");
            pst.setString(1, "%" + keyword + "%");
            pst.setString(2, "%" + keyword + "%");
        }

        ResultSet rs = pst.executeQuery();

        // Custom, read-only model with column headers
        DefaultTableModel model = new DefaultTableModel(
            new Object[]{"Serial No", "Asset Name", "Category", "Purchase Date", "Price", "Units", "Original Value", "Depreciation", "Residual Value", "ICR Date", "Status"},
            0
        ) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblAssets.setModel(model);

        // Formatters for currency and date
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));

        while (rs.next()) {
            Object[] row = {
                rs.getInt("serial_no"),
                rs.getString("asset_name"),
                rs.getString("category"),
                sdf.format(rs.getDate("purchase_date")),
                nf.format(rs.getDouble("purchase_price")),
                rs.getInt("num_units"),
                nf.format(rs.getDouble("original_value")),
                nf.format(rs.getDouble("depreciation")),
                nf.format(rs.getDouble("residual_value")),
                sdf.format(rs.getDate("icr_date")),
                rs.getString("status")
            };
            model.addRow(row);
        }

        con.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error loading assets: " + e.getMessage());
    }
}


    public void loadCategories() {
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(
            "jdbc:oracle:thin:@localhost:1521/XEPDB1", "asset", "asset");

        PreparedStatement pst = con.prepareStatement("SELECT cat_name FROM asset_categories WHERE is_active='Active'");
        ResultSet rs = pst.executeQuery();

        cmbCategory.removeAllItems(); // Clear old items
        while (rs.next()) {
            cmbCategory.addItem(rs.getString("cat_name"));
        }

        con.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error loading categories: " + e.getMessage());
    }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtAssetName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cmbCategory = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        datePurchase = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtUnits = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox<>();
        btnRegister = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAssets = new javax.swing.JTable();
        btnClear = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Asset Name");

        txtAssetName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAssetNameActionPerformed(evt);
            }
        });

        jLabel2.setText("Category");

        cmbCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCategoryActionPerformed(evt);
            }
        });

        jLabel3.setText("Purchase Date");

        jLabel4.setText("Purchase Price");

        jLabel5.setText("Number of Units");

        jLabel6.setText("Status");

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Working", "Broken" }));

        btnRegister.setText("Register Asset");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        tblAssets.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Serial No", "Asset Name", "Category", "Purchase Date", "Price", "Units", "Original Val", "Depreciation", "Residual Value", "ICR Da", "Status"
            }
        ));
        jScrollPane1.setViewportView(tblAssets);

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel4))
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(datePurchase, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(txtPrice, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cmbCategory, 0, 109, Short.MAX_VALUE)
                                    .addComponent(txtAssetName))
                                .addGap(201, 201, 201)
                                .addComponent(btnSearch)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtUnits, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(223, 223, 223)
                                .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(111, 111, 111))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnRegister)
                                .addGap(138, 138, 138)))
                        .addComponent(btnClear)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAssetName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cmbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSearch)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(datePurchase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtUnits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegister)
                    .addComponent(btnClear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCategoryActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cmbCategoryActionPerformed

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        // TODO add your handling code here:
         loadAssets();
        try {
    Class.forName("oracle.jdbc.driver.OracleDriver");
    Connection con = DriverManager.getConnection(
        "jdbc:oracle:thin:@localhost:1521/XEPDB1", "asset", "asset");

    String name = txtAssetName.getText();
    String cat = cmbCategory.getSelectedItem().toString();
    java.util.Date date = datePurchase.getDate();
    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
    double price = Double.parseDouble(txtPrice.getText());
    int units = Integer.parseInt(txtUnits.getText());
    double total = price * units;
    double dep = total * 0.1; // 10% depreciation
    double residual = total - dep;
    String status = cmbStatus.getSelectedItem().toString();

    PreparedStatement pst = con.prepareStatement(
        "INSERT INTO asset_register VALUES (asset_reg_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ?)");
    pst.setString(1, name);
    pst.setString(2, cat);
    pst.setDate(3, sqlDate);
    pst.setDouble(4, price);
    pst.setInt(5, units);
    pst.setDouble(6, total);
    pst.setDouble(7, dep);
    pst.setDouble(8, residual);
    pst.setString(9, status);

    pst.executeUpdate();
    JOptionPane.showMessageDialog(this, "Asset registered successfully! ✅");
    loadAssets();
    con.close();
} catch (Exception e) {
    JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
}

    }//GEN-LAST:event_btnRegisterActionPerformed

    private void txtAssetNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAssetNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAssetNameActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
txtAssetName.setText("");
cmbCategory.setSelectedIndex(0);
datePurchase.setDate(null);
txtPrice.setText("");
txtUnits.setText("");
cmbStatus.setSelectedIndex(0);
txtAssetName.requestFocus();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        loadAssets();

    }//GEN-LAST:event_btnSearchActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       switch (userRole.toLowerCase()) {
        case "admin":
            new MainScreenAdmin().setVisible(true);
            break;
        case "manager":
            new MainScreenManager().setVisible(true);
            break;
        case "employee":
            new MainScreenEmployee().setVisible(true);
            break;
        default:
            JOptionPane.showMessageDialog(this, "Unknown role. Cannot go back.");
            return;
    }
    this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AssetRegisterScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AssetRegisterScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AssetRegisterScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AssetRegisterScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AssetRegisterScreen("admin").setVisible(true);
                new AssetRegisterScreen("manager").setVisible(true);
                new AssetRegisterScreen("employee").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnRegister;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cmbCategory;
    private javax.swing.JComboBox<String> cmbStatus;
    private com.toedter.calendar.JDateChooser datePurchase;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblAssets;
    private javax.swing.JTextField txtAssetName;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtUnits;
    // End of variables declaration//GEN-END:variables
}
