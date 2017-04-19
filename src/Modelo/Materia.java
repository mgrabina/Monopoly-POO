package Modelo;

import java.io.Serializable;

public class Materia extends Propiedad implements Serializable {
	private final Departamento departamento;
	private Integer casas; //5 casas es un hotel

	public Materia(String nombre, Integer costo, Integer renta, Departamento departamento) {
		super(nombre, costo, renta);
		this.departamento = departamento;
		departamento.add(this);
		casas = 0;
	}
	
	/**
	 * Decrementa la cantidad de casas y modifica los creditos correspondientes
	 * @param jugador que quiere comprar la casa
	 * @return devuelve si la propiedad tenia casas para vender
	 */
	public boolean venderCasa(){
		if(casas>0){
			casas--;
			Double ganancia = new Double (departamento.getPrecioPorCasa()/2);
			getDuenio().setCreditos(ganancia.intValue());
			getDuenio().setCreditosPropiedades(-ganancia.intValue());		//le resto a creditos de propiedades esta ultima casa que vendio
			return true;
		}
		return false;				
	}

	/**
	 * Tira excepcion si no se puede comprar la casa y modifica los creditos del duenio 
	 */
	public void comprarCasa() { 
		if(getDuenio().getCreditos() < departamento.getPrecioPorCasa()){
			throw new NoSePudoComprarException("No tenes plata");
		}
		if (!getDuenio().getPropiedades().containsAll(departamento.getMaterias())) {
			throw new NoSePudoComprarException("No tenes todas las materias del departamento");
		}
		int min = 5;
		for(Materia m : departamento.getMaterias()){
			if(m.getCasas() < min)
				min = m.getCasas();
		}
		if(casas > min){
			throw new NoSePudoComprarException("Faltan las otras casas del departamento");
		}
		else{
			casas++;			//le resto los creditos por vender y le sumo a creditos propiedades el valor de venta de esta
			getDuenio().setCreditos(-departamento.getPrecioPorCasa());									
			getDuenio().setCreditosPropiedades(departamento.getPrecioPorCasa()/2);
		}

	}

	/**
	 * Tira excepcion si no se vendieron todas las casas antes de hipotecar
	 */
	@Override
	public void hipotecar() {
		if (casas != 0) {
			throw new NoSePuedeHipotecarException("Primero venda todas las casas antes de hipotecar");
		} else super.hipotecar();
	}

	/**
	 * Cobra la renta para el duenio
	 * @jugada contiene al jugador que debe pagar
	 */
	@Override
	public boolean accion(Jugada j) {
		if (isOwned() && j.getJugador() != getDuenio() ){
			Integer precioFinal = casas * departamento.getPrecioPorCasa() + super.getRenta();
			return super.cobrar(j.getJugador(), precioFinal);
		}
		return true;
	}
	
	public void reset(){
		casas=0;
		super.reset();
	}

	/**
	 * Getters and setters
	 */
	public Departamento getDepartamento() {
		return departamento;
	}

	public Integer getCasas() {
		return casas;
	}

}
