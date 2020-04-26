package app.client.components.configuraciones;

public class ConfiguracionesComponent {

    private ConfiguracionesTemplate configuracionesTemplate;

    public ConfiguracionesComponent(){

        configuracionesTemplate = new ConfiguracionesTemplate(this);
    }
    
    public ConfiguracionesTemplate getConfiguracionesTemplate(){
        return configuracionesTemplate;
    }
}