package Modelo;

import java.io.Serializable;
import java.util.Random;

public class Dados implements Serializable
{
	private final Random dado;
	private Integer	n1;
	private Integer	n2;
	private Boolean	esDoble;

	public Dados(){
		dado= new Random();
		dado.setSeed(System.currentTimeMillis());
		esDoble = false;
		n1 = n2 = 0;
	}

	public Integer tirar() {
		n1 = dado.nextInt(5) + 1;
		n2 = dado.nextInt(5) + 1;
		if (n1.equals(n2))
			esDoble = true;
		else
			esDoble = false;
		return n1+n2;
	}

	// setters&getters
	public Boolean esDoble() {
		return esDoble;
	}

	public Integer getN1() {
		return n1;
	}

	public Integer getN2() {
		return n2;
	}	
}
