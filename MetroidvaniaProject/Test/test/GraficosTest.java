package test;

import static org.junit.Assert.*;

import org.junit.Test;

import objetos.pantalla.Graficos;

public class GraficosTest {
	private static Graficos graficos = new Graficos(12, 22, " ");
	
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

}
