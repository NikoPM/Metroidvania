package objetos.pantalla;

import java.util.*;
import java.util.List;
import javax.swing.*;
import java.awt.*;


public class Plataformas extends Graficos {
	private static final long serialVersionUID = 1L;
	private static final int HITBOX = 1;
	private static final int VEL_X = 3;
	private static final String FRAME = "src/imagenes/plataforma.png";
	private Thread hilo;
	private JLabel label;
	public static List<Plataformas> listaPlat = new ArrayList<>();
	
	/**Constructor privado de la clase Platafromas
	 * @param x	Posicion X de la plataforma en pantalla
	 * @param y	Posicion Y de la plataforma en pantalla
	 * @param dir	String con la dirección a la imagen del 
	 */
	private Plataformas (int x, int y) {
		super(x, y, FRAME, VEL_X, 0, HITBOX);
	}
	
	//Getters y setters 
	@Override
	public int getPosX() {
		return super.getPosX();
	}
	
	@Override
	public int getHitbox() {
		return super.getHitbox();
	}
	
	@Override
	public int getPosY() {
		return super.getPosY();
	}
	
	@Override
	public void setPosY(int posY) {
		super.setPosY(posY);
	}
	
	@Override
	public void setPosX(int posX) {
		super.setPosX(posX);
	}
	
	@Override
	public void setHitbox(int hitbox) {
		super.setHitbox(hitbox);
	}
	
	//Metodos de la clase
	private void crear(final JFrame vent) {
		Plataformas plat = this;
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				label.setLocation(plat.getPosX(), plat.getPosY());
			}
		});
	}
	
	public void startThread(JFrame vent) {
		hilo = new Thread(new Runnable() {
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
	
	/** Metodo Estatico Generar
	  * @param x Posicion X de la plataforma en pantalla
	 * @param y Posicion Y de la plataforma en pantalla
	 * @param dir Direccion en la que se encuentra la imagen(es) de la plataforma
	 * @param vent Ventana en la que se crear la plataforma
	 * @return Crear un objeto de la clase plataforma y un JLabel del mismo, 
	 * lo introduce en la ventana animandolo y devuelve el JLabel con la imagen y posicion de la plataforma
	 * LLama al constructor y al metodo crear
	 */
	public static JLabel generar(int x, int y, JFrame vent) {
		Plataformas plat = new Plataformas(x, y);
		JLabel label = new JLabel(new ImageIcon(plat.dirImg));
		plat.label = label;
		listaPlat.add(plat);
		vent.getContentPane().setLayout(new FlowLayout());
		vent.getContentPane().add(label);
		plat.startThread(vent);
		return label;
	}
	/**Metodo estatico actualizar
	 * @param vent  Ventana en la que se encuentran las plataformas
	 * @param bool  Booleano que identifica si se avanza o se retrocede
	 * Actualiza la posicion de las plataformas
	 */
	public static void actualizarPos(JFrame vent, boolean bool) {
		for(Plataformas p:listaPlat) {
			if(bool) {
				p.setPosX(p.getPosX() - VEL_X);	
			} else {
				p.setPosX(p.getPosX() + VEL_X);
			}	
			p.startThread(vent);
		}
	}
}
