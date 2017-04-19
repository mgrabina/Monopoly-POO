package Modelo;

import java.io.Serializable;
/**
 * Un tipo de propiedad.
 * Existen 4 de este tipo en el tablero
 * Se cobra 25 * la cantidad de organizaciones que
 * posee el jugador
 * @author 
 *
 */

public class Organizacion extends Propiedad implements Serializable{
	
	public Organizacion(String nombre) {
		super(nombre, 200, 25);
	}

	@Override
	public boolean accion(Jugada j) {
		Integer amount = 0;
		if(this.getDuenio()!=null){
			if (this.getDuenio() != j.getJugador()){
				Double d = (double) 25 * Math.pow(2, getDuenio().cantOrganizacion() - 1);
				amount = (int) Math.round(d);
				}
			}
		return cobrar(j.getJugador(),amount);
	}
}
