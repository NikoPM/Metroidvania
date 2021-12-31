package ventanas;

import java.awt.*;
import javax.swing.*;

public class PanelImagen extends javax.swing.JPanel {
private static final long serialVersionUID = 1L;
public PanelImagen() {
  this.setSize(1280, 720);
 }

 @Override
 public void paintComponent(Graphics g) {
  Dimension tamanio = getSize();
  ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/imagenes/controles.png"));
  g.drawImage(imagenFondo.getImage(), 0, 0, tamanio.width, tamanio.height, null);
  setOpaque(false);
  super.paintComponent(g);
 }
}