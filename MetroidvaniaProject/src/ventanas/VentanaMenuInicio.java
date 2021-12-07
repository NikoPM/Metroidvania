package ventanas;

import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.border.*;



//Ventana que se mostrará al ejecutar el juego 
//Permite iniciar la partida, ver los controles del juego, volver al escritorio y ver el ranking
public class VentanaMenuInicio extends JFrame{
	//Atributos de la clase
		//Variables que indicarán el tamaño de la ventanas
	private static int x = 1800;	
	private static int y = 900;
		//Botones
	private static JButton bNuevaPartida = new JButton("Nueva partida");
	private static JButton bExit = new JButton("Volver al escritorio");
		//Variables que comprueban el estado de la ventana
	private static boolean isOpen = true;
		//Variables para los botones
	private static int lx = 700;
	private static int ly = 100;
	private static int espacio = 200;
	private static int altura = 100;
	private static int anchura = 500;
	private static Color color = Color.LIGHT_GRAY; 
	private static Font letra = new Font("Cambria", Font.BOLD, 17);
	private static String nombre;
		//Imagenes 
	private Image logo = new ImageIcon("src/imagenes/Hexagrama.jpg").getImage();
		//Logger de la ventana principal
	private static Logger logger = Logger.getLogger("VentanaMenuInicio");
	
	/*
	public static void main(String[] args) {
		//Main provisional para hacer pruebas
		VentanaMenuInicio v = new VentanaMenuInicio();
		v.setVisible(true);
	}
	*/
	
	public VentanaMenuInicio() {
		//WindowOpen o Closed checker
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				isOpen = false;
				logger.log(Level.INFO, "Ventana cerrada");
			}
		});
		
		new FondoPanel().dream(this);

		//Características de la ventana
		 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(x, y));
		this.setLocationRelativeTo(null);
		this.setTitle("El sueerman");
		 
		 
		//Componentes de la pantalla
		JPanel p = new JPanel();
		JPanel panelBotones = new JPanel();
		JPanel panelCentral = new FondoPanel();
		JButton bRanking = new JButton("Ranking");
		JButton bControles = new JButton("Controles");
		Border bordeBoton = BorderFactory.createLineBorder(Color.RED);
		 
		//Formato de los componentes
		 
		panelBotones.setLayout(new GridLayout(8, 1));
		panelBotones.setOpaque(false);
		p.setOpaque(false);

			//Situar los botones y establecer su tamaño
		bNuevaPartida.setBounds(lx, ly, anchura,altura );
		ly += espacio;
		bRanking.setBounds(lx, ly, anchura, altura);
		ly += espacio;
		bControles.setBounds(lx, ly, anchura, altura);
		ly += espacio;
		bExit.setBounds(lx, ly, anchura, altura);
		
			//Borde de los botones
		bNuevaPartida.setBorder(bordeBoton);
		bRanking.setBorder(bordeBoton);
		bControles.setBorder(bordeBoton);
		bExit.setBorder(bordeBoton);
		
			//Fuente de los botones
		bNuevaPartida.setFont(letra);
		bRanking.setFont(letra);
		bControles.setFont(letra);
		bExit.setFont(letra);
			//Color de los botones
		bNuevaPartida.setBackground(color);
		bRanking.setBackground(color);
		bControles.setBackground(color);
		bExit.setBackground(color);
		//Añadir componentes a la ventana
		 
		setIconImage(logo);
		add(bNuevaPartida);
		add(bRanking);
		add(bControles);
		add(bExit);
		add(panelCentral);
		
		//ActionListeners
		funcionBotones();
	}
	
	//Métodos get
	public static JButton getNuevaPartida() {
		return bNuevaPartida;
	}
	
	public static JButton getbExit() {
		return bExit;
	}
	
	//Métodos y clases que ayudan al correcto comportamiento del programa
	
	/**Añade los action listeners con sus respectivas funciones a cada JButton
	 * de la ventana
	 */
	public void funcionBotones() {
		//Permite acceder al juego y crea un usuario
		bNuevaPartida.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				logger.log(Level.INFO, "Botón accionado: Nueva Partida");
				String nombre = JOptionPane.showInputDialog("Nombre de usuario: ");
				VentanaJuego v = new VentanaJuego();
				v.setVisible(true);
				dispose();
				
			}
		});
		
		//Cierra la ventana y vuelve al escritorio
		bExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				logger.log(Level.INFO, "Botón accionado: bExit");
				try {
					logger.log(Level.INFO, "Juego terminado");
					dispose();
				}catch (Exception error) {
					logger.log(Level.SEVERE, "No se puede cerrar la ventana");
				}
										
			}
		});
	}
	
	/**Clase externa que nos permite dibujar el fondo de la paantalla sin que
	 * este se dibuje encima de los demás elementos
	 * @author Nicolás
	 */
	public class FondoPanel extends JPanel{
		private Image imagen;
		@Override
	    public void paint(Graphics g) {
			
			imagen = new ImageIcon("src/imagenes/Fondo1.jpg").getImage();
	        g.drawImage(imagen, 0, 0, getWidth(), getHeight(),
	                        this);
	 
	        setOpaque(false);
	        super.paint(g);
	    }
		
		
		private void dream(VentanaMenuInicio vent) {
			Thread hilo = new Thread() {
				@Override
				public void run() {
					while(vent.isOpen) {
						vent.setTitle("El sueño de kerman");
					}
				}
			};
			hilo.start();
		}

	}
	
	 /**Metodo getNombre de VentanaMenuInicio
	  * @return nombre del usuario que se ha pedido al iniciar partida
	  */
	 public static String getNombre() {
		return nombre;
	}
	
}
