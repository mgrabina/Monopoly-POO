package Modelo;

import java.io.Serializable;

/**
 * Son los casilleros que se pueden comprar. No es testeada porque es
 * abstractada y sus metodos son testeados en sus subclases
 * 
 * @author
 */

public abstract class Propiedad extends Casillero implements Serializable{
	private final Integer costo;
	private final Integer renta;
	private Jugador	duenio;
	private Boolean	esHipotecada;

	public Propiedad(String nombre, Integer costo, Integer renta) {
		super(nombre);
		this.costo = costo;
		this.renta = renta;
		duenio = null;
		esHipotecada = false;
	}
	
	/**
	 * Realiza un cambio de propiedad con un monto pautado durante el juego
	 */
	public static void transaccion(Propiedad propiedad, Jugador comprador, Integer monto) {
		if (comprador.getCreditos() < monto)
			throw new NoSePudoComprarException(comprador.getNombre() + " no tiene la plata suficiente");
		if (!propiedad.isOwned())
			throw new NoSePudoComprarException("La propiedad " + propiedad.getNombre() + " no tiene duenio");
		propiedad.getDuenio().setCreditos(monto);
		comprador.setCreditos(-monto);
		propiedad.getDuenio().getPropiedades().remove(propiedad);
		propiedad.setDuenio(comprador);
		propiedad.getDuenio().getPropiedades().add(propiedad);
	}

	/**
	 * Define comprar la propiedad y tira excepcion si no le alcanza la plata
	 */
	public void comprar(Jugador j){
		if(j.getCreditos() < costo) {
			throw new NoSePudoComprarException("No tenes suficiente plata");
		}
		duenio = j;
		j.addPropiedad(this);
	}

	/**
	 * Tira excepcion si la propiedad ya esta hipotecada, en caso contrario
	 * modifica los creditos correspondientes y pone el boolean esHipotecada en
	 * true, no checkea si tiene sunio porque directamente el jugador no tiene
	 * la opcion de hipotecar
	 */
	public void hipotecar(){
		if(this.esHipotecada())
			throw new NoSePuedeHipotecarException("La propiedad ya esta hipotecada");
		Double precio = new Double(costo / 2);   										//cuando se hipoteca se agrega los creditos
		duenio.setCreditos(precio.intValue());											//en efectivo y se le resta los creditos
		duenio.setCreditosPropiedades(-(precio.intValue()));							// de propiedades, ya que no puede  
		esHipotecada = true; 															// hipotecar esta ultima
		
	}

	/**
	 * Tira excepcion si no tiene la suficiente plata para deshipotecar y en caso contrario
	 * modifica los creditos correspondientes y pone el boolean esHipotecada en false
	 */
	public void deshipotecar(){
		Double precioDeshipoteca = new Double(costo * 0.75);				//cuando deshipotecas se le suma a los creditos propiedades el valor de la hipoteca, ya que esta propiedad se puede volver a hipotecar
		Double precioHipoteca = new Double(costo * 0.5);
		if(precioDeshipoteca > duenio.getCreditos())
			throw new NoSePudoComprarException("No tenes la suficiente plata para deshipotecar");
		if(esHipotecada == false)
			throw new NoSePudoComprarException(getNombre() + " no esta hipotecada por lo que no se puede deshipotecar");
		duenio.setCreditos(-precioDeshipoteca.intValue());					 
		duenio.setCreditosPropiedades(precioHipoteca.intValue());
		esHipotecada = false;
	}
	
	/**
	 * @return si la propiedad tiene duenio o no
	 */
	public boolean isOwned(){
		return duenio != null;
	}
	
	/**
	 * Se verifica si se puede realizar el pago y se modifican los creditos de
	 * los jugadores involucrados
	 * 
	 * @param paga
	 *            jugador que paga la renta
	 * @param precioFinal
	 *            varia de acuerdo al tipo de propiedad
	 * @return si se pudo pagar la renta o no
	 */
	public boolean cobrar(Jugador paga, Integer precioFinal){
		if(isOwned() && !duenio.equals(paga)){
			if(paga.getCreditosTotales() < precioFinal){
				paga.setEstaJugando(false);
				return false;
			}
			if(paga.getCreditos() < precioFinal)
				return false;
			else{
				paga.setCreditos(-precioFinal);
				duenio.setCreditos(precioFinal);
			}
		}
		return true;
	}
	/**
	 * para cuando pierde un jugador convierte sus propiedades en comprables
	 */
	
	public void reset(){
		duenio=null;
		esHipotecada=false;
	}
	
	
	/**
	 * Se utiliza para que cada propiedad realice la accion correspondiente
	 */
	public abstract boolean accion(Jugada j);

	/**
	 * Getters and setters
	 */
	public Integer getCosto() {
		return costo;
	}

	public Integer getRenta() {
		return renta;
	}

	public Jugador getDuenio() {
		return duenio;
	}

	public void setDuenio(Jugador duenio) {
		this.duenio = duenio;
	}

	public Boolean esHipotecada() {
		return esHipotecada;
	}
}
