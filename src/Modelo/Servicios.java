package Modelo;

import java.io.Serializable;
/** Es un tipo de propiedad.
 * Existen dos casilleros de este tipo.
 * Para cobrar multiplica los dados que sacaste por 7 
 * o por 10 segun tengas 1 o las dos propiedades
 * @author 
 */
public class Servicios extends Propiedad  implements Serializable{

	public Servicios(String nombre, Integer costo) {
		super(nombre, costo, 0);
	}

	@Override
	public boolean accion(Jugada j) {
		return cobrar(j.getJugador(), j.getN());
	}
	@Override
	public boolean cobrar(Jugador j, Integer dados){
		Integer amount = 0;
		if(isOwned() && getDuenio()!= j){
			if(getDuenio().cantServicios()==1)
				amount = dados*7;
			else
				amount = dados*10;
			return super.cobrar(j, amount);
		}
		return true;
	}
	
}
