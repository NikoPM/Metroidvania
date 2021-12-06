package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import objetos.pantalla.Character;
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
		
		public Ventana() {
			this.setAlwaysOnTop(true);
			this.setMinimumSize(new Dimension(500, 500));
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					Character.stopAll();
				}
			});
			Consumibles.generar(40, 100, "src/imagenes/pelota.png", this);
			Consumibles.generar(30, 10, "src/imagenes/pelota.png", this);
			Plataformas.generar(50, 100, "src/imagenes/plataforma.png", this);
			JLabel label = Character.generar(50, 50, "src/imagenes/pelota.png", this);
			this.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					Character.stopAnimar();
				} 
				@Override
				public void keyPressed(KeyEvent e) {
					if(KeyEvent.VK_RIGHT == e.getKeyCode()) {
						Character.animar(label, true);
					} else if(KeyEvent.VK_LEFT == e.getKeyCode()) {
						Character.animar(label, false);
					} else if(KeyEvent.VK_UP == e.getKeyCode()) {
						Character.animar(label, null);
					}
				}
			});
			this.setVisible(true);
		}	
	}
	
}