package objetos.pantalla;

public class Plataformas extends Graficos {
	private static final long serialVersionUID = 1L;
	//TODO Añadir los atributos de altura t anchura de la plataforma cuando Alvaro y Miguel cambien 
	//algunas cosas de la clase Graficos y otras
	
	/**Constructor privado de la clase Platafromas
	 * @param x	Posicion X de la plataforma en pantalla
	 * @param y	Posicion Y de la plataforma en pantalla
	 * @param dir	String con la dirección a la imagen del 
	 */
	private Plataformas (int x, int y, String dir) {
		super(x, y, dir);
	}
	
	
}
