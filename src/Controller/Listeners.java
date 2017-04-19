package Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;

import javax.swing.JOptionPane;

import GUI.ControlView;
import Modelo.Casillero;
import Modelo.Jugador;
import Modelo.Materia;
import Modelo.NoSePudoComprarException;
import Modelo.Organizacion;
import Modelo.Propiedad;
import Modelo.Servicios;
/**
 * Contiene a los botones y la interaccion de estos con el modelo
 * @author Juan Grethe
 */
public abstract class Listeners {  
	private static Monopoly partida;
	public static void iPartida(Monopoly p){
		partida = p;
	}
	
	/**
	 * Corresponden al bot�n correspondiente y realiza esta acci�n
	 */
	public static void comprarCasa(){ 
		Propiedad elegida = listaPropiedad();
		if(elegida != null)
			if(elegida instanceof Materia){
				partida.comprarCasa((Materia)elegida);
			}
			else
				ControlView.agregarMensaje("Solo se pueden poner casas en Materias");		
	}
	
	public static void venderCasas(){
		Propiedad elegida = listaPropiedad();
		if(elegida != null){
			if(elegida instanceof Materia){
				if ( ((Materia)elegida).venderCasa() ) 
					ControlView.agregarMensaje(partida.getJugada().getJugador().getNombre() + " ha vendido una casa en " + elegida.getNombre() + ".");
				else
					ControlView.agregarMensaje("No tiene casas en esa materia");
			}else	
					ControlView.agregarMensaje("Esa propiedad no puede vender casas");
			
		}
	}
	
	public static void hipotecar(){
		Propiedad elegida;
		elegida = listaPropiedad();
		if(elegida!= null){
			partida.hipotecar(elegida);
		}
	}
	
	public static void deshipotecar(){ 
		Propiedad elegida;
		elegida = listaPropiedad();
		if(elegida!= null){
			partida.deshipotecar(elegida);
		}
	}
		
	public static void guardarPartida() throws IOException{
		 FileOutputStream archivo = null;
	     ObjectOutputStream salida = null;
		try{	
	     	archivo = new FileOutputStream("partida.dat");
			salida= new ObjectOutputStream(archivo);
			salida.writeObject(partida);
			ControlView.agregarMensaje("Se guardo la partida.");
		}catch (IOException e) {
            System.out.println("2"+e.getMessage());
		}
	}

	/**
	 * Lee un input y lo pasa a Integer
	 * 
	 * @return devuelve un input
	 */
	private static Integer getnum(){
		try{
			String eleccion = (String) JOptionPane.showInputDialog(null,"Ponga un precio","",JOptionPane.PLAIN_MESSAGE,null,null,null);
			Integer elegido = Integer.valueOf(eleccion);
			return elegido;
		} catch (NumberFormatException exception) {
			ControlView.agregarMensaje("Ingrese un numero por favor");
		}
		return null;
	}
	/**
	 * Imprime una lista de las propiedades adquiridas por el jugador
	 * @return la propiedad elegida para la acci�n correspondiente
	 */
	private static Propiedad listaPropiedad() {
		
		try{
			Propiedad[] posibilidades = partida.getJugada().getJugador().getPropiedades().toArray(new Propiedad[partida.getJugada().getJugador().getPropiedades().size()] );
			String[] posi = new String[posibilidades.length];	
			for(int x=0; x < posibilidades.length; x++)
					posi[x]=posibilidades[x].getNombre();
			String eleccion= (String) JOptionPane.showInputDialog(null,"Elija una propiedad","",JOptionPane.PLAIN_MESSAGE,null,posi,null);
			Propiedad elegida = null;
			for(Propiedad p: posibilidades)
					if(p.getNombre() == eleccion)
						elegida = p;
			return elegida;
		}catch(NullPointerException excepcion){
			ControlView.agregarMensaje("Primero empiece el juego.");
		}
		return null;
		
	}

	/**
	 * Imprime una lista de los jugadores en el juego
	 * @return el jugador elegido
	 */
	private static Jugador listaJugador() {
		try{
			Jugador[] posibilidades = partida.getJugadores().toArray(new Jugador[partida.getJugadores().size()]);
			String[] posi = new String[posibilidades.length];
			for(int x=0; x < posibilidades.length; x++)
				posi[x]=posibilidades[x].getNombre();
			String eleccion= (String) JOptionPane.showInputDialog(null,"Elija un jugador","",JOptionPane.PLAIN_MESSAGE,null,posi,null);
			Jugador elegido = null;
			for (Jugador j : posibilidades)
				if (j.getNombre().equals(eleccion)) elegido = j;
			return elegido;
		} catch (NullPointerException excepcion) {
			ControlView.agregarMensaje("Primero empiece el juego.");
		}
		return null;
	}

	/**
	 * Bot�n para cuando el jugador decide terminar con su turno y se contin�a con el siguiente
	 */
	public static void terminoTurno() {
		if(partida.getIsPlaying())
			partida.jugar();
	}

	/**
	 * Bot�n si se quiere abrir una nueva partida
	 */
	public static void nuevoJuego() {
		partida.getJugadores().clear();
		Integer a=0;
		do{
			try{a = new Integer((String) (JOptionPane.showInputDialog(null, "Ingrese cant de jugadores ",
				"Monopooly", JOptionPane.PLAIN_MESSAGE, null, null, "0")));
				}catch(NumberFormatException exception){
					a=0;
				}
		}while(a==0 || a ==1 || a>5);
		String name;
		partida.setCantJugadores(a);
		for (int i = 0; i < a; i++) {
			do{
				name= (String) JOptionPane.showInputDialog(null, "Ingrese un nombre para el jugador " + i,"Monopooly", JOptionPane.PLAIN_MESSAGE, null, null, "Jugador " + i);
				if(name==null)
					name="";
			}while(name.isEmpty());
				partida.getJugadores().add(new Jugador(name));
		}
		Collections.shuffle(partida.getJugadores());
		partida.setTurno(partida.getJugadores().listIterator()); // Turno apunta al jugador que juega primero
		
	}

	/**
	 * Bot�n si se quiere abrir una partida anterior
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void abrir() throws IOException, ClassNotFoundException {
		 FileInputStream archivo = null;
	     ObjectInputStream entrada = null;
	     partida.getJugadores().clear();
	     try{
	    	 archivo=new FileInputStream("partida.dat");
	    	 entrada = new ObjectInputStream(archivo);
	    	 Monopoly p2=(Monopoly)entrada.readObject();
	    	 partida.setPlaying(p2.getIsPlaying());
	    	 partida.setJugadores(p2.getJugadores());
	    	 partida.setCasilleros(p2.getCasilleros());
	    	 partida.setJugada(p2.getJugada());
	    	 partida.setCantJugadores(p2.getCantJugadores());
	    	 partida.setTurno(p2.getJugadores().listIterator());
	     }catch (FileNotFoundException e){
	    	 JOptionPane.showMessageDialog(null, "No existe partida guardad. Empiece una nueva.");
	    	 nuevoJuego();
	     }
	}
	
	public static void ejecutarSobreMateria(Casillero c){
		String message = "Tipo: " + c.getClass() + "\nNombre:"+c.getNombre() +"\nPosicion:"+c.getPosicion()+"\n";
		if(c.getClass().equals(Materia.class))
			if(((Materia)c).isOwned())
				message += "Tiene due�o: "+((Materia)c).getDuenio().getNombre()+ "\nCasas: "+ ((Materia)c).getCasas() ;
			else message += "No tiene due�o. Se puede Comprar.";
		else if (c.getClass().equals(Organizacion.class) || c.getClass().equals(Servicios.class))
			if (((Propiedad) c).isOwned()) message += "Tiene due�o: " + ((Propiedad) c).getDuenio().getNombre();
			else message += "No tiene due�o. Es comprable.";
		else message += "No es comprable.";
		JOptionPane.showMessageDialog(null,message, "Informaci�n de Casillero", JOptionPane.INFORMATION_MESSAGE );
	}

	public static void transferencias() {
		Propiedad elegida = listaPropiedad();
		if(elegida!=null){
			Integer monto = getnum();
			if(monto!=null){
				Jugador comprador = listaJugador();
				if (comprador!=null && comprador!=elegida.getDuenio())
					try{
						Propiedad.transaccion(elegida, comprador, monto);
						ControlView.agregarMensaje("Se trasnfirio a " + comprador.getNombre()+ " por "+ monto);
					}catch(NoSePudoComprarException e){
						ControlView.agregarMensaje("Monto incorrecto");
					}
				
				}
			}
		}
}

