package objetos.pantalla;

import java.io.*;

public class Graficos implements Serializable {
	private static final long serialVersionUID = 1L;
	protected int posX;
	protected int posY;
	protected int altura;
	protected int anchura;
	
	protected Graficos(int x, int y, int alt, int anch) {
		this.setPosX(x);
		this.setPosY(y);
		this.setAltura(alt);
		this.setAnchura(anch);
	}
	
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
	
	protected void mover() {}
	
	protected void saltar() {}
	
	protected void animar() {}
}
