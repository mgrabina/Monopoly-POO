package GUI;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import Modelo.Jugador;

/**
 * Modelo de datos de la tabla del Control View. Parametro necesario interno
 * para el manejo de la clase JTable.
 * 
 * @author mgrabina
 *
 */
public class modeloTabla extends AbstractTableModel {

	private static ArrayList<Jugador>	jugadores;
	private static final String[]		columnas	= { "IdJugador", "Nombre", "Posicion", "Creditos" };
	private static Integer cantjugadores; 


	public static void setJugadores(ArrayList<Jugador> j,Integer cant) {
		cantjugadores=cant;
		jugadores = j;
	}
	public static void removerJugador(Jugador j){
		if(j!=null){
			jugadores.remove(j);
			cantjugadores--;
		}	
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object[][] datos=new Object[cantjugadores+2][4];
		 datos[0][0] ="Nombre";
		 datos[0][1] ="Posicion"; 
		 datos[0][2] ="Creditos";
		 datos[0][3] ="Propiedades";
		for(int fil=0; fil< cantjugadores; fil++){
				datos[fil+1][0]=jugadores.get(fil).getNombre();
				datos[fil+1][1]=jugadores.get(fil).getPosicion();
				datos[fil+1][2]=jugadores.get(fil).getCreditos();
				datos[fil+1][3]=jugadores.get(fil).getStringPropiedades().toString();
	}
		
		return datos[rowIndex][columnIndex];
	}

	@Override
	public int getRowCount() {
		return cantjugadores+1;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}
}
