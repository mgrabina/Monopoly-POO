package Modelo;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
/**
 * Contiene todos los datos necesarios de los jugadores y las acciones que puede realizar
 * @author 

 */
public class Jugador implements Serializable{
	private Integer creditos;
	private static Integer idjuego=1;
	private final Integer id;
	private Integer posicion;
	private final Set <Propiedad> propiedades;
	private final String nombre;
	private Integer creditosPropiedades; //son los creditos que  podes tener en caso de hipotecar y vender las casas
	private Boolean estaJugando;
	/**
	 * Setea los valaores iniciales, incluyendo los creditos que comienzan en 1500 segun el reglamento
	 * @param nombre es el nombre del jugador ingresado
	 */
	public Jugador(String nombre){
		propiedades =  new HashSet<Propiedad>();
		creditos = 1500;
		creditosPropiedades=0;
		id=idjuego;
		idjuego++;
		posicion=0;
		this.nombre=nombre;
		estaJugando = true;
	}
	
	/**
	 * Le permite al jugador comprar una propiedad
	 * @param propiedad a comprar
	 */
	public void addPropiedad(Propiedad prop){
		propiedades.add(prop);
		creditosPropiedades+=prop.getCosto()/2;
		creditos-=prop.getCosto();
	}
	
	/**
	 * @param cantidad de casilleros a avanzar
	 */
	public void avanzar(int cantidad){
		if(cantidad>0)
			posicion = (posicion + cantidad) % Casillero.getClassId();
	}
	
	/**
	 * @param casillero al cual se va a mover
	 */
	public void avanzar(Casillero c){
		if (c!=null)
				posicion=c.getPosicion();
	}
	
	/**
	 * @return la cantidad de organizaciones del cual el jugador es duenio
	 */
	public Integer cantOrganizacion(){
		int cantidad=0;
		for(Propiedad p: propiedades)
			if(p.getClass()==Organizacion.class)
				cantidad++;
		return cantidad;
	}
	
	/**
	 * @return la cantidad de servicios del cual el jugador es duenio
	 */
	public Integer cantServicios(){
		int cantidad=0;
		for(Propiedad p: propiedades)
			if(p.getClass()==Servicios.class)
				cantidad++;
		return cantidad;
	}
	
	public boolean equals(Object j) {
		if (j.getClass() == null) return false;
		if (j.getClass() == this.getClass()) if (((Jugador) j).getId() == this.id) return true;
		return false;
	}

	public int hashCode() {
		return id;
	}

	/**
	 * Getters y setters
	 */
	public void setCreditosPropiedades(int creditos){
		this.creditosPropiedades+=creditos; //tirar exception
	}
	
	public Integer getCreditosTotales(){
		return creditos+creditosPropiedades;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public Integer getPosicion(){
		return posicion;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setCreditos(Integer creditos){
		this.creditos+=creditos;
	}
	
	public Integer getCreditos(){
		return creditos;
	}
	
	public Boolean getEstaJugando(){
		return estaJugando;
	}
	
	public Set<Propiedad> getPropiedades(){
		return propiedades;
	}
	
	public void setEstaJugando(Boolean estaJugando){
		this.estaJugando = estaJugando;
	}
	public String getStringPropiedades(){
		String a = new String("[");
		for (Propiedad p : propiedades) {
			a += " " + p.getPosicion();
		}
		return a + " ]" ;
	}

	
	
	
}
