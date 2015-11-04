
package App;

import java.io.*;


/**
 *
 * @author Felipe Hidalgo
 */
public class LeerArchivo {
    
      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;  
      public Mensaje mensaje;
     
 public  LeerArchivo(String dir){
       String ruta = dir;

      try {               
          archivo = new File (ruta);  // recive la ruta del archivo seleccionado y lo lee
          fr = new FileReader (archivo);
          br = new BufferedReader(fr);
 
         // Lectura del fichero
        String linea;
        
       linea=br.readLine();
       System.out.println(linea);
       System.out.println("\n");
       linea=linea.replace("$",""); //aca elimino el simbolo $
       System.out.println(linea);
        System.out.println("\n");    
       String[] arraySepara=linea.split("#");   // primera separacion del string    
       for(int i=0;i<arraySepara.length;i++){
       System.out.println(arraySepara[i]);
           }
       System.out.println("\n"); 
       mensaje = new Mensaje(arraySepara,arraySepara.length); // creo la clase mensaje y le envio el arreglo de strings separado
       
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
 }
}
