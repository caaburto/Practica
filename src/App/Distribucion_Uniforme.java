package App;

public class Distribucion_Uniforme {
	public static final double E = 2.7182818284590452354;
    public static final double PI = 3.14159265358979323846;
	protected float X;
	protected float a;
	protected float b;
	protected float resultado;
	
	/*	Esta clase est� destinada al c�lculo probabilistico mediante la distribuci�n Uniforme:
     * 		Formula:
     * 			Resultado variable:
     * 					Si X<a, resultado =0.
     * 					Sino Si X>=b, resultado = 1.
     * 						 Sino: resultado= (X-a)/(X-b)
     *		Donde a debe ser menor a b
     */

	Distribucion_Uniforme(){
		X=0;
		a=0;
		b=0;
	}
	
	
	public boolean ValidacionIngreso(float X, float a, float b){
		if(a<b){
			this.a=a;
			this.b=b;
			this.X=X;
			return true;
		}
		return false;
	}
	
	public float GenerarValor(float X, float a, float b){
		System.out.println("*Distribucion Uniforme");
		if(ValidacionIngreso(X,a,b)==true){
			if(X<a){
				resultado=0;
			}else if(X>=b){
				resultado=1;
			}else{
				resultado= (X-a)/(X-b);
			}
			System.out.println("Con X: "+X+"\na: "+a+"\nb: "+b+"\nResultado: "+resultado);
			return resultado;
		}
		return -1;
	}
	
}
