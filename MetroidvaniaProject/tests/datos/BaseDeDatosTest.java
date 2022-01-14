package datos;

import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BaseDeDatosTest {

	@Before
	public void setUp() throws Exception {
		BaseDeDatos.abrirConexion( "test.bd", true );
	}

	@After
	public void tearDown() throws Exception {
		BaseDeDatos.cerrarConexion();
	}

	
	private static Usuario usuario = new Usuario(1, "Nico", 7992);
	private static ArrayList<Usuario> ret = new ArrayList<>();
	
	@Test
	public void testabrirConexion() {
		BaseDeDatos.cerrarConexion();
		assertEquals(true, BaseDeDatos.abrirConexion( "test.bd", true ));
	}
	
	@Test
	public void testinsertarUsuario() {
		assertEquals(true, BaseDeDatos.insertarUsuario(usuario));
	}
	
	
	@Test
	public void testgetUsuarios() {
		ret.add(usuario);
		assertEquals(ret, BaseDeDatos.getUsuarios()); 
	}
}