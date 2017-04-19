package Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Clase que tiene una coleccion de objetos ejecutables, su accion es agarrar
 * una al azar y ejecutarla sobre el jugador
 * 
 * @author Andres di Giovanni
 */
public class Suerte extends Casillero implements Serializable {

	private static final List<CartaSuerte> cartas = new ArrayList<CartaSuerte>();
	private static final Random r = new Random();
	
	public static List<CartaSuerte> getCartas() {
		return cartas;
	}

	/**
	 * Recibe una instancia de la interfaz Ejecutable y construye la carta,
	 * agregandola directamente a la coleccion de clase
	 * @param ejecutable
	 */
	public static void addCarta(Ejecutable e) {
		cartas.add(new CartaSuerte(e));
	}

	public Suerte(){
		super("Suerte");
	}

	/**
	 * Elige una carta al azar y la ejecuta sobre el jugador
	 */
	@Override
	public boolean accion(Jugada j) {
		return cartas.get(r.nextInt(cartas.size() - 1)).ejecutar(j.getJugador());
	}
	
}
