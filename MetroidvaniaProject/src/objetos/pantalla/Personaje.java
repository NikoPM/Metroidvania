package objetos.pantalla;

import java.awt.*;
import javax.swing.*;
 
public class Personaje extends Graficos {
	private static final long serialVersionUID = 1L; //Version Serializable
	private static final int VEL_X = 1; //Velocidad en el eje X
	private static final int VEL_Y = 3; //Velocidad en el eje Y
	private static final int HITBOX = 1; //HitBox del personaje
	private static Thread hiloX; //Hilo para el eje X del personaje
	private static Thread hiloY; //Hilo para el eje Y del personaje
	private static Personaje yo; //Mismo personaje

	/** Constructor Privado de objetos de clase Consumibles
	 * @param x Posicion X del consumible en pantalla
	 * @param y Posicion Y del consumible en pantalla
	 * @param dir Direccion en la que se encuentra la imagen(es) del consumible
	 */
	private Personaje(int x, int y, String dir) {
		super(x, y, dir, VEL_X, VEL_Y, HITBOX);
		yo = this;
	}
	
	public static Personaje getCharacter() {
		return yo;
	}

	/** Metodo Estatico Privado LabelMove
	 * @param pers Personaje que se anima y del cual se recibe la posicion en pantalla
	 * @param label JLabel que se edita
	 * @param b boolean que indica si realizar la operacion de suma o resta
	 * Establece y edita la posicion del label
	 */
	private static void labelMove(final Personaje pers, final JLabel label, final boolean b) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if(b){
					pers.setPosX(pers.getPosX() + pers.getVelX());
				} else {
					pers.setPosX(pers.getPosX() - pers.getVelX());	
				}
				label.setLocation(pers.getPosX(), pers.getPosY());
			}
		});
	}
	
	private static void LabelFall(final Personaje pers, final JLabel label, final JFrame vent) {
		SwingUtilities.invokeLater(new Runnable() {	
			@Override
			public void run() {
				if(pers.getPosY()<100) { //<vent.getHeight()
					pers.setPosY(pers.getPosY() + pers.getVelY());
					label.setLocation(pers.getPosX(), pers.getPosY());
				}
			}
		});
	}
	
	public static void mover(JLabel label, boolean b) {
		hiloX = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i<10 && !Thread.interrupted(); i++) {
					try {
						labelMove(getCharacter(), label, b);
						Thread.sleep(100);
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
					}
				}
			}
		});
		hiloX.start();
	}
	
	public static void stopMover() {
		try {
			hiloX.interrupt();
		} catch (NullPointerException e) {}
	}
	
	public static void stopFall() {
		try {
			hiloY.interrupt();
		} catch (NullPointerException e) {}
	}
	
	public static void stopAll() {
		stopMover();
		stopFall();
	}
	
	public static JLabel generar(int x, int y, String dir, JFrame vent) {
		Personaje pers = new Personaje(x, y, dir);
		JLabel label = new JLabel(new ImageIcon(pers.dirImg));
		vent.getContentPane().setLayout(new FlowLayout());
		vent.getContentPane().add(label);
		hiloY = new Thread(new Runnable() {
			@Override
			public void run() {
				while(!Thread.interrupted()) {
					try {
						LabelFall(pers, label, vent);
						Thread.sleep(10);
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
					}
				}
			}
		});
		hiloY.start();
		return label;
	}
}
