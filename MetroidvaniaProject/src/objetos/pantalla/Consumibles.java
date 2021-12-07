package objetos.pantalla;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.util.List;

/**
 * Clase Consumibles de los objetos que aparcen por pantalla
 */
public class Consumibles extends Graficos {
	private static final long serialVersionUID = 1L; //Version Serializable
	private static final int VEL = 1; //Velocidad del consumible
	private static final int HITBOX = 1; //Hitbox del consumible
	private Thread hilo; //Hilo de consumible
	private static List<Consumibles> listaCons = new ArrayList<>(); //Lista que contiene los consumibles
 
	/** Constructor Privado de objetos de clase Consumibles
	 * @param x Posicion X del consumible en pantalla
	 * @param y Posicion Y del consumible en pantalla
	 * @param dir Direccion en la que se encuentra la imagen(es) del consumible
	 */
	private Consumibles(int x, int y, String dir) {
		super(x, y, dir, VEL, VEL, HITBOX);
	}
	
	/** Metodo Estatico Privado LabelMove
	 * @param cons Consumible que se anima y del cual se recibe la posicion en pantalla
	 * @param label JLabel que se edita
	 * @param b boolean que indica si realizar la operacion de suma o resta
	 * Establece y edita la posicion del label
	 */
	private static void labelMove(final Consumibles cons, final JLabel label, final boolean b) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if(b) {
					cons.setPosY(cons.getPosY() + cons.getVelY());
				} else {
					cons.setPosY(cons.getPosY() - cons.getVelY());	
				}
				label.setLocation(cons.getPosX(), cons.getPosY());
			}
		});
	}
	
	/** Metodo Privado Animar
	 * @param label JLabel con la imagen del consumible
	 * Crea un hilo que llama a la funcion labelMove que anima el consumible haciendolo 
	 * subir o bajar n pixeles
	 */
	private void animar(JLabel label) {
		Consumibles cons = this;
		hilo = new Thread(new Runnable() {
			@Override
			public void run() {
				while(!Thread.interrupted()) {
					for(int j = 0; j<10; j++) {
						if(j<5) {
							labelMove(cons, label, true);
						} else {
							labelMove(cons, label, false);
						}
						try {
							Thread.sleep(1000/(10*cons.getVelY()));
						} catch(InterruptedException e) {
							Thread.currentThread().interrupt();
						}
					}
				}
			}
		});
		hilo.start();
	}
	 
	/** Metodo StopAnimar
	 * @param i indice del consumible interrumpido
	 * Interrumpe el hilo de un consumible
	 */
	public static void stopAnimar(int i) {
		listaCons.get(i).hilo.interrupt();
	}
	 
	/** Metodo StopAll
	 *  Interrumpe el hilo de todos los consumibles
	 */
	public static void stopAll() {
		for(Consumibles cons: listaCons) {
			cons.hilo.interrupt();
		}
	}

	/** Metodo Estatico Generar
	 * @param x Posicion X del consumible en pantalla
	 * @param y Posicion Y del consumible en pantalla
	 * @param dir Direccion en la que se encuentra la imagen(es) del consumible
	 * @param vent Ventana en la que se crear el consumible
	 * @return Crear un objeto de la clase consumible y un JLabel del mismo, 
	 * lo introduce en la ventana animandolo y devuelve el JLabel con la imagen y posicion del consumible
	 * LLama al constructor y al metodo crear e introduce el consumible en la lista
	 */
	public static JLabel generar(int x, int y, String dir, JFrame vent) {
		Consumibles cons = new Consumibles(x, y, dir);
		listaCons.add(cons);
		JLabel label = new JLabel(new ImageIcon(cons.dirImg));
		vent.getContentPane().setLayout(new FlowLayout());
		vent.getContentPane().add(label);
		SwingUtilities.invokeLater(new Runnable() {	
			@Override
			public void run() {
				cons.animar(label);
			}
		});
		return label;
	}
}