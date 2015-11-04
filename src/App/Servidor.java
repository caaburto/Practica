
package App;

import java.io.*;
import java.net.*;

/**
 *
 * @author Felipe Hidalgo
 */
public class Servidor implements Runnable{
    public String[] valor;
    final int puerto = 5000;
    static ServerSocket sc;
    static Socket so;
    static DataOutputStream salida; // respuesta que se le dara al cliente, en este caso segun el ws su dato probabilistico  
    String mensajeRecibido ="";
    static DataInputStream entrada;
    public  void Servidor(String [] datos) throws IOException{  // aca puse el throws  por el sc.close(); en el catch
    // se guardara aca lo que recivamos del cliente
     this.valor=datos;
     Thread hilo = new Thread(this);
     hilo.start();
   
           
    }
    @Override
    public void run() {
       // int count = 0;
          try{
     sc= new ServerSocket(puerto); //el puerto y cuantos clientes puede atender
     so= new Socket();
     while(true){
     System.out.println("Esperando una conexión:");
     so = sc.accept();
     System.out.println("Un cliente se ha conectado.");
     entrada = new DataInputStream(so.getInputStream());
     salida = new DataOutputStream(so.getOutputStream());
     System.out.println("Confirmando conexion al cliente....");
     mensajeRecibido = entrada.readUTF();  // no necesito una segunda respuesta del cliente solo la primera con su  id
     
      if("1".equals(mensajeRecibido)){
     salida.writeUTF(valor[0]);  // aca deberia mandar el dato correspondiente al cliente
   
     }
       if("2".equals(mensajeRecibido)){
     salida.writeUTF(valor[1]+","+valor[2]+","+valor[3]);  // aca deberia mandar el dato correspondiente al cliente
     }
       if("3".equals(mensajeRecibido)){
     salida.writeUTF(valor[4]+","+valor[5]);  // aca deberia mandar el dato correspondiente al cliente
     }
       if("4".equals(mensajeRecibido)){
     salida.writeUTF(valor[6]);  // aca deberia mandar el dato correspondiente al cliente
     }
     System.out.println(mensajeRecibido);    
    
     System.out.println("Cerrando conexión...");
     so.close();//Aqui se cierra la conexión con el cliente
    // count++;
     
     /*if (count == 4){
      sc.close();
      break;
     }*/
     } 
          } 
          
          catch(Exception e){
     System.out.println("Error: "+e.getMessage());
   
    }
     
    }
             
      public void stop() throws IOException{
      
      so.close();
      sc.close();
      }   
}
