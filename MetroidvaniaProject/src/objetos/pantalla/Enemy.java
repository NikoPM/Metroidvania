package objetos.pantalla;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.util.List;

/**
 * Clase Enemy de los enemigos que aparecen por pantalla
 */
public class Enemy extends Graficos {
	private static final long serialVersionUID = 1L; //Version Serializable
	private static final int VEL = 7; //Velocidad del enemigo
	private static final int HITBOX = 20; //Hitbox del enemigo
	private static final String FRAME = "src/imagenes/enemigo.png"; //Imagen del enemigo
	private Thread hilo; //Hilo de enemigo
	private JLabel label; //Label del enemigo
	private static List<Enemy> listaEne = new ArrayList<>(); //Lista que contiene los enemigo
 
	/** Constructor Privado de objetos de clase Enemy
	 * @param x Posicion X del enemigo en pantalla
	 * @param y Posicion Y del enemigo en pantalla
	 * @param dir Direccion en la que se encuentra la imagen(es) del enemigo
	 */
	private Enemy(int x, int y) {
		super(x, y, FRAME, VEL, VEL, HITBOX);
	}
	
	/** Metodo Estatico getListaEne
	 * @return devuelve la lista que contiene los enemigos
	 */
	public static List<Enemy> getListaEne() {
		return listaEne;
	}
	
	/** Metodo Estatico getLabel
	 * @return devuelve el label del enemigo
	 */
	public JLabel getLabel() {
		return label;
	}
	
	/** Metodo Estatico Privado LabelMove
	 * @param cons Enemigo que se anima y del cual se recibe la posicion en pantalla
	 * @param label JLabel que se edita
	 * @param b boolean que indica si realizar la operacion de suma o resta
	 * Establece y edita la posicion del label
	 */
	private static void labelMove(final Enemy ene, final JLabel label) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				int rand = new Random().nextInt(VEL);
				if(ene.getPosX() < Personaje.getPersonaje().getPosX()) {
					ene.setPosX(ene.getPosX() + rand);
				} 
				if(ene.getPosY() < Personaje.getPersonaje().getPosY()) {
					ene.setPosY(ene.getPosY() + rand);
				}
				if(ene.getPosX() > Personaje.getPersonaje().getPosX()) {
					ene.setPosX(ene.getPosX() - rand);
				} 
				if(ene.getPosY() > Personaje.getPersonaje().getPosY()) {
					ene.setPosY(ene.getPosY() - rand);
				}
				label.setLocation(ene.getPosX(), ene.getPosY());
			}
		});
	}
	
	/** Metodo Privado Animar
	 * @param label JLabel con la imagen del enemigo
	 * @param label JFrame en el que se dibuja el enemigo
	 * Crea un hilo que llama a la funcion labelMove que anima el enemigo haciendolo moverse
	 */
	private void animar(JLabel label, JFrame vent) {
		Enemy ene = this;
		hilo = new Thread(new Runnable() {
			@Override
			public void run() {
				while(!Thread.interrupted()) {
					try {
						labelMove(ene, label);
						if(Personaje.colisionShoot(ene)) {
							int pos = new Random().nextInt(1500) - new Random().nextInt(1500);	
							ene.setPosX(pos);
							pos = new Random().nextInt(1500) - new Random().nextInt(1500);
							ene.setPosY(pos);
						}
						if(Personaje.colision(ene)) {
							Personaje.decVida(1);
						}
						Thread.sleep(30);
					} catch(InterruptedException e) {
						Thread.currentThread().interrupt();
					}
				}
			}
		});
		hilo.start();
	}
	 
	/** Metodo StopAnimar
	 * @param i indice del enemigo interrumpido
	 * Interrumpe el hilo de un enemigo
	 */
	public static void stopAnimar(int i) {
		listaEne.get(i).hilo.interrupt();
	}
	 
	/** Metodo StopAll
	 *  Interrumpe el hilo de todos los enemigos
	 */
	public static void stopAll() {
		for(Enemy ene: listaEne) {
			ene.hilo.interrupt();
		}
	}

	/** Metodo Estatico Generar
	 * @param x Posicion X del enemigo en pantalla
	 * @param y Posicion Y del enemigo en pantalla
	 * @param dir Direccion en la que se encuentra la imagen(es) del enemigo
	 * @param vent Ventana en la que se crear el enemigo
	 * Crear un objeto de la clase enemigo y un JLabel del mismo, 
	 * lo introduce en la ventana animandolo
	 * LLama al constructor y al metodo crear e introduce el enemigo en la lista
	 */
	public static void generar(int x, int y, JFrame vent) {
		Enemy ene = new Enemy(x, y);
		listaEne.add(ene);
		JLabel label = new JLabel(new ImageIcon(ene.dirImg));
		ene.label = label;
		vent.getContentPane().setLayout(new FlowLayout());
		vent.getContentPane().add(ene.label);
		SwingUtilities.invokeLater(new Runnable() {	
			@Override
			public void run() {
				ene.animar(label, vent);
			}
		});
	}
}