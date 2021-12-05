package objetos.pantalla;

import java.awt.*;
import javax.swing.*;

public class Character extends Objeto {
	
	private static final int VEL_X = 0;
	private static final int VEL_Y = 0;
	private static final int HITBOX = 0;
	
	private Character(int x, int y, String imagen) {
		super(x, y, VEL_X, VEL_Y, HITBOX, imagen);
	}
	
	/** Metodo Privado Generar
	 * @param vent Ventana en la que se crear el personaje
	 */
	public static JLabel generar(int x, int y, String imagen, JFrame vent) {
		Character charac = new Character(x, y, imagen);
		vent.getContentPane().setLayout(new FlowLayout());
		JLabel label = new JLabel(new ImageIcon(charac.dirImg));
		vent.getContentPane().add(label);
		return label;
	}
	
	private static void moverCharacter(final JLabel label , final boolean eje) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if(eje) {
					label.setLocation(label.getX() + 1, label.getY());
				} else {
					label.setLocation(label.getX(), label.getY() + 1);
				}
			}
		});
	}
	 
	public static Thread mover(JLabel label, boolean eje) {
		Thread hilo = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i<10 && !Thread.interrupted(); i++) {
					try {
						moverCharacter(label, eje);
						Thread.sleep(100);
					} catch (InterruptedException e) {}
				}
			}
		});
		return hilo;
	}
}
