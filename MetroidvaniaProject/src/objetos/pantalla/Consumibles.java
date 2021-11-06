package objetos.pantalla;

public class Consumibles extends Graficos {
	private static final long serialVersionUID = 1L;
	private static final int ANCHURA = 0;
	private static final int ALTURA = 0;

	public Consumibles(int x, int y, String dir) {
		super(x, y, ALTURA, ANCHURA, dir);
	}
}
