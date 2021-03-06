package objetos.pantalla;

import java.awt.*;
import javax.swing.*;
 
public class Personaje extends Graficos {
	private static final long serialVersionUID = 1L; //Version Serializable
	private static final int VEL_X = 5; //Velocidad en el eje X
	private static final int VEL_Y = 6; //Velocidad en el eje Y
	private static final int VEL_SHOOT = 10; //Velocidad del disparo
	private static final int DIST_SHOOT = 15; //Distancia del disparo
	private static final int HITBOX = 1; //HitBox del personaje
	private static final String[] FRAMES = //Direccion de las imagenes que componen la animacion del personaje
	{"imagenes/pers1.png", "imagenes/pers2.png"};
	private static Thread hiloX; //Hilo para el eje X del personaje
	private static Thread hiloY; //Hilo para el eje Y del personaje
	private static Personaje self; //Mismo personaje
	private static JLabel label; //Label del personaje
	private static JLabel labelShoot; //Label del disparo del personaje
	private static boolean salto = false; //Boolean que indica el salto
	private static boolean fall = false; //Boolean que indica la caida
	private static boolean shoot = false; //Boolean que indica el disparo
	private static int vida = 100; //Vida del personaje inicializada a 100

	/** Constructor Privado de objetos de clase Consumibles
	 * @param x Posicion X del consumible en pantalla
	 * @param y Posicion Y del consumible en pantalla
	 */
	private Personaje(int x, int y) {
		super(x, y, FRAMES[0], VEL_X, VEL_Y, HITBOX);
		self = this; //Se asigna a si mismo su valor
	}
	
	/** Metodo GetVida
	 * @return devuelve la vida del personaje
	 */
	public static int getVida() {
		return vida;
	}
	
	/** Metodo Estatico GetLabel
	 * @return se devuelve el label del personaje
	 */
	public static JLabel getLabel() {
		return label;
	}
	
	/** Metodo Estatico GetLabelShoot
	 * @return se devuelve el label del disparo del personaje
	 */
	public static JLabel getLabelShoot() {
		return labelShoot;
	}
	
	/** Metodo DecVida
	 * @param i cantidad que se decrementa
	 * @return vida del personaje 
	 * Decrementa la vida del personaje en i
	 */
	public static int decVida(int i) {
		if(vida - i < 0) {
			vida = 0;
		} else {
			vida -= i;
		}
		return vida;
	}
	
	/** Metodo IncVida
	 * @param i cantidad que se incrementa
	 * @return vida del personaje 
	 * Incrementa la vida del personaje en i
	 */
	public static int incVida(int i) {
		if(vida + i > 100) {
			vida = 100;
		} else {
			vida += i;
		}
		return vida;
	}
	
	/** Metodo Estatico GetPersonaje
	 * @return se devuelve a si mismo
	 */
	public static Personaje getPersonaje() {
		return self;
	}

	/** Metodo Estatico Privado LabelMove
	 * @param pers Personaje que se anima y del cual se recibe la posicion en pantalla
	 * @param label JLabel que se edita
	 * @param vent Ventana en la que se edita
	 * @param b boolean que indica si realizar la operacion de suma o resta
	 * Establece y edita la posicion del label y adem??s si se llega al limite de la ventana
	 * mueve las plataformas
	 */
	private static void labelMoveX(final Personaje pers, final JLabel label, final JFrame vent, final boolean b) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
						if(b){
							if(pers.getPosX()<400) {
								pers.setPosX(pers.getPosX() + pers.getVelX());
							} else {
								Plataformas.actualizarPos(vent, b);
								Consumibles.actualizarPos(vent, b);
							}
						} else {
							if(pers.getPosX()>100) {
								pers.setPosX(pers.getPosX() - pers.getVelX());
							} else if(pers.getPosX() == 100){
								Plataformas.actualizarPos(vent, b);
								Consumibles.actualizarPos(vent, b);
							}
						}
						label.setLocation(pers.getPosX(), pers.getPosY());
						vent.repaint();
				} catch (NullPointerException e) {}
			}
		});
	}
	 
	/** Metodo Estatico Salto
	 * @param label label que se edita
	 * @param vent Ventana en la que se edita
	 * Si el personaje no esta saltando crea un hilo que llama a LabelMoveY para saltar
	 */
	public static void salto(JLabel label) {
		if(!salto && !fall) {
			Thread hilo = new Thread(new Runnable() {
				@Override
				public void run() {
					salto = true;
					for(int i = 0; i< 20; i++) {
						try {
							LabelMoveY(getPersonaje(), label, null, true);
							Thread.sleep(15);
							if(i==19) {
								Thread.sleep(100);
							}
						} catch (InterruptedException e) {
							salto = false;
							Thread.currentThread().interrupt();	
						}
					}
					salto = false;
				}
			});
			hilo.start();
		}
	}
	
	/** Metodo Estatico Privado LabelMoveY
	 * @param pers Personaje que se edita
	 * @param label label que se edita
	 * @param vent ventana en la que se edita
	 * @param b boolean que indica si sumar o restar
	 * Establece y edita la posicion del label
	 * En el caso de que caiga, solo lo hace si no esta saltando, si hace falta que caiga 
	 * y si no colisiona con ninguna plataforma
	 */
	private static void LabelMoveY(final Personaje pers, final JLabel label, final JFrame vent, final boolean b) {
		SwingUtilities.invokeLater(new Runnable() {	
			@Override
			public void run() {
				boolean coli = false;
				for(Plataformas plat: Plataformas.getListaPlat()) {
					coli |= colision(plat);
				}
				if(!b) {
					if(/*pers.getPosY() < vent.getHeight() - 100 &&*/ !salto && !coli) { //Cae al vacio
						pers.setPosY(pers.getPosY() + pers.getVelY());
						fall = true;
					}  else {
						fall = false;
					}
				} else {
					pers.setPosY(pers.getPosY() - pers.getVelY());
				}
				label.setLocation(pers.getPosX(), pers.getPosY());
				try {
					vent.repaint();
				} catch (NullPointerException e) {}
			}
		});
	}
	
	/** Metodo Estatico Privado Animar
	 * @param pers Personaje que se edita
	 * @param label label que se edita
	 * @param vent ventana en la que se edita
	 * @param b boolean que se introduce en el metodo labelMoveX
	 * Edita la imagen del label del personaje y llama al metodo labelMoveX
	 */
	private static void animar(final Personaje pers, final JLabel label, final JFrame vent, boolean b) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if(pers.getDirImg().equals(FRAMES[0])) {
					pers.setDirImg(FRAMES[1]);
				} else {
					pers.setDirImg(FRAMES[0]);
				}
				label.setIcon(new ImageIcon(pers.getDirImg()));
				try {
					vent.repaint();
				} catch (NullPointerException e) {}
				labelMoveX(pers, label, vent, b);
			}
		});
	}
	
	/** Metodo Estatico Mover
	 * @param label label que se edita
	 * @param vent Ventana en la que se edita
	 * @param b indica si mover a la izquierda o derecha
	 * Crea un hilo que mueve al personaje en el eje Xs
	 */
	public static void mover(JLabel label, JFrame vent, boolean b) {
		if(hiloX == null) {
			hiloX = new Thread(new Runnable() {
				@Override
				public void run() {
					while(!Thread.interrupted()) {
						try {
							animar(getPersonaje(), label, vent, b);
							//labelMoveX(getPersonaje(), label, vent, b);
							Thread.sleep(35);
						} catch (InterruptedException e) {
							Thread.currentThread().interrupt();
						}
					}
				}
			});
			hiloX.start();
		}
	}
	
	/** Metodo Privado Estatico ShootLabel
	 * @param label label que se edita
	 * @param x nueva posicion en el eje X del label
	 * Mueve el label a una posicion en el eje X y la posicion en el eje Y del personaje
	 */
	private static void shootLabel(final JLabel label, int x) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				label.setLocation(x, getPersonaje().getPosY());
			}
		});
	}
	
	/** Metodo Estatico GenerarShoot
	 * @param vent Ventana en la que se edita
	 * Crea un label con una imagen, lo introduce en la ventana y lo vuelve invisible
	 */
	public static void generarShoot(JFrame vent) {
		labelShoot = new JLabel(new ImageIcon("imagenes/pelota.png"));
		vent.getContentPane().setLayout(new FlowLayout());
		vent.getContentPane().add(labelShoot);
		labelShoot.setVisible(false);
	}
	 
	/** Metodo Estatico Shoot
	 * @param vent Ventana en la que se edita
	 * @param label label que se edita
	 * Vuelve el label visible y lo dispara hacia delante del personaje para luego hacerlo desaparecer
	 */
	public static void shoot(JFrame vent, JLabel label) {
		label.setVisible(true);
		Thread hilo = new Thread(new Runnable() {
			@Override
			public void run() {
				if(!shoot) {
					shoot = true;
					for(int i = 0; i<DIST_SHOOT; i++) {
						try {
							if(i == 0) {
								shootLabel(label, getPersonaje().getPosX() + VEL_SHOOT); 
							} else {
								shootLabel(label, label.getX() + VEL_SHOOT + i);
							}
							Thread.sleep(20);
						} catch (InterruptedException e) {
							shoot = false;
						}
					}
					labelShoot.setLocation(-10000, -10000);
					shoot = false;
				} 
			}
		});
		hilo.start();
	}
	
	/** Metodo Estatico stopMover
	 * Detiene el hilo que mueve al personaje en el eje X
	 */
	public static void stopMover() {
		try {
			hiloX.interrupt();
			hiloX = null;
		} catch (NullPointerException e) {}
	}
	
	/** Metodo Estatico stopFall
	 * Detiene el hilo que mueve al personaje en el eje Y hacia abajo
	 */
	public static void stopFall() {
		try {
			hiloY.interrupt();
		} catch (NullPointerException e) {}
	}
	
	/** Metodo Estatico stopFall
	 * Llama a stopMover y stopFall y detiene ambos hilos
	 */
	public static void stopAll() {
		stopMover();
		stopFall();
	}
	
	/** Metodo Estatico Generar
	 * @param x posicion en el eje X del personaje
	 * @param y posicion en el eje Y del personaje
	 * @param dir direccion donde se encuentra la imagen del personaje
	 * @param vent ventana en la que se introduce el personaje
	 * Llama al constructor y crea un personaje, un label con la imagen del personaje
	 * lo introduce en la ventana y crea un hilo que se encarga de las caidas del personaje
	 */
	public static void generar(int x, int y, JFrame vent) {
		try {
			Personaje pers = new Personaje(x, y);
			label = new JLabel(new ImageIcon(pers.dirImg));
			vent.getContentPane().setLayout(new FlowLayout());
			vent.getContentPane().add(label);
			hiloY = new Thread(new Runnable() {
				@Override
				public void run() {
					while(!Thread.interrupted()) {
						try {
							LabelMoveY(pers, label, vent, false);
							Thread.sleep(10);
						} catch (InterruptedException e) {
							Thread.currentThread().interrupt();
						}
					}
				}
			});
			hiloY.start();
		} catch (NullPointerException e) {
			generar(x, y, vent);
		}
	}
	
	/** Metodo Estatico Colision
	 * @param graf grafico con el que compara la colision
	 * @return devuelve un booleano que indica si colisionan o no
	 * Si el grafico es una instancia de enemigo se activa la colision un poco mas facil
	 */
	public static boolean colision(Graficos graf) {
		if(graf instanceof Enemy) return (self.getPosY() > graf.getPosY() - 20 && self.getPosY() <= graf.getPosY() + 20) && (self.getPosX() >= graf.getPosX() && self.getPosX() <= graf.getPosX() + graf.getHitbox());
		return (self.getPosY() > graf.getPosY() && self.getPosY() <= graf.getPosY() + 10) && (self.getPosX() >= graf.getPosX() && self.getPosX() <= graf.getPosX() + graf.getHitbox());
	}
	
	/** Metodo Estatico ColisionShoot
	 * @param graf grafico con el que compara la colision
	 * @return devuelve un booleano que indica si colisionan o no
	 * Si el grafico es una instancia de enemigo se activa la colision un poco mas facil
	 */
	public static boolean colisionShoot(Graficos graf) {
		if(graf instanceof Enemy) return (labelShoot.getY() >= graf.getPosY() - 20 && labelShoot.getY() <= graf.getPosY() + 20) && (labelShoot.getX() >= graf.getPosX() - graf.getHitbox() && labelShoot.getX() <= graf.getPosX() + graf.getHitbox());
		return (labelShoot.getY() > graf.getPosY() && labelShoot.getY() <= graf.getPosY() + 10) && (labelShoot.getX() >= graf.getPosX() && labelShoot.getX() <= graf.getPosX() + graf.getHitbox());
	}
}
