package ventanas;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//Ventana que se mostrará al ejecutar el juego 
//Permite iniciar la partida, ver los controles del juego, volver al escritorio y ver el ranking
public class VentanaMenuInicio extends JFrame{
	//Atributos de la clase
	
	private static int x = 2000;	
	private static int y = 1000;
	private Image logo = new ImageIcon(getClass().getResource("/images/Hexagrama.jpg")).getImage();
	private ImageIcon fondo = new ImageIcon(getClass().getResource("/images/Fondo1.jpg"));
	private JLabel panelFondo;
	
	public static void main(String[] args) {
		//Main provisional para hacer pruebas
		VentanaMenuInicio v = new VentanaMenuInicio();
		v.setVisible(true);
	}
	
	public VentanaMenuInicio() {
		 //Características de la ventana
		 
		 setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		 setSize(x, y);
		 setMinimumSize(new Dimension(900, 600));
		 this.setLocationRelativeTo(null);
		 this.setTitle("El sueño de kerman");
		 
		 
		 //Componentes de la pantalla
		 panelFondo = new JLabel(fondo);
		 JPanel j = new JPanel();
		 //Formato de los componentes
		 
		 //Interfaz del Menu
		 
		 //Añadir componentes a la ventana

		 add(panelFondo);
		 
		 setIconImage(logo);
		 
		 
	}

}
