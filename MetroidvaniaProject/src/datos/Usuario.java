package datos;

import java.util.ArrayList;

public class Usuario {

	private int idUsuario;
	private String nombre; 
	private double tiempo;
	
	public Usuario(int idUsuario, String nombre, double tiempo) {
		super();
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.tiempo = tiempo;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setId(int id) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getTiempo() {
		return tiempo;
	}

	public void setTiempo(double tiempo) {
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
}

