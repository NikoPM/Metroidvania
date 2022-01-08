package ventanas;

import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import datos.BaseDeDatos;
import datos.Usuario;
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
	private JFrame ventana = this;
	private int numEnemigos = 3;
	
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
		Personaje.generar(500, 50, this);
		Personaje.generarShoot(this);
		Personaje.shoot(ventana, Personaje.getLabelShoot());
	
		//Hilo sin terminar
		//JLabel label3 = Enemigo.generar(250, 0,1,1,3,"src/imagenes/enemigo.png", this);
		//Creacion Consumibles
		Consumibles.generar(150, 380, this);
		//Creacion de plataformas
		Plataformas.generar(120, 380, this);
		Plataformas.generar(300, 300 , this);
		Plataformas.generar(500, 250, this);
		Plataformas.generar(750, 300, this);
		Plataformas.generar(1000, 350, this);
		Thread hilo2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(Plataformas plat: Plataformas.getListaPlat()) {
					plat.startThread(ventana);
				}
			}
		});
		hilo2.start();
	
		hilo = new Thread(new Runnable() {
			@Override
			public void run() {
				while(!Thread.interrupted()) {
					generarEnemy();
					hpBar.setValue(Personaje.getVida());
					repaint();
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
	 *  Cierra la ventana y si el jugador acepta, abre una ventana menu.
	 */
	private void gameOver() {
		JOptionPane.showMessageDialog(con, "Juego terminado.", "Game Over", JOptionPane.INFORMATION_MESSAGE);
		this.dispose();
	}
	
	/**Metodo privado generarEnemy
	 * Comprueba si el numero de enemigos es el correcto, sino genera el numero de enemigos que
	 * faltan por pantalla
	 */
	private void generarEnemy() {
		int contador = 0;
		for(Enemy e: Enemy.getListaEne()) {
			if(!e.muerto) {
				contador++;
			}
		}
		if(contador<numEnemigos) {
			for(int i =0; i<numEnemigos-contador; i++) {
				int posXY = new Random().nextInt(1000);	
				Enemy.generar(posXY, posXY, this);
				logger.log(Level.INFO, "enemigo generado");
			}
		}
	}
}
