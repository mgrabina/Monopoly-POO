package GUI;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Modelo.Casillero;
import Modelo.Departamento;

/**
 * Majena toda la interfaz del tablero poniendo automaticamente todos los
 * casilleros por tipo.
 * 
 * @author MartinGrabina
 *
 */
@SuppressWarnings("unused")
public class TableroView extends JPanel {
	private Integer tamanio;
	private Integer anchoCasillero;
	private Integer altoCasillero;
	private Point posicion;
	ArrayList<Casillero> casilleros;
	public TableroView(Integer tamanio, Integer x, Integer y, ArrayList<Casillero> casilleros, ArrayList<Departamento> departamentos) {
		super();
		this.tamanio = tamanio;
		this.posicion = new Point(x,y);
		anchoCasillero = 8*tamanio/100;
		altoCasillero = 14*tamanio/100;
		this.setBounds(x, y, tamanio, tamanio);
		this.setLayout(null);
		this.casilleros = casilleros;
		crearTablero();
	}
	private void crearTablero(){
		JPanel Salida = new JPanel();
		JPanel VisitaAsistida = new JPanel();
		JPanel Feriado = new JPanel();
		JPanel Asistida = new JPanel();
		
		
		Salida.setSize(100, 100);
		Salida.setBounds(0, tamanio-altoCasillero, altoCasillero, altoCasillero);
		VisitaAsistida.setBounds(0, 0, altoCasillero, altoCasillero);
		Feriado.setBounds(tamanio-altoCasillero, 0, altoCasillero, altoCasillero);
		Asistida.setBounds(tamanio-altoCasillero, tamanio-altoCasillero, altoCasillero, altoCasillero);

		JPanel CentroDeTablero = new JPanel();
		CentroDeTablero.setBounds(altoCasillero, altoCasillero, tamanio - 2*altoCasillero, tamanio - 2*altoCasillero);
		
		
		TreeSet<MateriaView> Casilleros = new TreeSet<MateriaView>(new ComparadorDeCasilleros<MateriaView>());
		int c=0;
		Salida.add(new JLabel(casilleros.get(c++).getNombre()));
		//Sector L
		for (int i = 0; i < 9; i++, c++) {
			Casilleros.add(new MateriaView(new Point(0, tamanio - (altoCasillero + anchoCasillero * (i + 1))), "L",
					altoCasillero, anchoCasillero, c, casilleros.get(c)));
		}
		VisitaAsistida.add(new JLabel(casilleros.get(c++).getNombre()));
		//Sector T
		for (int i = 0; i < 9; i++, c++) {
			Casilleros.add(new MateriaView(new Point(altoCasillero + anchoCasillero * i, 0), "T", anchoCasillero,
					altoCasillero, c, casilleros.get(c)));
		}
		Feriado.add(new JLabel(casilleros.get(c++).getNombre()));
		//Sector R
		for (int i = 0; i < 9; i++, c++) {
			Casilleros.add(new MateriaView(new Point(tamanio - altoCasillero, altoCasillero + anchoCasillero * i), "R",
					altoCasillero, anchoCasillero, c, casilleros.get(c)));
		}
		Asistida.add(new JLabel(casilleros.get(c++).getNombre()));
		//Sector D
		for (int i = 0; i < 9; i++,c++) {
			Casilleros.add(new MateriaView(
					new Point(tamanio - altoCasillero - anchoCasillero * (i + 1), tamanio - altoCasillero), "D",
					anchoCasillero, altoCasillero, c, casilleros.get(c)));
		}
		for (MateriaView i : Casilleros) {
			this.add(i);
		}
	
		
		
		
		//CentroDeTablero.add(new ImagePanel(img::centro.jpg));
//		Salida.add(new ImagePanel(new ImageIcon(MonopolyView.path + "img/salida.png").getImage()));
//		Asistida.add(new ImagePanel(new ImageIcon(MonopolyView.path + "img/asistida.jpg").getImage()));
//		VisitaAsistida.add(new ImagePanel(new ImageIcon(MonopolyView.path + "img/visita.jpg").getImage()));
//		Feriado.add(new ImagePanel(new ImageIcon(MonopolyView.path + "img/feriado.jpg").getImage()));
		Salida.setBackground(new Color(127,255,212));
		Salida.setBorder(BorderFactory.createLoweredBevelBorder());
		Asistida.setBackground(new Color(0,139,139));
		Asistida.setBorder(BorderFactory.createLoweredBevelBorder());
		VisitaAsistida.setBackground(new Color(127,255,212));
		VisitaAsistida.setBorder(BorderFactory.createLoweredBevelBorder());
		Feriado.setBackground(new Color(173,216,230));
		Feriado.setBorder(BorderFactory.createLoweredBevelBorder());
		CentroDeTablero.setBackground(new Color(245,245,220));
		//CentroDeTablero.setBorder(BorderFactory.createRaisedBevelBorder());
		this.add(CentroDeTablero);
		
		this.add(Salida);
		this.add(VisitaAsistida);
		this.add(Feriado);
		this.add(Asistida);
	}
	
	
	
}
