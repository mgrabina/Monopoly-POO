package Modelo;

import java.io.Serializable;

/**
 * Casillero que cobra 200 al caer.
 * @author 
 *
 */

public class Impuestos extends Casillero implements Serializable{

	private final static Integer VALORIMPUESTO= 200; 
	
	
	public Impuestos(String nombre) {
		super(nombre);
	}
	
	@Override
	public boolean accion(Jugada j) {
		if (j.getJugador().getCreditos() > VALORIMPUESTO){ 
			j.getJugador().setCreditos(-VALORIMPUESTO);
			return true;
		}
		return false;
	}
}
