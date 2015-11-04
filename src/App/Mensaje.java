
package App;

/**
 *
 * @author Felipe Hidalgo
 */
public class Mensaje {
   public String [] mensaje;
   public int largo;
    public Mensaje(String [] arreglo, int lon){ // aca voy a capturar el mensaje para hacerlo 
      this.mensaje = arreglo; //accesible desde otras clases y para poder enviarlo
      this.largo= lon;
    for(int i=0; i<largo;i++){
      System.out.println(mensaje[i]);
    } 
    }  
    
   /* public void leer(){
    for(int i=0; i<largo;i++){
      System.out.println(mensaje[i]);
    }
    }*/
 public int obtenerLargo(){
     return largo;
 }
public  String[] obtenerMensaje (){
    return mensaje;
}
  
}
