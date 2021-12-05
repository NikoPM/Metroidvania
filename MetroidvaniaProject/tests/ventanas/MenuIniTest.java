package ventanas;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.swing.JButton;

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
	public void funcionBotonesTest() {
		assertNotNull(v);
		//Comprobacion del metodo que contiene los ActionListeners de los botones
		v.funcionBotones();
	}

}
