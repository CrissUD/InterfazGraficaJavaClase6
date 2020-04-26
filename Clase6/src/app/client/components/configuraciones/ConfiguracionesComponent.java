package app.client.components.configuraciones;

import app.client.vistaPrincipal.VistaPrincipalComponent;

public class ConfiguracionesComponent {

    private ConfiguracionesTemplate configuracionesTemplate;
    private VistaPrincipalComponent vistaPrincipalComponent;

    public ConfiguracionesComponent(VistaPrincipalComponent vistaPrincipalComponent){
        this.vistaPrincipalComponent = vistaPrincipalComponent;
        this.vistaPrincipalComponent.getClass();
        configuracionesTemplate = new ConfiguracionesTemplate(this);
    }
    
    public ConfiguracionesTemplate getConfiguracionesTemplate(){
        return configuracionesTemplate;
    }
}