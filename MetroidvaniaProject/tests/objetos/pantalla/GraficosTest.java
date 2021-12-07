package objetos.pantalla;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
 
import objetos.pantalla.Graficos;
//TODO EDITAR LOS TESTS RELATIVOS A 3 NUEVOS ATRIBUTOS CAMBIOS 
public class GraficosTest {
	
	private Graficos graficos;
	
	@Before
	public void setUp() {
		graficos = new Graficos(12, 22, " ", 1 , 1, 1);
	}
	 
	@Test
	public void testGetPosX() {
		assertEquals(12, graficos.getPosX());
	}

	@Test
	public void testGetPosY() {
		assertEquals(22, graficos.getPosY());
	}

	@Test
	public void testGetDirImg() {
		graficos.setDirImg("DireccionDePrueba");
		assertEquals("DireccionDePrueba", graficos.getDirImg());
	}

	@Test
	public void testSetPosX() {
		graficos.setPosX(2);
		assertEquals(2, graficos.getPosX());
	}

	@Test
	public void testSetPosY() {
		graficos.setPosY(33);
		assertEquals(33, graficos.getPosY());
	}

	@Test
	public void testSetDirImg() {
		graficos.setDirImg("Cambio");
		assertEquals("Cambio", graficos.getDirImg());
	}

	@Test
	public void testGetVelX() {
		assertEquals(1, graficos.getVelX());
	}
	
	@Test
	public void testGetVelY() {
		assertEquals(1, graficos.getVelY());
	}
	
	@Test
	public void testGetHitbox() {
		assertEquals(1, graficos.getHitbox());
	}
}
