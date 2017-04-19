package Modelo;

import java.io.Serializable;

/**
 * Son los casilleros de los que va a estar formado el tablero
 * @author 
 *
 */
public abstract class Casillero implements Accionable,Serializable{
	//carcel y free parking
	private static Integer classId=0;
	private final String nombre;
	private final Integer posicion;

	public Casillero(String nombre){
		this.nombre=nombre;
		posicion=classId;
		classId++;
	}

	/**
	 * @return si el casillero se puede comprar
	 */
	public Boolean sePuedeComprar(){
		if(this instanceof Propiedad){
			Propiedad propiedad = (Propiedad) this;
			if(!propiedad.isOwned()){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Llama al metodo de comprar de la propiedad
	 * @param jugador que quiere comprar
	 */
	public void comprar(Jugador j){
		if(this instanceof Propiedad){
			((Propiedad)this).comprar(j);
		}
	}
	
	public boolean equals(Object obj){
		if(obj==null)
			return false;
		if(obj==this)
			return true;
		if(obj.getClass()!=this.getClass())
			return false;
		Casillero c=(Casillero) obj;
		return c.getPosicion().equals(posicion);
	}

	public int hashcode(){
		return posicion;
	}

	/**
	 * Gatters y setters
	 */
	public static Integer getClassId() {
		return classId;
	}

	public String getNombre() {
		return nombre;
	}

	public Integer getPosicion() {
		return posicion;
	}
}
