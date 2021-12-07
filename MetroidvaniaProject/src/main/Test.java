package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import objetos.pantalla.*;

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
		private Ventana vent = this;
		
		public Ventana() {
			this.setAlwaysOnTop(true);
			this.setMinimumSize(new Dimension(1000, 1000));
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					Personaje.stopAll();
					Consumibles.stopAll();
				}
			}); 
			Consumibles.generar(40, 100, "src/imagenes/pelota.png", this);
			Consumibles.generar(30, 10, "src/imagenes/pelota.png", this);
			Plataformas.generar(50, 100, "src/imagenes/plataforma.png", this);
			JLabel label = Personaje.generar(50, 50, this);
			this.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					Personaje.stopMover();
				}  
				@Override
				public void keyPressed(KeyEvent e) {
					if(KeyEvent.VK_RIGHT == e.getKeyCode()) {
						Personaje.mover(label, vent, true);
					} else if(KeyEvent.VK_LEFT == e.getKeyCode()) {
						Personaje.mover(label, vent, false);
					} else if(KeyEvent.VK_UP == e.getKeyCode()) {
						Personaje.salto(label);
					} else if(KeyEvent.VK_SPACE == e.getKeyCode()) {
						//Personaje.shoot(vent);
					}
				}
			});
			this.setVisible(true);
		}	
	}
	
}