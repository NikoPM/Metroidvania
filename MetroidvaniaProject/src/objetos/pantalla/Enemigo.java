package objetos.pantalla;

import java.awt.FlowLayout;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

public class Enemigo extends Objeto /** implements Destruible **/
{
	private static final long serialVersionUID = 1L;

<<<<<<< HEAD
=======
public class Enemigo extends Graficos /**implements Destruible**/{
	
	//protected String explosion;
	
>>>>>>> branch 'master' of https://github.com/NikoPM/Metroidvania.git
	private static Random random = new Random();
<<<<<<< HEAD

	public Enemigo(int x, int y, int velocidadX, int velocidadY, int radioHitbox,
			String imagen /** ,String explosion **/
	) {
		super(x, y, velocidadX, velocidadY, radioHitbox, "enemigo.png");
=======
	
	public Enemigo(int x, int y, int velocidadX, int velocidadY, int radioHitbox, String imagen /**,String explosion**/) {
		super(x, y, "enemigo.png", velocidadX, velocidadY, radioHitbox);
		
		//this.explosion="/imagenes/explosion.jpg";
>>>>>>> branch 'master' of https://github.com/NikoPM/Metroidvania.git
	}

<<<<<<< HEAD
	private static void movimiento(final JLabel label, final int posX, final int posY, final int j, final int k) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				label.setLocation(posX+j, posY+k);
			}
		});
=======
	//public void setExplosion(String explosion) {
	//this.explosion = explosion;
	//}

	//modificar parametros para ajustar la velocidad
	public void mover(VentanaGrafica juego) {    
		
		posX += this.velX;
		posY += this.velY;
		if (posX < 0 || posX > juego.getAnchura()) {
			this.velX = - this.velX;
		}
		if (posY < 0 || posY > juego.getAltura()) {
			this.velY = - this.velY;
		}
>>>>>>> branch 'master' of https://github.com/NikoPM/Metroidvania.git
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
