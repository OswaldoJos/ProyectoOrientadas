/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criaturas;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
    * Esta clase define un objeto Tiburon que es un tipo de criatura
    * @author Oswaldo Aguilar , Jonathan Gorotiza y Eduardo Salazar
    * @version 24/08/2016
    */
public class Tiburon extends Criatura{
    //atributos de la clase
    
    /**
     * Arreglo de velocidades del tiburon por cada nivel
     */
    private double[] velocidades = {10,15,20,25,30};
    
    /**
     * Arreglo de posiciones del tiburon por tamanio de la palabra
     */
    public static final double[][][] posicionesLabel =
        { {{125,57}} ,
          {{120,57}, {135,57}},
          {{115,57}, {130,57}, {145,57}},
          {{100,57}, {115,57}, {130,57}, {145,57}},
          {{70,57}, {85,57}, {100,57}, {115,57}, {130,57}},
          {{60,57}, {75,57}, {90,57}, {105,57}, {120,57}, {135,57}},
          {{60,57}, {75,57}, {90,57}, {105,57}, {120,57}, {135,57},{150,57}},
        };
    
    /**
    * Constructor de Tiburon con tres parametros
    * @param palabras de tipo ArrayList que las palabras contenidas en el tiburon
    * @param posX de tipo double que la coordanada x del tiburon en el mar
    * @param posY de tipo double que la coordanada y del tiburon en el mar
    */
    public Tiburon(ArrayList<String> palabras, double posX, double posY){
        super("src/util/imagenes/tiburonBlanco.png", palabras, posX, posY, posicionesLabel[palabras.get(0).length()-1], Color.DARKBLUE);
        this.setPalabras(palabras);
    }
    
    /**
    * Constructor de Tiburon con cuator parametros
    * @param rutaImagen de tipo String que representa la ruta de la imagen, puede ser negro o blanco
    * @param palabras de tipo ArrayList que las palabras contenidas en el tiburon
    * @param posX de tipo double que la coordanada x del tiburon en el mar
    * @param posY de tipo double que la coordanada y del tiburon en el mar
    */
    public Tiburon(String rutaImagen, ArrayList<String> palabras, double posX, double posY) {
        super(rutaImagen, palabras, posX, posY, posicionesLabel[palabras.get(0).length()-1], Color.VIOLET);
    }
    
    /**
    * Metodo que mueve el tiburon con todos sus componentes en mar un valor dependiendo del nivel
    * @param nivel de tipo int que representa el nivel del mar
    */
    @Override
    public void avanzar(int nivel) {
        super.avanzar(velocidades[nivel-1]);
    }
    
    
}
