package App;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Generacion_Ventanas_Emergentes {
	/* Clase encargada de la configuraci�n interna de los mensajes emergentes que se implementan en la interfaz:
	 * 		-Base para los Mensajes de Error.
	 * 		-Funci�n de Aproximaci�n de Valor: 
	 * 		-Mensajes de ingreso de Variables con restricciones del tipo de dato ingresado impidiendo, 
	 * 			en la mayor�a de los casos, errores.
	 * 		-Mensajes y configuraci�n de los tipos de distribuci�n existentes en la ComboBox de la clase Maquinaria
	 * 		-Mensajes visibles en la intefaz.
	 */
	public void MensajeError(String texto, String tipoError, int tipo){
		if(tipo==1){
		JOptionPane.showMessageDialog(null, texto, "ERROR: "+ tipoError, JOptionPane.WARNING_MESSAGE, null);
		}else if(tipo==2){
			JOptionPane.showMessageDialog(null, texto, "ERROR: "+ tipoError, JOptionPane.ERROR_MESSAGE, null);
		}else if(tipo==0){
			JOptionPane.showMessageDialog(null, texto, "ERROR: "+ tipoError,JOptionPane.PLAIN_MESSAGE, null);
		}
	}
	public float AproximacionValor(float aproximado){
		aproximado= (float) Math.rint(aproximado*100)/100;
		return aproximado;
	} 
	public String MensajeUnaVariable(String NVar1, String tipo1){
		String Variable=" ";
		int result;
		JTextField Field1 = new JTextField(5);
		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel(NVar1+":"));
		myPanel.add(Field1);
		result = JOptionPane.showConfirmDialog(null, myPanel, "Ingrese Valores", JOptionPane.OK_CANCEL_OPTION);
		System.out.println("Resultado eleccion:"+result +" Elemento elegido: "+Field1.getText()+"-");
		
		if(Field1.getText().isEmpty()){
			System.out.println("Vacio");
			return Variable="-1";
		}
		if(result== -1 | result==2){
			return Variable="-1"; 
		}
		 if (result == 0) {
	   	  String a=Field1.getText();
	   	  if(tipo1.contentEquals("int")==true){
	   		  System.out.println("valor ingresado: "+ a);
	   		  if(a.contains(".")==true){
	   			  MensajeError("El valor ingresado no corresponde a un Entero", "Error", 2);
	   			 return Variable="-1"; 
	   		  }else{
		   		  Integer medio;
		   		  medio= Integer.valueOf(a);
		   		  a= Integer.toString(medio);
	   		  }
	   	  }else if(tipo1.contentEquals("float")==true){
	   		  System.out.println("valor ingresado"+ a);
		   		  Float medio;
		   		  medio= Float.valueOf(a);
		   		  a= Float.toString(medio);	  
	   	  }
	   	  Variable= a;
		 }if(result == JOptionPane.CANCEL_OPTION){
			return Variable="-1"; 
		 }
		return Variable;
	}
	public String[] MensajeDosVariables(String NVar1, String NVar2, String tipo1, String tipo2){
		String[] Variables={" "," "};
		int result;
			JTextField Field1 = new JTextField(5);
		    JTextField Field2 = new JTextField(5);
		    JPanel myPanel = new JPanel();
		    myPanel.add(new JLabel(NVar1+":"));
		    myPanel.add(Field1);
		    myPanel.add(new JLabel(NVar2+":"));
		    myPanel.add(Field2);
		    result = JOptionPane.showConfirmDialog(null, myPanel, "Ingrese Valores", JOptionPane.OK_CANCEL_OPTION);
		    System.out.println("Resultado eleccion:"+result);
		    if(Field1.getText().isEmpty() | Field2.getText().isEmpty()){
				System.out.println("Vacio");
				String[] Nuevo ={"CANCEL"};
	   			  return Nuevo;
			}
		    if (result == JOptionPane.OK_OPTION) {
	    	  String a=Field1.getText(), b=Field2.getText();
	    	  if(tipo1.contentEquals("int")==true){
	    		  System.out.println("valor ingresado: "+ a);
		   		  if(a.contains(".")==true){
		   			  MensajeError("El primer valor ingresado no corresponde a un Entero", "Error", 2);
		   			  String[] Nuevo ={"CANCEL"};
		   			  return Nuevo;
		   		  }else{
			   		  Integer medio;
			   		  medio= Integer.valueOf(a);
			   		  a= Integer.toString(medio);
		   		  }
	    	  }else if(tipo1.contentEquals("float")==true){
	    		  Float medio;
	    		  medio= Float.valueOf(a);
	    		  a= Float.toString(medio);
	    	  }
	    	  if(tipo2.contentEquals("int")==true){
	    		  System.out.println("valor ingresado: "+ b);
	    		  if(b.contains(".")==true){
		   			  MensajeError("El segundo valor ingresado no corresponde a un Entero", "Error", 2);
		   			  String[] Nuevo ={"CANCEL"};
		   			  return Nuevo;
		   		  }else{
		   			Integer medio;
	    		  	medio= Integer.valueOf(b);
	    		  	b= Integer.toString(medio);
		   		  }
	    	  }else if(tipo2.contentEquals("float")==true){
	    		  Float medio;
	    		  medio= Float.valueOf(b);
	    		  b= Float.toString(medio);
	    	  }
	         Variables[0]=a;
	         Variables[1]=b;
		    }else if(result == JOptionPane.CANCEL_OPTION | result==-1){
				String[] Nuevo ={"CANCEL"};
				return Nuevo;
			 }
		return Variables;
	}
	public String[] MensajeTresVariables(String NVar1, String NVar2, String NVar3, String tipo1, String tipo2, String tipo3){
		String[] Variables={" "," "," "};
			JTextField Field1 = new JTextField(5);
		    JTextField Field2 = new JTextField(5);
		    JTextField Field3 = new JTextField(5);
		    JPanel myPanel = new JPanel();
		    myPanel.add(new JLabel(NVar1+":"));
		    myPanel.add(Field1);
		    myPanel.add(new JLabel(NVar2+":"));
		    myPanel.add(Field2);
		    myPanel.add(new JLabel(NVar3+":"));
		    myPanel.add(Field3);
		    int result = JOptionPane.showConfirmDialog(null, myPanel, "Ingrese Valores", JOptionPane.OK_CANCEL_OPTION);
		    System.out.println("Resultado eleccion:"+result);
		    if(Field1.getText().isEmpty() | Field2.getText().isEmpty() |Field3.getText().isEmpty()){
				System.out.println("Vacio");
				String[] Nuevo ={"CANCEL"};
	   			  return Nuevo;
			}
		      if (result == JOptionPane.OK_OPTION) {
		    	  String a=Field1.getText(), b=Field2.getText(),c=Field3.getText();
		    	  if(tipo1.contentEquals("int")==true){
		    		  System.out.println("valor ingresado: "+ a);
			   		  if(a.contains(".")==true){
			   			  MensajeError("El primer valor ingresado no corresponde a un Entero", "Error", 2);
			   			  String[] Nuevo ={"CANCEL"};
			   			  return Nuevo;
			   		  }else{
				   		  Integer medio;
				   		  medio= Integer.valueOf(a);
				   		  a= Integer.toString(medio);
			   		  }
		    	  }else if(tipo1.contentEquals("float")==true){
		    		  Float medio;
		    		  medio= Float.valueOf(a);
		    		  a= Float.toString(medio);
		    	  }
		    	  if(tipo2.contentEquals("int")==true){
		    		  System.out.println("valor ingresado: "+ b);
		    		  if(b.contains(".")==true){
			   			  MensajeError("El segundo valor ingresado no corresponde a un Entero", "Error", 2);
			   			  String[] Nuevo ={"CANCEL"};
			   			  return Nuevo;
			   		  }else{
			   			Integer medio;
		    		  	medio= Integer.valueOf(b);
		    		  	b= Integer.toString(medio);
			   		  }
		    	  }else if(tipo2.contentEquals("float")==true){
		    		  Float medio;
		    		  medio= Float.valueOf(b);
		    		  b= Float.toString(medio);
		    	  }
		    	  if(tipo3.contentEquals("int")==true){
		    		  System.out.println("valor ingresado: "+ c);
		    		  if(c.contains(".")==true){
			   			  MensajeError("El tercer valor ingresado no corresponde a un Entero", "Error", 2);
			   			  String[] Nuevo ={"CANCEL"};
			   			  return Nuevo;
			   		  }else{	
			    		  Integer medio;
			    		  medio= Integer.valueOf(c);
			    		  c= Integer.toString(medio);
			   		  }
		    	  }else if(tipo3.contentEquals("float")==true){
		    		  Float medio;
		    		  medio= Float.valueOf(c);
		    		  c= Float.toString(medio);
		    	  }
		         Variables[0]=a;
		         Variables[1]=b;
		         Variables[2]=c;
		      }else if(result == JOptionPane.CANCEL_OPTION| result==-1){
					String[] Nuevo ={"CANCEL"};
					return Nuevo;
				 }
		return Variables;
	}
	public String[] MensajeCuatroVariables(String NVar1, String NVar2, String NVar3, String NVar4, String tipo1, String tipo2, String tipo3, String tipo4){
		String[] Variables={" "," "," "," "};
			JTextField Field1 = new JTextField(5);
		    JTextField Field2 = new JTextField(5);
		    JTextField Field3 = new JTextField(5);
		    JTextField Field4 = new JTextField(5);
		    JPanel myPanel = new JPanel();
		    myPanel.add(new JLabel("\n"+NVar1+":"));
		    myPanel.add(Field1);
		    myPanel.add(new JLabel(NVar2+":"));
		    myPanel.add(Field2);
		    myPanel.add(new JLabel(NVar3+":"));
		    myPanel.add(Field3);
		    myPanel.add(new JLabel(NVar4+":"));
		    myPanel.add(Field4);
		    int result = JOptionPane.showConfirmDialog(null, myPanel, "Observacion: Implemente valores grandes...", JOptionPane.OK_CANCEL_OPTION);
		    if(Field1.getText().isEmpty() | Field2.getText().isEmpty() |Field3.getText().isEmpty()|Field4.getText().isEmpty()){
				System.out.println("Vacio");
				String[] Nuevo ={"CANCEL"};
	   			  return Nuevo;
			}
		    System.out.println("Resultado eleccion:"+result);
		      if (result == JOptionPane.OK_OPTION) {
		    	  String a=Field1.getText(), b=Field2.getText(),c=Field3.getText(),d=Field4.getText();
		    	  if(tipo1.contentEquals("int")==true){
		    		  System.out.println("valor ingresado: "+ a);
			   		  if(a.contains(".")==true){
			   			  MensajeError("El primer valor ingresado no corresponde a un Entero", "Error", 2);
			   			  String[] Nuevo ={"CANCEL"};
			   			  return Nuevo;
			   		  }else{
				   		  Integer medio;
				   		  medio= Integer.valueOf(a);
				   		  a= Integer.toString(medio);
			   		  }
		    	  }else if(tipo1.contentEquals("float")==true){
		    		  Float medio;
		    		  medio= Float.valueOf(a);
		    		  a= Float.toString(medio);
		    	  }
		    	  if(tipo2.contentEquals("int")==true){
		    		  System.out.println("valor ingresado: "+ b);
		    		  if(b.contains(".")==true){
			   			  MensajeError("El segundo valor ingresado no corresponde a un Entero", "Error", 2);
			   			  String[] Nuevo ={"CANCEL"};
			   			  return Nuevo;
			   		  }else{
			   			Integer medio;
		    		  	medio= Integer.valueOf(b);
		    		  	b= Integer.toString(medio);
			   		  }
		    	  }else if(tipo2.contentEquals("float")==true){
		    		  Float medio;
		    		  medio= Float.valueOf(b);
		    		  b= Float.toString(medio);
		    	  }
		    	  if(tipo3.contentEquals("int")==true){
		    		  System.out.println("valor ingresado: "+ c);
		    		  if(c.contains(".")==true){
			   			  MensajeError("El tercer valor ingresado no corresponde a un Entero", "Error", 2);
			   			  String[] Nuevo ={"CANCEL"};
			   			  return Nuevo;
			   		  }else{	
			    		  Integer medio;
			    		  medio= Integer.valueOf(c);
			    		  c= Integer.toString(medio);
			   		  }
		    	  }else if(tipo3.contentEquals("float")==true){
		    		  Float medio;
		    		  medio= Float.valueOf(c);
		    		  c= Float.toString(medio);
		    	  }
		    	  if(tipo4.contentEquals("int")==true){
		    		  System.out.println("valor ingresado: "+ d);
		    		  if(d.contains(".")==true){
			   			  MensajeError("El cuarto valor ingresado no corresponde a un Entero", "Error", 2);
			   			  String[] Nuevo ={"CANCEL"};
			   			  return Nuevo;
			   		  }else{	
			    		  Integer medio;
			    		  medio= Integer.valueOf(d);
			    		  d= Integer.toString(medio);
			   		  }
		    	  }else if(tipo4.contentEquals("float")==true){
		    		  Float medio;
		    		  medio= Float.valueOf(d);
		    		  d= Float.toString(medio);
		    	  }
		         Variables[0]=a;
		         Variables[1]=b;
		         Variables[2]=c;
		         Variables[3]=d;
		      }else if(result == JOptionPane.CANCEL_OPTION| result==-1){
					String[] Nuevo ={"CANCEL"};
					return Nuevo;
				 }
		return Variables;
	}

/**		Tipos de Distribuci�n para la ComboBox		**/	
	/*	Estas funciones son el v�nculo entre cada una de las clases de c�lculo probabilistico y la interfaz del sistema.
	 * 	Cada una de las nuevas funciones est�n reguladas con respecto a las condiciones que independientemente poseen, con
	 *  lo que, si se cumplen las condiciones, el usuario podr� visualizar el resultado del calculo en la interfaz. 
	 *  	El valor -1 es un valor estandarizado para la negaci�n del ingreso del valor calculado, por lo que una falla en el registro
	 *  o un mal ingreso de datos no permitir� que se calculen los valores solicitados. 
	 */
	
	/*Distribucion T Student*/  //1ro
		public float VentanaDistribucionTStudent(){
			float a=0, t;
			int n;
			String[] Variables= {"",""};
			Distribucion_T_Student Distrib = new Distribucion_T_Student();
			Variables = MensajeDosVariables("Grado de libertad, entero positivo: (n)","Area, N�Real: (t)", "int", "float");
			if(Variables[0]=="CANCEL"){
				return -1;
			}
			if(Variables[0]!=" " & Variables[1]!=" "){
				n=Integer.valueOf(Variables[0]);
				t=Float.valueOf(Variables[1]);
				a= Distrib.CalculoStudent(n, t);
				if(a!=-1){
					a= AproximacionValor(a);
					}else {
						MensajeError("No fue posible calcular el resultado.","Valor de Lambda no valido.", 0);
						System.out.println("Valor variable= "+a);
						return -1;
					}
			}else{
				return -1;
			}
			return a;
		}
	/*Distribucion Exponencial*/ //2do
		public float VentanaDistribucionExponencial(){
			float a=0, lambda=0, X=0;
			String[] Variables= {"",""};
			Distribucion_Exponencial Distrib = new Distribucion_Exponencial();
			Variables = MensajeDosVariables("Lambda N�Real, Lambda>0 ","X N�Real", "float", "float");
			if(Variables[0]=="CANCEL"){
				return -1;
			}
			if(Variables[0]!=" " & Variables[1]!=" "){
				lambda=Float.valueOf(Variables[0]);
				X=Float.valueOf(Variables[1]);
				a= Distrib.CalcularResultado(X, lambda);
				if(a!=-1){
				a= AproximacionValor(a);
				}else {
					MensajeError("No fue posible calcular el resultado.","Valor de Lambda no valido.", 0);
				}
			}else{
				return -1;
			}
			return a;
		}
	/*Distribucion Laplace*/	//3ro		
		public float VentanaDistribucionLaplace(){
			float a=0, b=0, u=0, x=0;
			String[] Variables= {"","",""};
			Distribucion_Laplace Distrib = new Distribucion_Laplace();
			Variables = MensajeTresVariables("b N�Real, b>0 ","u N�Real ","x N�Real ","float", "float", "float");
			if(Variables[0]=="CANCEL"){
				return -1;
			}
			if(Variables[0]!=" " & Variables[1]!=" "){
				b=Float.valueOf(Variables[0]);
				u=Float.valueOf(Variables[1]);
				x=Float.valueOf(Variables[2]);
				a= Distrib.Calculo(b, u, x);
				if(a!=-1){
				a= AproximacionValor(a);
				}else {
					MensajeError("b debe ser mayor a 0","Valor no v�lido.", 0);
				}
			}else{
				return -1;
			}
			return a;
		}
	/*Distribucion Normal Estandar*/ //4to
		public float VentanaDistribucionNormalEstandar(){
			float a=0;
			do{
				String inputValue = MensajeUnaVariable("z en [-5.5 , 5.5]", "float");
				System.out.println(inputValue);
				
				if(inputValue.contentEquals("-1")){
					return -1;
				}
				a= Float.valueOf(inputValue);
				if(a<-5.5 | a>5.5){
					MensajeError("El valor ingresado no pertenece al intervalo [-5.5 , 5.5]","", 0);
				}
			}while(a<-5.5 | a>5.5);
			
			Distribucion_Normal_Estandar Valor = new Distribucion_Normal_Estandar();
			float ab;
			ab=Valor.CalculoDistribucionNormalEstandar(a);
			if(ab!=-1){
				ab=  AproximacionValor(ab);
				System.out.println(ab);
			}else{
				return -1;
			}
			return ab;
		}
	/*Distribucion Poisson*/ //5to
		public float VentanaDistribucionPoisson(){
			float a=0, lambda=0, X=0;
			String[] Variables= {"",""};
			Distribucion_Poisson Distrib = new Distribucion_Poisson();
			Variables = MensajeDosVariables("Lambda N�Real, Lambda >0:","X N�Real, X>0", "float", "float");
			if(Variables[0]=="CANCEL"){
				return -1;
			}
			if(Variables[0]!=" " & Variables[1]!=" "){
				lambda=Float.valueOf(Variables[0]);
				X=Float.valueOf(Variables[1]);
				a= Distrib.NuevoValor(lambda,X);
				if(a!=-1){
				a= AproximacionValor(a);
				}else {
					MensajeError("Lambda debe ser mayor a cero\nX debe ser mayor a cero","Valor de Lambda no v�lido.", 0);
				}
			}else{
				return -1;
			}
			return a;
		}
	/*Distribucion Uniforme*/ //6to
		public float VentanaDistribucionUniforme(){
			float a=0, Va=0, b=0, x=0;
			String[] Variables= {"","",""};
			Distribucion_Uniforme Distrib = new Distribucion_Uniforme();
			Variables = MensajeTresVariables("a N�Real, a<b","b N�Real, b>a","x N�Real","float", "float", "float");
			if(Variables[0]=="CANCEL"){
				return -1;
			}
			if(Variables[0]!=" " & Variables[1]!=" "){
				Va=Float.valueOf(Variables[0]);
				b=Float.valueOf(Variables[1]);
				x=Float.valueOf(Variables[2]);
				a= Distrib.GenerarValor(x, Va, b);
				if(a!=-1){
				a= AproximacionValor(a);
				}else {
					MensajeError("b debe ser mayor que a","Valor no v�lido.", 0);
				}
			}
			return a;
		}
	/*Distribucion Weibull*/ //7mo
		public float VentanaDistribucionWeibull(){
			float a=0, X=0, k=0, lambda=0;
			String[] Variables= {"","",""};
			Distribucion_Weibull Distrib = new Distribucion_Weibull();
			Variables = MensajeTresVariables("X N�Real, X>=0","K N�Real, K>0","Lambda N�Real, Lambda>0","float", "float", "float");
			if(Variables[0]=="CANCEL"){
				return -1;
			}
			if(Variables[0]!=" " & Variables[1]!=" "){
				X=Float.valueOf(Variables[0]);
				k=Float.valueOf(Variables[1]);
				lambda=Float.valueOf(Variables[2]);
				a= Distrib.CalcularResultado(X, k, lambda);
				if(a!=-1){
				a= AproximacionValor(a);
				}else {
					MensajeError("X debe ser mayor a 0\nLambda no debe ser menor a 0\n k no debe ser menor a 0","Valor no v�lido.", 0);
					a=0;
				}
			}
			return a;
		}
	/*Congruencia Lineal*/  //8vo
		public float VentanaCongruencia_Lineal(){
			float a=0, X0;
			int Va=0, c=0, m=0;
			String[] Variables= {"","","",""};
			Congruencia_Lineal Distrib = new Congruencia_Lineal();
			Variables = MensajeCuatroVariables("X0 N�Real excepto 0", "Modulo m, Enero positivo", "Constante a N�Real excepto 0 ", "Constante c N�Real excepto 0", "float","int","int","int");
			if(Variables[0]=="CANCEL"){
				return -1;
			}
			if(Variables[0]!=" " & Variables[1]!=" "){
				X0=Float.valueOf(Variables[0]);
				m=Integer.valueOf(Variables[1]);
				Va=Integer.valueOf(Variables[2]);
				c=Integer.valueOf(Variables[3]);
				a= Distrib.CalculoPrimeraVez(X0, Va, c, m);
				if(a!=-1){
				a= AproximacionValor(a);
				int preguntar= JOptionPane.showOptionDialog(null, "El valor generado fue " +a+": \n �Desea calcular otro valor?" , "Congruencia Lineal", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null,new String[]{"Si", "No"}, null);
				//Si=0 - No=1
				if(preguntar==0){
					do{
						a= Distrib.CalculoSegundo();
						preguntar= JOptionPane.showOptionDialog(null, "El valor generado fue " +a+": \n �Desea calcular otro valor?" , "Congruencia Lineal", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null,new String[]{"Si", "No"}, null);
					}while(preguntar==0);
				}
				}else {
					MensajeError("b debe ser mayor a 0","Valor no v�lido.", 0);
				}
			}
			return a;
	}
	/*Ingreso Usuario*/ //9no
		public float VentanaIngresoUsuario(){
			float a=0;
			do{
				String inputValue = MensajeUnaVariable("X en [0,1]", "float");
				if(inputValue.contentEquals("-1")| inputValue.contentEquals(" ")){
					return -1;
				}
				a= Float.valueOf(inputValue);
				if(a<0 | a>1){
					MensajeError("El valor ingresado no pertenece al intervalo [0,1]","", 0);
				}
			}while(a<0 | a>1);
			Ingreso_Usuario Valor = new Ingreso_Usuario();
			float ab;
			ab=Valor.IngresoValor(a);
			ab=  AproximacionValor(ab);
			System.out.println(ab);
			return ab;
		}	
					
		
/**		Interfaz		**/
	
//Gestionar Areas de Trabajo. 
	/*	Mensajes simples predeterminados para la recepci�n de acciones.*/
	public int GestionAreas(){
	     int a = JOptionPane.showOptionDialog(null,"�Qu� desea hacer?","Gestion de Areas de Trabajo.", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Ingresar Area", "Eliminar Area", "Cancelar"}, null);
	    // System.out.println("Opcion elegida: "+a);
	     //Ingresar Area= 0 - Eliminar Area= 1- Cancelar =2
	     return a;
	}
	public int GestionAreas_Pregunta1(){
		int a = JOptionPane.showOptionDialog(null, "Existe la posibilidad de que se encuentre un valor de Area de Trabajo disponible:\n �Desea utilizarlo?", "Gestion de Areas de Trabajo.", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null,new String[]{"Mantener valor ingresado", "Seleccionar valor disponible", "Cancelar"}, null);
		//Mantener valor=0; - cambiar valor=1 - Cancelar =2
		return a;
	}	
	public int GestionarAreas_Pregunta1(int valor){
		int a = JOptionPane.showOptionDialog(null, "Existe el numero " +valor+" disponible para ser una nueva Area de trabajo" , "Gestion de Areas de Trabajo.", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null,new String[]{"Si", "No"}, null);
		//Si=0 - No=1
		System.out.println(a);
		return a;
	}
	public int GestionAreas_Pregunta2b(){
		int a = JOptionPane.showOptionDialog(null, "�Desea ingresar Maquinas a la nueva area de trabajo", "Gestion de Areas de Trabajo.", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null,new String[]{"Luego.", "Ahora."}, null);
		//Luego=0
		//Ahora=1
		return a;
	}
	
	/* Mensajes m�s complejos para la implementaci�n de acciones futuras en la interfaz:
	 * 		Algunas de estas funciones ya no se encuentran implentadas en la interfaz, no obstante se dejaron para
	 * 	un posible an�lisis futuro.
	 * 		El resto de las funciones son utilizadas tanto por la ventana principal y/o por la ventana de las �reas de trabajo
	 * 	de la interfaz.
	 */
	public int GestionAreas_Pregunta2(){ //Funci�n ya no utilizada.
		int a = JOptionPane.showOptionDialog(null, "�Desea ingresar Maquinas a la nueva area de trabajo", "Gestion de Areas de Trabajo.", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null,new String[]{"Luego.", "Ahora."}, null);
		//Luego=0 - Ahora=1
		if(a==1){
			do{
				String inputValue = JOptionPane.showInputDialog("Ingrese la cantidad de Maquinas a ingresar");
				a= Integer.valueOf(inputValue);	
			}while(a<1);
		}
		return a;
	}
	public int GestionArea_Pregunta2c(){ //Funci�n ya no utilizada.
		String a= MensajeUnaVariable("Ingrese la cantidad de Maquinas a ingresar", "int");
		if(a.contentEquals("-1")){
			System.out.println("no hay nada");
			return -1;
		}
		int b;
		b= Integer.valueOf(a);
		return b;
	}
	public int NumeroNuevaArea(){//Funci�n ya no utilizada.
	int a=0;
	String ab = MensajeUnaVariable("Ingrese el numero de la Nueva Area", "int");
	System.out.println("NUmero nueva Area"+ab);
	if(ab.contentEquals("-1")| ab.contentEquals(" ")==true){
		return -1;
	}
	a= Integer.valueOf(ab);
	return a;
}	
	public String[] PI_Maquina_Area(int posicion){
		String[] Variables={" "," "};
		int result;
			JTextField Field1 = new JTextField(5);
		    JTextField Field2 = new JTextField(5);
		    JPanel myPanel = new JPanel();
		    myPanel.add(new JLabel("Nombre de la M�quina:"));
		    myPanel.add(Field1);
		    myPanel.add(new JLabel("C�digo de la M�quina:"));
		    myPanel.add(Field2);
		    result = JOptionPane.showConfirmDialog(null, myPanel, "Ingrese Datos para el ingreso "+posicion+":", JOptionPane.OK_CANCEL_OPTION);
		    if (result == JOptionPane.OK_OPTION) {
	    	  String a=Field1.getText(), b=Field2.getText(); 
	    	  Variables[0]=a;
	    	  Variables[1]=b;
	    	  System.out.println("Nombre: "+a+"\nCodigo: "+b);
		    }
		return Variables;
	}
	public String[] PI_Maquina_ELIM(){
		String[] Variables={" "," "};
		int result;
			JTextField Field1 = new JTextField(5);
		    JTextField Field2 = new JTextField(5);
		    JPanel myPanel = new JPanel();
		    myPanel.add(new JLabel("Area de la M�quina:"));
		    myPanel.add(Field1);
		    myPanel.add(new JLabel("Nombre de la M�quina:"));
		    myPanel.add(Field2);
		    result = JOptionPane.showConfirmDialog(null, myPanel, "Datos de la Maquina a Eliminar:", JOptionPane.OK_CANCEL_OPTION);
		    if (result == JOptionPane.OK_OPTION) {
	    	  String a=Field1.getText(), b=Field2.getText(); 
	    	  Variables[0]=a;
	    	  Variables[1]=b;
	    	  System.out.println("Area: "+a+"\nNombre: "+b);
		    }
		return Variables;
	}
	public String[] PI_Maquina_Area(){
		String[] Variables={" "," ", " "};
		int result;
			JTextField Field1 = new JTextField(5);
		    JTextField Field2 = new JTextField(5);
		    JTextField Field3 = new JTextField(5);
		    JPanel myPanel = new JPanel();
		    myPanel.add(new JLabel("Area de la Maquina"));
		    myPanel.add(Field1);
		    myPanel.add(new JLabel("Nombre de la M�quina:"));
		    myPanel.add(Field2);
		    myPanel.add(new JLabel("C�digo de la M�quina:"));
		    myPanel.add(Field3);
		    result = JOptionPane.showConfirmDialog(null, myPanel, "Ingrese Datos para el ingreso:", JOptionPane.OK_CANCEL_OPTION);
		    if (result == JOptionPane.OK_OPTION) {
	    	  String a=Field1.getText(), b=Field2.getText(),  c=Field3.getText(); 
	    	  int ab =Integer.valueOf(a);
	    	  a=Integer.toString(ab);
	    	  Variables[0]=a;
	    	  Variables[1]=b;
	    	  Variables[2]=c;
	    	  System.out.println("Area: "+ a +"Nombre: "+b+"\nCodigo: "+c);
		    }
		return Variables;
	}
	public String[] NumeroNuevaArea1(){
	int a=0;
	String[] ab = {" "," "};
	ab=MensajeDosVariables("Ingrese el numero de la Nueva Area", "Ingrese el numero de maquinas", "int", "int");
	System.out.println("NUmero nueva Area"+ab[0]);
	if(ab[0]=="CANCEL"){
		return ab;
	}
	if(ab[1]=="0"){
		String[] Nuevo ={"CANCEL"};
		return Nuevo;
	}
	return ab;
}
	public int NumeroELIMINAArea(){
	int a=0;
	String ab = MensajeUnaVariable("Ingrese el numero del Area a eliminar", "int");
	if(ab.contentEquals("-1")==true | ab.contentEquals(" ")==true){
		return -1;
	}
	a= Integer.valueOf(ab);
	return a;
}
	public int VentCANTarea(){
	int a=0;
	String ab = MensajeUnaVariable("Ingrese la catidad de areas a ingresar", "int");
	System.out.println("\t\t\tVentCANTarea "+ab);
	if(ab.contentEquals("-1")){
		return -1;
	}
	if(ab.contentEquals(" ")==true){
		System.out.println("ab es null");
		a=-1;
		return -1;
	}
	a= Integer.valueOf(ab);
	return a;
}	

}
