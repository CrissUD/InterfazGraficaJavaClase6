package app.client.components.productos;

import javax.swing.JPanel;
import java.awt.Color;

public class ProductosTemplate extends JPanel {
  private static final long serialVersionUID = 1L;

  private ProductosComponent productosComponent;

  public ProductosTemplate(ProductosComponent productosComponent) {
    this.productosComponent = productosComponent;
    this.productosComponent.getClass();

    this.setSize(850, 600);
    this.setBackground(Color.YELLOW);
    this.setLayout(null);
    this.setVisible(true);
  }
}