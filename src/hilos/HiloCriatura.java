/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilos;

import criaturas.Criatura;
import criaturas.MegaTiburon;
import criaturas.Pirania;
import criaturas.Tiburon;
import java.io.File;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import principales.Mar;
import util.ConstantesyFunciones;

/**
    * Esta clase define un hilo de una criatura del mar
    * @author Oswaldo Aguilar , Jonathan Gorotiza y Eduardo Salazar
    * @version 24/08/2016
    * @see Criatura
    * @see Mar
    * @see HiloBuzo
    * @see HiloCriatura
    */
public class HiloCriatura extends Thread{
    /**
     * Criatura que referencia el hilo
     */
    private Criatura criatura;
    /**
     * Mar donde se encuentra la criatura
     */
    private Mar mar;
    
    /**
    * Constructor de HiloCriatura con dos parametros
    * @param criatura de tipo Criatura que referencia a la criatura
    * @param mar de tipo Mar que referencia al mar
    */
    public HiloCriatura(Criatura criatura, Mar mar) {
        this.criatura = criatura;
        this.mar = mar;
    }

    /**
    * Metodo que ejcutara todo el proceso de cada ciratura
    */
    @Override
    public void run() {
        while(mar.getBuzo().getVidas() > 0){
            HiloCriatura.this.criatura.avanzar(mar.getNivel()); 
            if(!criatura.isCorrecta())
                HiloCriatura.this.criatura.avanzar(mar.getNivel());
            try {
                int aleatorio = new Random().nextInt(10);
                HiloCriatura.this.sleep(50*aleatorio);
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloCriatura.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(criatura.getImagen().getLayoutX() <= 50){
                if(criatura.getClass().equals(Pirania.class)){
                    if(mar.getBuzo().getContPiranias() == 2){
                        mar.getBuzo().quitarVida();
                        mar.getBuzo().setContPiranias(0);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                mar.getVidas().setText(""+mar.getBuzo().getVidas());
                            }
                        });
                    }else{
                        mar.getBuzo().setContPiranias(mar.getBuzo().getContPiranias() + 1);
                    }
                }
                else{
                    mar.getBuzo().quitarVida();
                    Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    mar.getVidas().setText(""+mar.getBuzo().getVidas());
                                }
                            });
                }
            }
            if(criatura.getPalabras().isEmpty()){
                if(criatura.getClass().equals(Pirania.class))
                    mar.getBuzo().setPuntaje(mar.getBuzo().getPuntaje() + 5);
                if(criatura.getClass().equals(Tiburon.class))
                    mar.getBuzo().setPuntaje(mar.getBuzo().getPuntaje() + 10);
                if(criatura.getClass().equals(MegaTiburon.class))
                    mar.getBuzo().setPuntaje(mar.getBuzo().getPuntaje() + 20);
                
                Platform.runLater(new Runnable() {
                            @Override
                            public void run() {  
                                mar.getlPuntaje().setText("Puntaje: " + mar.getBuzo().getPuntaje());
                            }
                        });
                
                if(mar.getBuzo().getPuntaje() >= 100){
                    Platform.runLater(new Runnable() {
                            @Override
                            public void run() { 
                                mar.getPoder().setImage(ConstantesyFunciones.PODER_ACTIVO); 
                            }
                        });
                }
            }
            if(criatura.getPalabras().isEmpty() || criatura.getImagen().getLayoutX() <= 50){
                System.out.println("entra");
                File file = new File("src/util/imagenes/boom.png");
                Image image = new Image(file.toURI().toString());
                this.criatura.getImagen().setImage(image);
                try {
                    this.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HiloCriatura.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.mar.getHilosCriaturas().remove(this);
                this.mar.getCriaturas().remove(this.criatura);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        HiloCriatura.this.mar.getPanel().getChildren().remove(HiloCriatura.this.criatura.getImagen());
                    }
                        
                });
                
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        for(Label l: HiloCriatura.this.criatura.getTexto()){
                           HiloCriatura.this.mar.getPanel().getChildren().remove(l);
                        }}
                });
                
                break;
            }
        }
        
    }
    
}
