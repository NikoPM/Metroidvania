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
	
	//public String getExplosion() {
	//return explosion;
	//}

	//public void setExplosion(String explosion) {
	//this.explosion = explosion;
	//}

	//modificar parametros para ajustar la velocidad
	public void mover(VentanaGrafica juego) {    
		
		this.x += this.velocidadX;
		this.y += this.velocidadY;
		if (this.x < 0 || this.x > juego.getAnchura()) {
			this.velocidadX = - this.velocidadX;
		}
		if (this.y < 0 || this.y > juego.getAltura()) {
			this.velocidadY = - this.velocidadY;
		}
	}
	
	//public  void destruir() {
	//	System.out.println("enemigo destruido");
	//}
}
