package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import ventanas.VentanaMenuInicio;
import ventanas.VentanaMenuInicio.FondoPanel;

public class MenuIniTest {
	private VentanaMenuInicio v;
	
	//Inicialización de las clases que vamos a testear
	@Before
	public void setUp() {
		v = new VentanaMenuInicio();
	}
	
	@Test
	public void probar() {
		assertNotNull(v);	
	}

}
