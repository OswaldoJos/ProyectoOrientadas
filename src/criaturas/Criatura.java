/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criaturas;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
    * Esta clase define un objeto Criatura y es abstracto de ella heredaran las otras ciraturas
    * @author Oswaldo Aguilar , Jonathan Gorotiza y Eduardo Salazar
    * @version 24/08/2016
    */
public abstract class Criatura {
    /**
     * Imagen de la criatura para ponerla en el mar
     */
    private ImageView imagen;
     /**
     * palabras que continene la ciratura para ser eliminadas
     */
    private ArrayList<String> palabras ;
     /**
     * Arreglo de Label que representa cada letra de la palabra actual
     */
    private Label[] texto;
     /**
     * Bandera de referencia a si ya etsa escribiendo en esta criatura
     */
    private boolean marcada;
     /**
     * Bandera que representa si la ultima letra fue correct
     */
    private boolean correcta;

    
    /**
    * Constructor de Tiburon con seis parametros}
    * @param rutaImagen de tipo String que nos da la ruta de la iamgne
    * @param palabras de tipo ArrayList que las palabras contenidas en el tiburon
    * @param posX de tipo double que la coordanada x del tiburon en el mar
    * @param posY de tipo double que la coordanada y del tiburon en el mar
     * @param posiciones nos indica la posicon de la criatura el mar
     * @param color nos indica la coloracion del texto en la criatura
    */
    public Criatura(String rutaImagen, ArrayList<String> palabras, double posX, double posY, double[][] posiciones, Color color) {
        File file = new File(rutaImagen);
        Image image = new Image(file.toURI().toString());
        this.imagen = new ImageView(image);
        imagen.setFitHeight(150);
        imagen.setFitWidth(250);
        this.palabras = palabras;
        System.out.println(this.palabras.get(0));
        this.imagen.setLayoutX(posX);
        this.imagen.setLayoutY(posY);
        this.crearLabels(this.palabras.get(0), posiciones, color);
        this.marcada = false;
        this.correcta = true;
    }
    
    /**
     * Metodo que devuelve la imagen de la criatura
     * @return imagen de tipo ImageView
     */
    public ImageView getImagen() {
        return imagen;
    }
    /**
     * Metodo que actualiza la imagen de la criatura
     * @param imagen de tipo ImageView
     */
    public void setImagen(ImageView imagen) {
        this.imagen = imagen;
    }
    /**
     * Metodo que devuelve lel arreglo de plabras 
     * @return palabras 
     */
    public ArrayList<String> getPalabras() {
        return palabras;
    }
    /**
     * Metodo que actualiza lel arreglo de plabras 
     * @param palabras  son las palabras
     */
    public void setPalabras(ArrayList<String> palabras) {
        this.palabras = palabras;
    }
    
    /**
     * Metodo que el arreglo de labels de cada palabra 
     * @return texto 
     */
    public Label[] getTexto() {
        return texto;
    }

    /**
     * Metodo que el actualiza de labels de cada palabra 
     * @param texto reprensta el texto
     */
    public void setTexto(Label[] texto) {
        this.texto = texto;
    }
    
    /**
     * Metodo que devuelve si una criatura esta marcada
     * @return marcada 
     */
    public boolean isMarcada() {
        return marcada;
    }
    
    /**
     * Metodo que actualiza si una criatura esta marcada
     * @param marcada  representa la marca
     */
    public void setMarcada(boolean marcada) {
        this.marcada = marcada;
    }
    
    /**
     * Metodo que devuelve si la ultima letra tipeada fue correcta
     * @return correcta 
     */
    public boolean isCorrecta() {
        return correcta;
    }
    
    /**
     * Metodo que actualiza si la ultima letra tipeada fue correcta
     * @param correcta representa la correctitud
     */
    public void setCorrecta(boolean correcta) {
        this.correcta = correcta;
    }
   
    /**
    * Metodo que mueve la criatura con todos sus componentes en mar un valor dependiendo del nivel
    * @param velocidad de tipo double
    */
    public void avanzar(double velocidad){
        this.imagen.setLayoutX(this.imagen.getLayoutX() - velocidad);
        for(int i = 0; i < texto.length; i++){
           texto[i].setLayoutX(this.texto[i].getLayoutX() - velocidad);
        }
    }
    /**
    * Metodo que crea los labels de cada palabra
     * @param palabra de tipo String palabra actual en la criatura
     * @param posiciones arreglo de posiciones
     * @param color color del texto
    */
    public void crearLabels(String palabra, double[][] posiciones, Color color){
        this.texto = new Label[palabra.length()];
        for(int i = 0; i < palabra.length(); i++){
            Label l = new Label(""+palabra.charAt(i));
            l.setLayoutX(posiciones[i][0] + this.imagen.getLayoutX());
            l.setLayoutY(posiciones[i][1] + this.imagen.getLayoutY());
            l.setTextFill(color);
            l.setFont(new Font(19));
            this.texto[i] = l;
        }
        
    }
    
    /**
    * Metodo que crea los una criatura aleatoriamente
     * @param posX coordenada x en el mar
     * @param posY coordenada y en el mar
     * @param palabras de tipo ArrayList con todas las palabras registradas
     * @return ciratura aleatoria creada

    */
    public static Criatura criaturaAleatoria(ArrayList<String> palabras, double posX, double posY){
        //1: piraña, 2: tiburón, 3: tiburón negro
        ArrayList<Integer> probably = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i < 3; i++) {
            probably.add(1);
        }
        for (int i = 0; i < 5; i++) {
            probably.add(2);
        }
        for (int i = 0; i < 2; i++) {
            probably.add(3);
        }
        int num = rnd.nextInt(probably.size());
        int opcion = probably.get(num);
        int aleatorio = new Random().nextInt(palabras.size());
        String palabra = palabras.get(aleatorio);
        ArrayList<String> palabrasx = new ArrayList<>();
        System.out.println(opcion);
        switch(opcion) {
            case 1:
                palabrasx.add(palabra.substring(0,1));
                return new Pirania(palabrasx, posX, posY);
                
            case 2:
                palabrasx.add(palabra);
                return new Tiburon(palabrasx, posX, posY);
        }
        palabrasx.add(palabra);
        aleatorio = new Random().nextInt(palabras.size());
        palabrasx.add(palabras.get(aleatorio));
        if(aleatorio % 2 == 0){
            aleatorio = new Random().nextInt(palabras.size());
            palabrasx.add(palabras.get(aleatorio));         
        }
        
        System.out.println(palabrasx);
        return new MegaTiburon(palabrasx, posX, posY);
    }
   
    /**
    * Metodo que mueve la Criatura con todos sus componentes en mar un valor dependiendo del nivel
    * @param nivel de tipo int que representa el nivel del mar
    */
    public abstract void avanzar(int nivel);
}
