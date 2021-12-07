package ventanas;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import objetos.pantalla.Personaje;



public class VentanaJuego extends JFrame{
	
	private static JPanel barraDeVida;//Barra de vida del personaje
	private static Container con;//Contenedor donde se meterán todos los aspectos que se muestren por pantalla
	private static JPanel hp;
	
	public static void main(String[] args) {
		VentanaJuego v = new VentanaJuego();
		v.setVisible(true);
	}
	
	public VentanaJuego() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600, 500);
		this.setLocationRelativeTo(null);
		
		
		con = new Container();
		barraDeVida = new JPanel();
		hp = new JPanel();
		JProgressBar hpBar = new JProgressBar(0,100);
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
		
		add(con);
		
		
		//
		addKeyListener(new KeyListener() {	
			@Override
			public void keyTyped(KeyEvent e) {	
			}
	
			@Override
			public void keyReleased(KeyEvent e) {
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(KeyEvent.VK_RIGHT == e.getKeyCode()) {
					hpBar.setValue(70);
					repaint();
				}else if(KeyEvent.VK_LEFT == e.getKeyCode()) {
					hpBar.setValue(25);
				}
				
			}
		});
		
	}
}
