package App;

public class Distribucion_Weibull {
	public static final double E = 2.7182818284590452354;
    public static final double PI = 3.14159265358979323846;
	protected float X;
	protected float Resultado;
	protected float k;
	protected float lambda;
	
    /*	Esta clase est� destinada al c�lculo probabilistico mediante la distribuci�n Weibull:
     * 		Formula:
     * 			exponente=  -1 *((X/lambda)^k)
     * 			resultado=  1 - E^Exponente.
     * 
     *		Donde lambda debe ser mayor a 0, X mayor o igual a 0 y K debe ser mayor a 0.	
     */
	
	Distribucion_Weibull(){
		X=0;
		Resultado=0;
		k=0;
		lambda=0;
	}

	public boolean ValidarVariables(float X, float k, float lambda){
		if(X>=0 & lambda>0 & k>0){
			this.X=X;
			this.k=k;
			this.lambda=lambda;
			return true;
		}
		return false;
	} 
	
	public float CalcularResultado(float X, float k, float lambda){
		System.out.println("*Distribucion Weibull");
		float exponente;
		if(ValidarVariables(X,k,lambda)==true){
			exponente= (X/lambda);
			exponente= (float) ((-1)*Math.pow(exponente, k));
			Resultado= (float) (1 - Math.pow(E, exponente));
			System.out.println("Con X: "+X+"\nk: "+k+"\nlambda: "+lambda+"\nResultado: "+Resultado);
			return Resultado;
		}
		return -1;
	}
}
