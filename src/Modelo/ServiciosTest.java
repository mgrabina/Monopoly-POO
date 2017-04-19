package Modelo;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ServiciosTest {

	
	Jugador j;
	Jugador j2;
	Servicios s;
	@Before
	public void setUp()  {
		j=new Jugador("s");
		j2=new Jugador("saa");
		s=new Servicios("a", 10);
		s.setDuenio(j);
		j.addPropiedad(s);
		j2.setCreditos(-1493);
		
				
	}

	@After
	
	@Test
	public void cobrarTest1(){
		s.cobrar(j2, 1);
		assertEquals((Integer) 0, j2.getCreditos());
		
	}

}
