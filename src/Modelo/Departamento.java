package Modelo;

import java.awt.Color;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Las materias se organizan por departamentos.
 * Para poder comprar casas se necesitan todas la materias del dept.
 * @author 
 *
 */

public class Departamento implements Serializable{	
	private final String nombre;
	private final Integer precioPorCasa;
	private final Set<Materia> materias;
	private final Color color;

	public Departamento(String nombre,Integer precioPorCasa,Color color) {
		this.nombre=nombre;
		materias = new HashSet<Materia>();
		this.precioPorCasa = precioPorCasa;
		this.color=color;
	}

	public void add(Materia m) {
		materias.add(m);
	}

	// setters&getters
	public Color getColor(){
		return color;
	}
	public String getNombre(){
		return nombre;
	}
	
	public Set<Materia> getMaterias(){
		return materias;
	}

	public Integer getPrecioPorCasa() {
		return precioPorCasa;
	}
	
	
	
	
	
	
}
