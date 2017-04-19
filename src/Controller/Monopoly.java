package Controller;
import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import GUI.ControlView;
import GUI.modeloTabla;
import Modelo.Carcel;
import Modelo.Casillero;
import Modelo.Dados;
import Modelo.Departamento;
import Modelo.Ejecutable;
import Modelo.Impuestos;
import Modelo.Jugada;
import Modelo.Jugador;
import Modelo.Materia;
import Modelo.Neutro;
import Modelo.NoSePudoComprarException;
import Modelo.NoSePuedeHipotecarException;
import Modelo.Organizacion;
import Modelo.Propiedad;
import Modelo.Salida;
import Modelo.Servicios;
import Modelo.Suerte;

/**
 * Clase que organiza la logica del juego y se comunica con el Listener
 * @author Martina Arco, Andres Di Giovanni
 */

public class Monopoly implements Serializable {
	private boolean isPlaying;
	private List<Casillero> casilleros;
	private final List<Departamento> departamentos;
	private List<Jugador>				jugadores;
	private final Carcel carcel;
	private transient Iterator<Jugador> turno; 
	private Boolean fueDoble;
	private Integer countDoble;
	private Jugada jugada;
	private Integer CantJugadores=0;

	public Monopoly() {
		isPlaying = true;
		jugada = new Jugada();
		fueDoble = false;
		countDoble = 0;
		jugadores = new ArrayList<Jugador>(6);
		departamentos = new ArrayList<Departamento>();
		casilleros = new ArrayList<Casillero>();
		this.iDepartamentos();
		this.iCartas();
		this.iTablero();
		carcel= (Carcel) casilleros.get(30);
	}
	
	/**
	 * Se inicializan los casilleros
	 */
	private void iTablero(){
			casilleros.add(new Salida(200));
	
			casilleros.add(new Materia("Introduccion a la informatica",60,2,departamentos.get(0)));
			casilleros.add(new Suerte());
			casilleros.add(new Materia("Metodologia",60,4,departamentos.get(0)));
			casilleros.add(new Impuestos("ADITBA"));
			casilleros.add(new Organizacion("SABF"));
			casilleros.add(new Materia("XML",100,6,departamentos.get(1)));
			casilleros.add(new Suerte());
			casilleros.add(new Materia("Formacion",100,6,departamentos.get(1)));
			casilleros.add(new Materia("Sistemas de Representacion",120,8,departamentos.get(1)));
	
			casilleros.add(new Neutro("Clase de apoyo"));
	
			casilleros.add(new Materia("Quimica",140,10,departamentos.get(2)));
			casilleros.add(new Servicios("La Copia",150));
			casilleros.add(new Materia("Matematica discreta",140,10,departamentos.get(2)));
			casilleros.add(new Materia("POO",160,12,departamentos.get(2)));
			casilleros.add(new Organizacion("CEITBA"));
			casilleros.add(new Materia("Metodos Numericos I",180,14,departamentos.get(3)));
			casilleros.add(new Suerte());
			casilleros.add(new Materia("Analisis I",180,14,departamentos.get(3)));
			casilleros.add(new Materia("Fisica II",180,16,departamentos.get(3)));
	
			casilleros.add(new Neutro("Sum"));
	
			casilleros.add(new Materia("Probabilidad y estadistica ",220,18,departamentos.get(4)));
			casilleros.add(new Suerte());
			casilleros.add(new Materia("Analisis II",220,18,departamentos.get(4)));
			casilleros.add(new Materia("Fisica I",240,20,departamentos.get(4)));
			casilleros.add(new Organizacion("Trama"));
			casilleros.add(new Materia("Arquitectura de la pc",260,22,departamentos.get(5)));
			casilleros.add(new Materia("Algebra",260,22,departamentos.get(5)));
			casilleros.add(new Servicios("Eatbar",150));
			casilleros.add(new Materia("Metodos numericos II",280,24,departamentos.get(5)));
	
			casilleros.add(new Carcel(jugadores, (Neutro) casilleros.get(10)));
			
			casilleros.add(new Materia("Fisica 3",300,26,departamentos.get(6)));
			casilleros.add(new Materia("Base de Datos",300,26,departamentos.get(6)));
			casilleros.add(new Suerte());
			casilleros.add(new Materia("PI",320,28,departamentos.get(6)));
			casilleros.add(new Organizacion("Deporte"));
			casilleros.add(new Suerte());
			casilleros.add(new Materia("Sistemas operativos",350,30,departamentos.get(7)));
			casilleros.add(new Impuestos("EATBAR"));
			casilleros.add(new Materia("Logica computacional",400,40,departamentos.get(7)));
	}
	
	/**
	 * Se crean las cartas de Suerte con clases anonimas
	 */
	private void iCartas(){
		Suerte.addCarta(new Ejecutable(){
			@Override
			public boolean ejecutar(Jugador j){
				printMessage(j.getNombre() + ", retroceda 2 casilleros.");
				j.avanzar(-2);
				return true;
			}
		});
		Suerte.addCarta(new Ejecutable() {
			@Override
			public boolean ejecutar(Jugador j) {
				printMessage(j.getNombre() + ", comiste en la biblioteca, pone 50");
				if (j.getCreditosTotales() > 50) {
					j.setCreditos(-50);
					return true;
				} else return false;
			}
		});
		Suerte.addCarta(new Ejecutable() {
			@Override
			public boolean ejecutar(Jugador j) {
				printMessage("Enhorabuena! " + j.getNombre()
						+ " ganaste 25 por haber estudiado Iterator para el primer parcial");
				j.setCreditos(25);
				return true;
			}
		});
		Suerte.addCarta(new Ejecutable() {
			@Override
			public boolean ejecutar(Jugador j) {
				printMessage("Cobre 200!");
				j.setCreditos(200);
				return true;
			}
		});
		Suerte.addCarta(new Ejecutable(){
			@Override
			public boolean ejecutar(Jugador j){
				printMessage("Tenes que pagarle a todos 50");
				int cJugadores=0;
				for(Jugador otro: jugadores){
					if(!j.equals(otro)){
						cJugadores++;
						otro.setCreditos(50);
					}
				}
				int amount = -cJugadores*50;
				if(Math.abs(amount) <= j.getCreditosTotales()){
					j.setCreditos(-cJugadores*50);
					return true;
				} else
					return false;
			}
		});
		Suerte.addCarta(new Ejecutable(){
			@Override
			public boolean ejecutar(Jugador j){
				//mensaje al view
				int cCasas=0;
				for(Propiedad p: j.getPropiedades()){
					if(p instanceof Materia)
						cCasas+= ((Materia) p).getCasas();
				}
				int amount = -cCasas*50;
				if(Math.abs(amount) <= j.getCreditosTotales()){
					j.setCreditos(-cCasas*50);
					return true;
				} else
					return false;
			}
		});
	}

	/**
	 * Se inicializan los departamentos con su respectivo nombre y color
	 */
	private void iDepartamentos(){		
		departamentos.add(new Departamento("Otras",50,Color.MAGENTA));
		departamentos.add(new Departamento("",50,Color.RED));
		departamentos.add(new Departamento("",100,Color.YELLOW));
		departamentos.add(new Departamento("Quimica",100,new Color(255,127,80)));
		departamentos.add(new Departamento("",150,Color.BLUE));
		departamentos.add(new Departamento("Programacion",150,Color.CYAN));
		departamentos.add(new Departamento("Fisica",200,Color.ORANGE));
		departamentos.add(new Departamento("Matematica",200,Color.PINK));
	}

	/**
	 * Inicializa la jugada
	 * @return si va a la carcel
	 */
	private boolean iJugada() {			
		if(!fueDoble){	
			countDoble=0;
			jugada = new Jugada(new Dados(),cambiaTurno());
		}
		else {
			jugada = new Jugada(new Dados(),jugada.getJugador());
			countDoble++;
		}
		if(countDoble>2){
			carcel.accion(jugada);
			if(JOptionPane.showConfirmDialog(null, "Queres pagar "+ carcel.getPrecioSalida() + " para salir en el proximo turno?", "Salga de la Carcel hoy!", JOptionPane.YES_NO_OPTION) == 1){
				try{
					carcel.pagaCarcel(jugada.getJugador());
				}
				catch(NoSePudoComprarException exception){
					printMessage(exception.getMessage());
				}
			}
			fueDoble = false;		//el proximo iJugada va a cambiar turno
			return false;
		}
		fueDoble = jugada.esDoble();
		return true;
	}

	private Jugador cambiaTurno() {
		if (!turno.hasNext()) 
			turno = jugadores.listIterator(); // Turno apunta de vuelta al primero
		return turno.next();
	}

	/**
	 * Pregunta si quiere comprar y en ese caso se fija si puede
	 * @param jugada actual
	 */
	private void pruebaComprar(Jugada j){
		Propiedad propiedad= (Propiedad) casilleros.get(j.getJugador().getPosicion());
		if(JOptionPane.showConfirmDialog(null, "Quiere comprar " + propiedad.getNombre() + " por " + propiedad.getCosto() + "?", "Compre ahora!", JOptionPane.YES_NO_OPTION) == 0){
			try{
				propiedad.comprar(j.getJugador());
				printMessage(j.getJugador().getNombre() + " compro " + propiedad.getNombre() + " por " + propiedad.getCosto() + " creditos.");
			} catch(NoSePudoComprarException excepcion){
				printMessage(excepcion.getMessage());
			}
		}
	}

	/**
	 * Define el ciclo de la jugada, interacuando con el modelo y el front
	 */
	public void jugar(){
		if(iJugada()){ 			//si fue a la carcel no sigue el turno			
			printMessage("Es el turno de " + jugada.getJugador().getNombre());
			printMessage("Dados:"+jugada.getDados().getN1()+"+"+jugada.getDados().getN2());
			if (jugada.esDoble())
				printMessage("Sacaste doble, tire de nuevo!!!");
			Integer posicionAnterior = jugada.getJugador().getPosicion();
			jugada.hacer();
			Casillero cLlegada = casilleros.get(jugada.getJugador().getPosicion());
			if(posicionAnterior > cLlegada.getPosicion() && cLlegada != casilleros.get(0)){    //Si paso por la salida tiene que cobrar			
				casilleros.get(0).accion(jugada);
				printMessage(getJugada().getJugador().getNombre() + " cobro 200 creditos");
			}
	
			while(!accionar(cLlegada));
	
			if(cLlegada.sePuedeComprar())		//Si cayo en una propiedad y no tiene duenio, ofrecer compra a traves de view
				pruebaComprar(jugada);
	
			System.out.println("Turno del jugador" + jugada.getJugador().getNombre());
		}
		if(jugadores.size() == 1)
			isPlaying=false;
	}

	/**
	 * Se llama cada vez que se cae en un casillero y se fija si el jugador pierde
	 * @param casillero donde esta el jugador en ese momento
	 * @return si se pudo realizar la accion correspondiente al casillero
	 */
	private boolean accionar(Casillero c) {
		if (!c.accion(jugada)) { // accion devuelve si se pudo hacer el pago
			if (!jugada.getJugador().getEstaJugando()) {
				jugadores.remove(jugada.getJugador());
				CantJugadores--;
				modeloTabla.removerJugador(jugada.getJugador());
				printMessage("El jugador "+ jugada.getJugador().getNombre()+ " perdio.");
				turno=jugadores.listIterator();
				for(Propiedad p: jugada.getJugador().getPropiedades())
					p.reset();
				if(jugadores.size()==1){
					printMessage("Juego terminado. ");
					JOptionPane.showMessageDialog(null, "Juego terminado. El ganador es " +jugadores.get(0).getNombre()+"" );
					System.exit(1);
					
				}
				
				return true;	
			} else {
				printMessage("Tenes que hipotecar");
				Listeners.hipotecar();
			}
			return false;
		}
		else if(c instanceof Propiedad){
			if(((Propiedad) c).isOwned())
				if(c instanceof Materia)
					printMessage("Se pago la renta con "+((Materia)c).getCasas()+" casas:"+ 
					(((Materia)c).getRenta()+((Materia)c).getDepartamento().getPrecioPorCasa()*((Materia)c).getCasas())+"");  
					
					
		}
		return true;
	}
	
	/**
	 * Tira excepcion con el mensaje correspodiente si no se pudo hacer la accion correspondiente,
	 * no se checkea si no tiene duenio porque no apareceria la opcion cuando la llama el Listener
	 * @param materia en donde se hace la accion
	 */
	public void comprarCasa(Materia m){
		try{
			m.comprarCasa();
			printMessage("El jugador "+jugada.getJugador().getNombre()+" ha comprado una casa para "+m.getNombre());
		} catch (NoSePudoComprarException e){
			printMessage(e.getMessage());
		}
	}
	
	public void deshipotecar(Propiedad p){
		try{
			p.deshipotecar();
			printMessage("Se ha deshipotecado " + p.getNombre());
		}catch (NoSePudoComprarException e) {
			printMessage(e.getMessage());
		}
	}
		
	public void hipotecar(Propiedad p){
		try{
			p.hipotecar();
			printMessage("Se ha hipotecado " + p.getNombre());
		}catch (NoSePuedeHipotecarException e) {
			printMessage(e.getMessage());
		}
	}
	
	/**
	 * Imprime el mensaje
	 * @param texto a imprimir
	 */
	private void printMessage(String texto) {
		ControlView.agregarMensaje(texto);
	}
	
	/**
	 * Getters and setters
	 */
	
	
	public void setCantJugadores(Integer a){
		CantJugadores=a;
	}
	public void setCasilleros(List<Casillero> casilleros) {
		this.casilleros = casilleros;
	}

	public Integer getCantJugadores(){
		return CantJugadores;
	}
	public void setTurno(Iterator<Jugador> turno) {
		this.turno = turno;
	}
	
	public Jugada getJugada() {
		return jugada;
	}
	
	public boolean getIsPlaying(){
		return isPlaying;
	}

	public ArrayList<Casillero> getCasilleros() {
		return (ArrayList<Casillero>) casilleros;
	}

	public ArrayList<Jugador> getJugadores() {
		return (ArrayList<Jugador>) jugadores;
	}

	public ArrayList<Departamento> getDeptos() {
		return (ArrayList<Departamento>) departamentos;
	}

	public Boolean getFueDoble() {
		return fueDoble;
	}

	public void setFueDoble(Boolean fueDoble) {
		this.fueDoble = fueDoble;
	}

	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	public Iterator<Jugador> getTurno() {
		return turno;
	}

	public void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}

	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public void setCountDoble(Integer countDoble) {
		this.countDoble = countDoble;
	}

	public void setJugada(Jugada jugada) {
		this.jugada = jugada;
	}
	
	
}
