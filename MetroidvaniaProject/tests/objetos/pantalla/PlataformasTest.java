package objetos.pantalla;

import static org.junit.Assert.*;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.junit.Test;

import ventanas.VentanaJuego;

public class PlataformasTest {
	
	private static Plataformas p = new Plataformas(0, 0);
	
	@Test
	public void testGetPosX() {
		assertEquals(0, p.getPosX());
	}

	@Test
	public void testGetPosY() {
		assertEquals(0, p.getPosY());
	}

}
