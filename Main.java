/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainJFrame.java
 *
 * Created on Nov 25, 2013, 11:04:14 PM
 */
package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import obfuscator.FileAndDirectory;
import obfuscator.FileParser;


/**
 *
 * @author cuonglb
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form MainJFrame
     */
    public Main(String title) {
        super(title);
        initComponents();
    }
    private static ArrayList<String> listFile;
    public static ArrayList listExistedName;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabMain = new javax.swing.JTabbedPane();
        tabDirectory = new javax.swing.JPanel();
        lblSrcDir = new javax.swing.JLabel();
        txtSrcDir = new javax.swing.JTextField();
        btSrcDir = new javax.swing.JButton();
        lblDesDir = new javax.swing.JLabel();
        txtDesDir = new javax.swing.JTextField();
        btnDesDir = new javax.swing.JButton();
        btnObfuscateDir = new javax.swing.JButton();
        btnResetDir = new javax.swing.JButton();
        btnCancelDir = new javax.swing.JButton();
        tabFile = new javax.swing.JPanel();
        lblSrcFile = new javax.swing.JLabel();
        txtSrcFile = new javax.swing.JTextField();
        btnSrcFile = new javax.swing.JButton();
        btnDesFile = new javax.swing.JButton();
        txtDesFile = new javax.swing.JTextField();
        lblDesFile = new javax.swing.JLabel();
        btnFileObfuscate = new javax.swing.JButton();
        btnFileReset = new javax.swing.JButton();
        btnFileCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblSrcDir.setText("Source directory:");

        btSrcDir.setText("Browse");
        btSrcDir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSrcDirActionPerformed(evt);
            }
        });

        lblDesDir.setText("Destination directory:");

        btnDesDir.setText("Browse");
        btnDesDir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesDirActionPerformed(evt);
            }
        });

        btnObfuscateDir.setText("Obfuscate");
        btnObfuscateDir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObfuscateDirActionPerformed(evt);
            }
        });

        btnResetDir.setText("Reset");
        btnResetDir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetDirActionPerformed(evt);
            }
        });

        btnCancelDir.setText("Cancel");
        btnCancelDir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelDirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabDirectoryLayout = new javax.swing.GroupLayout(tabDirectory);
        tabDirectory.setLayout(tabDirectoryLayout);
        tabDirectoryLayout.setHorizontalGroup(
            tabDirectoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabDirectoryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabDirectoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDesDir)
                    .addComponent(lblSrcDir))
                .addGroup(tabDirectoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabDirectoryLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btnResetDir, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnObfuscateDir, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86)
                        .addComponent(btnCancelDir, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tabDirectoryLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addGroup(tabDirectoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDesDir, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSrcDir, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(tabDirectoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDesDir)
                    .addComponent(btSrcDir))
                .addContainerGap())
        );
        tabDirectoryLayout.setVerticalGroup(
            tabDirectoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabDirectoryLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(tabDirectoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabDirectoryLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(lblSrcDir, javax.swing.GroupLayout.DEFAULT_SIZE, 15, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabDirectoryLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtSrcDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btSrcDir))
                .addGap(18, 18, 18)
                .addGroup(tabDirectoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabDirectoryLayout.createSequentialGroup()
                        .addComponent(btnDesDir, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                        .addGap(38, 38, 38))
                    .addGroup(tabDirectoryLayout.createSequentialGroup()
                        .addGroup(tabDirectoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabDirectoryLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lblDesDir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtDesDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(tabDirectoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnResetDir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnObfuscateDir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCancelDir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(35, 35, 35))
        );

        tabMain.addTab("Obfuscate Directory", tabDirectory);

        lblSrcFile.setText("Source file:");

        btnSrcFile.setText("Browse");
        btnSrcFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSrcFileActionPerformed(evt);
            }
        });

        btnDesFile.setText("Browse");
        btnDesFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesFileActionPerformed(evt);
            }
        });

        lblDesFile.setText("Destination file:");

        btnFileObfuscate.setText("Obfuscate");
        btnFileObfuscate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFileObfuscateActionPerformed(evt);
            }
        });

        btnFileReset.setText("Reset");
        btnFileReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFileResetActionPerformed(evt);
            }
        });

        btnFileCancel.setText("Cancel");
        btnFileCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFileCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabFileLayout = new javax.swing.GroupLayout(tabFile);
        tabFile.setLayout(tabFileLayout);
        tabFileLayout.setHorizontalGroup(
            tabFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabFileLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSrcFile)
                    .addComponent(lblDesFile))
                .addGap(34, 34, 34)
                .addGroup(tabFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabFileLayout.createSequentialGroup()
                        .addComponent(btnFileReset, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(98, 98, 98)
                        .addComponent(btnFileObfuscate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                        .addComponent(btnFileCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtSrcFile)
                    .addComponent(txtDesFile))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(tabFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDesFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSrcFile))
                .addContainerGap())
        );
        tabFileLayout.setVerticalGroup(
            tabFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabFileLayout.createSequentialGroup()
                .addGroup(tabFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tabFileLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(lblSrcFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(tabFileLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(tabFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSrcFile, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSrcFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(tabFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabFileLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(lblDesFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(tabFileLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(tabFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDesFile)
                            .addComponent(btnDesFile))))
                .addGap(18, 18, 18)
                .addGroup(tabFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabFileLayout.createSequentialGroup()
                        .addComponent(btnFileReset, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(1, 1, 1))
                    .addGroup(tabFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnFileObfuscate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnFileCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(36, 36, 36))
        );

        tabMain.addTab("Obfuscate File", tabFile);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabMain)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabMain)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnResetDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetDirActionPerformed
        // TODO add your handling code here:
        txtDesDir.setText("");
        txtSrcDir.setText("");
    }//GEN-LAST:event_btnResetDirActionPerformed

    private void btnDesDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesDirActionPerformed
        // TODO add your handling code here:
        txtDesDir.setText(browseDesFolder());
    }//GEN-LAST:event_btnDesDirActionPerformed

    private void btSrcDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSrcDirActionPerformed
        // TODO add your handling code here:
        String folderName = browseSourceFolder();
        txtSrcDir.setText(folderName);
        txtDesDir.setText(folderName + "_Obfuscated");
    }//GEN-LAST:event_btSrcDirActionPerformed

    private void btnObfuscateDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObfuscateDirActionPerformed
        // TODO add your handling code here:
        listExistedName = new ArrayList();
        listFile = new ArrayList<String>();
        try {
            FileAndDirectory.copyDirectoryOneLocationToAnotherLocation(new File(txtSrcDir.getText()), new File(txtDesDir.getText()));
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
        String folderName = txtDesDir.getText();
        getListFile(folderName);
        if (listFile.size() == 0) {
            JOptionPane.showMessageDialog(null, "Directory is not contain any java file", "Thông báo", 0);
            return;
        }
        ArrayList<ArrayList<String>> listClasses = new ArrayList<ArrayList<String>>();
        try {
            for (int i = 0; i < listFile.size(); i++) {
                FileParser fp = new FileParser();

                ArrayList<ArrayList<String>> classes = fp.RenameMethod(listFile.get(i), listFile);
                if (classes != null && classes.size() > 0) {
                    for (int j = 0; j < classes.size(); j++) {
                        listClasses.add(classes.get(j));
                    }
                }

            }
            if (listClasses != null && listClasses.size() > 0) {
                for (int i = 0; i < listClasses.size(); i++) {
                    ArrayList<String> element = listClasses.get(i);
                    String oldName = element.get(0);
                    String newName = element.get(1);
                    File oldFile = new File(oldName);
                    File newFile = new File(newName);
                    oldFile.renameTo(newFile);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error", "Message", 0);
            return;
        }
        JOptionPane.showMessageDialog(null, "Success", "Message", 1);
    }//GEN-LAST:event_btnObfuscateDirActionPerformed

    private void btnDesFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesFileActionPerformed
        // TODO add your handling code here:
        txtDesFile.setText(browseDesFile());
    }//GEN-LAST:event_btnDesFileActionPerformed

    private void btnFileResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFileResetActionPerformed
        // TODO add your handling code here:
        txtDesFile.setText("");
        txtSrcFile.setText("");
    }//GEN-LAST:event_btnFileResetActionPerformed

    private void btnFileObfuscateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFileObfuscateActionPerformed
        // TODO add your handling code here:
        listExistedName = new ArrayList();
        listFile = new ArrayList();
        try {
            FileAndDirectory.copyFile(new File(txtSrcFile.getText()), new File(txtDesFile.getText()));
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
        listFile.add(txtDesFile.getText());
        ArrayList listClasses = new ArrayList();
        try {
            for (int i = 0; i < listFile.size(); i++) {
                FileParser fp = new FileParser();

                ArrayList classes = fp.RenameMethod((String) listFile.get(i), listFile);
                if ((classes != null) && (classes.size() > 0)) {
                    for (int j = 0; j < classes.size(); j++) {
                        listClasses.add(classes.get(j));
                    }
                }
            }

            if ((listClasses != null) && (listClasses.size() > 0)) {
                for (int i = 0; i < listClasses.size(); i++) {
                    ArrayList element = (ArrayList) listClasses.get(i);
                    String oldName = (String) element.get(0);
                    String newName = (String) element.get(1);
                    File oldFile = new File(oldName);
                    File newFile = new File(newName);
                    oldFile.renameTo(newFile);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error", "Message", 0);
            return;
        }
        JOptionPane.showMessageDialog(null, "Success", "Message", 1);

    }//GEN-LAST:event_btnFileObfuscateActionPerformed

    private void btnSrcFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSrcFileActionPerformed
        // TODO add your handling code here:
        txtSrcFile.setText(browseSourceFile());
    }//GEN-LAST:event_btnSrcFileActionPerformed

    private void btnCancelDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelDirActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnCancelDirActionPerformed

    private void btnFileCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFileCancelActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnFileCancelActionPerformed

    private String browseSourceFolder() {
        JFileChooser j = new JFileChooser();
        j.setDialogTitle("Lựa chọn thư mục");
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        j.setAcceptAllFileFilterUsed(false);
        String file = "";
        if (j.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            file = j.getSelectedFile().getAbsolutePath();
        }
        return file;
    }

    private String browseSourceFile() {
        JFileChooser j = new JFileChooser();
        FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter("Java file (*.java)", "java");
        j.setFileFilter(xmlfilter);
        j.setDialogTitle("Lựa chọn file nguồn");
        j.setFileSelectionMode(JFileChooser.FILES_ONLY);
        j.setAcceptAllFileFilterUsed(false);
        String file = "";
        if (j.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            file = j.getSelectedFile().getAbsolutePath();
        }
        return file;
    }

    private String browseDesFolder() {
        JFileChooser j = new JFileChooser();
        j.setDialogTitle("Lựa chọn thư mục");
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        j.setAcceptAllFileFilterUsed(false);
        String file = "";
        if (j.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            file = j.getSelectedFile().getAbsolutePath();
        }
        return file;

    }

    private String browseDesFile() {
        JFileChooser j = new JFileChooser();
        j.setDialogTitle("Choose destination file");
        j.setFileSelectionMode(0);
        j.setAcceptAllFileFilterUsed(false);
        String file = "";
        if (j.showSaveDialog(this) == 0) {
            file = j.getSelectedFile().getAbsolutePath();
        }
        return file;
    }

    private void getListFile(String DirPath) {
        File directory = new File(DirPath);
        File[] fList = directory.listFiles();

        for (File file : fList) {
            if (file.isFile()) {
                String ext = FileAndDirectory.getExtentionFileName(file);
                if (ext.equals("java")) {
                    listFile.add(file.getAbsolutePath());
                }
            } else {
                getListFile(file.getAbsolutePath());
            }
        }
    }



    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Main("Java Obfuscator - Designed by Manh Khuc").setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btSrcDir;
    private javax.swing.JButton btnCancelDir;
    private javax.swing.JButton btnDesDir;
    private javax.swing.JButton btnDesFile;
    private javax.swing.JButton btnFileCancel;
    private javax.swing.JButton btnFileObfuscate;
    private javax.swing.JButton btnFileReset;
    private javax.swing.JButton btnObfuscateDir;
    private javax.swing.JButton btnResetDir;
    private javax.swing.JButton btnSrcFile;
    private javax.swing.JLabel lblDesDir;
    private javax.swing.JLabel lblDesFile;
    private javax.swing.JLabel lblSrcDir;
    private javax.swing.JLabel lblSrcFile;
    private javax.swing.JPanel tabDirectory;
    private javax.swing.JPanel tabFile;
    private javax.swing.JTabbedPane tabMain;
    private javax.swing.JTextField txtDesDir;
    private javax.swing.JTextField txtDesFile;
    private javax.swing.JTextField txtSrcDir;
    private javax.swing.JTextField txtSrcFile;
    // End of variables declaration//GEN-END:variables
}
