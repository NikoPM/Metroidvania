package objetos.pantalla;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

public class Salud extends Graficos {
	//imports correspondientes, los atributos (ninguno que no este en la clase padre) y el super
	public Salud(int x, int y, int velocidadX, int velocidadY, int radioHitbox, String imagen) {
		super(x, y, imagen, velocidadX, velocidadY, radioHitbox);
	}
	//XD
	public Salud(int x, int y) {
		super(x, y, "magia.png", 0, 0, 0);
	}

	public void mover(VentanaGrafica juego) {
		//movimientos lineales en los ejes x e y, por defecto esta hecho para que dispare hacia arriba
		posX+= this.velX;
		posY += this.velY;
	}

}
