package GUI;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.Listeners;
import Controller.Monopoly;
import Modelo.Casillero;
import Modelo.Departamento;
import Modelo.Jugador;

/**
 * Pantalla inicial del juego, muestra las opciones de abrir nuevo o cerrar,
 * reacciona a la correspondiente, crea la pantalla principal del juego donde se
 * encuentra el tablero y la zona de control del mismo. Todos los valores de
 * tamaños del juego se manejan bajo el concepto responsive, dependiente del
 * tamaño de la pantalla en que se lo corra, donde el tablero es siempre
 * cuadrado al tamaño del alto de la pantalla, y el control lo que sobra.
 * 
 * @author MartinGrabina
 *
 */



public abstract class MonopolyView {
	public static final Integer ANCHO = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	public static final Integer ALTO = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	public static final String path = "/Users/MartinGrabina/EclipseWorkspace/POO-ITBA/src/monopoly/";
	private static JPanel PanelTablero;
	private static JPanel PanelControl;
	
//	public static void main(String[] args) throws IOException {
//		iniciar();
//	}
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void iniciar(Monopoly partida){
		
		JFrame inicio = new JFrame("Monopooly");
		inicio.setSize(300, 300);
		inicio.setLocationRelativeTo(null);
		inicio.setLayout(new BorderLayout());
		inicio.setResizable(false);
		inicio.setForeground(new Color(144, 238, 144));
		
		JButton nuevo = new JButton();
		nuevo.setText("Nueva Partida");
		nuevo.setSize(300, 100);
		nuevo.setForeground(new Color(127,255,212));
		nuevo.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 30));
		nuevo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Listeners.nuevoJuego();
				iniciarPartida(partida.getCasilleros(), partida.getJugadores(), partida.getDeptos(), partida);
				inicio.setVisible(false);
				Listeners.terminoTurno();
				

			}
		});
		
		
		JButton abrir = new JButton();
		abrir.setText("Abrir Partida");
		abrir.setSize(300, 100);
		abrir.setForeground(new Color(175,238,238));
		abrir.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 30));
		abrir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Listeners.abrir();
					
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				iniciarPartida(partida.getCasilleros(), partida.getJugadores(), partida.getDeptos(), partida);
				inicio.setVisible(false);

			}
		});
		
		JButton cerrar = new JButton();
		cerrar.setText("Cerrar");
		cerrar.setSize(300, 100);
		cerrar.setForeground(new Color(176,224,230));
		cerrar.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 30));
		cerrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		
		inicio.add(nuevo, BorderLayout.CENTER);
		inicio.add(abrir, BorderLayout.NORTH);
		inicio.add(cerrar, BorderLayout.SOUTH);
		inicio.setVisible(true);
	}
	public static void iniciarPartida(List<Casillero> casilleros, List<Jugador> jugadores, List<Departamento> departamentos, Monopoly partida){
		JFrame mainFrame = new JFrame("Monopooly");
		mainFrame.setBounds(0, 0, ANCHO, ALTO);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//mainFrame.setIconImage(ImageIO.read(new URL(getCodeBase(), "img/icono.png")));
		PanelTablero = new TableroView(ALTO, 0, 0, (ArrayList<Casillero>) casilleros, (ArrayList<Departamento>) departamentos); 
		PanelControl = new ControlView(ANCHO - ALTO, ALTO, ALTO, 0, (ArrayList<Jugador>) jugadores, partida); 
			
		mainFrame.getContentPane().add(PanelTablero);
		mainFrame.getContentPane().add(PanelControl);
		mainFrame.setVisible(true);
	}
	public static ControlView getControlView(){
		return (ControlView) PanelControl;
	}
	public static ControlView getTableroView(){
		return (ControlView) PanelTablero;
	}
}


//JOptionPane.showMessageDialog(null, texto, titulo, JOptionPane.WARNING_MESSAGE);	//Cartel de Alerta
//JOptionPane.showMessageDialog(null, texto, titulo, JOptionPane.ERROR_MESSAGE);	//Cartel de Error
//int respuesta = JOptionPane.showOptionDialog(null, pregunta, titulo, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, "Si", "No"); //SiNoCartel
////The values for this integer are YES_OPTION, NO_OPTION, CANCEL_OPTION, OK_OPTION, and CLOSED_OPTION. Except for CLOSED_OPTION, each option corresponds to the button the user pressed. When CLOSED_OPTION is returned, it indicates that the user closed the dialog window explicitly, rather than by choosing a button inside the option pane.
//String s = (String)JOptionPane.showInputDialog( null,mensaje,titulo,JOptionPane.PLAIN_MESSAGE, null,arrayDePosibilidades, valorDefault); //Cartel para elegir una opcion, devuelve el string de la opcion que eligio.
//retorno = JOptionPane.showInputDialog(mensaje); //Cartel para que el usuario 