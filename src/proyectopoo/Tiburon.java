/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectopoo;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author novicompu
 */
public class Tiburon extends Criatura{
    private double[] velocidades = {10,15,20,25,30};
    public static final double[][][] posicionesLabel =
        { {{125,57}} ,
          {{120,57}, {135,57}},
          {{115,57}, {130,57}, {145,57}},
          {{100,57}, {115,57}, {130,57}, {145,57}},
          {{70,57}, {85,57}, {100,57}, {115,57}, {130,57}},
          {{60,57}, {75,57}, {90,57}, {105,57}, {120,57}, {135,57}},
          {{60,57}, {75,57}, {90,57}, {105,57}, {120,57}, {135,57},{150,57}},
        };
    
    public Tiburon(ArrayList<String> palabras, double posX, double posY){
        super("src/imagenes/tiburon.gif", palabras, posX, posY, posicionesLabel[palabras.get(0).length()-1], Color.WHITE);
        this.setPalabras(palabras);
    }
    
    @Override
    public void avanzar(int nivel) {
        super.avanzar(velocidades[nivel-1]);
    }
    
    
}
