package objetos.pantalla;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import org.junit.Test;

public class PlataformasTest {
	
	@Test
	public void testGetListaPlat() {
		assertEquals(new ArrayList<Plataformas>(), Plataformas.getListaPlat());
	}
	
	@Test
	public void testGetPosMax() {
		assertEquals(1000, Plataformas.getPosMax());
	}
}
