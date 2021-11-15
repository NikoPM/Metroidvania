package objetos.pantalla;

import java.util.Random;

import utils.ventanas.ventanaBitmap.VentanaGrafica;


public class Enemigo extends Objeto /**implements Destruible**/{
	
	//protected String explosion;
	
	private static Random random = new Random();
	
	public Enemigo(int x, int y, int velocidadX, int velocidadY, int radioHitbox, String imagen /**,String explosion**/) {
		super(x, y, velocidadX, velocidadY, radioHitbox, "enemigo.png");
		
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
		
		posX += this.velocidadX;
		posY += this.velocidadY;
		if (posX < 0 || posX > juego.getAnchura()) {
			this.velocidadX = - this.velocidadX;
		}
		if (posY < 0 || posY > juego.getAltura()) {
			this.velocidadY = - this.velocidadY;
		}
	}
	
	//public  void destruir() {
	//	System.out.println("enemigo destruido");
	//}
}
