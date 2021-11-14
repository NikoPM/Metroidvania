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
	
	/** Metodo Estatico Privado EditarLabel
	 * @param label el label que se edita
	 * @param posX la posicion en el eje X del label
	 * @param posY la posicion en el eje Y del label
	 * Anima el label haciendolo bajar y subir 5 pixeles durante 70*5*2*10 miliseg (por ahora) 
	 * o hasta que el hilo sea interrumpido
	 */
	private static void editarLabel(final JLabel label, final int posX, final int posY) {
		Thread hilo = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; !Thread.interrupted() && i< 10; i++) {
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
			}
		});
		hilo.start();
	}
	
	/** Metodo Privado Crear
	 * @param vent Ventana en la que se crear el consumible
	 * @return devuelve el JLabel con la imagen y posicion del consumible
	 * Añade el consumible al Contentpane de la ventana, edita su layout a flowlayout 
	 * y llama a la funcion editarLabel que anima el consumible haciendolo 
	 * subir y bajar 5 pixeles
	 */
	private JLabel crear(JFrame vent) {
		JLabel label = new JLabel(new ImageIcon(this.dirImg));
		vent.getContentPane().setLayout(new FlowLayout());
		vent.getContentPane().add(label);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				editarLabel(label, posX, posY);
			}
		});
		return label;
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