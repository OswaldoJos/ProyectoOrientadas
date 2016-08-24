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
    * Esta clase define un objeto MegaTiburon que es un tipo de criatura
    * @author Oswaldo Aguilar , Jonathan Gorotiza y Eduardo Salazar
    * @version 24/08/2016
    */
public class MegaTiburon extends Tiburon {
    
    /**
    * Constructor de MegaTiburon con tres parametros
    * @param palabras de tipo ArrayList que las palabras contenidas en el tiburon
    * @param posX de tipo double que la coordanada x del tiburon en el mar
    * @param posY de tipo double que la coordanada y del tiburon en el mar
    */
    public MegaTiburon(ArrayList<String> palabras, double posX, double posY) {
        super("src/util/imagenes/tiburon.png", palabras, posX, posY);
    }
    
}
