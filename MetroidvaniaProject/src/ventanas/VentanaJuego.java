package ventanas;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.logging.*;
import datos.*;
import javax.swing.*;
import objetos.pantalla.*;

public class VentanaJuego extends JFrame {
	private static final long serialVersionUID = 1L;
	private static JPanel barraDeVida;//Barra de vida del personaje
	private static Container con;//Contenedor donde se meterán todos los aspectos que se muestren por pantalla
	private static JPanel hp;
	private static Thread hilo;
	private static JProgressBar hpBar;
	//Fecha de inicio y fin para calcular el tiempo de partida
	private static long fechaIni;
	private static long fechaFin;
	private static JFrame ventana;
	
	private static Logger logger = Logger.getLogger("VentanaJuego");
	 
	public VentanaJuego() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(600, 500));
		setResizable(false);
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				fechaIni = new Date().getTime();
			}
			@Override 
			public void windowClosed(WindowEvent e) {
				Personaje.stopAll();
				Consumibles.stopAll();
				Enemy.stopAll();
				hilo.interrupt();
				fechaFin = new Date().getTime();
				Usuario usuario = new Usuario(1, VentanaMenuInicio.getNombre(), fechaFin-fechaIni);
				BaseDeDatos.abrirConexion("usuarios.db", false);
				BaseDeDatos.insertarUsuario(usuario);
				BaseDeDatos.cerrarConexion();
				logger.log(Level.INFO, "Juego terminado");
			}
		}); 
	
		//Creacion de los componentes
		con = getContentPane();
		barraDeVida = new JPanel();
		hp = new JPanel();
		hpBar = new JProgressBar(0,100);
		JLabel lHp = new JLabel("HP");
		ventana = this;
		
		//Atributos de los componentes
		lHp.setFont(new Font("Cambria", Font.BOLD, 17));
		hp.setBounds(150, -5, 40, 40);
		hpBar.setBounds(-100, 80, 150, 20);
		hpBar.setForeground(Color.green);
		hpBar.setBackground(Color.red);
		
		
		//Add
		barraDeVida.add(hpBar);
		con.add(barraDeVida);
		con.add(hp);	
		hp.add(lHp);
		
		
		
		//Creacion del personaje
		Personaje.generar(200, 50, this);
		Personaje.generarShoot(this);
		Personaje.shoot(ventana, Personaje.getLabelShoot());
		//Creacion Enemys
		Enemy.generar(-100, 600, this);
		Enemy.generar(-100, -100, this);
		Enemy.generar(1000, 700, this);
		Enemy.generar(1000, -100, this);
		//Creacion Consumibles
		Consumibles.generar(1500, 150, this, false);
		Consumibles.generar(10100, 380, this, true);
		//Creacion de plataformas
		Plataformas.generar(120, 380, this);
		Plataformas.generar(300, 300 , this);
		Plataformas.generar(500, 250, this);
		Plataformas.generar(750, 300, this);
		Plataformas.generar(1000, 350, this);
		Plataformas.generar(1200, 250, this);
		Plataformas.generar(1400, 150, this);
		Plataformas.generar(10000, 380, this);
		//Creacion del Fondo
		Fondo.generar(this);
		
		hilo = new Thread(new Runnable() {
			@Override
			public void run() {
				while(!Thread.interrupted()) {
					generarPlat();
					if(Personaje.getPersonaje().getPosY() >= 404) Personaje.decVida(100); //Caer al vacio
					hpBar.setValue(Personaje.getVida());
					if(Personaje.getVida() == 0) { 
						gameOver();
						hilo.interrupt();
					}
				}
			}
		});
		hilo.start();
		
		//Listeners de teclado 
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(KeyEvent.VK_RIGHT == e.getKeyCode() || KeyEvent.VK_LEFT == e.getKeyCode()) {
					Personaje.stopMover();
					logger.log(Level.INFO, "Tecla direccion pulsada");
				} else if(KeyEvent.VK_UP == e.getKeyCode()) {
					logger.log(Level.INFO, "Tecla direccion arriba pulsada");
				} else if(KeyEvent.VK_SPACE == e.getKeyCode()) {
					logger.log(Level.INFO, "Tecla espacio pulsada");
				}
			}  
			@Override
			public void keyPressed(KeyEvent e) {
				if(KeyEvent.VK_RIGHT == e.getKeyCode()) {
					Personaje.mover(Personaje.getLabel(), ventana, true);
				} else if(KeyEvent.VK_LEFT == e.getKeyCode()) {
					Personaje.mover(Personaje.getLabel(), ventana, false);
				} else if(KeyEvent.VK_UP == e.getKeyCode()) {
					Personaje.salto(Personaje.getLabel());
					//Personaje.decVida(1); 
				} else if(KeyEvent.VK_SPACE == e.getKeyCode()) {
					Personaje.shoot(ventana, Personaje.getLabelShoot());
				}
			}
		});
		this.setVisible(true);
	}
	
	/** Metodo Privado GameOver
	 *  Cierra la ventana.
	 */
	private void gameOver() {
		JOptionPane.showMessageDialog(con, "Juego terminado.", "Game Over", JOptionPane.INFORMATION_MESSAGE);
		dispose();
	}
	
	/** Metodo Privado GameWin
	 *  Cierra la ventana.
	 */
	public static void gameWin() {
		JOptionPane.showMessageDialog(con, "Has ganado.", "Victory", JOptionPane.INFORMATION_MESSAGE);
		ventana.dispose();
	}
	
	/**
	 * Medoto generarPlat
	 * Cambia la posicion de las plataformas al llegar al extremo izquierdo. 
	 */
	private void generarPlat() {
		for(Plataformas p: Plataformas.getListaPlat()) {
			if(p.getPosX() <= -500) {
				p.setPosX(Plataformas.getPosMax() + new Random().nextInt(40) - new Random().nextInt(40));
				p.setPosY(p.getPosY() + new Random().nextInt(20) - new Random().nextInt(10));
				if(p.getPosY() > 380) p.setPosY(380);
			}
		}
		for(Consumibles cons: Consumibles.getListaCons()) {
			if(cons.getPosX() <= -10) {
				int num = new Random().nextInt(Plataformas.getListaPlat().size());
				cons.setPosX(Plataformas.getListaPlat().get(num).getPosX() + 30 + new Random().nextInt(100));
				cons.setPosY(Plataformas.getListaPlat().get(num).getPosY());
			}
		}
	}
	
}
