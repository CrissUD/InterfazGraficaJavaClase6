package app.client.components.amigos;

public class AmigosComponent {
  private AmigosTemplate amigosTemplate;

  public AmigosComponent() {
    amigosTemplate = new AmigosTemplate(this);
  }

  public AmigosTemplate getAmigosTemplate() {
    return amigosTemplate;
  }
}