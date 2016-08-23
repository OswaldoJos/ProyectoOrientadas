/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criaturas;

import static criaturas.Tiburon.posicionesLabel;
import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 *
 * @author User 1
 */
public class MegaTiburon extends Tiburon {
    private int vidas;
    
    public MegaTiburon(ArrayList<String> palabras, double posX, double posY) {
        super("src/util/imagenes/tiburon.png", palabras, posX, posY);
        this.vidas = 2;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }
    
}
