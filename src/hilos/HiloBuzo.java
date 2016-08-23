/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilos;

import criaturas.Criatura;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Label;
import principales.Buzo;
import principales.Mar;

/**
 *
 * @author Administrador
 */
public class HiloBuzo extends Thread{
    private Buzo buzo;
    private Mar mar;
    private boolean subida;
    
    public HiloBuzo(Buzo buzo, Mar mar) {
        this.buzo = buzo;
        this.mar = mar;
        this.subida = false;
    }

    @Override
    public void run() {
        while(true){
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
             
            if(mar.getCriaturas().isEmpty()){
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Murieron todos");
                        mar.agregarCriaturas(mar.getPalabras());
                        }
                });
                
            }
        }
    }
    
    
}
