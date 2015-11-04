
package App;

import java.io.*;
import java.net.*;
import javax.swing.JFrame;

/**
 *
 * @author Felipe Hidalgo
 */
public class Cliente {
    
    //public FrameCliente a = new FrameCliente();
    
     public String IP ="";  // aca se deberia poner la IP del servidor al que se quere conectar
     public String ID="";
     static final int puerto =5000;
     static  DataInputStream datos_entrada;  // recibira los datos del servidor (distribucion probabilistica)
     static  DataOutputStream identificador; //  se enviara  el identificador del cliente al servidor
     static  Socket sckCliente;
     static String mensajeRecibido = "";
 
    public String  Cliente(String ip, String id){
        
        this.IP=ip;
        this.ID=id;
        
          System.out.println("la ip es");
          System.out.println(IP);
          System.out.println("la opcion es");
          System.out.println(ID);
   
       
         try{
            sckCliente = new Socket(IP,puerto);
            identificador = new DataOutputStream (sckCliente.getOutputStream());
            identificador.writeUTF(ID);  // envia id de cliente
            
        }catch(IOException e){
            System.out.println(e);
        }
                
                try {
               mensajeRecibido="";
              datos_entrada = new DataInputStream(sckCliente.getInputStream() ); // recibimos las respuestas del servidor
           
             mensajeRecibido= datos_entrada.readUTF();
             System.out.println(mensajeRecibido);     // el mensajendebe contener los datos para cada cliente
             sckCliente.close();
               }catch(IOException e){
            System.out.println(e);
        }
    // aca define que frame se mostrara
                
               return(mensajeRecibido);
        
    }
   
}
