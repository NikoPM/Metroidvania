package objetos.pantalla;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Plataformas extends Graficos {
	private static final long serialVersionUID = 1L;
	private static final int HITBOX = 1;
	
	/**Constructor privado de la clase Platafromas
	 * @param x	Posicion X de la plataforma en pantalla
	 * @param y	Posicion Y de la plataforma en pantalla
	 * @param dir	String con la dirección a la imagen del 
	 */
	private Plataformas (int x, int y, String dir) {
		super(x, y, dir, 0, 0, HITBOX);
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
	
	/** Metodo Privado Crear
	 * @param label JLabel con la imagen de la plataforma
	 * @param vent Ventana en la que se crear la plataforma
	 * Añade el consumible al Contentpane de la ventana, edita su layout a flowlayout 
	 */
	private void crear(JLabel label, JFrame vent) {
		vent.getContentPane().setLayout(new FlowLayout());
		vent.getContentPane().add(label);
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
	public static JLabel generar(int x, int y, String dir, JFrame vent) {
		Plataformas plat = new Plataformas(x, y, dir);
		JLabel label = new JLabel(new ImageIcon(plat.dirImg));
		SwingUtilities.invokeLater(new Runnable() {	
			@Override
			public void run() {
				plat.crear(label, vent);
			}
		});
		return label;
	}
}
