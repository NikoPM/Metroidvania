package main;

import java.awt.*;
import javax.swing.*;
import objetos.pantalla.*;

public class TestEnemigo {
	//Test de mostrar un consumible por ventana y animarlo un poco
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Ventana();
			}
		});
	}
	static class Ventana extends JFrame {
		private static final long serialVersionUID = 1L;

		public Ventana() {
			this.setAlwaysOnTop(true);
			this.setMinimumSize(new Dimension(1280, 720));
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			Enemigo.generar(250, 0,2,2,3,"src/imagenes/enemigo.png", this);
			Enemigo.generar(500, 100,4,4,3,"src/imagenes/enemigo.png", this);
			Enemigo.generar(750, 0,2,2,3,"src/imagenes/enemigo.png", this);
			Enemigo.generar(1000, 100,2,2,3,"src/imagenes/enemigo.png", this);
			this.setVisible(true);
		}	
	}
}
