/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectopoo;

import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 *
 * @author novicompu
 */
public class HiloCriatura extends Thread{
    private Criatura criatura;
    private Mar mar;
    public HiloCriatura(Criatura criatura, Mar mar) {
        this.criatura = criatura;
        this.mar = mar;
    }

    @Override
    public void run() {
        while(true){
            HiloCriatura.this.criatura.avanzar(1);  
            try {
                int aleatorio = new Random().nextInt(10);
                HiloCriatura.this.sleep(50*aleatorio);
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloCriatura.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(criatura.getPalabras().isEmpty() || criatura.getImagen().getLayoutX() == 0){
                System.out.println("entra");
                File file = new File("src/imagenes/boom.png");
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
