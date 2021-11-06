package objetos.pantalla;

import javax.swing.*;

public class Consumibles extends Graficos {
	private static final long serialVersionUID = 1L;
	private static final int ANCHURA = 0;
	private static final int ALTURA = 0;

	private Consumibles(int x, int y, String dir) {
		super(x, y, ALTURA, ANCHURA, dir);
	}
	
	private JLabel crear(JFrame vent) {
		JLabel label = new JLabel(new ImageIcon(this.dirImg));
		vent.getContentPane().add(label);
		Thread hilo = new Thread() {
			@Override
			public void run() {
				for(int i = 0; i< 10; i++) {
					for(int j = 0; j<5; j++) {
						label.setLocation(posX, posY+1*j);
						try {
							Thread.sleep(70);
						} catch(InterruptedException e) {}
					}
					for(int j = 0; j<5; j++) {
						label.setLocation(posX, 5+posY-1*j);
						try {
							Thread.sleep(70);
						} catch(InterruptedException e) {}
					}
				}
			}
		};
		hilo.start();
		return label;
	}
	
	public static JLabel generar(int x, int y, String dir, JFrame vent) {
		Consumibles cons = new Consumibles(x, y, dir);
		return cons.crear(vent);
	}
}
