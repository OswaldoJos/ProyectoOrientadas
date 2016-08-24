/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criaturas;

import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
    * Esta clase define un objeto Pirania que es un tipo de criatura
    * @author Oswaldo Aguilar , Jonathan Gorotiza y Eduardo Salazar
    * @version 24/08/2016
    */
public class Pirania extends Criatura{
    /**
     * Arreglo de velocidades de la pirania por cada nivel
     */
    private double[] velocidades = {15,25,30,35,40};
    /**
     * Arreglo de doubles que reprensenta las posiciones de los labels en el mar
     */
    public static final double[][][] posicionesLabel =
            {{{60,30}}};
    
    /**
    * Constructor de Pirania con tres parametros
    * @param palabras de tipo ArrayList que las palabras contenidas en el tiburon
    * @param posX de tipo double que la coordanada x del tiburon en el mar
    * @param posY de tipo double que la coordanada y del tiburon en el mar
    */
    public Pirania(ArrayList<String> palabras, double posX, double posY){
        super("src/util/imagenes/Carvanha.png", palabras, posX, posY, posicionesLabel[palabras.get(0).length()-1], Color.BLUE);
        this.setPalabras(palabras);
    }
    
    /**
    * Metodo que mueve la Pirania con todos sus componentes en mar un valor dependiendo del nivel
    * @param nivel de tipo int que representa el nivel del mar
    */
    @Override
    public void avanzar(int nivel) {
        avanzar(velocidades[nivel-1]);
    }
}
