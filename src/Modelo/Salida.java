package Modelo;

import java.io.Serializable;
/**
 * casillero de salida
 * al pasar agrega 200 creditos al jugadors
 * @author 
 *
 */
public class Salida extends Casillero implements Serializable{
	private final  Integer pago;

	public Salida(Integer pago){
		super("Salida");
		this.pago=pago;
	}

	@Override
	public boolean accion(Jugada j) {
		j.getJugador().setCreditos(pago);
		return true;
	}

	public Integer getPago(){
		return pago;
	}
}
