package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import objetos.pantalla.Character;

public class Test {
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
		private static Thread hilo;

		public Ventana() {
			this.setAlwaysOnTop(true);
			this.setMinimumSize(new Dimension(500, 500));
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			//Consumibles.generar(40, 100, "src/imagenes/pelota.png", this);
			//Consumibles.generar(30, 10, "src/imagenes/pelota.png", this);
			JLabel label = Character.generar(50, 50, "src/imagenes/pelota.png", this);
			addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					hilo.interrupt();
				} 
				
				@Override
				public void keyPressed(KeyEvent e) {
					if(KeyEvent.VK_RIGHT == e.getKeyCode()) {
						hilo = Character.mover(label, true);
						hilo.start();
					} else if(KeyEvent.VK_RIGHT == e.getKeyCode()) {
						hilo = Character.mover(label, false);
						hilo.start();
					}
				}
			});
			this.setVisible(true);
		}	
	}
	
}