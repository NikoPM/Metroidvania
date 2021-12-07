package ventanas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.*;
import objetos.pantalla.*;

public class VentanaJuego extends JFrame {
	private static final long serialVersionUID = 1L;
	private static JPanel barraDeVida;//Barra de vida del personaje
	private static Container con;//Contenedor donde se meterán todos los aspectos que se muestren por pantalla
	private static JPanel hp;
	private VentanaJuego vent;
	private static Thread hilo;
	private static JProgressBar hpBar;
	
	public static void main(String[] args) {
		new VentanaJuego();
	}
	 
	public VentanaJuego() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(600, 500));
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				Personaje.stopAll();
				hilo.interrupt();
			}
		}); 
	
		con = getContentPane();
		barraDeVida = new JPanel();
		hp = new JPanel();
		hpBar = new JProgressBar(0,100);
		JLabel lHp = new JLabel("HP");
		
		
		lHp.setFont(new Font("Cambria", Font.BOLD, 17));
		
		
		
		hp.setBounds(150, -5, 40, 40);
		hpBar.setBounds(-100, 80, 150, 20);
		hpBar.setForeground(Color.green);
		hpBar.setBackground(Color.red);
		
		barraDeVida.add(hpBar);
		con.add(barraDeVida);
		con.add(hp);
		
		
		hp.add(lHp);
		
		JLabel label = Personaje.generar(500, 50, this);
 
		hilo = new Thread(new Runnable() {
			@Override
			public void run() {
				while(!Thread.interrupted()) {
					hpBar.setValue(Personaje.getVida());
					repaint();
				}
			}
		});
		hilo.start();
		
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(KeyEvent.VK_RIGHT == e.getKeyCode() || KeyEvent.VK_LEFT == e.getKeyCode()) {
					Personaje.stopMover();
				}
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
