package app.client.components.inicio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InicioComponent implements ActionListener{

    private InicioTemplate inicioTemplate;

    public InicioComponent(){
        this.inicioTemplate=  new InicioTemplate(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public InicioTemplate getInicioTemplate() {
        return this.inicioTemplate;
    }

}