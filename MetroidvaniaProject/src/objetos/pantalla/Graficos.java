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
	protected int velX; //Velocidad en el eje X
	protected int velY; //Velocidad en el eje X
	protected int hitbox; //Tamaño de la hitbox
	
	/** Constructor de objetos de clase Graficos
	 * @param x Posicion X en pantalla
	 * @param y Posicion Y en pantalla
	 * @param dir Dirección donde se encuentra la imagen
	 * @param velX Velocidad en el eje X
	 * @param velY Velocidad en el eje Y
	 * @param hitB Tamaño de la hitbox
	 * Crea un objeto de tipo grafico con unos valores introducidos
	 */
	public Graficos(int x, int y, String dir, int velX, int velY, int hitB) {
		this.setPosX(x);
		this.setPosY(y);
		this.setDirImg(dir);
		this.setVelX(velX);
		this.setVelY(velY);
		this.setHitbox(hitB);
	}
	
	//Setters y Getters basicos
	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public String getDirImg() {
		return dirImg;
	}
	
	public int getVelX() {
		return velX;
	}
	
	public int getVelY() {
		return velY;
	}
	
	public int getHitbox() {
		return hitbox;
	}
	
	public void setPosX(int posX) {
		this.posX = posX;
	}
	
	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public void setDirImg(String dirImg) {
		this.dirImg = dirImg;
	}
	
	public void setVelX(int velX) {
		this.velX = velX;
	}
	
	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	public void setHitbox(int hitbox) {
		this.hitbox = hitbox;
	}
}