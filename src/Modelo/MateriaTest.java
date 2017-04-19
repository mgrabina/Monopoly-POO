package Modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.awt.Color;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MateriaTest {

	private Jugada	jugada;
	private Materia	m1;
	private Materia	m2;
	private Jugador	jugador;

	@Before

	public void setUp() {
		jugada = new Jugada();
		jugador = new Jugador("Juan");
		m1 = new Materia("nombre1", 5, 10, new Departamento("nombre1", 5, Color.BLACK));
		m2 = new Materia("nombre2", 5, 10, new Departamento("nombre1", 5, Color.BLACK));
	}

	@After

	@Test
	public void testHipotecar1() {
		try {
			m1.hipotecar();
			fail();
		} catch (NoSePuedeHipotecarException e) {
			assertEquals(e.getMessage(), "La propiedad debe tener duenio para ser hipotecada");
		}
	}

	@Test
	public void testHipotecar2() {
		m2.comprar(jugador);
		m2.hipotecar();
		assertEquals(true, m2.esHipotecada());
	}

	@Test
	public void testAccion() {
		assertEquals(true, m1.accion(jugada));
	}

	@Test
	public void testVenderCasa1() {
		m2.comprar(jugador);
		m2.comprarCasa();
		assertEquals(true, m2.venderCasa());
	}

	@Test
	public void testVenderCasa2() {
		m2.comprar(jugador);
		assertEquals(false, m2.venderCasa());
	}

	@Test
	public void testComprarCasa1() {
		m2.comprar(jugador);
		m2.comprarCasa();
		assertEquals((Integer) 1, m2.getCasas());
	}

	@Test
	public void testComprarCasa2() {
		m2.comprar(jugador);
		assertEquals((Integer) 0, m2.getCasas());
	}

}
