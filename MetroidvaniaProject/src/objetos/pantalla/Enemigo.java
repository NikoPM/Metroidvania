package objetos.pantalla;

import java.awt.FlowLayout;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

public class Enemigo extends Character /** implements Destruible **/
{
	private static final long serialVersionUID = 1L;

	private static Random random = new Random();

	public Enemigo(int x, int y, int velocidadX, int velocidadY, int radioHitbox,
			String imagen /** ,String explosion **/
	) {
		super(x, y, velocidadX, velocidadY, radioHitbox, "enemigo.png");
	}

	private static void movimiento(final JLabel label, final int posX, final int posY, final int j, final int k) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				label.setLocation(posX+j, posY+k);
			}
		});
	}

	private void crear(JLabel label, JFrame vent) {
		vent.getContentPane().setLayout(new FlowLayout());
		vent.getContentPane().add(label);
		Thread hilo = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; !Thread.interrupted() && i < 500; i++) {
					for (int j = 0; j < 500; j++) {
						for (int k = 0; k < 500; k++) {
							movimiento(label, posX, posY, j, k);

							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								Thread.currentThread().interrupt();
							}
						}
					}
				}
			}
		});
		hilo.start();
	}

	public static JLabel generar(int x, int y,int velocidadX, int velocidadY, int radioHitbox, String dir, JFrame vent) {
		Enemigo e = new Enemigo(x, y, velocidadX, velocidadY, radioHitbox, dir);
		JLabel label = new JLabel(new ImageIcon("src/imagenes/enemigo.png"));
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				e.crear(label, vent);
			}
		});
		return label;
	}

}
