package ventanas;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import objetos.pantalla.Consumibles;
import objetos.pantalla.Personaje;



public class VentanaJuego extends JFrame{
	
	private static JPanel barraDeVida;//Barra de vida del personaje
	private static Container con;//Contenedor donde se meterán todos los aspectos que se muestren por pantalla
	private static JPanel hp;
	private VentanaJuego vent;
	private static Thread hilo;
	private static JProgressBar hpBar;
	private static Personaje pers;
	
	public static void main(String[] args) {
		VentanaJuego v = new VentanaJuego();
	}
	
	public VentanaJuego() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(600, 500));
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);
		
		this.addWindowListener(new WindowAdapter() {
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
		
		
		barraDeVida.setBounds(0, -2,150 ,20);
		//barraDeVida.setBackground(Color.green);
		hp.setBounds(150, -5, 40, 40);
		hpBar.setPreferredSize(new Dimension(150,20));
		hpBar.setForeground(Color.green);
		hpBar.setBackground(Color.red);
		

		con.add(barraDeVida);
		con.add(hp);
		
		barraDeVida.add(hpBar);
		hp.add(lHp);
		
		JLabel label = Personaje.generar(500, 50, this);
		pers = Personaje.getPersonaje();
 
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
