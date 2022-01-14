package objetos.pantalla;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class EnemyTest {
	
	List<Enemy> eList;
	
	@Test
	public void testGetListaCons() {
		eList = new ArrayList<>();
		assertEquals(eList, Enemy.getListaEne());
	}
}
