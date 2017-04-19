package Modelo;
import java.io.Serializable;

import Controller.Listeners;
import Controller.Monopoly;
import GUI.MonopolyView;

public class Main implements Serializable
{
	public static void main(String[] args)
	{
		Monopoly partida = new Monopoly();
		Listeners.iPartida(partida);
		MonopolyView.iniciar(partida);
	}

}
