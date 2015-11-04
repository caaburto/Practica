package App;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.LinkedList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/* 		Clase que genera el contenido interno de los paneles de las pesta�as de AreasTrabajos con las 
 * m�quinas que existen en cada �rea de trabajo: El objetivo de esta clase es permitir la visualizaci�n de las m�quinas existentes
 * en cada �rea de trabajo adem�s de permitir el ingreso y la eliminaci�n de maquinarias de alguna �rea.
 * 
 * La funci�n VaciarArea() se encuentra en desuso, debido a que se a restringido la generaci�n de �reas de trabajo vac�as.
 * 
 * 		-Esta clase funciona como un panel que contiene varios paneles de la clase "M�quinaria" y dicho panel 
 *       se implementa en la clase "AreasTrabajos".
 * 
 */

public class Area {
	protected JScrollPane scrollPane;
	protected JPanel Area;
	private Maquinaria Maquina;
	private LinkedList<Maquinaria> Lista;
	private LinkedList<String> NombreLista;
	protected int NumeroMaquina=0;
	protected int NumeroArea;
	

	Area(){ //Constructor de clase.
		setNumeroMaquinas(0);
		scrollPane= new JScrollPane();
		Lista = new LinkedList<Maquinaria>();
		NombreLista = new LinkedList<String>();
		Area = new JPanel();
		Area.add(Box.createHorizontalStrut(5));
		Area.setLayout(new BoxLayout(Area, BoxLayout.Y_AXIS));
		scrollPane.getViewport().add(Area);
		scrollPane.setSize(400,400);
		
	}
/** FUNCIONES DE LA CLASE **/

/* IngresarMaquina:
 * 		Esta funcion tiene como objetivo agregar el panel de una nueva m�quina al panel actual que se puede visualizar en la
 * 	 interfaz del programa.
 */
	public void IngresarMaquina(String nombre, String Codigo){
		JPanel jpanel = new JPanel();
		Maquina = new Maquinaria(nombre,Codigo);
		//System.out.println(NumeroArea);
		Maquina.setNumArea(NumeroArea);
		jpanel.setBorder(BorderFactory.createLineBorder(Color.black));
		jpanel.add(Maquina);
		jpanel.add(Box.createHorizontalStrut(10));
		Lista.add(Maquina);
		NombreLista.add(nombre);
		Area.add(jpanel);
		Area.add(Box.createHorizontalStrut(25));
		setNumeroMaquinas(getNumeroMaquinas() + 1);
	}

/*	EliminarMaquina:
 * 		Esta funcion tiene como objetivo crear un nuevo panel sin incluir a la m�quina que fue eliminada por petici�n de
 * 	  usuario para luego permitir que este cambio sea visualizado en la Intefaz del programa.
 * 	  En esta funci�n se obtiene la posici�n de la m�quina que se desea eliminar del panel, y luego se omite su ingreso
 *    cuando se genera el nuevo panel. 
 */	
	public void EliminarMaquina(String nombre){
		JPanel jpanel=new JPanel();
		JPanel JPMaq= new JPanel();
		int a=-1, i;
		for(i=0; i<NombreLista.size(); i++){
			String NomActual=NombreLista.get(i);
			if(nombre==NomActual){
		    	a=i;
		    	i=NombreLista.size();
		    }
		for(i=0; i<Lista.size(); i++){
			if(i!=a & a!=-1){
				Maquina = Lista.get(i);
				JPMaq.setBorder(BorderFactory.createLineBorder(Color.black));
				JPMaq.add(Maquina);
				JPMaq.add(Box.createHorizontalStrut(50));
				Lista.add(i, Maquina);
				jpanel.add(JPMaq);
			}else{
				i--;
			}			
		}
		Area.add(jpanel);
		setNumeroMaquinas(i);
		}
	    
	}
//GET y SET de la cantidad de m�quinas en un �rea.
	public int getNumeroMaquinas() {
		return NumeroMaquina;
	}

	public void setNumeroMaquinas(int numeroMaquinas) {
		NumeroMaquina = numeroMaquinas;
	}

//FUNCION YA NO IMPLEMENTADA...
	public void VaciarArea(){ 
		Area= new JPanel();
		JLabel Mensaje = new JLabel("No existen m�quinas en el Area");
		Mensaje.setHorizontalAlignment(SwingConstants.CENTER);
		Area.add(Mensaje);
		scrollPane.getViewport().add(Area);
		setNumeroMaquinas(0);
	}
	
}
