package Modelo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class SuerteTest {
	Jugador		j;
	Ejecutable	exe;
	@Before
	public void setUp() {
		j = new Jugador("Pepe");
		exe = new Ejecutable() {
			@Override
			public boolean ejecutar(Jugador jugador) {
				jugador.setCreditos(100);
				return true;
			}
		};
	}
	@Test
	public void testAddCarta() {
		int count = Suerte.getCartas().size();
		Suerte.addCarta(exe);
		assertEquals(true, Suerte.getCartas().size() == count + 1);
	}


	@Test
	public void testAccion() {
		int inicial = j.getCreditos();
		exe.ejecutar(j);
		assertEquals(true, j.getCreditos() == inicial + 100);
	}

}
