package Modelo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CarcelTest {

	private Jugada	jugada;
	private Carcel	carcel;
	private Jugador	jugador;

	@Before

	public void setUp() {
		jugador = new Jugador("Juan");
		jugada = new Jugada(new Dados(), jugador);
		carcel = new Carcel(new ArrayList<Jugador>(), new Neutro("nombre"));
	}

	@After

	@Test
	public void testAccion() {
		assertEquals(true, carcel.accion(jugada));
	}

	@Test
	public void testPagaCarcel() {
		carcel.accion(jugada);
		carcel.pagaCarcel(jugador);
		assertEquals(true, !carcel.getMap().containsKey(jugador));
	}
}
