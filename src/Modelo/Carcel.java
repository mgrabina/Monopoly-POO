package Modelo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Es el casillero que manda a los jugadores a la carcel
 * 
 * @author
 */
public class Carcel extends Casillero implements Serializable{
	private final Map<Jugador,Integer> mapa;
	private final Neutro ubicacionCarcel;
	private final Integer precioSalida;

	public Carcel(List<Jugador> jugadores, Neutro ubicacionCarcel){
		super("Asistida");
		this.ubicacionCarcel = ubicacionCarcel;
		mapa= new HashMap<Jugador,Integer>(jugadores.size());
		for(Jugador j: jugadores){
			mapa.put(j,0);
		}
		precioSalida = 50;
	}

	/**
	 * Mueve el jugador hacia donde esta la carcel y lo agrega al mapa, siendo
	 * el Integer del value la cantidad de turnos que pierde. Siempre se va a
	 * poder realizar, por lo que siempre devuelve true
	 */
	@Override
	public boolean accion(Jugada j) {
		j.getJugador().avanzar(ubicacionCarcel); // la distancia del jugador al casillero
		mapa.replace(j.getJugador(), 3);
		return true;
	}

	/**
	 * Modifica los creditos y en caso de que se realice el pago lo saca del Map
	 * 
	 * @param jugador
	 *            que esta en la carcel y quiere pagar la salida
	 */
	public void pagaCarcel(Jugador j){
		if(j.getCreditos() < precioSalida){
			throw new NoSePudoComprarException("No tenes suficiente plata");
		}
		j.setCreditos(-precioSalida);
		removeJugador(j);
	}

	/**
	 * @param jugador
	 *            a remover de la carcel
	 */
	private void removeJugador(Jugador j){
		mapa.replace(j, 0);
	}

	/**
	 * Getters and setters
	 */
	public Integer getPrecioSalida(){
		return precioSalida;
	}
	
	public Map<Jugador,Integer> getMap(){
		return mapa;
	}

	public Integer getTiempo(Jugador j){
		return mapa.get(j);
	}

}
