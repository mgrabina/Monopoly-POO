package Modelo;

import java.io.Serializable;

public class CartaSuerte implements Serializable{
	private static Integer classId=0;
	private final Integer id;
	private final Ejecutable e;

	public CartaSuerte(Ejecutable e){
		id=classId++;
		this.e=e;
	}

	public boolean ejecutar(Jugador j){
		return e.ejecutar(j);
	}

	public int hashCode(){
		return id;
	}

	public boolean equals(Object o){
		if (o == null)
			return false;
		if (o == this)
			return true;
		if(o.getClass() != getClass())
			return false;
		CartaSuerte c = (CartaSuerte) o;
		return c.getId().equals(id);
	}

	public Integer getId() {
		return id;
	}
}
