package ventanas;

import java.awt.*;
import java.awt.event.*;
import java.util.Date;
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
	private VentanaJuego vent;
	private static Thread hilo;
	private static JProgressBar hpBar;
	//Fecha de inicio y fin para calcular el tiempo de partida
	private static long fechaIni;
	private static long fechaFin;
	
	private static Logger logger = Logger.getLogger("VentanaJuego");
	 
	public VentanaJuego() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(600, 500));
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
		JLabel label = Personaje.generar(500, 50, this);
		JLabel label2 = Personaje.generarShoot(this);
		//JLabel label3 = Enemigo.generar(250, 0,1,1,3,"src/imagenes/enemigo.png", this);
		//Hilo sin terminar
		JLabel plataforma = Plataformas.generar(0, 0 , this);
	
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
		
		//Listeners de teclado 
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(KeyEvent.VK_RIGHT == e.getKeyCode() || KeyEvent.VK_LEFT == e.getKeyCode()) {
					Personaje.stopMover();
					logger.log(Level.INFO, "Tecla direccion izda. pulsada");
				} else if(KeyEvent.VK_LEFT == e.getKeyCode()) {
					logger.log(Level.INFO, "Tecla direccion izda. pulsada");
				} else if(KeyEvent.VK_UP == e.getKeyCode()) {
					logger.log(Level.INFO, "Tecla direccion arriba pulsada");
				} else if(KeyEvent.VK_SPACE == e.getKeyCode()) {
					logger.log(Level.INFO, "Tecla espacio pulsada");
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
					Personaje.decVida(1); //Prueba de que funciona
					if(Personaje.getVida() == 0) { //Decrementa la vida en 1 por cada salto
						dispose(); //Si llega a 0 se cierra la ventana y acaba el juego
					}
				} else if(KeyEvent.VK_SPACE == e.getKeyCode()) {
					Personaje.shoot(vent, label2);
				}
			}
		});
		this.setVisible(true);
	}
}
