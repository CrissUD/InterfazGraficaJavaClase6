package app.client.components.amigos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AmigosComponent implements ActionListener{

    private AmigosTemplate amigosTemplate;

    public AmigosComponent(){
        amigosTemplate = new AmigosTemplate(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    
    }

    public AmigosTemplate getAmigosTemplate(){
        return amigosTemplate;
    }
}