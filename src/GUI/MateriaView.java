package GUI;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.xml.bind.Marshaller.Listener;

import Controller.Listeners;
import Modelo.Casillero;
import Modelo.Materia;
import Modelo.Organizacion;
import Modelo.Servicios;
import Modelo.Suerte;

/**
 * Se implementa el diseño de los casilleros internos segun su tipo.
 * 
 * @author MartinGrabina
 *
 */
@SuppressWarnings("unused")
public class MateriaView extends JPanel {
	private final Point posicion;
	private final String sector;
	private final Integer ancho;
	private final Integer alto;
	Integer id;	//No es privada ya que tiene que poder ser vista por el comparator ComparadorDeCasilleros para el correcto funcionamiento del Set en el tablero.
	private final Casillero				casillero;
	
	
	public MateriaView(Point posicion, String sector, Integer ancho, Integer alto, Integer id, Casillero casillero) {
		super();
		this.posicion = posicion;
		this.sector = sector;
		this.ancho = ancho;
		this.alto = alto;
		this.id = id;
		this.casillero = casillero;
		this.setBounds(posicion.x, posicion.y, ancho, alto);
		this.setBackground(Color.white);
		this.setForeground(Color.BLACK);
		//this.add(new ImagePanel(new ImageIcon(MonopolyView.path + "img/casillero"+sector+".png").getImage()));
		diseniarCasillero();
	}
	
	private void diseniarCasillero(){
		JLabel numero = new JLabel(casillero.getPosicion().toString() + "| ");
		
		numero.setForeground(Color.BLACK);
		numero.setFont(new Font("Times New Roman",Font.BOLD, 15));
		numero.setAlignmentY(CENTER_ALIGNMENT);
		
		JLabel titulo = new JLabel(casillero.getNombre());
		
		titulo.setForeground(Color.DARK_GRAY);
		titulo.setFont(new Font("Times New Roman",Font.ITALIC, 15));
		titulo.setAlignmentY(CENTER_ALIGNMENT);
		
		
		if (id % 2 == 0) this.setBackground(new Color(220,220,220));

		this.setLayout(new BorderLayout());

		// Si es Materia
		if (casillero.getClass() == Materia.class) {
			JPanel colorDepto = new JPanel();
			switch (sector) {
				case "L":
					this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 30, ((Materia) casillero).getDepartamento().getColor()));
					break;
				case "T":
					this.setBorder(BorderFactory.createMatteBorder(1, 1, 30, 1, ((Materia) casillero).getDepartamento().getColor()));
					break;
				case "R":
					this.setBorder(BorderFactory.createMatteBorder(1, 30, 1, 1, ((Materia) casillero).getDepartamento().getColor()));
					break;
				case "D":
					this.setBorder(BorderFactory.createMatteBorder(30, 1, 1, 1, ((Materia) casillero).getDepartamento().getColor()));
					break;
				default:
			}
		}else{
			this.setBorder(BorderFactory.createLineBorder(Color.black));
		}

		// Si es Suerte
		if (casillero.getClass() == Suerte.class) {
			this.setForeground(Color.yellow);

		}
		// Si es organizacion
		if (casillero.getClass().equals(Organizacion.class)) {
			this.setForeground(Color.pink);
		}
		// Si es servicio
		if (casillero.getClass().equals(Servicios.class)) {
			this.setForeground(Color.orange);
		}
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.add(titulo,0);
		this.add(numero,0);
		this.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					Listeners.ejecutarSobreMateria(casillero);	
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					ControlView.agregarMensaje("Haga click en el casillero para ver informacion del mismo.");
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
									
				}
			});
	}
	

	
	
}
