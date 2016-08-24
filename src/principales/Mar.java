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
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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
    private int nivel;
    private int hordas;
    private Label vidas;
    private Label lNivel;
    private Label lPuntaje;
    
    public Mar() {
        this.nivel = 1;
        this.hordas = 5;
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
        this.bt = new Button("Rendirse");
        this.bt.setLayoutX(900);
        this.bt.setLayoutY(550);
        this.bt.setFocusTraversable(true);
        this.vidas = new Label("3");
        this.vidas.setLayoutX(100);
        this.vidas.setLayoutY(20);
        this.vidas.setFont(new Font(28));
        this.vidas.setTextFill(Color.WHITE);
        ImageView imagen = new ImageView(ConstantesyFunciones.CORAZON);
        imagen.setLayoutX(5);
        imagen.setLayoutY(5);
        imagen.setFitHeight(75);
        imagen.setFitWidth(75);
        this.lNivel = new Label("Nivel 1");
        this.lNivel.setLayoutX(250);
        this.lNivel.setLayoutY(20);
        this.lNivel.setFont(new Font(28));
        this.lNivel.setTextFill(Color.WHITE);
        
        this.lPuntaje = new Label("Puntaje: 0");
        this.lPuntaje.setLayoutX(450);
        this.lPuntaje.setLayoutY(20);
        this.lPuntaje.setFont(new Font(28));
        this.lPuntaje.setTextFill(Color.WHITE);
        
        this.panel.getChildren().add(bt);
        this.panel.getChildren().addAll(buzo.getImagen(), vidas, imagen, lNivel, lPuntaje);
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

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getHordas() {
        return hordas;
    }

    public void setHordas(int hordas) {
        this.hordas = hordas;
    }

    public Label getVidas() {
        return vidas;
    }

    public void setVidas(Label vidas) {
        this.vidas = vidas;
    }

    public Label getlNivel() {
        return lNivel;
    }

    public void setlNivel(Label lNivel) {
        this.lNivel = lNivel;
    }

    public Label getlPuntaje() {
        return lPuntaje;
    }

    public void setlPuntaje(Label lPuntaje) {
        this.lPuntaje = lPuntaje;
    }

    
    public void aumentarNivel(){
        this.nivel++;
    }
    
    public void pasarHorda(){
        this.hordas --;
    }
    
    public void agregarCriaturas(ArrayList<String> palabras){
        this.palabras = palabras;
        int aux = this.hordas;
        if(this.hordas < 0){
            aux = 0;
        }
        while(this.criaturas.size() < 7 - aux){
            int posAleatoria = new Random().nextInt(380);
            //Pirania pirania = new Pirania(palabrasx,800, posAleatoria);
            Criatura criatura = Criatura.criaturaAleatoria(palabras, 800, posAleatoria + 75);
            System.out.println(this.panel.getChildren());
            this.panel.getChildren().addAll(criatura.getImagen());
            for(Label l: criatura.getTexto()){
                this.panel.getChildren().addAll(l);
            }
            this.criaturas.add(criatura);

            HiloCriatura hilo = new HiloCriatura(criatura, this);
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
            if(event.getCode().equals(KeyCode.ENTER)){
                if(buzo.getPuntaje() >= 100){
                    System.out.println("Super Poder");
                    for(Criatura c: criaturas){
                        c.setPalabras(new ArrayList<>());
                    }
                    buzo.setPuntaje(buzo.getPuntaje()-100);
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
                            if(!c.getPalabras().isEmpty()){
                                for(Label l: c.getTexto()){
                                    panel.getChildren().remove(l);
                                }
                                c.crearLabels(c.getPalabras().get(0), Tiburon.posicionesLabel[c.getPalabras().get(0).length()-1], Color.WHITE);
                                for(Label l: c.getTexto()){
                                    panel.getChildren().add(l);
                                }
                            }
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