package App;

public class Ingreso_Usuario {
	protected float Valor;
	
	/* 	Clase que comprueba que el valor ingresado por el usuario pertenezca a un 
	 * valor probabilistico, es decir que exista en el intervalo [0,1].
	 */
	
	Ingreso_Usuario(){
		Valor =0;
	}
	
	public float IngresoValor(float valor){
		System.out.println("*Ingreso Usuario");
		if(valor<=1 & valor>=0){
			Valor=valor;		
			System.out.println("Valor: "+Valor);
		}
		return valor;
	}
	
}
