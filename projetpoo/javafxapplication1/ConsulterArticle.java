/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javafxapplication1;

/**
 *
 * @author mohamed
 */
import java.sql.*;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;
import java.sql.Statement;
import java.sql.ResultSet;
public class ConsulterArticle extends javax.swing.JFrame {

    private final String JDBC_URL = "jdbc:mysql://localhost:3306/projetpoo";
    private final String USERNAME = "root";
    private final String PASSWORD = "";
    private int idArticle;
    
    public ConsulterArticle() {
        initComponents();
        setLocationRelativeTo(null);
        jButton2.setVisible(false);
        jButton3.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    
   

    private void displaySubmittedArticles() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT id_article, titre_article, etat FROM article")) {

            DefaultListModel<String> listModel = new DefaultListModel<>();
            String header = String.format("%-30s%-60s%-20s", "Id", "Titre de l'article", "Etat");
//            listModel.addElement("Id                                         Titre de l'article                                     Etat");
            listModel.addElement(header);
                while (resultSet.next()) {
                int articleId = resultSet.getInt("id_article");
                String titreArticle = resultSet.getString("titre_article");
                String etat = resultSet.getString("etat");
                String articleInfo;
                if(titreArticle.length()<15)
                    articleInfo = String.format("%-30d%-60s%-2s", articleId, titreArticle, etat);
                else
                    articleInfo = String.format("%-30d%-54s%-2s", articleId, titreArticle, etat);

                listModel.addElement(articleInfo);
            }

            jList1.setModel(listModel); // Set the populated model to the JList

        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle exception
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton2 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 500));

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 500));

        jButton1.setText("Consulter les articles soumis");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jScrollPane1.setBackground(new java.awt.Color(0, 153, 153));

        jList1.setBackground(new java.awt.Color(0, 153, 153));
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jList1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jList1.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jList1.setSelectionForeground(new java.awt.Color(0, 153, 153));
        jScrollPane1.setViewportView(jList1);

        jButton2.setBackground(new java.awt.Color(204, 51, 0));
        jButton2.setText("Affecter");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton6.setText("previous");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 153, 51));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Confirmer");
        jButton3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(300, 300, 300))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(355, 355, 355))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(206, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(141, 141, 141))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton6)
                .addGap(57, 57, 57)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(99, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 847, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                   displaySubmittedArticles();
                   jButton2.setVisible(true);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        int selectedIndex = jList1.getSelectedIndex();

    if (selectedIndex != -1) {
        // An item is selected, retrieve id_article
        String selectedItem = jList1.getSelectedValue();
//        System.out.println("ligne: "+selectedItem);
        String[] parts = selectedItem.split(" ");
//        System.out.println("partie: "+parts[2]);
        String etat = selectedItem.substring(selectedItem.lastIndexOf(" ") );
//        System.out.println("last index: "+selectedItem.lastIndexOf(" "));
        etat = etat.trim();
//        System.out.println("etat: "+etat);
        if(!("Id".equals(parts[0].trim()))){
        if("NA".equals(etat)){
        
        idArticle = Integer.parseInt(parts[0]);

        // Clear the list
        DefaultListModel<String> listModel = new DefaultListModel<>();
        jList1.setModel(listModel);

        // Populate the list with data of users who have type "com_sc"
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM utilisateur WHERE type = 'com_sc'")) {
            listModel.addElement("Id                                                 Nom                                    Prenom");
            while (resultSet.next()) {
                // Retrieve user information
                int userId = resultSet.getInt("id_user");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                // Add the user information to the list
                listModel.addElement(userId + "                                            " + nom + "                                     " + prenom);
            }

            jList1.setModel(listModel); // Set the populated model to the JList
            jButton3.setVisible(true);
            jButton2.setVisible(false);

        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle exception
        }
    } else {
        // No item is selected, display a message
        JOptionPane.showMessageDialog(this, "article est deja affecté.");
    }
    }  else JOptionPane.showMessageDialog(this, "ligne invalide.");
    
    }   else {
        // No item is selected, display a message
        JOptionPane.showMessageDialog(this, "Veuillez choisir une ligne.");
    }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        this.dispose();
        manipulation manipulationPage = new manipulation();
        manipulationPage.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        jButton2.setVisible(false);
         int selectedIndex = jList1.getSelectedIndex();

    if (selectedIndex != -1) {
        // An item is selected, retrieve id_article
        String selectedItem = jList1.getSelectedValue();
        String[] parts = selectedItem.split("  ");
         if(!("Id".equals(parts[0].trim()))){
        int idUser = Integer.parseInt(parts[0]);
        // Update the corresponding row in the 'article' table
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("UPDATE article SET id_user = ?, etat = 'UE' WHERE id_article = ?")) {
            statement.setInt(1, idUser);
            statement.setInt(2, idArticle);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "La ligne correspondante dans la table 'article' a été mise à jour avec succès.");
                this.dispose();
                ConsulterArticle NewWindow=new ConsulterArticle();
                NewWindow.setVisible(true);
            } else {
                System.out.println("Erreur lors de la mise à jour de la ligne dans la table 'article'.");
                JOptionPane.showMessageDialog(this, "Erreur lors de la mise à jour de la ligne dans la table 'article'.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle exception
        }
         }else JOptionPane.showMessageDialog(this, "ligne invalide.");
    } else {
        // No item is selected, display a message
        JOptionPane.showMessageDialog(this, "Veuillez choisir un membre du comité scientifique.");
    }
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(ConsulterArticle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsulterArticle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsulterArticle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsulterArticle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsulterArticle().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
