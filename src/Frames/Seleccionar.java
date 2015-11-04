
package Frames;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Felipe Hidalgo
 */
public class Seleccionar extends javax.swing.JDialog {
         
    public String ruta;
    public App.LeerArchivo direccion;
    public Seleccionar(java.awt.Frame parent, boolean modal) {
      
        super(parent, modal);
        initComponents();
        FileNameExtensionFilter filtroAbrir= new FileNameExtensionFilter(".txt","txt");  //filtro de archivos 
        jFileChooser1.setFileFilter(filtroAbrir);
        jFileChooser1.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);	
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jFileChooser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooser1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jFileChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser1ActionPerformed
        JFileChooser selectorArchivo= (JFileChooser)evt.getSource();
        String command = evt.getActionCommand();
            
        if (command.equals(JFileChooser.APPROVE_SELECTION)){
        File archivoSeleccionado = selectorArchivo.getSelectedFile();
       // JOptionPane.showMessageDialog(this, "Archivo: "+archivoSeleccionado.getName());
        String ruta= jFileChooser1.getSelectedFile().getAbsolutePath(); // toma la ruta del archivo seleccionado
        direccion= new App.LeerArchivo(ruta);   // envia como parametro la ruta del archivo  a la clase LeerArchivo

            this.setVisible(false);// permite ocultar la ventana de dialogo
        } else if (command.equals(JFileChooser.CANCEL_SELECTION)){
            JOptionPane.showMessageDialog(this,"Saldra de seleccion");
            this.setVisible(false); // permite ocultar la ventana de dialogo
        }
    }//GEN-LAST:event_jFileChooser1ActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser jFileChooser1;
    // End of variables declaration//GEN-END:variables
}
