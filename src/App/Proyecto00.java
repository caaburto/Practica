/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import javax.swing.JFrame;

/**
 *
 * @author Carlos
 */
public class Proyecto00 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Frames.Ventana VentanaPrincipal = new Frames.Ventana();
        VentanaPrincipal.setLocationRelativeTo(null);
        VentanaPrincipal.setTitle("Gestor y Colector de Retrasos");
        VentanaPrincipal.setResizable(false);
        VentanaPrincipal.setVisible(true);
        VentanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
