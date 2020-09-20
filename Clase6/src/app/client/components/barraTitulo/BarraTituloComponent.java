package app.client.components.barraTitulo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.client.vistaPrincipal.VistaPrincipalComponent;

public class BarraTituloComponent implements ActionListener {
  private BarraTituloTemplate barraTituloTemplate;
  private VistaPrincipalComponent vistaPrincipalComponent;

  public BarraTituloComponent(VistaPrincipalComponent vistaPrincipalComponent) {
    this.vistaPrincipalComponent = vistaPrincipalComponent;
    this.barraTituloTemplate = new BarraTituloTemplate(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == barraTituloTemplate.getBMinimizar())
      vistaPrincipalComponent.minimizar();
    if (e.getSource() == barraTituloTemplate.getBCerrar())
      vistaPrincipalComponent.cerrar();
  }

  public BarraTituloTemplate getBarraTituloTemplate() {
    return this.barraTituloTemplate;
  }
}