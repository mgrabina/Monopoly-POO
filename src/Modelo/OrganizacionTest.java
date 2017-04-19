package Modelo;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OrganizacionTest {

	private Jugador	j;
	private Organizacion o;
	private Jugada jugada;
	@Before
	public void setUp(){
		j= new Jugador("juan");
		o=new Organizacion("o");
		jugada= new Jugada();
	}
	@After
	
	@Test
	
	public void accionTest(){
		o.accion(jugada);
	}

}
