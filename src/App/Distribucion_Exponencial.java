package App;

public class Distribucion_Exponencial {
	public static final double E = 2.7182818284590452354;
    public static final double PI = 3.14159265358979323846;
    protected float X;
    protected float lambda;
    protected float resultado;
    
    /*	Esta clase est� destinada al c�lculo probabilistico mediante la distribuci�n Exponencial:
     * 		Formula:
     * 			exponente=  (-1) *( lambda *X)
     * 			resultado= 1 - E^exponente.
     * 
     *		Donde lambda debe ser mayor a 0.	
     */
    
    Distribucion_Exponencial(){
    	X=0;
    	lambda=0;
    	resultado=0;
    }
    
    public boolean ValidarDatos(float X, float lambda){
    	if(lambda>0){
    		this.X=X;
    		this.lambda=lambda;
    		return true;
    	}
    	return false;
    } 
    
    public float CalcularResultado(float X, float lambda){
		System.out.println("*Distribucion Exponencial");
		float exponente;
		if(ValidarDatos(X,lambda)==true){
			exponente= (-1) *(lambda *X);
			resultado= (float) (1- Math.pow(E, exponente));
			System.out.println("Con X: "+X+"\nlambda: "+lambda+"\nResultado: "+resultado);
			return resultado;
		}
		return -1;
    }
    
}
