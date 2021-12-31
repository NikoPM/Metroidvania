package datos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFrame;

/**
 * Clase Data para almacenar datos en un fichero
 * 
 */
public class Data {
	private static int tiempo = 0; //tiempo abierta la ventana
	
	// Metodo tiempoVentana que acumula el tiempo que esta la ventana abierta.
	public static void tiempoVentana(JFrame ventana) {
		
		Thread hilo = new Thread() {
			@Override
			public void run() {
				for(int i = 0; i<10; i++) {
					tiempo = tiempo + 1;
					try {
						Thread.sleep(1000);
					}catch (InterruptedException u){
						
					}
				}
				guardarEnFicheroBinario();
			}
		};
		hilo.start();
		
			
	}
	
	//guarda el tiempo en el fichero "datos.dat"
		public static void guardarEnFicheroBinario() {
			try {
				ObjectOutputStream salida = new ObjectOutputStream( new FileOutputStream( "datos.dat" ) );
				salida.writeObject(  tiempo);
				salida.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		//carga el tiempo del fichero binario "datos.dat"
		public static void cargarEnFicheroBinario() {
			try {
				ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("datos.dat"));
				tiempo = (int)entrada.readObject();
				entrada.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
}
