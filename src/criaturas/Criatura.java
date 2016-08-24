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
 *
 * @author novicompu
 */
public abstract class Criatura {
    private ImageView imagen;
    private ArrayList<String> palabras ;
    private Label[] texto;
    private boolean marcada;
    private boolean correcta;

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

    public ImageView getImagen() {
        return imagen;
    }

    public void setImagen(ImageView imagen) {
        this.imagen = imagen;
    }

    public ArrayList<String> getPalabras() {
        return palabras;
    }

    public void setPalabras(ArrayList<String> palabras) {
        this.palabras = palabras;
    }

    public Label[] getTexto() {
        return texto;
    }

    public void setTexto(Label[] texto) {
        this.texto = texto;
    }

    public boolean isMarcada() {
        return marcada;
    }

    public void setMarcada(boolean marcada) {
        this.marcada = marcada;
    }

    public boolean isCorrecta() {
        return correcta;
    }

    public void setCorrecta(boolean correcta) {
        this.correcta = correcta;
    }
    
    public void avanzar(double velocidad){
        this.imagen.setLayoutX(this.imagen.getLayoutX() - velocidad);
        for(int i = 0; i < texto.length; i++){
           texto[i].setLayoutX(this.texto[i].getLayoutX() - velocidad);
        }
    }
    
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
    
    public static Criatura criaturaAleatoria(ArrayList<String> palabras, double posX, double posY){
        //1: piraña, 2: tiburón, 3: tiburón negro
        ArrayList<Integer> probably = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i < 12; i++) {
            probably.add(1);
        }
        for (int i = 0; i < 7; i++) {
            probably.add(2);
        }
        for (int i = 0; i < 1; i++) {
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
    
    public abstract void avanzar(int nivel);
}
