package datos;

import static org.junit.Assert.*;
import org.junit.Test;

public class UsuarioTest {

	private static Usuario usuario = new Usuario(1, "Andoni", 200);
	
	@Test
	public void testGetIdUsuario() {
		assertEquals(1, usuario.getIdUsuario());
	}
	@Test
	public void testGetNombre() {
		assertEquals("Andoni", usuario.getNombre());
	}
	@Test
	public void testGetTiempo() {
		assertEquals(200, usuario.getTiempo(), 1);
	}
}
