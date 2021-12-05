package objetos.pantalla;

import java.util.Random;

import utils.ventanas.ventanaBitmap.VentanaGrafica;


public class Enemigo extends Graficos /**implements Destruible**/{
	
	//protected String explosion;
	
	private static Random random = new Random();
	
	public Enemigo(int x, int y, int velocidadX, int velocidadY, int radioHitbox, String imagen /**,String explosion**/) {
		super(x, y, "enemigo.png", velocidadX, velocidadY, radioHitbox);
		
		//this.explosion="/imagenes/explosion.jpg";
	}
	//XD
	//public String getExplosion() {
	//return explosion;
	//}

	//public void setExplosion(String explosion) {
	//this.explosion = explosion;
	//}

	//modificar parametros para ajustar la velocidad
	public void mover(VentanaGrafica juego) {    
		
		posX += this.velX;
		posY += this.velY;
		if (posX < 0 || posX > juego.getAnchura()) {
			this.velX = - this.velX;
		}
		if (posY < 0 || posY > juego.getAltura()) {
			this.velY = - this.velY;
		}
	}
	
	//public  void destruir() {
	//	System.out.println("enemigo destruido");
	//}
}
