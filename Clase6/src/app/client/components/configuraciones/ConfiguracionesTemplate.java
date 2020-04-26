package app.client.components.configuraciones;

import javax.swing.JPanel;
import java.awt.Color;

public class ConfiguracionesTemplate extends JPanel{

    private static final long serialVersionUID = -4187150474671099409L;

    private ConfiguracionesComponent configuracionesComponent;

    public ConfiguracionesTemplate(ConfiguracionesComponent configuracionesComponent) {

        this.configuracionesComponent = configuracionesComponent;
        this.configuracionesComponent.getClass();
        
        this.setSize(850, 600);
        this.setBackground(Color.ORANGE);
        this.setLayout(null);
        this.setVisible(true);
    }
}