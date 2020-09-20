package app.client.components.inicio;

public class InicioComponent {
  private InicioTemplate inicioTemplate;

  public InicioComponent() {
    this.inicioTemplate = new InicioTemplate(this);
  }

  public InicioTemplate getInicioTemplate() {
    return this.inicioTemplate;
  }
}