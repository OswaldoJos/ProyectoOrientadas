/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principales;

import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.ConstantesyFunciones;

/**
 *
 * @author novicompu
 */
public class Buzo {
    private ImageView imagen;
    private String nombre;
    private int vidas;
    private int puntaje;

    public Buzo(String nombre) {
        this.nombre = nombre;
        this.imagen = new ImageView(ConstantesyFunciones.BUZO);
        this.imagen.setLayoutX(5);
        this.imagen.setLayoutY(20);
        this.vidas = 3;
        this.puntaje = 0;
        
    }

    public ImageView getImagen() {
        return imagen;
    }

    public void setImagen(ImageView imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
    
    public void superAtacar(Mar mar){
        
    }
    
}
