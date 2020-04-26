package app.client.components.barraTitulo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BarraTituloComponent implements ActionListener{

    private BarraTituloTemplate barraTituloTemplate;

    public BarraTituloComponent(){
        this.barraTituloTemplate=  new BarraTituloTemplate(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }

    public BarraTituloTemplate getBarraTituloTemplate() {
        return this.barraTituloTemplate;
    }
}