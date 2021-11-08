package objetos.pantalla;

import java.io.*;

/**
 * Clase Madre Graficos de la cual heredaran los objetos en pantalla
 */
public class Graficos implements Serializable {
	private static final long serialVersionUID = 1L; //Version Serializable
	protected int posX; //Posicion X en pantalla
	protected int posY; //Posicion Y en pantalla
	protected String dirImg; //Direccion de la imagen en pantalla
	
	
	/** Constructor de objetos de clase Graficos
	 * @param x Posicion X en pantalla
	 * @param y Posicion Y en pantalla
	 * @param alt Altura en pantalla
	 * @param anch Anchura en pantalla
	 * Crea un objeto de tipo grafico con unos valores introducidos
	 */
	protected Graficos(int x, int y, String dir) {
		this.setPosX(x);
		this.setPosY(y);
		this.setDirImg(dir);
	}
	
	//SEtters y Getters basicos
	protected int getPosX() {
		return posX;
	}
	
	protected int getPosY() {
		return posY;
	}
	
	public String getDirImg() {
		return dirImg;
	}
	
	protected void setPosX(int posX) {
		this.posX = posX;
	}
	
	protected void setPosY(int posY) {
		this.posY = posY;
	}
	
	public void setDirImg(String dirImg) {
		this.dirImg = dirImg;
	}
}
