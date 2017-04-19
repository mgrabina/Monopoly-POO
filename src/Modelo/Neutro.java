package Modelo;

import java.io.Serializable;

public class Neutro extends Casillero implements Serializable
{
	public Neutro(String name){
		super(name);
	}

	public boolean accion(Jugada j) {
		// Hacer nada es hacer algo. -Francisco Delgado
		return true;
	}
}
