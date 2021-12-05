package ventanas;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import ventanas.VentanaJuego;

public class VentanaJuegoTest {
	private VentanaJuego v;
	 
	@Before
	public void setUp() {
		v = new VentanaJuego();
	}
	
	@Test
	public void probar() {
		assertNotNull(v);
	}
}
