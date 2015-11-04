package App;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import cl.marav.P_Logica.*;

/* Clase que vela por la generaci�n independiente de cada panel de maquinaria de una determinada, gestionando tambien los eventos 
 * que genera la seleci�n de alg�n elemento existente en la ComboBOx al igual que la aceptaci�n o no de algun valor generado.
 * Es en esta clase en la cual se llaman a las ventanas de cada uno de los tipos de distribuci�n 
 * estadisticos implementados en la parte l�gica del programa y en la cual se llama a la generaci�n del registro de valores aceptados
 * en el programa.
 * 
 */
public class Maquinaria extends JPanel implements ActionListener{
	private JPanel jpanel;
	private String Nombre;
	private String Codigo;
	private JComboBox calculoProb;
	private JTextField Valor;
	private JRadioButton Acepted;
	private boolean Aceptado;
	private int NumArea;
	ActionListener actionListener;
	private int opcion;
	private float  ValorACEPTADO= 0;
	
	Maquinaria(){
		ValorACEPTADO=-1;

	}
	
	Maquinaria(String Nombre,String Codigo){
		JPanel jpanel= new JPanel();
		jpanel.setLayout(new BoxLayout(jpanel, BoxLayout.X_AXIS));
		if(Nombre!= null){
			this.Nombre= Nombre;
		}
		if(Codigo!= null){
			this.Codigo= Codigo;
		}
		Aceptado= false;
		Acepted= new JRadioButton();
		
		calculoProb = ComboBoxProb();
		Valor= new JTextField(5);
		Acepted.setEnabled(false);
		Valor.setEditable(false);
		jpanel.add(Box.createHorizontalStrut(10));
		JLabel nombre = new JLabel(Nombre);
		jpanel.add(nombre);
		jpanel.add(Box.createHorizontalStrut(200));
		jpanel.add(calculoProb);
		jpanel.add(Box.createHorizontalStrut(75));
		jpanel.add(Valor);
		jpanel.add(Acepted);
		jpanel.add(Box.createHorizontalStrut(5));
		//jpanel.setBorder(BorderFactory.createLineBorder(Color.black));
		add(jpanel);
		
		//Eventos
		Acepted.addActionListener(this);
		calculoProb.addActionListener(this);
		
	}

	public void setNumArea(int NumArea){
		this.NumArea= NumArea;
	}
	
	public JComboBox ComboBoxProb(){
		final String lista[] = {"Calculo Probabilidad",
				"Distribucion T de Student"  			//1
				,"Distribucion Exponencial" 			//2
				,"Distribucion Laplace"					//3
				,"Distribucion Normal Estandar"			//4
				,"Distribucion Poisson"					//5
				,"Distribucion Uniforme"				//6	
				,"Distribucion Weibull"					//7
				,"Congruencia Lineal"					//8
				,"Ingresar Datos"						//9
				};
		final JComboBox comboBox = new JComboBox(lista);
		comboBox.setMaximumRowCount(4);
		comboBox.setEditable(false);
		return(comboBox);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Generacion_Ventanas_Emergentes GVE= new Generacion_Ventanas_Emergentes();
		Funciones_Interfaz FI = new Funciones_Interfaz();
		float valor=0;
		if(e.getSource()== Acepted & opcion!=0){
			if(Aceptado==false){
				Aceptado=true;
				calculoProb.setEnabled(false);
				String aceptado= Valor.getText();
				ValorACEPTADO= Float.valueOf(aceptado);
				String NumerArea= Integer.toString(NumArea);
				FI.GeneraRegistro(true,NumerArea,Codigo,aceptado);
				
			} else{
				Aceptado=false;
				calculoProb.setEnabled(true);
				ValorACEPTADO= -1;
				String aceptado= Valor.getText();
				String NumerArea= Integer.toString(NumArea);
				FI.GeneraRegistro(false,NumerArea,Codigo,aceptado);
			}
		}
		
		
		if(e.getSource()== calculoProb){
			opcion= calculoProb.getSelectedIndex();
			System.out.println("opcion: "+opcion);
			if(opcion==0){//No se ha elegido ninguna opcion
				valor=-1;
			}
			if(opcion==1){ //1	"Distribucion T de Student"
				valor= GVE.VentanaDistribucionTStudent();
			} 
			if(opcion==2){//2	"Distribucion Exponencial"
				valor= GVE.VentanaDistribucionExponencial();
			}
			if(opcion==3){//3	"Distribucion Laplace"
				valor= GVE.VentanaDistribucionLaplace();
			}
			if(opcion==4){//4	"Distribucion Normal Estandar"
				valor=GVE.VentanaDistribucionNormalEstandar();
			}
			if(opcion==5){//5	"Distribucion Poisson"
				valor= GVE.VentanaDistribucionPoisson();
			}
			if(opcion==6){//6	"Distribucion Uniforme"	
				valor= GVE.VentanaDistribucionUniforme();
			}	
			if(opcion==7){//7	"Distribucion Weibull"	
				valor= GVE.VentanaDistribucionWeibull();
			}
			if(opcion==8){//8	"Congruencia Lineal"
				valor= GVE.VentanaCongruencia_Lineal();
			}
			if(opcion==9){//9	"Ingreso Usuario"
				valor= GVE.VentanaIngresoUsuario();
				
			}
			if(valor==-1){
				Acepted.setEnabled(false);
			}else  if(valor>=0){
					if(valor>=1){
							valor=1;
					}
					String Muestra= Float.toString(valor);
					Valor.setText(Muestra);
					Acepted.setEnabled(true);
				}
			}
		
	}
}
