/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principales;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
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
    private int contPiranias = 0;
    
    public Buzo(String nombre) {
        this.nombre = nombre;
        this.imagen = new ImageView(ConstantesyFunciones.BUZO);
        this.imagen.setLayoutX(5);
        this.imagen.setLayoutY(20);
        this.vidas = 3;
        this.puntaje = 0;
        
    }
    
    public Buzo(String nombre, int puntaje) {
        this.nombre = nombre;
        this.puntaje = puntaje;
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
    public void quitarVida(){
        this.vidas = this.vidas -1;
    }

    public int getContPiranias() {
        return contPiranias;
    }

    public void setContPiranias(int contPiranias) {
        this.contPiranias = contPiranias;
    }
    
    
    public static LinkedList<Buzo> cargarTop10(){
        LinkedList<Buzo> lista = new LinkedList<>();
        File archivo = new File("src/util/archivos/top10.txt");
        try {
            Scanner sc = new Scanner(archivo);
            sc.useDelimiter("\n");
            while(sc.hasNext()) {
                String linea = sc.nextLine();
                String[] campos = linea.split("\\,");
                lista.add(new Buzo(campos[0], Integer.parseInt(campos[1])));
            }
            sc.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return lista;
    }
}
