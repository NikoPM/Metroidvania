package datos;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

public class BaseDeDatosTest {

	private static Usuario usuario = new Usuario(1, "Andoni", 200);
	private static BaseDeDatos baseDeDatos = new BaseDeDatos();
	private static ArrayList<Usuario> ret = new ArrayList<>();
	
	@Test
	public void testabrirConexion() {
		assertEquals(true, baseDeDatos.abrirConexion(null, true));
	}
	
	@Test
	public void testinsertarUsuario() {
		assertEquals(true, baseDeDatos.insertarUsuario(usuario));
		assertEquals(false, baseDeDatos.insertarUsuario(usuario));
	}
	
	@Test
	public void testgetUsuarios() {
		assertEquals(ret, baseDeDatos.getUsuarios()); 
	}
}