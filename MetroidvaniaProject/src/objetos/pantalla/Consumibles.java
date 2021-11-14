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
	
	/** Metodo Estatico Privado LabelMove
	 * @param label JLabel que se edita
	 * @param posX posicion en el eje X del label
	 * @param posY posicion en el eje X del label
	 * @param j int que se suma o resta a la posY
	 * @param b boolean que indica si realizar la operacion de suma o resta
	 * Establece y edita la posicion del label
	 */
	private static void labelMove(final JLabel label, final int posX, final int posY, final int j, final boolean b) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if(b) {
					label.setLocation(posX, posY+1*j);
				} else {
					label.setLocation(posX, 5+posY-1*j);		
				}
			}
		});
	}
	
	/** Metodo Privado Crear
	 * @param label JLabel con la imagen del consumible
	 * @param vent Ventana en la que se crear el consumible
	 * Añade el consumible al Contentpane de la ventana, edita su layout a flowlayout 
	 * y crea un hilo que llama a la funcion labelMove que anima el consumible haciendolo 
	 * subir o bajar 5 pixeles
	 */
	private void crear(JLabel label, JFrame vent) {
		vent.getContentPane().setLayout(new FlowLayout());
		vent.getContentPane().add(label);
		Thread hilo = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; !Thread.interrupted() && i< 10; i++) {
					for(int j = 0; j<10; j++) {
						if(j<5) {
							labelMove(label, posX, posY, j, true);
						} else {
							labelMove(label, posX, posY, j-5, false);
						}
						try {
							Thread.sleep(70);
						} catch(InterruptedException e) {
							Thread.currentThread().interrupt();
						}
					}
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
	 * @return Crear un objeto de la clase consumible y un JLabel del mismo, 
	 * lo introduce en la ventana animandolo y devuelve el JLabel con la imagen y posicion del consumible
	 * LLama al constructor y al metodo crear
	 */
	public static JLabel generar(int x, int y, String dir, JFrame vent) {
		Consumibles cons = new Consumibles(x, y, dir);
		JLabel label = new JLabel(new ImageIcon(cons.dirImg));
		SwingUtilities.invokeLater(new Runnable() {	
			@Override
			public void run() {
				cons.crear(label, vent);
			}
		});
		return label;
	}
}