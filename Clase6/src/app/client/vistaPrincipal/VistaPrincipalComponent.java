package app.client.vistaPrincipal;

import app.client.components.amigos.AmigosComponent;
import app.client.components.barraTitulo.BarraTituloComponent;
import app.client.components.configuraciones.ConfiguracionesComponent;
import app.client.components.inicio.InicioComponent;
import app.client.components.navegacionUsuario.NavegacionUsuarioComponent;
import app.client.components.perfil.PerfilComponent;
import app.client.components.productos.ProductosComponent;
import app.client.login.LoginComponent;

public class VistaPrincipalComponent {

    private VistaPrincipalTemplate vistaPrincipalTemplate;

    //Declaración Componentes
    private BarraTituloComponent barraTituloComponent;
    private NavegacionUsuarioComponent navegacionUsuarioComponent;
    private InicioComponent inicioComponent;
    private ConfiguracionesComponent configuracionesComponent;
    private LoginComponent loginComponent;

    public VistaPrincipalComponent(LoginComponent loginComponent){
        this.loginComponent = loginComponent;
        this.vistaPrincipalTemplate= new VistaPrincipalTemplate(this);
        this.barraTituloComponent = new BarraTituloComponent();
        this.navegacionUsuarioComponent = new NavegacionUsuarioComponent(this);
        this.inicioComponent = new InicioComponent();
        this.configuracionesComponent = new ConfiguracionesComponent(this);

        vistaPrincipalTemplate.getPBarra().add(
            barraTituloComponent.getBarraTituloTemplate()
        );
        vistaPrincipalTemplate.getPNavegacion().add(
            navegacionUsuarioComponent.getNavegacionUsuarioTemplate()
        );
        // vistaPrincipalTemplate.getPPrincipal().add(
        //     inicioComponent.getInicioTemplate()
        // );
    }

    public void mostrarPanel(String comando){
        vistaPrincipalTemplate.getPPrincipal().removeAll();
        switch(comando){
            case "Inicio":
                vistaPrincipalTemplate.getPPrincipal().add(
                    inicioComponent.getInicioTemplate()
                );
                break;
            case "Perfil":
                vistaPrincipalTemplate.getPPrincipal().add(
                    new PerfilComponent().getPerfilTemplate()
                );
                break;
            case "Amigos":
                vistaPrincipalTemplate.getPPrincipal().add(
                    new AmigosComponent().getAmigosTemplate()
                );
                break;
            case "Productos":
                vistaPrincipalTemplate.getPPrincipal().add(
                    new ProductosComponent().getProductosTemplate()
                );
                break;
            case "Configuraciones":
                vistaPrincipalTemplate.getPPrincipal().add(
                    configuracionesComponent.getConfiguracionesTemplate()
                );
                break;
            case "Cerrar Sesión":
            this.loginComponent.getLoginTemplate().setVisible(true);
            this.vistaPrincipalTemplate.setVisible(false);
                break;
        }
        vistaPrincipalTemplate.repaint();
    }

}