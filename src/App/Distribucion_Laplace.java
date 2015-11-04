package App;

public class Distribucion_Laplace {
	public static final double E = 2.7182818284590452354;
    public static final double PI = 3.14159265358979323846;
    protected float b; //debe ser mayor a 0
	protected Float u;
	protected Float x;
	protected float Resultado;
	
	Distribucion_Laplace(){
		b=u=x=Resultado=0;
	}
	
	/* Clase destinana al c�lculo probabilistico de la distribuci�n de Laplace
	 *  Formula de la distribucion de Laplace:
	 *  	F(x)=0,5[1+sgn(x-u)(1-exp(-|x-u|/b))]
	 *  	variables:
	 *  	u, x, b que debe ser mayor a 0
	 *  
	 */
	
	public boolean ComprobarVariables(float b, float u, float x){
		if(b>0){
			this.b=b;
			this.u=u;
			this.x=x;
			return true;
		}
		return false;
		
	}
	public int SignumXU(float x, float u){
		float base;
		int resultado;
		if(x==u & x==0 & u==0){
			return 0;
		}else{
			base= x+u;
			if(base<0){
				base= base *-1;
			}
			resultado= (int) ((x+u)/base);
			return resultado;
		}
	}
	
	public float Calculo(float b, float u, float x){
		System.out.println("*Distribucion Laplace");
		float exponente, resultado;
		if(ComprobarVariables(b, u, x)==true){			
				exponente= (x - u);
			if(exponente<0){
				exponente= exponente *-1;
			}
			exponente= (exponente/b) *-1;
			Resultado= (float) (0.5*(1 + SignumXU(x,-u)*(1 - Math.pow(E, exponente))));
			System.out.println("Con x: "+x+"\nb: " +b+ "\nu: "+u+"\nResultado: "+ Resultado);
			return Resultado;
		}
		return -1;
	}

}
