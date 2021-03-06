package datos;

public class Usuario implements Comparable<Usuario> {

	private int idUsuario;
	private String nombre; 
	private long tiempo;
	
	public Usuario(int idUsuario, String nombre, long tiempo) {
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.tiempo = tiempo;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setId(int id) {
		this.idUsuario = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getTiempo() {
		return tiempo;
	}

	public void setTiempo(long tiempo) {
		this.tiempo = tiempo;
	}
	
	@Override
	public String toString() {
		return idUsuario + "\t" + nombre + "\t" + tiempo;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Usuario) {
			return idUsuario == ((Usuario)obj).idUsuario;
		} else {
			return false;
		}
	}
	
	@Override
	public int compareTo(Usuario u) {
		if(this.idUsuario > u.getIdUsuario()) return 1;
		if(this.idUsuario < u.getIdUsuario()) return -1;
		return 0;
	}
}

