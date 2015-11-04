package App;

public class Distribucion_Normal_Estandar {
	public static final double E = 2.7182818284590452354;
    public static final double PI = 3.14159265358979323846;
	protected float Z;
	protected float Resultado;
	protected boolean negativo;

	/** 
	 * La formula que entrega resultados aproximados para el uso de esta distribucion es:
	 *    D(z)= 1 - e^(a/b)
	 *    donde 
	 *    	a= -83z^3 - 351 z^2 + 562 z
	 *    	b= 165z + 703
	 * 	 		para los z entre  0< z < 5,5
	 * 
	 *   Al resultado de D(z) se debe redondear con math.rint(D(z) * 1000)/1000;
	 *   Valores mayores al 4,3 se aproximan a 1.
	 */
	
	Distribucion_Normal_Estandar(){
		Z= 0;
		Resultado=0;
		negativo=false;
	}
	
	public boolean ComprobarZ(float Z){
		if(( Z>0) & (Z<5.5)){
				this.Z= Z;
				return true;
		}else if(Z>-5.5){
					float a;
					a=(float) 5.5;
					this.Z=  a+Z;
					negativo=true;
					return true;
				}
		return false;
	}
	
	public float CalculoDistribucionNormalEstandar(float Z){
		System.out.println("*Distribucion Normal Estandar");
		float Z1= Z;
		if(ComprobarZ(Z)==true){
			float a, b, c;
			Z=this.Z;
			a= -83*Z*Z*Z - 351*Z*Z + 562*Z;
			b= 165*Z + 703;
			c= a/b;
			Resultado= 1- (float) Math.pow(E, c) ;
			if(Resultado<0){
				Resultado= Resultado*(-1);
			}
			this.Z= Z1;
			System.out.println("Con un Z: "+ this.Z +"\nProbabilidad: "+Resultado);
			return this.Resultado;
		}
		return -1;
	}
	
}
