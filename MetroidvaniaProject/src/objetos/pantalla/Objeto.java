package objetos.pantalla;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

abstract public class Objeto {
	//variables protected, para usar en las clases hijas
	protected int x = 0;
	protected int y = 0;
	protected int velocidadX = 0;
	protected int velocidadY = 0;
	protected int radioHitbox = 0;
	protected String imagen;
	//XD
	public Objeto(int x, int y, int velocidadX, int velocidadY, int radioHitbox, String imagen) {
		super();
		this.x = x;
		this.y = y;
		this.velocidadX = velocidadX;
		this.velocidadY = velocidadY;
		this.radioHitbox = radioHitbox;
		this.imagen = imagen ;
	}
	//getters y setters generados con source
	public Objeto() {
		super();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

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

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	//ajustes de la ventana grafica
	public void dibujar(VentanaGrafica juego) {
		juego.dibujaImagen( "/imagenes/"+this.imagen, this.x, this.y, 1.0, 0.0, 1.0f);
	}
	//metodo mover que usaremos en cada clase hija
	abstract public void mover(VentanaGrafica juego);
	//metodo que registra los impactos sobre el jugador utilizando el atributo de la hitbox
	public boolean impacto(Objeto p) {
		double d = Math.sqrt((this.x-p.x)*(this.x-p.x) + (this.y-p.y)*(this.y-p.y));
		return (d < this.radioHitbox + p.radioHitbox);
	}

}
