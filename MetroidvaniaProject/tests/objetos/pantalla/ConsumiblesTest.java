package objetos.pantalla;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import org.junit.Test;

public class ConsumiblesTest {

private List<Consumibles> cList;
	 
	@Test
	public void testGetListaCons() {
		cList = new ArrayList<Consumibles>();
		assertEquals(cList, Consumibles.getListaCons());
	}
	
	@Test
	public void testGetVictory() {
		Consumibles.generar(0, 0, new JFrame(), false);
		assertFalse(Consumibles.getListaCons().get(0).getVictory());
	}
}
