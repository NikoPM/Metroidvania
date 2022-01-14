package objetos.pantalla;

import java.awt.*;
import javax.swing.*;
import ventanas.*;
import java.util.*;
import java.util.List;

/**
 * Clase Consumibles de los objetos que aparecen por pantalla
 */
public class Consumibles extends Graficos {
	private static final long serialVersionUID = 1L; //Version Serializable
	private static final int VEL_X = 5; //Velocidad eje X del consumible
	private static final int VEL_Y = 1; //Velocidad eje Y del consumible
	private static final int HITBOX = 20; //Hitbox del consumible
	private static final String FRAME = "imagenes/ElixirVida.png";
	private Thread hilo; //Hilo de consumible
	private JLabel label; //Label del consumible
	private boolean victory; //boolean que indica el consumible de la victoria
	private static List<Consumibles> listaCons = new ArrayList<>(); //Lista que contiene los consumibles
 
	/** Constructor Privado de objetos de clase Consumibles
	 * @param x Posicion X del consumible en pantalla
	 * @param y Posicion Y del consumible en pantalla
	 * @param dir Direccion en la que se encuentra la imagen(es) del consumible
	 */
	private Consumibles(int x, int y) {
		super(x, y, FRAME, VEL_X, VEL_Y, HITBOX);
	}
	
	/** Metodo Estatico getListaCons
	 * @return devuelve la lista que contiene los consumibles
	 */
	public static List<Consumibles> getListaCons() {
		return listaCons;
	}
	
	/** Metodo Estatico getLabel
	 * @return devuelve el label del consumible
	 */
	public JLabel getLabel() {
		return label;
	}
	
	public boolean getVictory() {
		return victory;
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
				if(Personaje.colision(cons)) {
					if(cons.victory) {
						VentanaJuego.gameWin();
						stopAll();
					} else {
						Personaje.incVida(30);
						int num = new Random().nextInt(Plataformas.getListaPlat().size());
						cons.setPosX(Plataformas.getListaPlat().get(num).getPosX() + 30 + new Random().nextInt(100));
						cons.setPosY(Plataformas.getListaPlat().get(num).getPosY());		
					}
				}
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
	 * @param b boolean que da el valor a victory
	 * Crear un objeto de la clase consumible y un JLabel del mismo, 
	 * lo introduce en la ventana animandolo
	 * LLama al constructor y al metodo crear e introduce el consumible en la lista
	 */
	public static void generar(int x, int y, JFrame vent, boolean b) {
		Consumibles cons = new Consumibles(x, y);
		cons.victory = b;
		listaCons.add(cons);
		JLabel label = new JLabel(new ImageIcon(cons.dirImg));
		if(b) {
			label.setIcon(new ImageIcon("imagenes/pelota.png"));
		}
		cons.label = label;
		vent.getContentPane().setLayout(new FlowLayout());
		vent.getContentPane().add(cons.label);
		SwingUtilities.invokeLater(new Runnable() {	
			@Override
			public void run() {
				cons.animar(label);
			}
		});
	}
	
	/**Metodo estatico actualizarPos
	 * @param vent  Ventana en la que se encuentran las plataformas
	 * @param bool  Booleano que identifica si se avanza o se retrocede
	 * Actualiza la posicion de los consumibles
	 */
	public static void actualizarPos(JFrame vent, boolean bool) {
		for(Consumibles p:listaCons) {
			if(bool) {
				p.setPosX(p.getPosX() - VEL_X);	
			} else {
				p.setPosX(p.getPosX() + VEL_X);
			}	
		}
	}
}