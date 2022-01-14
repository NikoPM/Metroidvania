package objetos.pantalla;

import static org.junit.Assert.*;
import javax.swing.JFrame;
import org.junit.Before;
import org.junit.Test;

public class PersonajeTest {
	
	@Before
	public void setUp() {
		Personaje.generar(0, 0, new JFrame());
	}
	
	@Test
	public void testVida() {
		assertEquals(100, Personaje.getVida());
	}
	
	@Test
	public void testDecVida() {
		assertEquals(99, Personaje.decVida(1));
		assertEquals(0, Personaje.decVida(101));
	}
	
	@Test
	public void testIncVida() {
		assertEquals(1, Personaje.incVida(1));
		assertEquals(100, Personaje.incVida(101));
	}
}
