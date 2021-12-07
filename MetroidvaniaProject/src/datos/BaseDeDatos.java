package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;




public class BaseDeDatos {
	private static Connection conexion;
	private static Logger logger = Logger.getLogger( "BaseDatos" );
	
	public static boolean abrirConexion( String nombreBD, boolean reiniciaBD ) {
		try {
			logger.log( Level.INFO, "Carga de librería org.sqlite.JDBC" );
			Class.forName("org.sqlite.JDBC");  // Carga la clase de BD para sqlite
			logger.log( Level.INFO, "Abriendo conexión con " + nombreBD );
			conexion = DriverManager.getConnection("jdbc:sqlite:" + nombreBD );
			if (reiniciaBD) {
				Statement statement = conexion.createStatement();
				String sent = "DROP TABLE IF EXISTS usuario";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				sent = "CREATE TABLE usuario (idUsuario INTEGER PRIMARY KEY AUTOINCREMENT, nombre varchar(100), tiempo INTEGER);";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				try {
					Scanner scanner = new Scanner( BaseDeDatos.class.getResourceAsStream("productos-inic.txt") );
					while (scanner.hasNextLine()) {
						String linea = scanner.nextLine();
						String[] datos = linea.split( "\t" );
						sent = "insert into usuario (idUsuario, nombre, tiempo) values (" + datos[0] + ",'" + datos[1] + "'," + datos[2] + ");";
						logger.log( Level.INFO, "Statement: " + sent );
						statement.executeUpdate( sent );
					}
					scanner.close();
				} catch(Exception e) {
					logger.log( Level.SEVERE, "Excepción", e );
				}
			}
			return true;
		} catch(Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return false;
		}
	}
	
	/** Cierra la conexión abierta de base de datos ({@link #abrirConexion(String)})
	 */
	public static void cerrarConexion() {
		try {
			logger.log( Level.INFO, "Cerrando conexión" );
			conexion.close();
		} catch (SQLException e) {
			logger.log( Level.SEVERE, "Excepción", e );
		}
	}
	
	public static boolean insertarUsuario( Usuario usuario ) {
		try (Statement statement = conexion.createStatement()) {
			String sent = "insert into usuario (idUsuario, nombre, tiempo) values (" + usuario.getIdUsuario() + ",'" + usuario.getNombre() + "'," + usuario.getTiempo() + ");";
			logger.log( Level.INFO, "Statement: " + sent );
			int insertados = statement.executeUpdate( sent );
			if (insertados!=1) return false;  // Error en inserción
			// Búsqueda de la fila insertada - para ello hay que recuperar la clave autogenerada. Hay varias maneras, vemos dos diferentes:
			// Se hace utilizando método del propio objeto statement
			ResultSet rrss = statement.getGeneratedKeys();  // Genera un resultset ficticio con las claves generadas del último comando
			rrss.next();  // Avanza a la única fila 
			int pk = rrss.getInt( 1 );  // Coge la única columna (la primary key autogenerada)
			usuario.setId( pk );
			return true;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return false;
		}
	}
	
	public static ArrayList<Usuario> getUsuarios() {
		try (Statement statement = conexion.createStatement()) {
			ArrayList<Usuario> ret = new ArrayList<>();
			String sent = "select * from usuario;";
			logger.log( Level.INFO, "Statement: " + sent );
			ResultSet rs = statement.executeQuery( sent );
			while( rs.next() ) { // Leer el resultset
				int idUsuario = rs.getInt("idUsuario");
				String nombre = rs.getString("nombre");
				double tiempo = rs.getDouble("tiempo");
				ret.add( new Usuario ( idUsuario, nombre, tiempo) );
			}
			return ret;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return null;
		}
	}
	
	public static void borrarUsuario( Usuario usuario ) throws SQLException {
		Statement statement = conexion.createStatement();
		String sent = "delete from usuario  where idUsuario=" + usuario.getIdUsuario() + ";";
		logger.log( Level.INFO, "Statement: " + sent );
		int borrados = statement.executeUpdate( sent );
		if (borrados==0) throw new SQLException( "No se ha borrado ningun usuario con idUsuario=" + usuario.getIdUsuario() );
		statement.close();
	}
	
}
