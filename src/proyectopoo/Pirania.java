/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criaturas;

import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 *
 * @author Administrador
 */
public class Pirania extends Criatura{
    private double[] velocidades = {10,15,20,25,30};
    public static final double[][][] posicionesLabel =
            {{{20,20}}};
    public Pirania(ArrayList<String> palabras, double posX, double posY){
            super("src/util/imagenes/Carvanha.png", palabras, posX, posY, posicionesLabel[palabras.get(0).length()-1], Color.WHITE);
            this.setPalabras(palabras);
        }
    @Override
    public void avanzar(int nivel) {
        avanzar(velocidades[nivel-1]);
    }
}
