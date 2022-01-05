package objetos.pantalla;

import java.util.*;
import java.util.List;
import javax.swing.*;
import java.awt.*;


public class Plataformas extends Graficos {
	private static final long serialVersionUID = 1L;
	private static final int HITBOX = 1;
	private static final String FRAME = "src/imagenes/plataforma.png";
	private Thread hilo;
	public static List<Plataformas> listaPlat = new ArrayList<>();
	
	
	/**Constructor privado de la clase Platafromas
	 * @param x	Posicion X de la plataforma en pantalla
	 * @param y	Posicion Y de la plataforma en pantalla
	 * @param dir	String con la dirección a la imagen del 
	 */
	private Plataformas (int x, int y) {
		super(x, y, FRAME, 0, 0, HITBOX);
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
	private void crear(final JLabel label, final JFrame vent) {
		Plataformas plat = this;
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				label.setLocation(plat.getPosX(), plat.getPosY());
			}
		});
	}

	public static void stopThread() {
		for(Plataformas plat: listaPlat) {
			plat.hilo.interrupt();
		}
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
		listaPlat.add(plat);
		vent.getContentPane().setLayout(new FlowLayout());
		vent.getContentPane().add(label);
		return label;
	}
}
