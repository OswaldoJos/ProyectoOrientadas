/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principales;


import criaturas.Criatura;
import criaturas.MegaTiburon;
import criaturas.Pirania;
import criaturas.Tiburon;
import hilos.HiloBuzo;
import hilos.HiloCriatura;
import hilos.HiloMegaTiburon;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import util.ConstantesyFunciones;

/**
 *
 * @author novicompu
 */
public class Mar {
    private Pane panel;
    private ArrayList<Criatura> criaturas;
    private ArrayList<Thread> hilosCriaturas;
    private Button bt;
    private final int[] NUMERO_CRIATURAS_POR_NIVEL = {5,7,9,12,15};
    private Buzo buzo;
    private HiloBuzo hiloBuzo;
    private ArrayList<String> palabras;
            
    public Mar() {
        this.buzo = new Buzo("Oswaldo");
        hiloBuzo = new HiloBuzo(buzo, this);
        hiloBuzo.start();
        this.panel = new Pane();
        this.panel.setOnKeyPressed(new letra());
        this.panel.setMaxSize(1000, 600);
        this.panel.setPrefSize(1000, 600);
        this.panel.setManaged(true);
        this.panel.setBackground(new Background(new BackgroundImage(ConstantesyFunciones.FONDO_MAR, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        this.bt = new Button("Iniciar");
        
        this.panel.getChildren().add(bt);
        this.panel.getChildren().addAll(buzo.getImagen());
        this.criaturas = new ArrayList<>();
        this.hilosCriaturas = new ArrayList<>();
    }

    public Pane getPanel() {
        return panel;
    }

    public ArrayList<Criatura> getCriaturas() {
        return criaturas;
    }

    public ArrayList<Thread> getHilosCriaturas() {
        return hilosCriaturas;
    }

    public void setHilosCriaturas(ArrayList<Thread> hilosCriaturas) {
        this.hilosCriaturas = hilosCriaturas;
    }

    public Button getBt() {
        return bt;
    }

    public void setBt(Button bt) {
        this.bt = bt;
    }

    public Buzo getBuzo() {
        return buzo;
    }

    public void setBuzo(Buzo buzo) {
        this.buzo = buzo;
    }

    public HiloBuzo getHiloBuzo() {
        return hiloBuzo;
    }

    public void setHiloBuzo(HiloBuzo hiloBuzo) {
        this.hiloBuzo = hiloBuzo;
    }

    public ArrayList<String> getPalabras() {
        return palabras;
    }

    public void setPalabras(ArrayList<String> palabras) {
        this.palabras = palabras;
    }
    
    public void agregarCriaturas(ArrayList<String> palabras){
        this.palabras = palabras;
        while(this.criaturas.size() != this.NUMERO_CRIATURAS_POR_NIVEL[0]){
            int aleatorio = new Random().nextInt(palabras.size());
            int posAleatoria = new Random().nextInt(500);
            String palabra = palabras.get(aleatorio);
            ArrayList<String> palabrasx = new ArrayList<>();
            posAleatoria = new Random().nextInt(500);
            palabrasx.add(palabra.substring(0,1));
            //Pirania pirania = new Pirania(palabrasx,800, posAleatoria);
            Criatura pirania = Criatura.criaturaAleatoria(palabrasx, 800, posAleatoria);
            if (pirania.getClass().isInstance(Pirania.class)) {
                pirania.setPalabras(palabrasx);
            } else {
                pirania.setPalabras(palabras);
            }
            System.out.println(this.panel.getChildren());
            this.panel.getChildren().addAll(pirania.getImagen());
            for(Label l: pirania.getTexto()){
                this.panel.getChildren().addAll(l);
            }
            this.criaturas.add(pirania);
            if (pirania.getClass().isInstance(MegaTiburon.class)) {
                HiloMegaTiburon hilo = new HiloMegaTiburon((MegaTiburon) pirania, this, palabras);
                hilosCriaturas.add(hilo);
                hilo.start();
            } else {
                HiloCriatura hilo = new HiloCriatura(pirania, this);
                hilosCriaturas.add(hilo);
                hilo.start();
            }
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
            if(event.getCode().equals(KeyCode.ENTER)){
                System.out.println("Super Poder");
                for(Criatura c: criaturas){
                    c.setPalabras(new ArrayList<>());
                }
            }
            if(!event.getCode().isLetterKey())
                return;
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
                if(c.getPalabras().size() > 0)
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