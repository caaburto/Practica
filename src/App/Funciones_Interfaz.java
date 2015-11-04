package App;

import Frames.Ventana_Servidor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.swing.JTabbedPane;

public class Funciones_Interfaz {
	private int numeroAreas;
	private int numeroMaquinarias;
	BufferedReader br = null;
	Generacion_Ventanas_Emergentes Ventanas= new Generacion_Ventanas_Emergentes();
	//AreasTrabajos AreaTRAB;
	
	/* 		Clase creada para servir de v�nculo entre la interfaz del programa y la parte l�gica, esta clase se 
	 * 	compone principalmente por funciones de construcci�n de registros internos, funciones de generaci�n de archivos de salida
	 * 	funciones que alteran los registros internos.
	 * 	
	 * 		Esta clase es la que habilita la funcionabilidad de la interfaz interna del sistema: Existen funciones que pueden 
	 *  no encontrarse habilitadas funcionalmente en la interfaz, no obstante su an�lisis podr�a servir como gu�a para
	 *  la implementaci�n de diversos c�digos.
	 *  
	 *  	Se dejaron las salidas a la pantalla del constructor para facilitar un futuro comprendimiento del c�digo, adem�s de que
	 *  la mayor�a de los comentarios mostrados en la pantalla de constructor en eclipse poseen "//" 
	 */
	
						/** Barra de Menu **/
	public void AbrirTrabajo(File archivoSistema){
		boolean dataFirst =true;
		boolean data$tot= true;
		/**/
		boolean NUM = true;
		boolean Area= false;
		boolean Maq=  false;
		boolean NOMBRE= false;
		boolean COD= false;
		Integer numero;
		try{
		 String sCurrentLine;
		 Integer cantMaqTOT=0, areaActual=0;
		 br = new BufferedReader(new FileReader(archivoSistema));
		 PrintWriter Maquinas = new PrintWriter("Maq.txt", "UTF-8");
		 PrintWriter AreasTrabajo = new PrintWriter("WS.txt", "UTF-8");
		 PrintWriter Codigos= new PrintWriter("Cod.txt", "UTF-8");
		 PrintWriter Registro = new PrintWriter("REGISTRO.txt", "UTF-8");
		 Registro.close();
		File Delete = new File("Cod.txt");
	  	File Borrar = new File("Maq.txt");
	  	File eliminar = new File("WS.txt");
	  	eliminar.deleteOnExit();
	  	Borrar.deleteOnExit();
	  	Delete.deleteOnExit();
	  	try{

	  	  	while ((sCurrentLine = br.readLine()) != null) {
	  				 if(sCurrentLine.contains("$")){
	  					 if(data$tot==true){
	  						 data$tot=false;
	  						 Area=true;
	  						 Maq=false;
	  						 COD = false;
	  					 }else{
	  						 data$tot=false;
	  						 Area=false;
	  						 Maq=false;
	  						 COD = false;
	  					 }
	  				 } else if(sCurrentLine.contains("#")){//lectura de nueva area
	  						 NUM=true;
	  						 NOMBRE=false;
	  						 Area=true;
	  						 COD=false;
	  						 NOMBRE=false;
	  					 } else	if(NOMBRE==true){
	  						 int a;
	  						 a= areaActual;
	  						 //System.out.println("NOMBRE TRUE por aqui ya pase: "+ sCurrentLine);
	  						 Maquinas.println(a);
	  						 Maquinas.println(sCurrentLine);
	  						 NOMBRE=false;
	  						 COD=true;
	  						 NUM=false;
	  						 Maq=false;
	  					 }else if(COD == true){
	  					 //System.out.println("\t\tCOD TRUE por aqui ya pase: "+ sCurrentLine);
	  					 Codigos.println(sCurrentLine);
	  					 Maquinas.println(sCurrentLine);
	  					 NUM=false;
	  					 Maq=false;
	  					 NOMBRE=true;
	  					 COD=false;
	  				 }else if(NUM== true){ //Est� leyendo un entero
	  					if(Area==true | Maq==true | dataFirst ==true){
	  							 numero= Integer.valueOf(sCurrentLine);
	  							 if(dataFirst==true){ //primera lectura: cantidad de Areas existentes
	  								numeroAreas= numero;
	  								dataFirst=false;
	  								AreasTrabajo.println(sCurrentLine);
	  						//	 System.out.println("\tFAREA por aqui ya pase : "+ sCurrentLine);
	  							 }
	  							 if(Area==true){
	  							//	 System.out.println("\tAREA TRUE por aqui ya pase: "+ sCurrentLine);
	  								 areaActual=numero;
	  								 AreasTrabajo.println(sCurrentLine);
	  								 Area=false;
	  								 Maq=true;
	  								 COD=false;
	  								 NOMBRE=false;
	  							 }else if(Maq==true){ //El entero que lee es una maquina.
	  								// System.out.println("MAQ True por aqui ya pase: "+ sCurrentLine);
	  								cantMaqTOT= cantMaqTOT+numero;
	  								 Maq=false;
	  								 NOMBRE=true;
	  								 COD=false;
	  								 NUM=false; //Despues del numero de las m�quinas vienen los nombres  
	  							 }
	  						 }
	  					 }

	  			 }
	  			 AreasTrabajo.close();
	  			 Maquinas.close();
	  			 Codigos.close();
	  			 br = new BufferedReader(new FileReader(archivoSistema));
	  			 PrintWriter Respaldo = new PrintWriter("RESPALDO.txt", "UTF-8");
	  			 while ((sCurrentLine = br.readLine()) != null) {
	  				 Respaldo.println(sCurrentLine);
	  			 }
	  			 Respaldo.close();
	  			 File Bye = new File("RESPALDO.txt");
			  	Bye.deleteOnExit();
	  			System.out.println("Maquinas totales: "+cantMaqTOT);
	  			GenerarWS();
	  			EliminarArchivos();
	  	}catch(NumberFormatException ea){
	  			AreasTrabajo.close();
	  			 Maquinas.close();
	  			 Codigos.close();
	  		  	eliminar.delete();
	  		  	Borrar.delete();
	  		  	Delete.delete();
	  	}
	
    } catch (IOException e) {
        System.out.println("File not found");
    } finally {
            try {       	
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
		
		}
	public int AbrirTrabajo2(File archivoSistema){
		boolean dataFirst =true;
		boolean data$tot= true;
		/**/
		boolean NUM = true;
		boolean Area= false;
		boolean Maq=  false;
		boolean NOMBRE= false;
		boolean COD= false;
		Integer numero;
		try{
		 String sCurrentLine;
		 Integer cantMaqTOT=0, areaActual=0;
		 br = new BufferedReader(new FileReader(archivoSistema));
		 PrintWriter Maquinas = new PrintWriter("Maq.txt", "UTF-8");
		 PrintWriter AreasTrabajo = new PrintWriter("WS.txt", "UTF-8");
		 PrintWriter Codigos= new PrintWriter("Cod.txt", "UTF-8");
		 PrintWriter Registro = new PrintWriter("REGISTRO.txt", "UTF-8");
		 Registro.close();
		File Delete = new File("Cod.txt");
	  	File Borrar = new File("Maq.txt");
	  	File eliminar = new File("WS.txt");
	  	eliminar.deleteOnExit();
	  	Borrar.deleteOnExit();
	  	Delete.deleteOnExit();
	  	try{

	  	  	while ((sCurrentLine = br.readLine()) != null) {
	  				 if(sCurrentLine.contains("$")){
	  					 if(data$tot==true){
	  						 data$tot=false;
	  						 Area=true;
	  						 Maq=false;
	  						 COD = false;
	  					 }else{
	  						 data$tot=false;
	  						 Area=false;
	  						 Maq=false;
	  						 COD = false;
	  					 }
	  				 } else if(sCurrentLine.contains("#")){//lectura de nueva area
	  						 NUM=true;
	  						 NOMBRE=false;
	  						 Area=true;
	  						 COD=false;
	  						 NOMBRE=false;
	  					 } else	if(NOMBRE==true){
	  						 int a;
	  						 a= areaActual;
	  						 //System.out.println("NOMBRE TRUE por aqui ya pase: "+ sCurrentLine);
	  						 Maquinas.println(a);
	  						 Maquinas.println(sCurrentLine);
	  						 NOMBRE=false;
	  						 COD=true;
	  						 NUM=false;
	  						 Maq=false;
	  					 }else if(COD == true){
	  					 //System.out.println("\t\tCOD TRUE por aqui ya pase: "+ sCurrentLine);
	  					 Codigos.println(sCurrentLine);
	  					 Maquinas.println(sCurrentLine);
	  					 NUM=false;
	  					 Maq=false;
	  					 NOMBRE=true;
	  					 COD=false;
	  				 }else if(NUM== true){ //Est� leyendo un entero
	  					if(Area==true | Maq==true | dataFirst ==true){
	  							 numero= Integer.valueOf(sCurrentLine);
	  							 if(dataFirst==true){ //primera lectura: cantidad de Areas existentes
	  								numeroAreas= numero;
	  								dataFirst=false;
	  								AreasTrabajo.println(sCurrentLine);
	  						//	 System.out.println("\tFAREA por aqui ya pase : "+ sCurrentLine);
	  							 }
	  							 if(Area==true){
	  							//	 System.out.println("\tAREA TRUE por aqui ya pase: "+ sCurrentLine);
	  								 areaActual=numero;
	  								 AreasTrabajo.println(sCurrentLine);
	  								 Area=false;
	  								 Maq=true;
	  								 COD=false;
	  								 NOMBRE=false;
	  							 }else if(Maq==true){ //El entero que lee es una maquina.
	  								// System.out.println("MAQ True por aqui ya pase: "+ sCurrentLine);
	  								cantMaqTOT= cantMaqTOT+numero;
	  								 Maq=false;
	  								 NOMBRE=true;
	  								 COD=false;
	  								 NUM=false; //Despues del numero de las m�quinas vienen los nombres  
	  							 }
	  						 }
	  					 }

	  			 }
	  			 AreasTrabajo.close();
	  			 Maquinas.close();
	  			 Codigos.close();
	  			 br = new BufferedReader(new FileReader(archivoSistema));
	  			 PrintWriter Respaldo = new PrintWriter("RESPALDO.txt", "UTF-8");
	  			 while ((sCurrentLine = br.readLine()) != null) {
	  				
	  				 Respaldo.println(sCurrentLine);
	  			 }
	  			 Respaldo.close();
	  			 File Bye = new File("RESPALDO.txt");
			  	Bye.deleteOnExit();
	  			System.out.println("Maquinas totales: "+cantMaqTOT);
	  			GenerarWS();
	  			EliminarArchivos();
	  	}catch(NumberFormatException ea){
	  			AreasTrabajo.close();
	  			 Maquinas.close();
	  			 Codigos.close();
	  		  	eliminar.delete();
	  		  	Borrar.delete();
	  		  	Delete.delete();
	  		  
	    	  return(1);
	  	}
	
    } catch (IOException e) {
        System.out.println("File not found");
    } finally {
            try {       	
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
		return(0);
		}
	public void AbrirTrabajo(String archivoSistema){
		boolean dataFirst =true;
		boolean data$tot= true;
		/**/
		boolean NUM = true;
		boolean Area= false;
		boolean Maq=  false;
		boolean NOMBRE= false;
		boolean COD= false;
		Integer numero;
		try{
		 String sCurrentLine;
		 Integer cantMaqTOT=0, areaActual=0;
		 br = new BufferedReader(new FileReader(archivoSistema));
		 PrintWriter Maquinas = new PrintWriter("Maq.txt", "UTF-8");
		 PrintWriter AreasTrabajo = new PrintWriter("WS.txt", "UTF-8");
		 PrintWriter Codigos= new PrintWriter("Cod.txt", "UTF-8");
		 PrintWriter Registro = new PrintWriter("REGISTRO.txt", "UTF-8");
		 Registro.close();
		 while ((sCurrentLine = br.readLine()) != null) {
			 if(sCurrentLine.contains("$")){
				 if(data$tot==true){
					 data$tot=false;
					 Area=true;
					 Maq=false;
					 COD = false;
				 }else{
					 data$tot=false;
					 Area=false;
					 Maq=false;
					 COD = false;
				 }
			 } else if(sCurrentLine.contains("#")){//lectura de nueva area
					 NUM=true;
					 NOMBRE=false;
					 Area=true;
					 COD=false;
					 NOMBRE=false;
				 } else	if(NOMBRE==true){
					 int a;
					 a= areaActual;
					 //System.out.println("NOMBRE TRUE por aqui ya pase: "+ sCurrentLine);
					 Maquinas.println(a);
					 Maquinas.println(sCurrentLine);
					 NOMBRE=false;
					 COD=true;
					 NUM=false;
					 Maq=false;
				 }else if(COD == true){
				 //System.out.println("\t\tCOD TRUE por aqui ya pase: "+ sCurrentLine);
				 Codigos.println(sCurrentLine);
				 Maquinas.println(sCurrentLine);
				 NUM=false;
				 Maq=false;
				 NOMBRE=true;
				 COD=false;
			 }else if(NUM== true){ //Est� leyendo un entero
				if(Area==true | Maq==true | dataFirst ==true){
						 numero= Integer.valueOf(sCurrentLine);
						 if(dataFirst==true){ //primera lectura: cantidad de Areas existentes
							numeroAreas= numero;
							dataFirst=false;
							AreasTrabajo.println(sCurrentLine);
					//	 System.out.println("\tFAREA por aqui ya pase : "+ sCurrentLine);
						 }
						 if(Area==true){
						//	 System.out.println("\tAREA TRUE por aqui ya pase: "+ sCurrentLine);
							 areaActual=numero;
							 AreasTrabajo.println(sCurrentLine);
							 Area=false;
							 Maq=true;
							 COD=false;
							 NOMBRE=false;
						 }else if(Maq==true){ //El entero que lee es una maquina.
							// System.out.println("MAQ True por aqui ya pase: "+ sCurrentLine);
							cantMaqTOT= cantMaqTOT+numero;
							 Maq=false;
							 NOMBRE=true;
							 COD=false;
							 NUM=false; //Despues del numero de las m�quinas vienen los nombres  
						 }
					 }
				 }

		 }
		 AreasTrabajo.close();
		 Maquinas.close();
		 Codigos.close(); 
		 br = new BufferedReader(new FileReader("RESPALDO.txt"));
		 br.close();
		 File Bye = new File("RESPALDO.txt");
	  	 Bye.deleteOnExit();
		System.out.println("Maquinas totales: "+cantMaqTOT);
    } catch (IOException e) {
        System.out.println("File not found");
    } finally {
            try {       	
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
        }
		GenerarWS();
		//Implementaci�n en la ventana.
		
		EliminarArchivos();
		}
	public void GenerarWS(){
		 try (BufferedReader br = new BufferedReader(new FileReader("Maq.txt"))) {
	        	String sCurrentLine;
	   		 Integer area1,  area2=0;
	   		 int numero=0;
	   		 boolean Num=true;
	   		 boolean NuevaArea=false;
	   		 sCurrentLine= br.readLine();
	   		 area1= Integer.valueOf(sCurrentLine);
	   		 PrintWriter AreasTrabajo = new PrintWriter("WS"+area1+".txt", "UTF-8");
	   		 do{
	   		 if(numero%3==0){
	   			 Num=true;
	   		 }
	   		 if(Num==true){
		   		 area1= Integer.valueOf(sCurrentLine);
		   		 if(area2==0){
		   			 area2=area1;
		   		 }else if(area2!=0 & area2!= area1){
		   			 //System.out.println("\t Cambio de area");
		   			 area2=area1;
		   			AreasTrabajo.close();
		   			NuevaArea=true;
		      		AreasTrabajo= new PrintWriter("WS"+area1+".txt", "UTF-8");
		   		 }
		   		 AreasTrabajo.println(sCurrentLine);
		   		 //System.out.println("+"+sCurrentLine);
		   		 Num=false;
		   		 numero++;
		   		 }
		   		 else{
		   			AreasTrabajo.println(sCurrentLine);
			   		//System.out.println("-"+sCurrentLine);
			   		numero++;
		   		 }
	   		 }while ((sCurrentLine = br.readLine()) != null);
	   		 AreasTrabajo.close();
	        } catch (IOException e) {
	            System.out.println("File not found");
	        }
	}
	public void leerWS(){
		String sCurrentLine;
		int CantidadA;
		try (BufferedReader br = new BufferedReader(new FileReader("WS.txt"))) {
			sCurrentLine=br.readLine();
			CantidadA= Integer.valueOf(sCurrentLine);
			for(int i=1; i<=CantidadA;i++ ){
				sCurrentLine=br.readLine();
				int AreaActual= Integer.valueOf(sCurrentLine);
				//Area_De_Trabajo a = new Area_De_Trabajo("WS"+AreaActual+".txt", AreaActual);
			}
			
		} catch (IOException e) {
            System.out.println("File not found");
        }
	}
	public void AreaTrabajo(File Archivo){
		 try (BufferedReader br = new BufferedReader(new FileReader(Archivo))) {
	        	String sCurrentLine;
	   		 Integer area1,  area2=0;
	   		 int numero=0;
	   		 boolean Num=true;
	   		 boolean NuevaArea=false;
	   		 sCurrentLine= br.readLine();
	   		 area1= Integer.valueOf(sCurrentLine);
	   		 PrintWriter AreasTrabajo = new PrintWriter("WS"+area1+".txt", "UTF-8");
	   		 do{
	   		 if(numero%3==0){
	   			 Num=true;
	   		 }
	   		 if(Num==true){
		   		 area1= Integer.valueOf(sCurrentLine);
		   		 if(area2==0){
		   			 area2=area1;
		   		 }else if(area2!=0 & area2!= area1){
		   			 //System.out.println("\t Cambio de area");
		   			 area2=area1;
		   			AreasTrabajo.close();
		   			NuevaArea=true;
		      		AreasTrabajo= new PrintWriter("WS"+area1+".txt", "UTF-8");
		   		 }
		   		 AreasTrabajo.println(sCurrentLine);
		   		 //System.out.println("+"+sCurrentLine);
		   		 Num=false;
		   		 numero++;
		   		 }
		   		 else{
		   			AreasTrabajo.println(sCurrentLine);
			   		//System.out.println("-"+sCurrentLine);
			   		numero++;
		   		 }
	   		 }while ((sCurrentLine = br.readLine()) != null);
	   		 AreasTrabajo.close();
	        } catch (IOException e) {
	            System.out.println("File not found");
	        }
	}
	
				/*Funcion de Carga de Archivos...*/	
	public boolean EliminarArea(int Numero){
		String sCurrentLine, NumeroString  =Integer.toString(Numero);
		try{
			BufferedReader numero= new BufferedReader(new FileReader("WS.txt"));
			String valor = numero.readLine();
			int NumAreas= Integer.valueOf(valor);
			LinkedList<String> nombreArea = new LinkedList<String>();
			for(int i=0; i< NumAreas;i++){
				valor= numero.readLine();
				int algo= Integer.valueOf(valor);
				if(algo!= Numero){
					nombreArea.add(valor);
					System.out.println(i+":"+ valor);
				}
			}
			if(nombreArea.size()==NumAreas){ //No existe area;
				System.out.println("Error: No existe Area");
				return false;
			}else{
				NumAreas--;
				System.out.println("Numero de Areas"+ NumAreas);
				if(NumAreas==0){
					System.out.println("No existen areas");
					return false;
				}
				PrintWriter Nuevo = new PrintWriter("WS.txt", "UTF-8");
				Nuevo.println(NumAreas);
				for(int i=0; i< NumAreas;i++){
					Nuevo.println(nombreArea.get(i));
				}
				Nuevo.close();
				//Eliminar el WS(numero)
					String eliminar =("WS"+Numero+".txt");
					File Elimino = new File(eliminar);
					Elimino.delete();
				//Eliminar las Maquinas de WS(numero) en Maq.txt Almacenar sus c�digos.
				int flag=1;
				boolean Area=false;
				LinkedList<String> respaldoMaq = new LinkedList<String>();
				LinkedList<String> EliminarCOD = new LinkedList<String>();
				BufferedReader numero2 = new BufferedReader(new FileReader("Maq.txt"));
				while ((sCurrentLine = numero2.readLine()) != null) {
					if(flag%3==1){
						if(sCurrentLine.contentEquals(NumeroString)){
							Area=true;
						}else{
							Area=false;
							
						}
					}if(Area==true){
						if(flag%3==0){
							EliminarCOD.add(sCurrentLine);
							System.out.println("Eliminar: "+sCurrentLine);
						}
					}else{
						respaldoMaq.add(sCurrentLine);
						System.out.println(sCurrentLine);
					}
				flag++;
				}
				numero2.close();
				
				PrintWriter Nuevo2 = new PrintWriter("Maq.txt", "UTF-8");
				for(int i=0; i<respaldoMaq.size(); i++){
					Nuevo2.println(respaldoMaq.get(i));
				}
				Nuevo2.close();
				//Borrar los c�digos de COD.txt
				LinkedList<String> respaldoCOD = new LinkedList<String>();
				BufferedReader numero3 = new BufferedReader(new FileReader("Cod.txt"));
			
				String CODIGOELIMINAR = EliminarCOD.pop();
				int a= EliminarCOD.size();
				while ((sCurrentLine = numero3.readLine()) != null) {	
					if(a>=0){
						if(sCurrentLine.contains(CODIGOELIMINAR)==true){
							if(a>0){
								CODIGOELIMINAR = EliminarCOD.pop();
								a--;
							}
							
						}else{
							respaldoCOD.add(sCurrentLine);
						}
					}else{
						respaldoCOD.add(sCurrentLine);
					}
					
				}
				numero3.close();
				PrintWriter Nuevo3 = new PrintWriter("Cod.txt", "UTF-8");
				for(int i=0; i<respaldoCOD.size(); i++){
					Nuevo3.println(respaldoCOD.get(i));
				}
				Nuevo3.close(); 
			}
			numero.close();
			return(true);
		}catch(IOException e){
			System.out.println("File not found");
		}
		return(false);
	
	}
	public int EliminarArea2(int Numero){
		String sCurrentLine, NumeroString  =Integer.toString(Numero);
		try{
			BufferedReader numero= new BufferedReader(new FileReader("WS.txt"));
			String valor = numero.readLine();
			int NumAreas= Integer.valueOf(valor);
			LinkedList<String> nombreArea = new LinkedList<String>();
			for(int i=0; i< NumAreas;i++){
				valor= numero.readLine();
				int algo= Integer.valueOf(valor);
				if(algo!= Numero){
					nombreArea.add(valor);
					System.out.println(i+":"+ valor);
				}
			}
			if(nombreArea.size()==NumAreas){ //No existe area;
				System.out.println("Error: No existe Area");
				return 0;
			}else{
				NumAreas--;
				System.out.println("Numero de Areas"+ NumAreas);
				if(NumAreas==0){
					
					return 2;
				}
				PrintWriter Nuevo = new PrintWriter("WS.txt", "UTF-8");
				Nuevo.println(NumAreas);
				for(int i=0; i< NumAreas;i++){
					Nuevo.println(nombreArea.get(i));
				}
				Nuevo.close();
				//Eliminar el WS(numero)
					String eliminar =("WS"+Numero+".txt");
					File Elimino = new File(eliminar);
					Elimino.delete();
				//Eliminar las Maquinas de WS(numero) en Maq.txt Almacenar sus c�digos.
				int flag=1;
				boolean Area=false;
				LinkedList<String> respaldoMaq = new LinkedList<String>();
				LinkedList<String> EliminarCOD = new LinkedList<String>();
				BufferedReader numero2 = new BufferedReader(new FileReader("Maq.txt"));
				while ((sCurrentLine = numero2.readLine()) != null) {
					if(flag%3==1){
						if(sCurrentLine.contentEquals(NumeroString)){
							Area=true;
						}else{
							Area=false;
							
						}
					}if(Area==true){
						if(flag%3==0){
							EliminarCOD.add(sCurrentLine);
							System.out.println("Eliminar: "+sCurrentLine);
						}
					}else{
						respaldoMaq.add(sCurrentLine);
						System.out.println(sCurrentLine);
					}
				flag++;
				}
				numero2.close();
				
				PrintWriter Nuevo2 = new PrintWriter("Maq.txt", "UTF-8");
				for(int i=0; i<respaldoMaq.size(); i++){
					Nuevo2.println(respaldoMaq.get(i));
				}
				Nuevo2.close();
				//Borrar los c�digos de COD.txt
				LinkedList<String> respaldoCOD = new LinkedList<String>();
				BufferedReader numero3 = new BufferedReader(new FileReader("Cod.txt"));
			
				String CODIGOELIMINAR = EliminarCOD.pop();
				int a= EliminarCOD.size();
				while ((sCurrentLine = numero3.readLine()) != null) {	
					if(a>=0){
						if(sCurrentLine.contains(CODIGOELIMINAR)==true){
							if(a>0){
								CODIGOELIMINAR = EliminarCOD.pop();
								a--;
							}
							
						}else{
							respaldoCOD.add(sCurrentLine);
						}
					}else{
						respaldoCOD.add(sCurrentLine);
					}
					
				}
				numero3.close();
				PrintWriter Nuevo3 = new PrintWriter("Cod.txt", "UTF-8");
				for(int i=0; i<respaldoCOD.size(); i++){
					Nuevo3.println(respaldoCOD.get(i));
				}
				Nuevo3.close(); 
			}
			numero.close();
			return(1);
		}catch(IOException e){
			System.out.println("File not found");
		}
		return(0);
	
	}
	
	
	public boolean EliminarMaquina(String numeroArea, String Nombre){
    	//Inicializaci�n de variables
    	boolean Condicion=false;
    	String sCurrentLine, Codigo= null;
    	int a,b,c,d, elementos=0;
    	int flag1=0, flag2=0, flag3=0;
    	int NumeroTot;
    	a=b=c=d=0;
    	int abcd= Integer.valueOf(numeroArea);
    	try{
    		
    			LinkedList<String> Codigos = new LinkedList<String>();
    			LinkedList<String> Maquinas = new LinkedList<String>();
    			LinkedList<String>  AreaUsada= new LinkedList<String>();
    			LinkedList<String> ElementosSW = new LinkedList<String>();
    			 //comprobar que existe el area
        		BufferedReader leer = new BufferedReader(new FileReader("WS.txt"));
        		sCurrentLine=leer.readLine();
        		System.out.println("\t\t\t\tAreas de trabajo existentes: "+sCurrentLine);
        		NumeroTot=Integer.valueOf(sCurrentLine);
        		while ((sCurrentLine = leer.readLine()) != null) {
        			if(sCurrentLine.contentEquals(numeroArea)){
            			a=2;
            		}else{
            			ElementosSW.add(sCurrentLine);
            			System.out.println("\t\t"+sCurrentLine);
            		}
            	}
            	if(a==2){
            		a=1;
            		System.out.println("Condicion 1 aceptada");
            	}else {//Error: Area no existente	
            		Ventanas.MensajeError("No existe el Area para eliminar a la Maquina", "",1);
            		}
            	leer.close();
      //comprobar existencia m�quina;
            	int ab=2;
            	leer = new BufferedReader(new FileReader("Maq.txt"));
            	System.out.println("Flag1 antes de ciclo: "+flag1);
            	while ((sCurrentLine = leer.readLine()) != null) {
            		if(ab%3==0){
    	        		if(sCurrentLine.contentEquals(Nombre)){
    	        			b=2;
    	        			flag1--;
    	        		}else{
    	        			Maquinas.add(flag1, sCurrentLine);
    	        		//	System.out.println("Maq.txt - "+flag1+": "+sCurrentLine);
    	        			flag1++;
    	        		}
            		}else if(b==2 & ab%3==1){
            			Codigo=sCurrentLine;
            			b=3;
            		}else{
            			Maquinas.add(flag1, sCurrentLine);
    	        		//System.out.println("Maq.txt - "+flag1+": "+sCurrentLine);
    	        		flag1++;
            		}
            		ab++;
            	}
            	if(b==3){
            		b=1;
            		System.out.println("Condicion 2 aceptada");
            	}else{//No existe la m�quina.
            		Ventanas.MensajeError("No existe la m�quina a eliminar", "",1);
            	}
            	leer.close();
       //comprobar existencia c�digo:
            	if(Codigo!= null){
            		leer = new BufferedReader(new FileReader("Cod.txt"));
                	while ((sCurrentLine = leer.readLine()) != null) {
                		if(sCurrentLine.contentEquals(Codigo)){
                			c=2;
           
                		}else{
    		            	//Codigos[flag2]=sCurrentLine;
    	        			Codigos.add(flag2, sCurrentLine);
                			//System.out.println("COD.txt - "+flag2+": "+sCurrentLine);
    	        			flag2++;
                		}
                	}
                	if(c==2){
                		c=1;
                		System.out.println("Condicion 3 aceptada");
                	}
                	leer.close(); 
            	}else{//Error Macro, incongruencia archivos
            		Ventanas.MensajeError("Incongruencia de los registros internos del area de trabajo.", "INCONGRUENCIA INTERNA",2);
            	}
         //Comprobar existencia area trabajo
             	int abc=1;
            	leer = new BufferedReader(new FileReader("WS"+abcd+".txt"));
            	while ((sCurrentLine = leer.readLine()) != null) {
            		System.out.println(sCurrentLine);
            		if(abc%3==2){
            			if(sCurrentLine.contentEquals(Nombre)){
    	        			d=2;
    	        			flag3--;
    	        		}else{
    	        			AreaUsada.add(flag3, sCurrentLine);
    	        			//System.out.println("WS"+abcd +".txt - "+flag3+": "+sCurrentLine);
    	        			flag3++;
    	        		}
            		}
            		if(abc%3==0){
    	        		if(sCurrentLine.contentEquals(Codigo)){
    	        			d=1;
    	        		}else{
    	        			AreaUsada.add(flag3, sCurrentLine);
    	        			//System.out.println("WS"+abcd +".txt - "+flag3+": "+sCurrentLine);
    	        			
    	        			flag3++;
    	        		}
            		} if(abc%3==1){
            			AreaUsada.add(flag3, sCurrentLine);
            			//System.out.println("WS"+abcd +".txt - "+flag3+": "+sCurrentLine);
            			flag3++;
            		}
            		abc++;
            		elementos++;
            	}
            	if(d==1){
            		d=1;
            		System.out.println("Condicion 4 aceptada");
            	}else{//No existe el c�digo
            		Ventanas.MensajeError("No existe la clave del robot elegido", "",1);
            	}
            	leer.close();
            	System.out.println("a: "+a +"b:"+ b +"c: "+c+"d: "+d);
            	if(a==1 & b==1 & c==1 & d==1){
            		Condicion=true;
            		System.out.println("Condiciones aceptadas: Se puede acceder al archivo");
            	}else{
            		//Mensaje Error: No se cumplen las condiciones para eliminar la maquina.
            		Ventanas.MensajeError("No se cumplen las condiciones para la eliminaci�n de la nueva m�quina.", "",1);
            	}
            	if(Condicion==true){
            		System.out.println("\n\n\n Cantidad de elementos en area:"+ elementos);
            		//Eliminar de Maq la maquina
           		    PrintWriter MaquED = new PrintWriter("Maq.txt", "UTF-8");
    	        		for(int i=0; i<flag1;i++){
    	        			MaquED.println(Maquinas.get(i));
    	        		}
    	        		MaquED.close();
            		
            		//Eliminar de Cod maquina
            		PrintWriter Cod= new PrintWriter("Cod.txt", "UTF-8");
    	        		for(int i=0;i<flag2;i++){
    	        			Cod.println(Codigos.get(i));
    	        		}
            		Cod.close();
            		//Eliminar de WSnumero la maquina.
            		PrintWriter AreaED= new PrintWriter("Cod.txt", "UTF-8");
            		for(int i=0; i<flag3;i++){
            			AreaED.println(AreaUsada.get(i));
            		}
            		AreaED.close();
            		System.out.println("Archivos editados");
            		if(elementos<=3){
            			System.out.println("Eliminar Area");
            			File elimino= new File("WS"+numeroArea+".txt");
            			elimino.delete();
            			NumeroTot--;
            			if(NumeroTot==0){
            				return false;
            			}
            			PrintWriter CONSTR= new PrintWriter("WS.txt", "UTF-8");
            			CONSTR.println(NumeroTot);
                		for(int i=0; i<NumeroTot;i++){
                			CONSTR.println(ElementosSW.get(i));
                		}
                		CONSTR.close();
            		}
            		return true;
            	}   		
    	}catch (IOException e) {
            System.out.println("File not found");
        }
    	return false;
	}
	public int EliminarMaquina2(String numeroArea, String Nombre){
    	//Inicializaci�n de variables
    	boolean Condicion=false;
    	String sCurrentLine, Codigo= null;
    	int a,b,c,d, elementos=0;
    	int flag1=0, flag2=0, flag3=0;
    	int NumeroTot;
    	a=b=c=d=0;
    	int abcd= Integer.valueOf(numeroArea);
    	try{
    		
    			LinkedList<String> Codigos = new LinkedList<String>();
    			LinkedList<String> Maquinas = new LinkedList<String>();
    			LinkedList<String>  AreaUsada= new LinkedList<String>();
    			LinkedList<String> ElementosSW = new LinkedList<String>();
    			 //comprobar que existe el area
        		BufferedReader leer = new BufferedReader(new FileReader("WS.txt"));
        		sCurrentLine=leer.readLine();
        		System.out.println("\t\t\t\tAreas de trabajo existentes: "+sCurrentLine);
        		NumeroTot=Integer.valueOf(sCurrentLine);
        		while ((sCurrentLine = leer.readLine()) != null) {
        			if(sCurrentLine.contentEquals(numeroArea)){
            			a=2;
            		}else{
            			ElementosSW.add(sCurrentLine);
            			System.out.println("\t\t"+sCurrentLine);
            		}
            	}
            	if(a==2){
            		a=1;
            		System.out.println("Condicion 1 aceptada");
            	}else {//Error: Area no existente	
            		Ventanas.MensajeError("No existe el Area para eliminar a la Maquina", "",1);
            		}
            	leer.close();
      //comprobar existencia m�quina;
            	int ab=2;
            	leer = new BufferedReader(new FileReader("Maq.txt"));
            	System.out.println("Flag1 antes de ciclo: "+flag1);
            	while ((sCurrentLine = leer.readLine()) != null) {
            		if(ab%3==0){
    	        		if(sCurrentLine.contentEquals(Nombre)){
    	        			b=2;
    	        			flag1--;
    	        		}else{
    	        			Maquinas.add(flag1, sCurrentLine);
    	        		//	System.out.println("Maq.txt - "+flag1+": "+sCurrentLine);
    	        			flag1++;
    	        		}
            		}else if(b==2 & ab%3==1){
            			Codigo=sCurrentLine;
            			b=3;
            		}else{
            			Maquinas.add(flag1, sCurrentLine);
    	        		//System.out.println("Maq.txt - "+flag1+": "+sCurrentLine);
    	        		flag1++;
            		}
            		ab++;
            	}
            	if(b==3){
            		b=1;
            		System.out.println("Condicion 2 aceptada");
            	}else{//No existe la m�quina.
            		Ventanas.MensajeError("No existe la m�quina a eliminar", "",1);
            	}
            	leer.close();
       //comprobar existencia c�digo:
            	if(Codigo!= null){
            		leer = new BufferedReader(new FileReader("Cod.txt"));
                	while ((sCurrentLine = leer.readLine()) != null) {
                		if(sCurrentLine.contentEquals(Codigo)){
                			c=2;
           
                		}else{
    		            	//Codigos[flag2]=sCurrentLine;
    	        			Codigos.add(flag2, sCurrentLine);
                			//System.out.println("COD.txt - "+flag2+": "+sCurrentLine);
    	        			flag2++;
                		}
                	}
                	if(c==2){
                		c=1;
                		System.out.println("Condicion 3 aceptada");
                	}
                	leer.close(); 
            	}else{//Error Macro, incongruencia archivos
            		Ventanas.MensajeError("Incongruencia de los registros internos del area de trabajo.", "INCONGRUENCIA INTERNA",2);
            	}
         //Comprobar existencia area trabajo
             	int abc=1;
            	leer = new BufferedReader(new FileReader("WS"+abcd+".txt"));
            	while ((sCurrentLine = leer.readLine()) != null) {
            		System.out.println(sCurrentLine);
            		if(abc%3==2){
            			if(sCurrentLine.contentEquals(Nombre)){
    	        			d=2;
    	        			flag3--;
    	        		}else{
    	        			AreaUsada.add(flag3, sCurrentLine);
    	        			//System.out.println("WS"+abcd +".txt - "+flag3+": "+sCurrentLine);
    	        			flag3++;
    	        		}
            		}
            		if(abc%3==0){
    	        		if(sCurrentLine.contentEquals(Codigo)){
    	        			d=1;
    	        		}else{
    	        			AreaUsada.add(flag3, sCurrentLine);
    	        			//System.out.println("WS"+abcd +".txt - "+flag3+": "+sCurrentLine);
    	        			
    	        			flag3++;
    	        		}
            		} if(abc%3==1){
            			AreaUsada.add(flag3, sCurrentLine);
            			//System.out.println("WS"+abcd +".txt - "+flag3+": "+sCurrentLine);
            			flag3++;
            		}
            		abc++;
            		elementos++;
            	}
            	if(d==1){
            		d=1;
            		System.out.println("Condicion 4 aceptada");
            	}else{//No existe el c�digo
            		Ventanas.MensajeError("No existe la clave del robot elegido", "",1);
            	}
            	leer.close();
            	System.out.println("a: "+a +"b:"+ b +"c: "+c+"d: "+d);
            	if(a==1 & b==1 & c==1 & d==1){
            		Condicion=true;
            		System.out.println("Condiciones aceptadas: Se puede acceder al archivo");
            	}else{
            		//Mensaje Error: No se cumplen las condiciones para eliminar la maquina.
            		Ventanas.MensajeError("No se cumplen las condiciones para la eliminaci�n de la nueva m�quina.", "",1);
            	}
            	if(Condicion==true){
            		System.out.println("\n\n\n Cantidad de elementos en area:"+ elementos);
            		//Eliminar de Maq la maquina
           		    PrintWriter MaquED = new PrintWriter("Maq.txt", "UTF-8");
    	        		for(int i=0; i<flag1;i++){
    	        			MaquED.println(Maquinas.get(i));
    	        		}
    	        		MaquED.close();
            		
            		//Eliminar de Cod maquina
            		PrintWriter Cod= new PrintWriter("Cod.txt", "UTF-8");
    	        		for(int i=0;i<flag2;i++){
    	        			Cod.println(Codigos.get(i));
    	        		}
            		Cod.close();
            		//Eliminar de WSnumero la maquina.
            		PrintWriter AreaED= new PrintWriter("Cod.txt", "UTF-8");
            		for(int i=0; i<flag3;i++){
            			AreaED.println(AreaUsada.get(i));
            		}
            		AreaED.close();
            		System.out.println("Archivos editados");
            		if(elementos<=3){
            			System.out.println("Eliminar Area");
            			File elimino= new File("WS"+numeroArea+".txt");
            			elimino.delete();
            			NumeroTot--;
            			if(NumeroTot==0){
            				return 2;
            			}
            			PrintWriter CONSTR= new PrintWriter("WS.txt", "UTF-8");
            			CONSTR.println(NumeroTot);
                		for(int i=0; i<NumeroTot;i++){
                			CONSTR.println(ElementosSW.get(i));
                		}
                		CONSTR.close();
            		}
            		return 1;
            	}   		
    	}catch (IOException e) {
            System.out.println("File not found");
        }
    	return 0;
	}
	public boolean IngresarMaquina(String numeroArea, String Nombre, String Codigo){
    //Inicializaci�n de variables
    	boolean Condiciones=false;
    	String sCurrentLine;
    	int a,b,c,d;
    	a=b=c=d=0;
    	int abcd= Integer.valueOf(numeroArea);
        try{
    //comprobar existencia area;
        	BufferedReader leer = new BufferedReader(new FileReader("WS.txt"));
        	while ((sCurrentLine = leer.readLine()) != null) {
        		if(sCurrentLine.contentEquals(numeroArea)){
        			a=2;
        		}
        	}
        	if(a==2){
        		a=1;
        		//System.out.println("Condicion 1 aceptada");
        	}else{//Mensaje de Error: Area no existe
        		Ventanas.MensajeError("Area de Trabajo no existente...", "",1);
        	}
        	leer.close();
   //comprobar no existencia c�digo:
        	leer = new BufferedReader(new FileReader("Cod.txt"));
        	while ((sCurrentLine = leer.readLine()) != null) {
        		if(sCurrentLine.contentEquals(Codigo)){
        			b=2;
        		}
        	}
        	if(b==0){
        		b=1;
        		//System.out.println("Condicion 2 aceptada");
        	}else{//Mensaje de Error: Codigo ya implementado.
        		Ventanas.MensajeError("C�digo del Robot ya implementado...", "",1);
        	}
        	leer.close();
   //comprobar inexistencia m�quina;
        	int ab=2;
        	leer = new BufferedReader(new FileReader("Maq.txt"));
        	while ((sCurrentLine = leer.readLine()) != null) {
        		if(ab%3==0){
	        		if(sCurrentLine.contentEquals(Nombre)){
	        			c=2;
	        		}
        		} ab++;
        	}
        	if(c==0){
        		c=1;
        		//System.out.println("Condicion 3 aceptada");
        	}else{//Mensaje de Error: Nombre de maquina ya utilizado 
        		Ventanas.MensajeError("Nombre de m�quina ya implementado...", "",1);
        	}
        	leer.close();
        	
        	int abc=1;
        	leer = new BufferedReader(new FileReader("WS"+abcd+".txt"));
        	while ((sCurrentLine = leer.readLine()) != null) {
        		//System.out.println(sCurrentLine);
        		if(abc%3==2){
        			if(sCurrentLine.contentEquals(Nombre)){
	        			d=2;
	        		}
        		}
        		if(abc%3==0){
	        		if(sCurrentLine.contentEquals(Codigo)){
	        			d=2;
	        		}
        		} 
        		abc++;
        	}
        	if(d==0){
        		d=1;
        		//System.out.println("Condicion 4 aceptada");
        	}else{//Mensaje de Error Macro: Incongruencia en los registros.
        		Ventanas.MensajeError("Incongruencia de los registros internos del area de trabajo.", "INCONGRUENCIA INTERNA",2);
        	}
        	leer.close();
        	if(a==1 & b==1 & c==1 & d==1){
        		Condiciones=true;
        		//System.out.println("Condiciones aceptadas: Se puede acceder al archivo");
        	}else{//Mensaje de Error: No se cumplen las condiciones para el ingreso de la nueva m�quina.
        		Ventanas.MensajeError("No se cumplen las condiciones para el ingreso de la nueva m�quina.", "",1);
        		return false;
        	}
	        if(Condiciones==true){	
	   		 FileWriter Codigos= new FileWriter("Cod.txt", true);
	 		 Codigos.write(Codigo+"\n");
	 		 Codigos.close();
	 		 FileWriter Maquinas = new FileWriter("Maq.txt", true);
			 Maquinas.write(numeroArea+"\n");
			 Maquinas.write(Nombre+"\n");
			 Maquinas.write(Codigo+"\n");
			 Maquinas.close();
			 FileWriter AreaTrabajoEsp = new FileWriter("WS"+abcd+".txt",true);
			 AreaTrabajoEsp.write(numeroArea+"\n");
			 AreaTrabajoEsp.write(Nombre+"\n");
			 AreaTrabajoEsp.write(Codigo+"\n");
	   		 AreaTrabajoEsp.close();
	   		 
			//System.out.println("Archivos actualizados");
	       
	        }
   		 } catch (IOException e) {
            System.out.println("File not found");
            return false;
        }
        return true;
         
    }
		
/*Funciones de Interfaz extras */
	public void EliminarArchivos(){
		try{
		File Delete = new File("Cod.txt");
		Delete.deleteOnExit();
		File Borrar = new File("Maq.txt");
		Borrar.deleteOnExit();
		File eliminar = new File("WS.txt");
		eliminar.deleteOnExit();
		File Bye = new File("RESPALDO.txt");
		Bye.deleteOnExit();
			BufferedReader numero= new BufferedReader(new FileReader("WS.txt"));
			String valor = numero.readLine();
			int NumAreas= Integer.valueOf(valor);
			for(int i=0; i< NumAreas;i++){
				valor= numero.readLine();
				File eliminas = new File("WS"+valor+".txt");
				eliminas.deleteOnExit();
			}
			numero.close();
		}catch(IOException e){
			System.out.println("File not found");
		}
	}
	public static void GeneraRegistro(boolean habilita,  String N_Area, String Codigo_Maquina, String VAceptado){
		try{ 
			/** Orden archivo:
			  * 			-N_Area
		 	  * 			-Codigo_Maquina
		 	  * 			-VAceptado
		 	  */
			if(habilita==true){//Registro M�quina
				FileWriter Regis= new FileWriter("REGISTRO.txt", true);
				 Regis.write(N_Area+"\n");
		 		 Regis.write(Codigo_Maquina+"\n");
		 		 Regis.write(VAceptado+"\n");
		 		 Regis.close(); 
		 		 File elimino= new File("REGISTRO.txt");
		 		 elimino.deleteOnExit();
			}else{//Elimina Maquina Rescribir Registro  // PrintWriter Registro = new PrintWriter("REGISTRO.txt", "UTF-8");
				System.out.println("\tArea: "+N_Area+"\n\tCodigo: "+ Codigo_Maquina+"\n\tValorC: "+VAceptado);
				BufferedReader leer= new BufferedReader(new FileReader("REGISTRO.txt"));	
				LinkedList<String> Almacena = new LinkedList<String>();
				String LeeAntes=null, sCurrentLine;
				int posicion=1, flag=0;
				boolean valorEncontrado=false, valorPosible=false;
				System.out.println("fuera del este");
				while ((sCurrentLine = leer.readLine()) != null) {
					if(valorEncontrado==false){
								if(posicion%3==1){
									if(sCurrentLine.contentEquals(N_Area)==true){
										LeeAntes=sCurrentLine;
										valorPosible=true;
								//		System.out.println("POSIBLE VALOR");
									}else{
										Almacena.add(flag, sCurrentLine);
									//	System.out.println(flag +" : "+sCurrentLine);
										flag++;
										
									}
								}else if(posicion%3==2) {
									if(valorPosible==true){
										if(sCurrentLine.contentEquals(Codigo_Maquina)==true){
											}else{
												//System.out.println(flag+":"+LeeAntes);
												Almacena.add(flag, LeeAntes);
												flag++;
												//System.out.println(flag +":"+sCurrentLine);
												Almacena.add(flag, sCurrentLine);
												flag++;
												valorPosible=false;
											}
									}else{
										//System.out.println(flag +":"+sCurrentLine);
										Almacena.add(flag, sCurrentLine);
										flag++;
									}
								}else{
									if(valorPosible==true){
										if(sCurrentLine.contentEquals(VAceptado)==true){
											//System.out.println("----Encontrado:" +flag +" : "+sCurrentLine);
											
											valorEncontrado=true;
										}else{
											//System.out.println(flag +" : "+sCurrentLine);
											Almacena.add(flag, sCurrentLine);
											flag++;
										}
									}else {
										//System.out.println(flag +" : "+sCurrentLine);
										Almacena.add(flag, sCurrentLine);
										flag++;
									}
									}	
						}else {
						 //System.out.println("Fuera - "+flag +" : "+sCurrentLine);
						 Almacena.add(flag, sCurrentLine);
					     flag++;
						}		
					posicion++;
				}
				PrintWriter RegisED = new PrintWriter("REGISTRO.txt", "UTF-8");
				System.out.println("\nInicio for:");
        		for(int i=0; i<flag;i++){
        		RegisED.println(Almacena.get(i));
        			System.out.println(i+" : "+Almacena.get(i));
        		}
        		RegisED.close();
			}	
		} catch (IOException e) {
	        System.out.println("File not found");
		}
	
	}
	public void GeneraSalida(){ 
		/** Generaci�n del archivo de salida de los datos generados para determinadas m�quinas del laboratorio. **/
		String sCurrentLine;
		int posicion=1;
		try{
			PrintWriter Salida = new PrintWriter("SALIDA.txt", "UTF-8");
			Salida.print("$");
			BufferedReader leer = new BufferedReader(new FileReader("REGISTRO.txt"));
			while ((sCurrentLine = leer.readLine()) != null) {
				if(posicion%4==1){//estoy leyendo un area
					Salida.print("WS"+sCurrentLine);
				} 
				if(posicion%4==2){//Leo c�digo
					Salida.print(","+sCurrentLine+",");
				} 
				if(posicion%4==3){ //Leo valor
					Salida.print(sCurrentLine);
				}
				if(posicion%4==0){
					if(sCurrentLine!=null){
						Salida.print("#");
						posicion++;
						Salida.print("WS"+sCurrentLine);
					}
				}
				System.out.println(posicion+":"+sCurrentLine);
				posicion++;
			}
			Salida.print("$");
			Salida.close();
		}catch(IOException e){
			System.out.println("File not found");
		}
	}
	public static void GArchSistema(){
		/** Salida Informaci�n total del sistema de sistema**/
		String sCurrentLine;
		try{
			BufferedReader numero= new BufferedReader(new FileReader("WS.txt"));
			String valor = numero.readLine();
			int NumAreas= Integer.valueOf(valor);
			LinkedList<String> nombreArea = new LinkedList<String>();
			//System.out.println("Areas de trabajo disponibles: "+NumAreas);
			for(int i=0; i<NumAreas;i++){
				valor= numero.readLine();
				nombreArea.add(i,valor);
			}
			numero.close();
			LinkedList<Integer> CantArea = new LinkedList<Integer>();	
			LinkedList<String> Elementos= new LinkedList<String>();
			
			int  flag=0, lugar=0, pos=0;
			String posicion = nombreArea.get(0);
		//	System.out.println(posicion);
			BufferedReader leer = new BufferedReader(new FileReader("Maq.txt"));
			while ((sCurrentLine = leer.readLine()) != null) {
				if(flag%3==0){
					if(sCurrentLine.contentEquals(posicion)==true){
						pos++;
					}else{
						CantArea.add(lugar, pos);
						lugar++;
						pos=1;
						posicion= nombreArea.get(lugar);
						CantArea.add(lugar, 1);
						//System.out.println(lugar +":" +nombreArea.get(lugar));
					}
				}else if (flag%3==1 | flag%3==2){ //Nombre m�quina o  codigo m�quina
					Elementos.add(sCurrentLine);
				}				
				
				
			flag++;
			}
			System.out.println("lugar: "+ lugar +"- pos: "+pos);
			if(NumAreas>1){
				CantArea.set(lugar, pos);
			}else{
				CantArea.add(Elementos.size()/2);
			}
			for(int a=0; a< Elementos.size(); a++){
				System.out.println(Elementos.get(a));
			}
		
			System.out.println("SIGO VIVO");
			PrintWriter RSalida = new PrintWriter("R_Salida.txt", "UTF-8");
			RSalida.println(NumAreas);
			RSalida.println("$");
			String Datos=null;
			int valorVec=0;
			for(int f=0; f<NumAreas; f++){
				RSalida.println(nombreArea.get(f));
				RSalida.println(CantArea.get(f));
				for(int w=0; w<CantArea.get(f);w++ ){
					for(int k=0; k<2;k++){
						Datos= Elementos.get(valorVec);
						System.out.println(Datos);
						RSalida.println(Datos);
						valorVec++;
					}
				}
				int siguiente =Elementos.size();
				//System.out.println("Siguiente: "+siguiente);
				if(valorVec <siguiente){
					System.out.println("#");
					RSalida.println("#");
				}else{
					//System.out.println("Limite");
					f=NumAreas+1;
				}
			}
			
			RSalida.println("$");
			RSalida.close();
			File elimino= new File("R_Salida.txt");
			elimino.deleteOnExit();
		}catch(IOException e){
			System.out.println("File not found");
		}
	}
	public int Mayor(LinkedList<Integer> e){
		int mayor, tam;
		tam=e.size();
		mayor= e.get(0);
		for(int i=1; i<tam; i++){
			if(mayor< e.get(i)){
				mayor=e.get(i);
			}
		}
		return mayor;
	}
	
/** Funciones para Construir la interfaz**/
	public LinkedList InicioListaConstructor(){
		 String sCurrentLine, AreaTrab;
		 LinkedList<String> lista= new LinkedList<String>();
		 boolean flag=true;
		 int contador1, contador3;
		 try{
			 BufferedReader leer = new BufferedReader(new FileReader("Maq.txt"));
			 contador3=1;
			 sCurrentLine = leer.readLine();
			 AreaTrab= sCurrentLine;
			 lista.add(AreaTrab);
			 contador1=1; //Elementos que faltaron por revisar.
			 while ((sCurrentLine = leer.readLine()) != null) {
				 if(flag==true){
					 if(contador1%3==0){
		        			if(sCurrentLine.equals(AreaTrab)==false){
		        				flag=false;
		        			}
		        		}else if(contador1%3==1 |contador1%3==2){
		        			lista.add(sCurrentLine);
		        		}
					 contador1++;
				 }
	        	contador3++;	
	        	}
			 System.out.println("*****");
			 leer.close();
			 for(int i=0; i<lista.size(); i++){
				 System.out.println(lista.get(i));
			 }
			 System.out.println("*****");
				 //Quedan elementos:
			 lista.add(String.valueOf(contador1));
			 lista.add(String.valueOf(contador3));
				 return lista;
			
		 }catch (IOException e) {
	            System.out.println("File not found");
	            return null;
		 }
	}
	public LinkedList<String> IngresoAreasExtras(int contador1, int contador3){
		 String sCurrentLine, AreaTrab;
		 LinkedList<String> lista= new LinkedList<String>();
		 LinkedList<String> lista3= new LinkedList<String>();
		 boolean flag=true;
		 try{ 
				 do{
					 int apoyo=0;
					 flag=true;
					 BufferedReader leer2 = new BufferedReader(new FileReader("Maq.txt"));
					 while(apoyo<contador1-1){
						 sCurrentLine= leer2.readLine();
						 apoyo++;
					 }
					 sCurrentLine=leer2.readLine();
					 AreaTrab= sCurrentLine;
					 lista.add(AreaTrab);
					 lista3.add(AreaTrab);
					 while ((sCurrentLine = leer2.readLine()) != null) {
						 if(flag==true){
							 if(contador1%3==0){
				        			if(sCurrentLine.equals(AreaTrab)==false){
				        				flag=false;
				        			}
				        		}else if(contador1%3==1 |contador1%3==2){
				        			lista.add(sCurrentLine);
				        			lista3.add(sCurrentLine);
				        		}
							 contador1++;
						 }
			        	}
					 leer2.close();
					 for(int i=0; i<lista.size(); i++){
						 System.out.println(lista.get(i));
					 }
					 System.out.println("*****");
					 System.out.println("Tama�o lista1: " +lista.size());
					 lista.clear();
					 if(contador1!=contador3){
						 lista3.add("*");
					 }
				 }while(contador1!=contador3);
			 System.out.println("Fin de ejecuci�n...\n\t nueva lista: ");
			 for(int a=0; a<lista3.size(); a++){
				 System.out.println(lista3.get(a));
			 }
			 return lista3;
			
		 }catch(IOException ea){
			 System.out.println("No funciona..");
		 	}
		 return lista3;
	 	}
	public static void EliminarAreas1(){
		try{
			BufferedReader numero= new BufferedReader(new FileReader("WS.txt"));
			String valor = numero.readLine();
			int NumAreas= Integer.valueOf(valor);
			
			for(int i=0; i<NumAreas;i++){
				valor= numero.readLine();
				System.out.println("\t\t\tArea: "+ valor);
				File elimino= new File("WS"+valor+".txt");
				elimino.delete();
		}
			numero.close();
		}catch(IOException e){
			System.out.println("File not found");
		}
			
	}
	public static void GArchSistema2(){
		/** Salida Informaci�n total del sistema de sistema**/
		String sCurrentLine;
		try{
			BufferedReader numero= new BufferedReader(new FileReader("WS.txt"));
			String valor = numero.readLine();
			int NumAreas= Integer.valueOf(valor);
			LinkedList<String> nombreArea = new LinkedList<String>();
			//System.out.println("Areas de trabajo disponibles: "+NumAreas);
			for(int i=0; i<NumAreas;i++){
				valor= numero.readLine();
				nombreArea.add(i,valor);
			}
			numero.close();
			LinkedList<Integer> CantArea = new LinkedList<Integer>();	
			LinkedList<String> Elementos= new LinkedList<String>();
			
			int  flag=0, lugar=0, pos=0;
			String posicion = nombreArea.get(0);
		//	System.out.println(posicion);
			BufferedReader leer = new BufferedReader(new FileReader("Maq.txt"));
			while ((sCurrentLine = leer.readLine()) != null) {
				if(flag%3==0){
					if(sCurrentLine.contentEquals(posicion)==true){
						pos++;
					}else{
						CantArea.add(lugar, pos);
						lugar++;
						pos=1;
						posicion= nombreArea.get(lugar);
						CantArea.add(lugar, 1);
						//System.out.println(lugar +":" +nombreArea.get(lugar));
					}
				}else if (flag%3==1 | flag%3==2){ //Nombre m�quina o  codigo m�quina
					Elementos.add(sCurrentLine);
				}				
				
				
			flag++;
			}
			System.out.println("lugar: "+ lugar +"- pos: "+pos);
			if(NumAreas>1){
				CantArea.set(lugar, pos);
			}else{
				CantArea.add(Elementos.size()/2);
			}
			for(int a=0; a< Elementos.size(); a++){
				System.out.println(Elementos.get(a));
			}
		
			System.out.println("SIGO VIVO");
			PrintWriter RSalida = new PrintWriter("Reborn.txt", "UTF-8");
			RSalida.println(NumAreas);
			RSalida.println("$");
			String Datos=null;
			int valorVec=0;
			for(int f=0; f<NumAreas; f++){
				RSalida.println(nombreArea.get(f));
				RSalida.println(CantArea.get(f));
				for(int w=0; w<CantArea.get(f);w++ ){
					for(int k=0; k<2;k++){
						Datos= Elementos.get(valorVec);
						System.out.println(Datos);
						RSalida.println(Datos);
						valorVec++;
					}
				}
				int siguiente =Elementos.size();
				//System.out.println("Siguiente: "+siguiente);
				if(valorVec <siguiente){
					System.out.println("#");
					RSalida.println("#");
				}else{
				//	System.out.println("Limite");
					f=NumAreas+1;
				}
			}
			
			RSalida.println("$");
			RSalida.close();
			
		}catch(IOException e){
			System.out.println("File not found");
		}
	}
	
}