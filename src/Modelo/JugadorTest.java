package Modelo;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JugadorTest {

	private Jugador		j;
	private Propiedad	o;
	private Propiedad	m;
	private Servicios	s;

	@Before
	public void setUp() {
		j = new Jugador("Juan");
		o = new Organizacion("CEITBA");
		m = new Materia("nombre1", 5, 10, new Departamento("nombre1", 5, Color.BLACK));
		s = new Servicios("hola", 10);
	}

	@After

	@Test
	public void testAddPropiedad1() {
		j.addPropiedad(o);
		assertEquals(true, j.getPropiedades().contains(o));
	}

	@Test
	public void testAddPropiedad2() {
		j.addPropiedad(m);
		assertEquals(true, j.getPropiedades().contains(m));
	}


	@Test
	public void testAvanzarInt1() {
		j.avanzar(1);
		assertEquals((Integer) 1, j.getPosicion());
	}

	@Test
	public void testAvanzarInt2() {
		j.avanzar(0);
		assertEquals((Integer) 0, j.getPosicion());
	}

	@Test
	public void testAvanzarCasillero() {
		j.avanzar(o);
		assertEquals(o.getPosicion(), j.getPosicion());
	}

	@Test
	public void testCantOrganizacion1() {
		j.addPropiedad(o);
		assertEquals((Integer) 1, j.cantOrganizacion());
	}

	@Test
	public void testCantOrganizacion2() {
		assertEquals((Integer) 0, j.cantOrganizacion());
	}

	@Test
	public void testCantServicios1() {
		j.addPropiedad(s);
		assertEquals((Integer) 1, j.cantServicios());
	}

	@Test
	public void testCantServicios2() {
		assertEquals((Integer) 0, j.cantServicios());
	}

}
