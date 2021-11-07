package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

//Ventana que se mostrará al ejecutar el juego 
//Permite iniciar la partida, ver los controles del juego, volver al escritorio y ver el ranking
public class VentanaMenuInicio extends JFrame{
	//Atributos de la clase
		//Variables que indicarán el tamaño de la ventanas
	private static int x = 2000;	
	private static int y = 1000;
		//Variables que situarán los botones en el centro de la pantalla
	

		//Imagenes 
	private Image logo = new ImageIcon(getClass().getResource("/images/Hexagrama.jpg")).getImage();	
	
	public static void main(String[] args) {
		//Main provisional para hacer pruebas
		VentanaMenuInicio v = new VentanaMenuInicio();
		v.setVisible(true);
	}
	
	public VentanaMenuInicio() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = this.getSize();
		
		int lx = (screenSize.width-frameSize.width)/2;
		int ly = (screenSize.height-frameSize.height)/2;

		//Características de la ventana
		 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(x, y);
		setMinimumSize(new Dimension(900, 600));
		this.setLocationRelativeTo(null);
		this.setTitle("El sueño de kerman");
		 
		 
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
		
		
		bNuevaPartida.setOpaque(false);
		bNuevaPartida.setSize(400, 200);
		bRanking.setOpaque(false);
		bControles.setOpaque(false);
		bExit.setOpaque(false);
		bNuevaPartida.setBorder(bordeBoton);
		bRanking.setBorder(bordeBoton);
		bControles.setBorder(bordeBoton);
		bExit.setBorder(bordeBoton);
		
		 
		//Añadir elementos a los paneles
		 
		panelBotones.add(bNuevaPartida);
		panelBotones.add(p);
		panelBotones.add(bRanking);
		panelBotones.add(p);
		panelBotones.add(bControles);
		panelBotones.add(p);
		panelBotones.add(bExit);
		 
		//Interfaz del Menu
		 
		//Añadir componentes a la ventana
		 
		setIconImage(logo);
		panelCentral.add(panelBotones, BorderLayout.CENTER);

		add(panelCentral);
		
		 	 
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

	}
	
}
