package main;

import java.awt.*;
import javax.swing.*;

public class Test {

	public static void main(String[] args) {
		new Ventana();
	}
	
	static class Ventana extends JFrame {
		
		public Ventana() {
			this.setAlwaysOnTop(true);
			this.setMinimumSize(new Dimension(300, 300));
			this.setVisible(true);
		}	
	}
}
