package app.client.components.amigos;

import javax.swing.JPanel;
import java.awt.Color;

public class AmigosTemplate extends JPanel {
  private static final long serialVersionUID = 1L;

  private AmigosComponent amigosComponent;

  public AmigosTemplate(AmigosComponent amigosComponent) {

    this.amigosComponent = amigosComponent;
    this.amigosComponent.getClass();

    this.setSize(850, 600);
    this.setBackground(Color.GREEN);
    this.setLayout(null);
    this.setVisible(true);
  }
}