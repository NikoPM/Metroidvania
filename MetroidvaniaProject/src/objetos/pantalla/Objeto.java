package objetos.pantalla;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

abstract public class Objeto extends Graficos {
	//variables protected, para usar en las clases hijas
	protected int velocidadX = 0;
	protected int velocidadY = 0;
	protected int radioHitbox = 0;

	public Objeto(int x, int y, int velocidadX, int velocidadY, int radioHitbox, String imagen) {
		super(x, y, imagen);
		this.velocidadX = velocidadX;
		this.velocidadY = velocidadY;
		this.radioHitbox = radioHitbox;
	}
	//getters y setters generados con source
	
	public int getVelocidadX() {
		return velocidadX;
	}

	public void setVelocidadX(int velocidadX) {
		this.velocidadX = velocidadX;
	}

	public int getVelocidadY() {
		return velocidadY;
	}

	public void setVelocidadY(int velocidadY) {
		this.velocidadY = velocidadY;
	}

	public int getRadioHitbox() {
		return radioHitbox;
	}

	public void setRadioHitbox(int radioHitbox) {
		this.radioHitbox = radioHitbox;
	}
	//TODO eliminar ventanagrafica
	/*
	//ajustes de la ventana grafica
	public void dibujar(VentanaGrafica juego) {
		juego.dibujaImagen( "/imagenes/"+dirImg, posX, posY, 1.0, 0.0, 1.0f);
	}
	//metodo mover que usaremos en cada clase hija
	abstract public void mover(VentanaGrafica juego);
	//metodo que registra los impactos sobre el jugador utilizando el atributo de la hitbox
	public boolean impacto(Objeto p) {
		double d = Math.sqrt((posX-p.getPosX())*(posX-p.getPosX()) + (posY-p.getPosY())*(posY-p.getPosY()));
		return (d < this.radioHitbox + p.radioHitbox);
	}
	*/

}
