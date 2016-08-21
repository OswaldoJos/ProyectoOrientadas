/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectopoo;


import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author novicompu
 */
public class Mar {
    private Pane panel;
    private ArrayList<Criatura> criaturas;
    private ArrayList<HiloCriatura> hilosCriaturas;
    private Button bt;
    private final int[] NUMERO_CRIATURAS_POR_NIVEL = {5,7,9,12,15};
            
    public Mar(String image) {
        this.panel = new Pane();
        this.panel.setOnKeyPressed(new letra());
        this.panel.setMaxSize(1000, 600);
        this.panel.setPrefSize(1000, 600);
        this.panel.setManaged(true);
        this.panel.setStyle("-fx-background-image: url('" + "fondoMarino.jpg" + "'); "
           + "-fx-background-position: center center; "
           + "-fx-background-repeat: stretch;");
        this.bt = new Button("Iniciar");
        
        this.panel.getChildren().add(bt);
        this.criaturas = new ArrayList<>();
        this.hilosCriaturas = new ArrayList<>();
    }

    public Pane getPanel() {
        return panel;
    }

    public ArrayList<Criatura> getCriaturas() {
        return criaturas;
    }

    public ArrayList<HiloCriatura> getHilosCriaturas() {
        return hilosCriaturas;
    }

    public void setHilosCriaturas(ArrayList<HiloCriatura> hilosCriaturas) {
        this.hilosCriaturas = hilosCriaturas;
    }
    
    public void agregarCriaturas(ArrayList<String> palabras){
        while(this.criaturas.size() != this.NUMERO_CRIATURAS_POR_NIVEL[0]){
            int aleatorio = new Random().nextInt(palabras.size());
            int posAleatoria = new Random().nextInt(500);
            String palabra = palabras.get(aleatorio);
            ArrayList<String> palabrasx = new ArrayList<>();
            palabrasx.add(palabra);
            Tiburon tiburon = new Tiburon(palabrasx,800, posAleatoria);
            this.panel.getChildren().addAll(tiburon.getImagen());
            for(Label l: tiburon.getTexto()){
                this.panel.getChildren().addAll(l);
            }
            this.criaturas.add(tiburon);
            HiloCriatura hilo = new HiloCriatura(tiburon, this);
            hilosCriaturas.add(hilo);
            hilo.start();
        }
    }
    
    
    public void removerCriatura(Criatura criatura){
        for(Label l: criatura.getTexto()){
            this.panel.getChildren().remove(l);
        }
        this.panel.getChildren().remove(criatura.getImagen());
        this.criaturas.remove(criatura);
    }
    
    private class letra implements EventHandler<KeyEvent>{

        @Override
        public void handle(KeyEvent event) {
            Criatura c = null;
            for(Criatura criatura: criaturas){
                if(criatura.isMarcada()){
                    if(c!= null){
                            c.setMarcada(false);
                    }
                    System.out.println("entra");
                    System.out.println(event.getText());
                    c = criatura;
                    break;
                }
                if(!criatura.getPalabras().isEmpty())
                    if(criatura.getPalabras().get(0).startsWith(event.getText())){
                        if(c!= null){
                            c.setMarcada(false);
                        }
                        c = criatura;
                        c.setMarcada(true);
                    }
            }
            if(c != null)
                if(c.getPalabras().get(0).startsWith(event.getText())){
                    c.setCorrecta(true);
                    if(c.getPalabras().get(0).length() == 1){
                        c.getPalabras().remove(0);
                        if(!c.getPalabras().isEmpty())
                            c.crearLabels(c.getPalabras().get(0), Tiburon.posicionesLabel[c.getPalabras().get(0).length()-1], Color.WHITE);
                    }else{
                        c.getPalabras().add(1, c.getPalabras().get(0).substring(1));
                        c.getPalabras().remove(0);
                        for(Label l: c.getTexto()){
                            if(l.getTextFill().equals(Color.WHITE)){
                                l.setTextFill(Color.RED);
                                break;
                            }
                        }
                    }
                }else{
                    c.setCorrecta(false);
                }
            
        }
        
    }
}