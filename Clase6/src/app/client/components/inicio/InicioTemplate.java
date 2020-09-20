package app.client.components.inicio;

import java.awt.Color;

import javax.swing.JPanel;

public class InicioTemplate extends JPanel {
  private static final long serialVersionUID = 1L;

  // Declaración Servicios y dependencias
  private InicioComponent inicioComponent;

  public InicioTemplate(InicioComponent inicioComponent) {
    this.inicioComponent = inicioComponent;
    this.inicioComponent.getClass();

    this.setSize(850, 600);
    this.setBackground(Color.DARK_GRAY);
    this.setLayout(null);
    this.setVisible(true);
  }
}