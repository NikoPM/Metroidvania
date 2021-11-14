package objetos.pantalla;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

public class Explosion extends Objeto {
	//imports correspondientes, los atributos (ninguno que no este en la clase padre) y el super
	public Explosion(int x, int y, int velocidadX, int velocidadY, int radioHitbox, String imagen) {
		super(x, y, velocidadX, velocidadY, radioHitbox, imagen);
	}
	//XD
	public Explosion(int x, int y) {
		this.x = x;
		this.y = y;
		this.velocidadX = 0;
		this.velocidadY = 0;
		this.radioHitbox = 0;
		this.imagen = "explosion.png";
	}

	public void mover(VentanaGrafica juego) {
		//movimientos lineales en los ejes x e y, por defecto esta hecho para que dispare hacia arriba
		this.x += this.velocidadX;
		this.y += this.velocidadY;
	}

}
