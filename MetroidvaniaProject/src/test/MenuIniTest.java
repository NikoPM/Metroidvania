package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import ventanas.VentanaMenuInicio;

public class MenuIniTest {
	
	@Test
	public void probar() {
		VentanaMenuInicio v = new VentanaMenuInicio();
		assertNotNull(v);
		//assertNotNull(v.getbExit().getActionListeners());
	}

}
