/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principales;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.Scanner;
import javafx.scene.image.ImageView;
import util.ConstantesyFunciones;

/**
    * Esta clase define un objeto Buzo
    * @author Oswaldo Aguilar , Jonathan Gorotiza y Eduardo Salazar
    * @version 24/08/2016
    */
public class Buzo {
    
    /**
     * imagen del buzo
     */
    private ImageView imagen;
    
    /**
     * nombre del buzo
     */
    private String nombre;
    
    /**
     * vidas restantes
     */
    private int vidas;
    
    /**
     * puntaje actual
     */
    private int puntaje;
    /**
     * numero de pirañas que mordieron al buzo
     */
    private int contPiranias = 0;
    
    /**
    * Constructor de Mar con un parametro
    * @param nombre de tipo String que indica el nombre del buzo
    */

    public Buzo(String nombre) {
        this.nombre = nombre;
        this.imagen = new ImageView(ConstantesyFunciones.BUZO);
        this.imagen.setLayoutX(5);
        this.imagen.setLayoutY(20);
        this.vidas = 3;
        this.puntaje = 0;
        
    }
    
    /**
    * Constructor de Mar con dos parametros
    * @param nombre de tipo String que indica el nombre del buzo
    * @param puntaje de tipo int que indica el puntaje del jugador
    */
    public Buzo(String nombre, int puntaje) {
        this.nombre = nombre;
        this.puntaje = puntaje;
    }

    /**
     * Metodo que devuelve la Imagen del buzo.
     * @return imagen tipo Buzo
     */
    public ImageView getImagen() {
        return imagen;
    }
    
    /**
     * Metodo que actualiza la Imagen del buzo.
     * @param imagen tipo Buzo
     */
    public void setImagen(ImageView imagen) {
        this.imagen = imagen;
    }
    
    /**
     * Metodo que devuelve la nombre del buzo.
     * @return nombre tipo String
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Metodo que actualiza la nombre del buzo.
     * @param nombre tipo String
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Metodo que devuelve el numero de vidas restantes del buzo
     * @return vidas tipo int
     */
    public int getVidas() {
        return vidas;
    }
    /**
     * Metodo que actualiza el numero de vidas restantes del buzo
     * @param vidas tipo int
     */
    public void setVidas(int vidas) {
        this.vidas = vidas;
    }
    
    /**
     * Metodo que devuelve el puntaje del buzo
     * @return puntaje tipo int
     */
    public int getPuntaje() {
        return puntaje;
    }
    
    /**
     * Metodo que actualiza el puntaje del buzo
     * @param puntaje tipo int
     */
    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
    
    /**
     * Metodo que quita una vida del buzo
     */
    public void quitarVida(){
        this.vidas = this.vidas -1;
    }
    
    /**
     * Metodo que agrega una vida del buzo
     */
    public void agregarVida(){
        this.vidas = this.vidas +1;
    }
    
    /**
     * Metodo que devuelve el numero de pirañas que han mordido al buzo
     * @return contPiranias de tipo int
     */
    public int getContPiranias() {
        return contPiranias;
    }
    
    /**
     * Metodo que actualiza el numero de pirañas que han mordido al buzo
     * @param contPiranias de tipo int
     */
    public void setContPiranias(int contPiranias) {
        this.contPiranias = contPiranias;
    }
    
    /**
     * Metodo estatico que devuelve True si el buzo entra al top 10
     * @param buzo que representa el buzo
     * @return true si el buzo entra al top, false si no entra
     */
    public static boolean entreTopDiez(Buzo buzo){
        LinkedList<Buzo> lista = cargarTop10();        
        boolean entro=false;
        if(buzo.getPuntaje() > lista.getLast().getPuntaje() || (lista.size()!=10)){
            for(int i = 0 ; i < lista.size() ; i++){
                if(buzo.getPuntaje() > lista.get(i).getPuntaje()){
                    lista.add(i, buzo);
                    entro=true;
                    break;
                }
            }
            if(!entro)
                lista.addLast(buzo);
            if(lista.size() > 10)
                lista.removeLast();
            File archivo = new File("src/util/archivos/top10.txt");
            try {
                FileWriter fw = new FileWriter(archivo);
                for (Buzo b : lista) {
                     fw.append(b.getNombre() + "," + b.getPuntaje() + "\n");
                }
                fw.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return true;
        }
        return false;
    }
    
    /**
     * Metodo que devuelve el top 10 de buzos
     * @return lista de tipo LinkedList
     */
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

    

