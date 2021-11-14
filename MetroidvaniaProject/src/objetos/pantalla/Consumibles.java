package objetos.pantalla;

import java.awt.*;
import javax.swing.*;

/**
 * Clase Consumibles de los objetos que aparcen por pantalla
 */
public class Consumibles extends Graficos {
	private static final long serialVersionUID = 1L; //Version Serializable

	/** Constructor Privado de objetos de clase Consumibles
	 * @param x Posicion X del consumible en pantalla
	 * @param y Posicion Y del consumible en pantalla
	 * @param dir Direccion en la que se encuentra la imagen(es) del consumible
	 */
	private Consumibles(int x, int y, String dir) {
		super(x, y, dir);
	}
	 
	private static void editarLabel(final JLabel label, final int posX, final int posY) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				for(int j = 0; j<5; j++) {
					label.setLocation(posX, posY+1*j);
					try {
						Thread.sleep(70);
					} catch(InterruptedException e) {
						Thread.currentThread().interrupt();
					}
				}
				for(int j = 0; j<5; j++) {
					label.setLocation(posX, 5+posY-1*j);
					try {
						Thread.sleep(70);
					} catch(InterruptedException e) {
						Thread.currentThread().interrupt();
					}
				}
			}
		});
	}
	
	/** Metodo Privado Crear
	 * @param vent Ventana en la que se crear el consumible
	 * @return devuelve el JLabel con la imagen y posicion del consumible
	 * Añade el consumible al Contentpane de la ventana, edita su layout a flowlayout 
	 * y tambien crea un hilo con duracion 70 seg(por ahora) que anima el consumible haciendolo 
	 * subir y bajar 5 pixeles
	 */
	private void crear(JFrame vent) {
		JLabel label = new JLabel(new ImageIcon(this.dirImg));
		vent.getContentPane().setLayout(new FlowLayout());
		vent.getContentPane().add(label);
		Thread hilo = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; !Thread.interrupted() && i< 10; i++) {
					editarLabel(label, posX, posY);
				}
			}
		});
		hilo.start();
	}
	
	/** Metodo Estatico Generar
	  * @param x Posicion X del consumible en pantalla
	 * @param y Posicion Y del consumible en pantalla
	 * @param dir Direccion en la que se encuentra la imagen(es) del consumible
	 * @param vent Ventana en la que se crear el consumible
	 * @return Crear un objeto de la clase consumible, lo introduce en la ventana animandolo 
	 * y devuelve el JLabel con la imagen y posicion del consumible
	 * LLama al constructor y al metodo crear
	 */
	public static void generar(int x, int y, String dir, JFrame vent) {
		SwingUtilities.invokeLater(new Runnable() {	
			@Override
			public void run() {
				Consumibles cons = new Consumibles(x, y, dir);
				cons.crear(vent);
			}
		});
	}
}
