package objetos.pantalla;

import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Fondo extends Graficos{
	private static final long serialVersionUID = 1L;
	protected static final String FONDO = "src/imagenes/Fondo1.jpg";
	private Thread hilo;
	private JLabel label;
	
	/**
	 * Constructor privado de la clase fondo
	 */
	private Fondo() {
		super(0, 0, FONDO, 0, 0, 0);
	}
	
	
	//Metodos de la clase
	/**Mantiene la posicion del fondo
	 * en la ventana .
	 * @param vent ventana en la que se quiere poner el fondo.
	 */
	private void crear(final JFrame vent) {
		Fondo fond= this;
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				label.setLocation(fond.getPosX(), fond.getPosY());
			}
		});
	}
		
	/**Llama a un hilo que cada segundo llama al metodo crear .
	 * El hilo termina cuando termina la sesion de juego.
	 * @param vent
	 */
	public void startThread(JFrame vent) {
		hilo = new Thread(new Runnable(){
			@Override
			public void run() {
				while(!Thread.interrupted()) {
					try {
						for(int i = 0; i<3; i++) {
							crear(vent);
							Thread.sleep(1000);
						}
						Thread.currentThread().interrupt();
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
					}
				}
			}
		});
		hilo.start();
	}
	
	/**Genera un JLabel con la imagen del fondo, 
	 * lo inserta en la ventana que se desea poner el fondo y 
	 * 	llama al metodo startThread() para mantenerlo en su sitio.
	 * @param vent Ventana en la que se desea poner un Fondo.
	 * @return JLabel con la imagen del fondo de pantalla.
	 */
	public static JLabel generar(JFrame vent) {
	Fondo fond = new Fondo();
	JLabel lab = new JLabel(new ImageIcon(fond.dirImg));
	fond.label = lab;
	vent.getContentPane().setLayout(new FlowLayout());
	vent.getContentPane().add(lab);
	fond.startThread(vent);
	return lab;	
	}
}
