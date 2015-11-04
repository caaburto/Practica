package App;

public class Distribucion_T_Student {
	public static final double E = 2.7182818284590452354;
    public static final double PI = 3.14159265358979323846;
    protected float Resultado;
    protected int n; //libertad
    protected float t; //Area
    
    /*	Esta clase, posee una aproximaci�n calculada mendiante la formula:
     * si t<0
     * 	  parA=integral((double) t,(double)0, 500);
     * sino 
     *    parA=integral((double) 0,(double)t, 500);
     *    
     *   Resultado:
     *   		Si t<0:	
	 *				Resultado1= (float) (0.5-(parA));
	 *			Sino, si t=0:
	 *	 			Resultado1= (float) 0.5;
	 *			  Sino:
	 *				Resultado1= (float) (0.5 +(parA));
	 *	Esta aproximaci�n fue obtenida por parte del Profesor Universitario Venezolano  �ngel Arvelo. 			
     */
    
    Distribucion_T_Student(){
    	Resultado=0;
    	n=0;
    	t=0;
    }
    public boolean Validar(int n, float t){
    	if(n>0){
    		this.n=n;
    		this.t=t;
    		return true;
    	}//Mensaje error.
    	return false;
    }

 	//Funcion T de Student
	public float CalculoStudent(int n, float t){
	System.out.println("*Distribucion T de Stundent");	
	 if(Validar(n,t)==true){
		 float Resultado1;
			double partPrin, parA;
				//convalidaci�n
			if(t<0){
				parA=integral((double) t,(double)0, 500);
				//parA=integral2((double) t,(double)0, 500);
			}else{
				parA=integral((double) 0,(double)t, 500);
				//parA=integral2((double) 0,(double)t, 500);
			}
			
			if(t<0){	
					Resultado1= (float) (0.5-(parA));
				} else if(t==0){
					Resultado1= (float) 0.5;
				}	else{
					Resultado1= (float) (0.5 +(parA));
				}
			System.out.println("Con n: "+n+"\nt: "+t+"\nResultado: "+Resultado1);
			if(Resultado1>=1){
				Resultado1=1;
			}
			this.Resultado= Resultado1;
			return Resultado;
	 }
	 return -1;
	}
	
	//Regla del Trapecio.
	public double integral( double a, double b, int d){
		double h=(b-a)/d;
	
			double suma=(f(a)+f(b))/2;
			for(int i=1; i<d; i++){
				suma+=f(a+i*h);
			//	System.out.println(i+": "+suma);
			}
			System.out.println(suma);
			System.out.println(suma*h);
			return  suma*h;
	
	}
	public double f(double x){
		double div= n/2, partPrin, exponente, gamma, XALTA;
		exponente=  (n+1)/2;
		XALTA=Math.pow(x, 2);
		gamma=  la_gamma(exponente);
		if(div== 1/2){
			partPrin =PI*Math.sqrt(n) * Math.pow(1 + XALTA, exponente);
		}else{
			partPrin =Math.sqrt(PI*n) * la_gamma(n/2) * Math.pow(1 + Math.pow(x, 2), exponente );
			}
		
		return gamma/partPrin;
	}
	
	public double integral2(double a, double b, int n,  double k){
        if(n%2==1) n++;     //hace que el n�mero de divisiones sea par
        double h=(b-a)/n;
        double suma=f(a)+f(b);
        for(int i=1; i<n; i+=2){
            suma+=4*f(a+i*h);
        }
        for(int i=2; i<n; i+=2){
            suma+=2*f(a+i*h);
        }
        return (suma*h/3);
    }
	
	//funcion Gamma de Lanczos
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
