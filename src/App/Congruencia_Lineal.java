package App;

public class Congruencia_Lineal {
	public static final double E = 2.7182818284590452354;
    public static final double PI = 3.14159265358979323846;
    protected float X0;  //Raiz !=0
    protected float Porciento;
    protected int X1;
    protected int m; //intervalo >0
    protected int a, c; //Constantes !=0
    protected int valor;
 
    Congruencia_Lineal(){
       X0 = Porciento= 0;
       X1= m = a = c =0;
       valor=1;
    }
    
    /** 	Congruencia Lineal: el porcentaje de la congruencina Lineal est� basado en la formaci�n de
     * valores aleatorios a partir del ingreso de cuatro variables:
     * 	- Una raiz, X0
     * 	- Un intervalo, m
     * 	- Dos constantes, a, c.
     * 
     *  Este c�lculo, para determinar un porcentaje utiliza dos formulas:
     *  		Numerador=  (XO + c)* a; Calculada mediante la funci�n Calculo Primero.
     *  		Denominador= m-1;
     *  		Porcentaje:  Numerador/ denominador;
     */ 
    
   //Funciones de la clase:
    /*		public float CalculoPrimero(): Sirve para calcular el denominador de fracci�n Porcentaje.
     * 		public float CalculoPorcentaje(): Sirve para calcular el porcentaje.
     * 		public boolean comprobarVariables(float X0, int a, int c, int m): Funci�n que se utiliza para 
     * 			comprobar que los valores ingresados a la clase pertenecen a los valores aceptables para la
     *  		realizaci�n del calculo: si los valores son aceptados esta retorna una se�al true que permite la realizaci�n 
     *  		del c�lculo por medio de la funci�n CalculoPorcentaje.
     *  	public float CalculoPrimeraVez(float X0, int a, int c, int m): Funci�n principal de la clase, la cual act�a como
     *  		receptor de los valores ingresados por teclado.
     *  	 public float CalculoSegundo(): Funci�n que sirve para recalcular otro valor a partir de las variables ya ingresadas 
     *  		anteriormente, con lo que se respeta la generaci�n aleatoria de la congruencia lineal.
     */
    
    public float CalculoPrimero(){
    	int mientras;
    	if (valor ==1){
    		mientras=((int)X0 + c)*a;
    		X1= mientras%m; 
    		valor=2;
    	} else{
    		 mientras=((int)X1 + c)*a;
    		 X1= mientras%m; 
    		}
    	return (float) X1;
    }
    public float CalculoPorcentaje(){
    	Porciento= CalculoPrimero()/(m-1);
    	if(Porciento<0){
    		Porciento= Porciento *(-1);
    	}
    	if(Porciento>=1){
    		Porciento= Porciento - (int)Porciento;
    	}
    	System.out.println("Con X0: "+X0+"\na: "+a+"\nc: " +c+"\nm: "+m+"\nResultado: "+Porciento);
    	return Porciento;
    }
    public boolean comprobarVariables(float X0, int a, int c, int m){
    	if(X0!= 0 & a!=0 & a!=1 & c!=0 & m>1){
    		if(m>0){
    			this.X0=X0;
    			this.a=a;
    			this.c=c;
    			this.m=m;
    			return true;
    		}
       	}
    	return false;
    }    
    public float CalculoPrimeraVez(float X0, int a, int c, int m){
    	System.out.println("*Congruencia Lineal");
    	float ba= 0;
    	if(comprobarVariables(X0, a, c, m)==true){
    		ba= CalculoPorcentaje();
    		return ba;
    	}
    	else{
    		System.out.println("valores no validos");
    		return -1;
    	}
    }
    public float CalculoSegundo(){
	   float ab;
	   ab =CalculoPorcentaje();
	   return ab;
   }
    
}
