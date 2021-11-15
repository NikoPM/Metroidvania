package objetos.pantalla;


import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.TreeMap;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

public class VentanaNivelPrueba {
	
	//XD
	//puntuaciones usando treemapssffewfsaf
	
	public static int puntos = 0;
	public static int cont = 0;
	
	//public static void puntuaciones() {	
	//}

	
	public static void main(String[] args) {
		VentanaNivelPrueba ventanaNivelPrueba = new VentanaNivelPrueba();//se divide el nivel en 3 fases, una implementa los objetos que estan desde el prncipio, otra que gestiona como estas interactuan y por ultimo una que detiene la ventana grafica
		ventanaNivelPrueba.iniciar();
		ventanaNivelPrueba.mecanicas();
		ventanaNivelPrueba.fin();
		puntos = 0;
	}
	private VentanaGrafica juego;
	private ArrayList<Objeto> listaObjetos = new ArrayList<Objeto>();
	private ArrayList<Salud> corazones = new ArrayList<Salud>();
	private ArrayList<Explosion> explosiones = new ArrayList<Explosion>();//los objetos que aparecen en pantalla se almacenan en este array

	public void iniciar() {
		juego = new VentanaGrafica(1280, 720, "nivel");//dimensiones y color de la ventana grafica
		juego.setColorFondo( Color.LIGHT_GRAY );
		listaObjetos.add(new Enemigo(80, 50, 7, 3, 10, "enemigo.png"/**,"explosion.jpg"**/)); //spawn de enemigos, tantos como indique el for
		listaObjetos.add(new Enemigo(80, 50, 6, 5, 10, "enemigo.png"/**,"explosion.jpg"**/)); //spawn de enemigos, tantos como indique el for	
		listaObjetos.add(new Enemigo(80, 50, 3, 15, 10, "enemigo.png"/**,"explosion.jpg"**/)); //spawn de enemigos, tantos como indique el for	

		
		corazones.add(new Salud(50, 40, 0, 0, 0, "salud.png"));
		corazones.add(new Salud(150, 40, 0, 0, 0, "salud.png"));
		corazones.add(new Salud(250, 40, 0, 0, 0, "salud.png"));
		corazones.add(new Salud(350, 40, 0, 0, 0, "salud.png"));
	
		juego.setDibujadoInmediato(false);
	}
	
	public void fin() {
		juego.acaba();
	}
	//dos nuevos arrays para los objetos que pueden desaparecer (magia y enemigos ya que si desaparece el jugador cierra el juego
	public void mecanicas() {
		Personaje personaje = new Personaje(juego.getAnchura()/2, juego.getAltura() - 50, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W,  KeyEvent.VK_S, KeyEvent.VK_ENTER);
		ArrayList<Magia> magiaBorrar = new ArrayList<Magia>();
		ArrayList<Enemigo> enemigosBorrar = new ArrayList<Enemigo>();
		
		
		boolean salir = false;
		
		while(!salir) {
			// controles, ya que no podemos verlos en accion en la ventana grafica, he puesto un syso que devuelve en la consola cuando se activa su control correspondiente
			if (juego.isTeclaPulsada(personaje.getDerecha())) {
				personaje.setVelocidadX(5);
				//System.out.println("dec prueba");
			}
			if (juego.isTeclaPulsada(personaje.getIzquierda())) {
				personaje.setVelocidadX(-5);
				//System.out.println("izq prueba");
			}
			
			if (juego.isTeclaPulsada(personaje.getArriba())) {
				personaje.setVelocidadY(-5);
				//System.out.println("arriba prueba");
			}
			
			if (juego.isTeclaPulsada(personaje.getAbajo())) {
				personaje.setVelocidadY(5);
				//System.out.println("abajo prueba");
			}
			if (juego.isTeclaPulsada(personaje.getDisparo())) {
					personaje.getDisparos().add(new Magia(personaje.getPosX(), personaje.getY()));


					
					
				
			}
			//sacar al jugador si le impactan
			for (Objeto pers : listaObjetos) {
				pers.mover(juego);
				if (pers.impacto(personaje)) {
					personaje.setRadioHitbox(0);
					personaje.setPosX(640);
					personaje.setY(720);
					cont+=1;
					personaje.setRadioHitbox(10);
					
					if(cont==1) {
						corazones.add(new Salud(350, 40, 0, 0, 0, "saludVacia.png"));
					}
					if(cont==2) {
						corazones.add(new Salud(250, 40, 0, 0, 0, "saludVacia.png"));
					}
					if(cont==3) {
						corazones.add(new Salud(150, 40, 0, 0, 0, "saludVacia.png"));
					}
					if(cont==4) {
						corazones.add(new Salud(50, 40, 0, 0, 0, "saludVacia.png"));
						salir = true;
						cont=0;
					}
					
				}
			}
			
			
			
			//disparos sobre los enemigos y su eliminacion
			
			
			
			personaje.mover(juego);
			for (Magia magia : personaje.getDisparos()) {
				magia.mover(juego);
				for (Objeto pers : listaObjetos) {
					if (pers instanceof Enemigo && magia.impacto(pers)) {
						Enemigo enemigo = (Enemigo) pers;
						magiaBorrar.add(magia);
							enemigosBorrar.add(enemigo);
							explosiones.add(new Explosion(enemigo.getX(), enemigo.getY(), 0, 0, 0, "explosion.png"));
							for (int j = 0; j < 1; j++) {
								puntos+=1;
								if(puntos == 3) {
									salir = true;
									
								}
							
							}
							
							
						
					}
				}
				if (magia.getY() < 0) {
					magiaBorrar.add(magia);
				}
			}
			for (Magia magia : magiaBorrar) {
				personaje.getDisparos().remove(magia);
			}
			magiaBorrar.clear();
			for (Enemigo enemigo : enemigosBorrar) {
				listaObjetos.remove(enemigo);
			}
			enemigosBorrar.clear();
			
			//dibujado en la ventana grafica, no funciona pero al no haber errores el fallo debe estar en las imagenes que importamos
			juego.borra();
			for (Objeto pers : listaObjetos) {
				pers.dibujar(juego);
			}
			for (Salud corazon : corazones) {
				corazon.dibujar(juego);
			}
			for (Explosion explosion : explosiones) {
				explosion.dibujar(juego);
			}
			for (Magia magia : personaje.getDisparos()) {
				magia.dibujar(juego);
			}
			personaje.dibujar(juego);
			juego.repaint();
			juego.espera(10);
			
		}
		
	}

}

