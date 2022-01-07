package objetos.pantalla;

import java.awt.FlowLayout;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Enemigo extends Graficos /** implements Destruible **/
{
	private static final long serialVersionUID = 1L;

	private static Random random = new Random();

	public Enemigo(int x, int y, int velocidadX, int velocidadY, int radioHitbox,
			String imagen /** ,String explosion **/
	) {
		super(x, y, "enemigo.png", velocidadX, velocidadY, radioHitbox);
	}

	private static void movimientoX(final Enemigo e, final JLabel label, final JFrame vent, final boolean dinamico) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					if (dinamico) {
						if (e.getPosX() <= 600) {
							e.setPosX(e.getPosX() + e.getVelX());
						}
					} else {
						e.setPosX(e.getPosX() - e.getVelX());
					}
					label.setLocation(e.getPosX(), e.getPosY());
					vent.repaint();
				} catch (NullPointerException e) {
				}
			}
		});
	}

	private static void movimientoY(final Enemigo e, final JLabel label, final JFrame vent, final boolean dinamico) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					if (dinamico) {
						if (e.getPosY() != 500) {
							e.setPosY(e.getPosY() + e.getVelY());
						}
					} else {
						e.setPosX(e.getPosY() - e.getVelY());

					}
					label.setLocation(e.getPosX(), e.getPosY());
					vent.repaint();
				} catch (NullPointerException e) {
				}
			}
		});
	}

	

	private void crear(/**Personaje pers ,**/Enemigo e, JLabel label, JFrame vent) {
		vent.getContentPane().setLayout(new FlowLayout());
		vent.getContentPane().add(label);
		Thread hilo = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; !Thread.interrupted() && i < 500; i++) {
					for (int j = 0; j < 500; j++) {
						for (int k = 0; k < 500; k++) {
							movimientoX(e, label, vent, true);
							movimientoY(e, label, vent, true);
							/**persecucion(pers,e,label,vent,true);**/

							try {
								Thread.sleep(3);
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

	public void explotar() {
		this.dirImg = "src/imagenes/explosion.png";
	}

	public static JLabel generar(int x, int y, int velocidadX, int velocidadY, int radioHitbox, String dir,
			JFrame vent) {
		//Personaje pers = new Personaje(x,y);
		Enemigo e = new Enemigo(x, y, velocidadX, velocidadY, radioHitbox, dir);
		JLabel label = new JLabel(new ImageIcon("src/imagenes/enemigo.png"));
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				e.crear(/**pers,**/e, label, vent);
			}
		});
		return label;
	}

}
