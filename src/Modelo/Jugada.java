package Modelo;

import java.io.Serializable;

public class Jugada implements Serializable{
	private final Dados	dados;
	private final Jugador	jugador;
	
	public Jugada(){			//Para la primer Jugada
		dados=null;
		jugador=null;
	}
	
	public Jugada(Dados dados, Jugador turno) {
		this.dados = dados;
		jugador = turno;
		dados.tirar();
	}
	
	public void hacer(){
		jugador.avanzar(getN());
	}
	// setters&getters
	public Integer getN() {
		return dados.getN1() + dados.getN2();
	}
	public boolean esDoble(){
		return dados.esDoble();
	}

	public Jugador getJugador() {
		return jugador;
	}

	public Dados getDados() {
		return dados;
	}

	
}
