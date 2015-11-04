package App;

public class Distribucion_Poisson {
	public static final double E = 2.7182818284590452354;
    public static final double PI = 3.14159265358979323846;
	protected float lamda; // lamda debe ser positivo
	protected float x;		//x debe ser positiva
	protected double valorDis;
	
	/*Formula de la Distribucion de Poisson:
	 *     P(x) = ((e^(-lamda)) - (lamda^x))/x!
	 * 	 En donde x>0 y lamda>0
	 */

	Distribucion_Poisson(){
		lamda= 0;
		x= 0;
		valorDis= 0;
	}
	
//Funciones comprobadoras de valores ingresados.	
	public boolean Comprobacion(float lamda, float x){
		if(lamda>0 & x>0){
			this.lamda = lamda;
			this.x = x;
			return true;
		}
			System.out.println("Valor ingresado en lamda no valido");
		return false;
	}

//Funcion Calculadora de la distribuci�n. 
	public float CalculoPoisson(){
		double valorDis;
		float factorialx;
		factorialx= CalculoFactorialX(x);
		valorDis= ((Math.pow(E, -lamda))* Math.pow(lamda, x))/factorialx;
		this.valorDis= valorDis;
		System.out.println("Con Lamda: " +lamda+"\nx: "+x+"\nResultado: " +this.valorDis);
		return (float) this.valorDis;
	}
		

	/*	Existen dos casos en el calculo de factorial: 
	 * 	 si el X es entero se utiliza la formula T(n)= n *(n-1)!
	 * 	sino se debe implementar la funcion gamma...
	 */
	
	public float CalculoFactorialX(float x){
		float factorial=1;
		float resto;
		resto= x - (int)x;
		if (resto == 0){
			for(int i=1;i<=x;i++){
				factorial= factorial*i;	
			}
		}
		else{
			factorial=(float)(la_gamma(x+1));
		}
		return  factorial;
	}
	
	public float NuevoValor(float lamda, float x){
		System.out.println("*Distribucion Poisson");
		if((Comprobacion(lamda, x)==true)){
			return CalculoPoisson();
		}		
		return -1;
	}

	public double la_gamma(double x){		
		double[] p = {0.99999999999980993, 676.5203681218851, -1259.1392167224028,
			     	  771.32342877765313, -176.61502916214059, 12.507343278686905,
			     	  -0.13857109526572012, 9.9843695780195716e-6, 1.5056327351493116e-7};
		int g = 7;
		if(x < 0.5) return Math.PI / (Math.sin(Math.PI * x)*la_gamma(1-x));
		x -= 1;
		double a = p[0];
		double t = x+g+0.5;
		for(int i = 1; i < p.length; i++){
			a += p[i]/(x+i);
			}
		return Math.sqrt(2*Math.PI)*Math.pow(t, x+0.5)*Math.exp(-t)*a;
	}

	
}
