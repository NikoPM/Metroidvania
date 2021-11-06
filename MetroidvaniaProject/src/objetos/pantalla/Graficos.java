package objetos.pantalla;

import java.io.*;

/**
 * Clase Madre Graficos de la cual heredaran los objetos en pantalla
 */
public class Graficos implements Serializable {
	private static final long serialVersionUID = 1L; //Version Serializable
	protected int posX; //Posicion X en pantalla
	protected int posY; //Posicion Y en pantalla
	protected int altura; //Altura en pantalla
	protected int anchura; //Anchura en pantalla
	
	
	/**
	 * @param x Posicion X en pantalla
	 * @param y Posicion Y en pantalla
	 * @param alt Altura en pantalla
	 * @param anch Anchura en pantalla
	 * Crea un objeto de tipo grafico con unos valores introducidos
	 */
	protected Graficos(int x, int y, int alt, int anch) {
		this.setPosX(x);
		this.setPosY(y);
		this.setAltura(alt);
		this.setAnchura(anch);
	}
	
	//SEtters y Getters basicos
	protected int getPosX() {
		return posX;
	}
	
	protected int getPosY() {
		return posY;
	}
	
	protected int getAltura() {
		return altura;
	}
	
	protected int getAnchura() {
		return anchura;
	}
	
	protected void setPosX(int posX) {
		this.posX = posX;
	}
	
	protected void setPosY(int posY) {
		this.posY = posY;
	}
	
	protected void setAltura(int altura) {
		this.altura = altura;
	}
	
	protected void setAnchura(int anchura) {
		this.anchura = anchura;
	}
	
	//Metodo mover vacio
	protected void mover() {}
	
	//Metodo saltar vacio
	protected void saltar() {}
	
	//Metodo animar vacio
	protected void animar() {}
}
