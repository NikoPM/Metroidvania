package ventanas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;


//Ventana que se mostrará al ejecutar el juego 
//Permite iniciar la partida, ver los controles del juego, volver al escritorio y ver el ranking
public class VentanaMenuInicio extends JFrame{
	//Atributos de la clase
		//Variables que indicarán el tamaño de la ventanas
	private static int x = 1800;	
	private static int y = 900;
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
		//Imagenes 
	private Image logo = new ImageIcon(getClass().getResource("/images/Hexagrama.jpg")).getImage();	
	
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
			}
		});
		
		new FondoPanel().dream(this);

		//Características de la ventana
		 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(x, y);
		setMinimumSize(new Dimension(x, y));
		this.setLocationRelativeTo(null);
		this.setTitle("El sueerman");
		 
		 
		//Componentes de la pantalla
		JPanel p = new JPanel();
		JPanel panelBotones = new JPanel();
		JPanel panelCentral = new FondoPanel();
		JButton bNuevaPartida = new JButton("Nueva Partida");
		JButton bRanking = new JButton("Ranking");
		JButton bControles = new JButton("Controles");
		JButton bExit = new JButton("Volver al escritorio");
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
		bExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
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
			
			imagen = new ImageIcon(getClass().getResource("/images/Fondo1.jpg")).getImage();
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
	
}
