/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Main.Archivo;
import javax.swing.JOptionPane;

/**
 *
 * @author aybso
 */
public class Editor extends javax.swing.JFrame {

    /**
     * Creates new form Editor
     */
    Archivo archivo;
    boolean modificado;

    public Editor(Archivo a) {
        initComponents();
        setLocationRelativeTo(null);
        modificado = false;
        archivo = a;

        lblFile.setText(a.getNombre());
        lblUser.setText(Main.Main.user.getUsuario());
        editor.setText(a.getContenido());
        editor.setRequestFocusEnabled(true);
        Main.Main.bitacora.add(Main.Main.user.getUsuario(), "Abrio el archivo " + archivo.getNombre());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        editor = new javax.swing.JEditorPane();
        jLabel1 = new javax.swing.JLabel();
        lblUser = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblFile = new javax.swing.JLabel();
        btnCerrar = new java.awt.Button();
        btnGuardar = new java.awt.Button();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        editor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 168, 73)));
        editor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        editor.setForeground(new java.awt.Color(41, 168, 73));
        editor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                editorKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(editor);

        jLabel1.setBackground(new java.awt.Color(153, 153, 153));
        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("EDD DRIVE /");

        lblUser.setBackground(new java.awt.Color(153, 153, 153));
        lblUser.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        lblUser.setForeground(new java.awt.Color(41, 168, 73));
        lblUser.setText("USER");

        jLabel2.setBackground(new java.awt.Color(153, 153, 153));
        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("File:");

        lblFile.setBackground(new java.awt.Color(153, 153, 153));
        lblFile.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        lblFile.setForeground(new java.awt.Color(41, 168, 73));
        lblFile.setText("File");

        btnCerrar.setBackground(new java.awt.Color(255, 51, 51));
        btnCerrar.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        btnCerrar.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrar.setLabel("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(41, 168, 73));
        btnGuardar.setEnabled(false);
        btnGuardar.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setLabel("Guardar ...");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblUser)
                        .addGap(215, 215, 215)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblFile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 290, Short.MAX_VALUE)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(lblUser)
                        .addComponent(jLabel2)
                        .addComponent(lblFile))
                    .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void editorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_editorKeyPressed
        btnGuardar.setEnabled(true);
        modificado = true;
    }//GEN-LAST:event_editorKeyPressed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        archivo.setContenido(editor.getText().strip());
        JOptionPane.showMessageDialog(null, "Guardado");
        modificado = false;
        btnGuardar.setEnabled(false);
        Main.Main.bitacora.add(Main.Main.user.getUsuario(), "Guardo cambios dentro del archivo " + archivo.getNombre());
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        if (modificado) {
            int opc = JOptionPane.showConfirmDialog(null, "¿Desea guardar los cambios?", "Guardar", JOptionPane.OK_OPTION);

            if (opc == JOptionPane.OK_OPTION) {
                archivo.setContenido(editor.getText().strip());
                JOptionPane.showMessageDialog(null, "Guardado");
                modificado = false;
                btnGuardar.setEnabled(false);
                Main.Main.bitacora.add(Main.Main.user.getUsuario(), "Guardo cambios dentro del archivo " + archivo.getNombre());
            }
            
        }
        
        this.setVisible(false);
    }//GEN-LAST:event_btnCerrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btnCerrar;
    private java.awt.Button btnGuardar;
    private javax.swing.JEditorPane editor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFile;
    private javax.swing.JLabel lblUser;
    // End of variables declaration//GEN-END:variables
}