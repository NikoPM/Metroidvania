package main;

import java.awt.*;
import javax.swing.*;
import objetos.pantalla.*;

public class Test {

	public static void main(String[] args) {
		new Ventana();
	}
	
	static class Ventana extends JFrame {
		
		public Ventana() {
			this.setAlwaysOnTop(true);
			this.setMinimumSize(new Dimension(500, 500));
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			JLabel obj1 = Consumibles.generar(200, 200, "imagenes/pelota.png", this);
			this.setVisible(true);
		}	
	}
}
