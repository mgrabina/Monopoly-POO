package Modelo;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ImpuestosTest {

	private Jugada jugada;
	private Impuestos	impuesto;

	@Before

	public void setUp() {
		jugada = new Jugada(new Dados(), new Jugador("Juan"));
		impuesto = new Impuestos("CEITBA");
	}

	@After
	
	@Test
	public void testAccion1() {
		assertEquals(true, impuesto.accion(jugada));
	}

	@Test
	public void testAccion2() {
		Jugador jugador1 = new Jugador("Manuel");
		jugador1.setCreditos(-2000);
		Jugada jugada1 = new Jugada(new Dados(), jugador1);
		assertEquals(false, impuesto.accion(jugada1));
	}

}
