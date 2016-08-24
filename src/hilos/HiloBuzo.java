/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilos;

import criaturas.Criatura;
import java.awt.BorderLayout;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import principales.Buzo;
import principales.Mar;
import typersharks.TyperSharks;
import util.ConstantesyFunciones;

/**
    * Esta clase define un hilo del buzo
    * @author Oswaldo Aguilar , Jonathan Gorotiza y Eduardo Salazar
    * @version 24/08/2016
    * @see Criatura
    * @see Mar
    * @see Buzo
    * @see HiloBuzo
    * @see HiloCriatura
    */
public class HiloBuzo extends Thread{
    /**
     * Buzo que referencia el hilo
     */
    private Buzo buzo;
    /**
     * Mar del juego
     */
    private Mar mar;
    /**
     * Variable direccion de movimiento
     */
    private boolean subida;
    /**
     * referencia al juego principal
     */
    TyperSharks juego;
    
    /**
    * Constructor de HiloBuzo con tres parametros
    * @param buzo de tipo Buzo que referencia al buzo
    * @param mar de tipo Mar que referencia al mar
    * @param juego de tipo TyperSharks que referencia al juego principal
    */
    public HiloBuzo(Buzo buzo, Mar mar, TyperSharks juego) {
        this.buzo = buzo;
        this.mar = mar;
        this.subida = false;
        this.juego = juego;
    }
    /**
    * Metodo que ejcutara todo el proceso del buzo
    */
    @Override
    public void run() {
        while(true){
            if(mar.getBuzo().getVidas() == 0){
                Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("FIN JUEGO");
                            alert.setHeaderText(null);
                            if(Buzo.entreTopDiez(buzo)){
                                System.out.println("entro");
                                alert.setContentText("Juego terminado. Usted ha entrado al Top 10");
                            }
                            else{
                            alert.setContentText("Juego terminado. No entro al Top10");
                            }
                            alert.showAndWait();
                            System.out.println("crear nuevo scene");
                            juego.primaryStage.setScene(juego.principal());
                        }
                    });
                
                break;
            }
            if(subida){
                buzo.getImagen().setLayoutY(this.buzo.getImagen().getLayoutY()-15);
                if(this.buzo.getImagen().getLayoutY() <= 0)
                    subida = false;
            }
            else{
                buzo.getImagen().setLayoutY(this.buzo.getImagen().getLayoutY()+15);
                if(this.buzo.getImagen().getLayoutY() > 300)
                subida = true;
            }
                    
            try {
                int aleatorio = new Random().nextInt(10);
                this.sleep(50*aleatorio);
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloCriatura.class.getName()).log(Level.SEVERE, null, ex);
            }
            try{
                if(mar.getCriaturas().isEmpty()){
                    mar.pasarHorda();
                    if(mar.getHordas() == 0){
                        if(mar.getNivel() < 5){
                            mar.aumentarNivel();
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    mar.getlNivel().setText("Nivel "+mar.getNivel() );
                                }
                            });
                            mar.setHordas(5);
                            mar.getBuzo().agregarVida();
                            Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                mar.getVidas().setText(""+mar.getBuzo().getVidas());
                            }
                        });
                        }
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            mar.agregarCriaturas(mar.getPalabras());
                            }
                    });

                }
            }catch(Exception e){};
        }
        
    }
    
    
    
}
