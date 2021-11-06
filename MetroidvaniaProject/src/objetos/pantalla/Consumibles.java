package objetos.pantalla;

import java.awt.*;
import javax.swing.*;

public class Consumibles extends Graficos {
	private static final long serialVersionUID = 1L;
	private static final int ANCHURA = 0;
	private static final int ALTURA = 0;

	public Consumibles(int x, int y, String dir) {
		super(x, y, ALTURA, ANCHURA, dir);
	}
	//DE MOMENTO ESTO NO FUNCIONA, ES PARA MOSTRAR Y CREAR UNA IMAGEN/OBJETO POR VENTANA
	public JLabel crear(JFrame vent) {
		JLabel label = new JLabel(new ImageIcon(this.dirImg));
		label.setLocation(new Point(this.posX, this.posY));
		vent.getContentPane().add(label);
		return label;
	}
	
	public static JLabel generar(int x, int y, String dir, JFrame vent) {
		Consumibles cons = new Consumibles(x, y, dir);
		return cons.crear(vent);
	}
}
