package app.client.components.inicio;

import javax.swing.JPanel;
import app.services.RecursosService;

public class InicioTemplate extends JPanel{

    private static final long serialVersionUID = -1137494554115287686L;
    
    // Declaración Servicios y dependencias
    private InicioComponent inicioComponent;
    private RecursosService sRecursos; 
    
    public InicioTemplate(InicioComponent inicioComponent){
        this.inicioComponent = inicioComponent;
        this.inicioComponent.getClass();
        sRecursos = RecursosService.getService();
        
        this.setSize(850, 600);
        this.setBackground(sRecursos.getColorGrisOscuro());
        this.setLayout(null);
        this.setVisible(true);
    }
}