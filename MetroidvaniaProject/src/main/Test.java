package main;

import java.awt.*;
import javax.swing.*;
import objetos.pantalla.*;

public class Test {
	//Test de mostrar un consumible por ventana y animarlo un poco
	public static void main(String[] args) {
		new Ventana();
	}
	static class Ventana extends JFrame {
		private static final long serialVersionUID = 1L;

		public Ventana() {
			this.setAlwaysOnTop(true);
			this.setMinimumSize(new Dimension(500, 500));
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			Consumibles.generar(40, 100, "src/images/pelota.png", this);
			Consumibles.generar(30, 10, "src/images/pelota.png", this);
			Consumibles.generar(60, 20, "src/images/Hexagrama.jpg", this);
			this.setVisible(true);
		}	
	}
}
