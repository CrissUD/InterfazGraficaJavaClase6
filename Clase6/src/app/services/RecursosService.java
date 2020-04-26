package app.services;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class RecursosService {

    private Color colorAzul, colorMorado, colorGrisOscuro, colorGrisClaro;
    private Font fontTPrincipal, fontTitulo, fontSubtitulo;
    private Font fontBotones, fontPequeña;
    private Cursor cMano;
    private Border borderInferiorAzul;
    private ImageIcon iCerrar;

    static private RecursosService servicio;

    private RecursosService(){
        colorMorado = new Color(151, 0, 158);
        colorAzul = new Color(60, 78, 120);
        colorGrisOscuro = new Color(80, 80, 80);
        colorGrisClaro = new Color(249, 246, 249);
        fontTPrincipal = new Font("Rockwell Extra Bold", Font.PLAIN, 20);
        fontTitulo = new Font("LuzSans-Book", Font.BOLD, 17);
        fontSubtitulo = new Font("Forte", Font.PLAIN, 13);
        fontBotones = new Font("LuzSans-Book", Font.PLAIN, 15);
        fontPequeña= new Font("LuzSans-Book", Font.PLAIN, 13);
        cMano = new Cursor(Cursor.HAND_CURSOR);
        borderInferiorAzul = BorderFactory.createMatteBorder(0, 0, 2, 0, colorAzul);
        iCerrar = new ImageIcon("Clase6/resources/img/cerrar.png");
    }
    
    public Color getColorMorado(){
        return colorMorado;
    }

    public Color getColorAzul(){
        return colorAzul;
    }

    public Color getColorGrisOscuro(){
        return colorGrisOscuro;
    }

    public Color getColorGrisClaro(){
        return colorGrisClaro;
    }

    public Font getFontTPrincipal(){
        return fontTPrincipal;
    }

    public Font getFontTitulo(){
        return fontTitulo;
    }
    
    public Font getFontSubtitulo(){
        return fontSubtitulo;
    }
    public Font getFontBotones(){
        return fontBotones;
    }

    public Font getFontPequeña(){
        return fontPequeña;
    }

    public Cursor getCMano(){
        return cMano;
    }

    public Border getBorderInferiorAzul(){
        return borderInferiorAzul;
    }

    public ImageIcon getICerrar(){
        return iCerrar;
    }

    public static RecursosService getService(){
        if(servicio == null){
            servicio = new RecursosService();
        }
        return servicio;
    }
}