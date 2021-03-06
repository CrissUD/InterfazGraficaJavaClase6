package app.client.vistaPrincipal;

import app.client.components.amigos.AmigosComponent;
import app.client.components.barraTitulo.BarraTituloComponent;
import app.client.components.configuraciones.ConfiguracionesComponent;
import app.client.components.inicio.InicioComponent;
import app.client.components.navegacionUsuario.NavegacionUsuarioComponent;
import app.client.components.perfil.PerfilComponent;
import app.client.components.productos.ProductosComponent;
import app.client.login.LoginComponent;

import java.awt.Frame;

public class VistaPrincipalComponent {
  private VistaPrincipalTemplate vistaPrincipalTemplate;

  // Declaración Componentes
  private BarraTituloComponent barraTituloComponent;
  private NavegacionUsuarioComponent navegacionUsuarioComponent;
  private InicioComponent inicioComponent;
  private PerfilComponent perfilComponent;
  private AmigosComponent amigosComponent;
  private ProductosComponent productosComponent;
  private ConfiguracionesComponent configuracionesComponent;
  private LoginComponent loginComponent;

  public VistaPrincipalComponent(LoginComponent loginComponent) {
    this.loginComponent = loginComponent;
    this.vistaPrincipalTemplate = new VistaPrincipalTemplate(this);
    this.barraTituloComponent = new BarraTituloComponent(this);
    this.navegacionUsuarioComponent = new NavegacionUsuarioComponent(this);

    vistaPrincipalTemplate.getPBarra()
      .add(barraTituloComponent.getBarraTituloTemplate());
    vistaPrincipalTemplate.getPNavegacion()
      .add(navegacionUsuarioComponent.getNavegacionUsuarioTemplate());
  }

  public VistaPrincipalTemplate getVistaPrincipalTemplate() {
    return this.vistaPrincipalTemplate;
  }

  public void mostrarComponente(String comando) {
    vistaPrincipalTemplate.getPPrincipal().removeAll();
    switch (comando) {
      case "Inicio":
        if (this.inicioComponent == null)
          this.inicioComponent = new InicioComponent();
        vistaPrincipalTemplate.getPPrincipal()
          .add(inicioComponent.getInicioTemplate());
        break;
      case "Perfil":
        if (this.perfilComponent == null)
          this.perfilComponent = new PerfilComponent();
        vistaPrincipalTemplate.getPPrincipal()
          .add(perfilComponent.getPerfilTemplate());
        break;
      case "Amigos":
        if (this.amigosComponent == null)
          this.amigosComponent = new AmigosComponent();
        vistaPrincipalTemplate.getPPrincipal()
          .add(amigosComponent.getAmigosTemplate());
        break;
      case "Productos":
        if (this.productosComponent == null)
          this.productosComponent = new ProductosComponent();
        vistaPrincipalTemplate.getPPrincipal()
          .add(productosComponent.getProductosTemplate());
        break;
      case "Configuraciones":
        if (this.configuracionesComponent == null)
          this.configuracionesComponent = new ConfiguracionesComponent();
        vistaPrincipalTemplate.getPPrincipal()
          .add(configuracionesComponent.getConfiguracionesTemplate());
        break;
      case "Cerrar Sesión":
        this.loginComponent.getLoginTemplate().setVisible(true);
        this.vistaPrincipalTemplate.setVisible(false);
        break;
    }
    vistaPrincipalTemplate.repaint();
  }

  public void cerrar() {
    System.exit(0);
  }

  public void minimizar() {
    this.vistaPrincipalTemplate.setExtendedState(Frame.ICONIFIED);
  }
}