package GUI;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import Controller.Listeners;
import Controller.Monopoly;
import Modelo.Jugador;

/**
 * Clase que maneja el panel derecho de la pantalla donde se encuentran todos
 * los controles del sistema y la comunicacion con el usuario.
 * 
 * @author MartinGrabina
 *
 */



@SuppressWarnings("unused")
public class ControlView extends JPanel {
	private Integer ancho;
	private Integer alto;
	private static ArrayList<String> mensajes;
	private static int cantMensajes = 0 ;
	private ArrayList<Jugador> jugadores;
	private static DefaultListModel<String>	modeloLista		= new DefaultListModel<String>();
	private static JList<String>			listaDeMensajes	= new JList<>(modeloLista);
	private static JTable					dataPartida;
	private static JButton venderCasas;
	private static JButton comprarButton;
	private static JButton guardarButton;
	private static JButton hipotecarButton;
	private static JButton deshipotecarButton;
	private static JButton dados;
	private static JButton transferencias;
	private static JComboBox<String> propiedades;
	private static Object datos[][];
	private static Monopoly partida;
	
	public ControlView(Integer ancho, Integer alto, Integer x, Integer y, ArrayList<Jugador> jugadores, Monopoly partida){
		this.setLayout(null);
		this.jugadores = jugadores;
		this.ancho = ancho;
		this.alto = alto;
		this.setBounds(x, y, ancho, alto);
		this.setBackground(new Color(176,196,222));
		this.partida = partida;
		// Titulo Principal
		JLabel titulo = new JLabel("Monopooly");
		titulo.setFont(new Font("Impact", Font.CENTER_BASELINE, 90));
		titulo.setBounds(this.getBounds().x, 10, ancho, titulo.getMinimumSize().height);
		titulo.setBackground(new Color(210,105,30));
		titulo.setAlignmentX(CENTER_ALIGNMENT);
		titulo.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED), BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)));	
		// Botones
		venderCasas = new JButton();
		venderCasas.setBounds(this.getBounds().x , 7 * alto / 8, ancho / 5, 100);
		venderCasas.setText("Vender Casas");
		venderCasas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Listeners.venderCasas();
				actualizarTabla();
			}
		});
		
		 comprarButton = new JButton();
		comprarButton.setBounds(this.getBounds().x + ancho/5, 7 * alto / 8, ancho / 5, 100);
		comprarButton.setText("Comprar Casas");
		comprarButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Listeners.comprarCasa();
				actualizarTabla();
			}
		});
		 guardarButton = new JButton();
		guardarButton.setBounds(this.getBounds().x + 2*ancho/5, 7 * alto / 8, ancho / 5, 100);
		guardarButton.setText("Guardar");
		guardarButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Listeners.guardarPartida();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		 hipotecarButton = new JButton();
		hipotecarButton.setBounds(this.getBounds().x + 3*ancho/5, 7 * alto / 8, ancho / 5, 100);
		hipotecarButton.setText("Hipotecar");
		hipotecarButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Listeners.hipotecar();
				actualizarTabla();
			}
		});
		deshipotecarButton = new JButton();
		deshipotecarButton.setBounds(this.getBounds().x + 4*ancho/5, 7 * alto / 8, ancho / 5, 100);
		deshipotecarButton.setText("Deshipotecar");
		deshipotecarButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Listeners.deshipotecar();
				actualizarTabla();
			}
		});
		
		
		
		
		//Boton de transferencias
		 	transferencias = new JButton("Transferencias");
			transferencias.setBounds(this.getBounds().x, comprarButton.getBounds().y - this.getBounds().height/20, this.getBounds().width, this.getBounds().height/20);
			transferencias.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Listeners.transferencias();
					actualizarTabla();
				}
			});
			
			//Boton tirar dadosDados
			 dados = new JButton("Terminar Turno");
			dados.setBounds(this.getBounds().x, transferencias.getBounds().y - this.getBounds().height/16, this.getBounds().width, this.getBounds().height/16);
			dados.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Listeners.terminoTurno();
					actualizarTabla();
				}
			});
		
		// Terminal de dialogo

		JScrollPane terminal = new JScrollPane();
		terminal.setBounds(this.getBounds().x, dados.getBounds().y - alto/2, ancho, alto / 2);
		terminal.setForeground(Color.pink);
		modeloLista.add(cantMensajes++, "Terminal de mensajes");
		listaDeMensajes.setBounds(terminal.getBounds().x, terminal.getBounds().y, terminal.getMaximumSize().width, terminal.getMaximumSize().height);
		listaDeMensajes.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		listaDeMensajes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		terminal.setViewportView(listaDeMensajes);
		listaDeMensajes.setAlignmentX(LEFT_ALIGNMENT);
		

		// Tabla Jugadores
		
		modeloTabla.setJugadores(jugadores,partida.getCantJugadores());
		dataPartida = new JTable(new modeloTabla());

		actualizarTabla();
		dataPartida.setBounds(this.getBounds().x, terminal.getBounds().y - dataPartida.getRowHeight() * 7, ancho + 1, dataPartida.getRowHeight() * 7);
		TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
				"");
		title.setTitlePosition(TitledBorder.ABOVE_TOP);
		dataPartida.setBorder(title);
		
		
		
		
		dataPartida.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		dataPartida.setCellSelectionEnabled(true);
		
		
		
		

		

		// Se agregan los elementos creados
		this.add(dataPartida);
		this.add(terminal);
		this.add(titulo);
		this.add(dados);
		this.add(transferencias);
		this.add(venderCasas);
		this.add(comprarButton);
		this.add(guardarButton);
		this.add(hipotecarButton);
		this.add(deshipotecarButton);
		
	}

	public static void agregarMensaje(String mensaje) {
		modeloLista.setSize(cantMensajes + 1);
		modeloLista.add(cantMensajes, cantMensajes++ + "~ " + mensaje);
		listaDeMensajes.clearSelection();
		listaDeMensajes.addSelectionInterval(cantMensajes, cantMensajes);
		listaDeMensajes.setModel(modeloLista);
		listaDeMensajes.ensureIndexIsVisible(listaDeMensajes.getModel().getSize() - 1);
		listaDeMensajes.updateUI();
	}

	public void actualizarTabla() {
		((AbstractTableModel) dataPartida.getModel()).fireTableStructureChanged();
	}
	
	public static void seleccionarJugador(int i) {
		ListSelectionModel selectionModel = dataPartida.getSelectionModel();
		selectionModel.setSelectionInterval(i - 1, i - 1);
		dataPartida.setSelectionModel(selectionModel);
	}
}
