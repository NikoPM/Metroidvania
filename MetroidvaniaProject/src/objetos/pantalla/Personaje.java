package objetos.pantalla;

import java.util.ArrayList;


import utils.ventanas.ventanaBitmap.VentanaGrafica;


public class Personaje extends Graficos {
	//nuevos atributos para los controles de movimiento y disparo, para estos ultimos se hace un array con elementos de la clase magia
	protected int Derecha;
	protected int Izquierda;
	protected int Arriba;
	protected int Abajo;
	protected int disparo;
	protected int disparoDisponible;
	protected ArrayList<Magia> disparos;
	
	//XD
	public Personaje(int x, int y, int velocidadX, int velocidadY, int radioHitbox, String imagen, int Derecha, int Izquierda,int Arriba ,int Abajo, int disparo, int disparoDisponible, ArrayList<Magia> disparos  ) {
		super(x, y, imagen, velocidadX, velocidadY, 10);
		
		this.Derecha = Derecha;
		this.Izquierda = Izquierda;
		this.Arriba = Arriba;
		this.Abajo = Abajo;
		this.disparo = disparo;
		this.disparoDisponible = disparoDisponible;
		this.disparos = disparos;
	
	}
	
	public Personaje(int x, int y, int Izquierda, int Derecha, int Arriba, int Abajo, int disparo ) {
		super(x, y, "personaje.png", 0, 0, 10);
		this.Izquierda = Izquierda;
		this.Derecha = Derecha;
		this.Arriba = Arriba;
		this.Abajo = Abajo;
		this.disparo = disparo;
		this.disparoDisponible = 5;
		this.disparos = new ArrayList<Magia>();
	}


	public int getDerecha() {
		return Derecha;
	}

	public void setDerecha(int derecha) {
		Derecha = derecha;
	}

	public int getIzquierda() {
		return Izquierda;
	}

	public void setIzquierda(int izquierda) {
		Izquierda = izquierda;
	}

	
	

	public int getArriba() {
		return Arriba;
	}

	public void setArriba(int arriba) {
		Arriba = arriba;
	}

	public int getAbajo() {
		return Abajo;
	}

	public void setAbajo(int abajo) {
		Abajo = abajo;
	}

	

	public int getDisparo() {
		return disparo;
	}

	public void setDisparo(int disparo) {
		this.disparo = disparo;
	}
	
	public int getDisparoDisponible() {
		return disparoDisponible;
	}

	public void setDisparoDisponible(int disparoDisponible) {
		this.disparoDisponible = disparoDisponible;
	}

	public ArrayList<Magia> getDisparos() {
		return disparos;
	}

	public void setDisparos(ArrayList<Magia> disparos) {
		this.disparos = disparos;
	}
	
	
//movimiento del personaje usando las variables de posicion, velocidad y las dimensiones del mapa
	public void mover(VentanaGrafica juego) {
		if (posX < 0) {
			posX = 0;
			this.velX = 0;
		} else if (posX> juego.getAnchura()) {
			posX = juego.getAnchura();
			this.velX = 0;
		} else {
			posX += this.velX;
		}
		
		if (posY< 0) {
			posY = 0;
			this.velY = 0;
		} else if (posY > juego.getAltura()) {
			posY = juego.getAltura();
			this.velY = 0;
		} else {
			posY += this.velY;
		}
	}
	
	
}
