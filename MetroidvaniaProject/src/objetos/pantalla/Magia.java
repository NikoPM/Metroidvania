package objetos.pantalla;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

public class Magia extends Objeto {
	//imports correspondientes, los atributos (ninguno que no este en la clase padre) y el super
	public Magia(int x, int y, int velocidadX, int velocidadY, int radioHitbox, String imagen) {
		super(x, y, velocidadX, velocidadY, radioHitbox, imagen);
	}
	//XD
	public Magia(int x, int y) {
		super(x, y, 0, -35, 5, "magia.png");
	}

	public void mover(VentanaGrafica juego) {
		//movimientos lineales en los ejes x e y, por defecto esta hecho para que dispare hacia arriba
		posX += this.velocidadX;
		posY += this.velocidadY;
	}

}
