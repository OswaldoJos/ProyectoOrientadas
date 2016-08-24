/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principales;

//Importaciones
import criaturas.Criatura;
import criaturas.Tiburon;
import hilos.HiloBuzo;
import hilos.HiloCriatura;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
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
import javafx.stage.Stage;
import typersharks.TyperSharks;
import util.ConstantesyFunciones;

/**
    * Esta clase define un objeto Mar donde ocurrira la mayor parte de ejecucion del programa
    * @author Oswaldo Aguilar , Jonathan Gorotiza y Eduardo Salazar
    * @version 24/08/2016
    * @see Criatura
    * @see Mar
    * @see Buzo
    * @see HiloBuzo
    * @see HiloCriatura
    */

public class Mar {
    //atributos de la clase
    
    /**
     * panel principal
     */
    private Pane panel; //cariable tipo Pane el cual sera el contenedor de todos los objetos presentes en el Mar
    
    /**
     * Arreglo de criaturas
     */
    private ArrayList<Criatura> criaturas;  //variable tipo ArrayList que contendra las criaturas presentes en el Mar
    
    /**
     * Arreglo de hilos 
     */
    private ArrayList<Thread> hilosCriaturas;   //varible tipo ArrayList que contendra los hilos de las criaturas presentes en el Mar
    
    /**
     * boton rendirse
     */
    private Button bt;  //variable tipo Button que sera presionado en caso que el jugador se rinda
    
    /**
     * arreglo de enteros
     */
    private final int[] NUMERO_CRIATURAS_POR_NIVEL = {5,7,9,12,15}; //variable tipo arreglo que seran el numero de criaturas por nivel
    
    /**
     * buzo
     */
    private Buzo buzo;  //variable tipo buzo que sera el jugador principal
    
    /**
     * hilo del buzo
     */
    private HiloBuzo hiloBuzo;  //variable tipo HiloBuzo que sera el encargado de dar movimiento al buzo
    
    /**
     * Arreglo de palabras
     */
    private ArrayList<String> palabras; //variable tipo ArrayList que contendra las palabras a ser usadas en el juego
    
    /**
     * nivel del juego
     */
    private int nivel;  //variable tipo int para saber en que nivel se encuentra el jugador
    
    /**
     * hordas del nivel
     */
    private int hordas; //variable tipo entero que dara cuantas hordas van en en nivel que se encuentre
    
    /**
     * vidas del jugador
     */
    private Label vidas;    // variable tipo Lbale que tendra la informacion de cuantas vidas tiene el jugador para presentarselas
    
    /**
     * nivel del juego
     */
    private Label lNivel;   //variable tipo Label que tendra el numero del nivel en el que se encuentra el jugador
    
    /**
     * puntaje del jugador
     */
    private Label lPuntaje; //variable tipo Label que tendra el puntaje que tiene el jugador
    
    /**
     * imagen que representa si el poder esta activo
     */
    private ImageView poder;
    /**
     * Referencia al juego principal
     */
    public TyperSharks juego;
    
    
    /**
    * Constructor de Mar con dos parametros
    * @param nombre de tipo String que indica el nombre del buzo
    * @param juego de tipo TyperSharks que indica el juego principal
    */
    public Mar(String nombre, TyperSharks juego) {
        this.juego = juego;
        System.out.println(nombre);
        this.nivel = 1;
        this.hordas = 5;
        this.buzo = new Buzo(nombre);
        hiloBuzo = new HiloBuzo(buzo, this, juego);
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
        this.bt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                buzo.setVidas(0);
            }
        });
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
        this.lPuntaje.setLayoutX(750);
        this.lPuntaje.setLayoutY(20);
        this.lPuntaje.setFont(new Font(28));
        this.lPuntaje.setTextFill(Color.WHITE);
        
        this.poder = new ImageView(ConstantesyFunciones.PODER_INACTIVO);
        poder.setLayoutX(500);
        poder.setLayoutY(5);
        poder.setFitHeight(75);
        poder.setFitWidth(75);
        
        this.panel.getChildren().add(bt);
        this.panel.getChildren().addAll(buzo.getImagen(), vidas, imagen, lNivel, lPuntaje, poder);
        this.criaturas = new ArrayList<>();
        this.hilosCriaturas = new ArrayList<>();
    }
    
    /**
     * Metodo que devuelve el panel principal el que representa a todo el mar.
     * @return panel tipo Pane
     */
    public Pane getPanel() {
        return panel;
    }
    
    /**
     * Metodo que devuelve el arreglo de todas las criaturas presentes en el mar
     * @return criaturas tipo ArrayList
     */
    public ArrayList<Criatura> getCriaturas() {
        return criaturas;
    }
    
    /**
     * Metodo que devuelve el arreglo de los hilos de todas las criaturas presentes en el mar
     * @return criaturas tipo ArrayList
     */
    public ArrayList<Thread> getHilosCriaturas() {
        return hilosCriaturas;
    }
    
    /**
     * Metodo que actualiza el arreglo de todas las criaturas presentes en el mar
     * @param hilosCriaturas tipo ArrayList
     */
    public void setHilosCriaturas(ArrayList<Thread> hilosCriaturas) {
        this.hilosCriaturas = hilosCriaturas;
    }
    
    /**
     * Metodo que devuleve el buzo que representa a jugador
     * @return buzo tipo Buzo
     */
    public Buzo getBuzo() {
        return buzo;
    }
    
    /**
     * Metodo que actualiza el buzo que representa a jugador
     * @param buzo tipo Buzo
     */
    public void setBuzo(Buzo buzo) {
        this.buzo = buzo;
    }
    
    /**
     * Metodo que devuleve el hilo del buzo que representa a jugador
     * @return hiloBuzo tipo HiloBuzo
     */
    public HiloBuzo getHiloBuzo() {
        return hiloBuzo;
    }
    
    /**
     * Metodo que actualiza el hilo del buzo que representa a jugador
     * @param hiloBuzo tipo HiloBuzo
     */
    public void setHiloBuzo(HiloBuzo hiloBuzo) {
        this.hiloBuzo = hiloBuzo;
    }
    
    /**
     * Metodo que devuleve el arreglo de palabras de las que se crearan las criaturas
     * @return plabras de tipo ArrayList
     */
    public ArrayList<String> getPalabras() {
        return palabras;
    }
    
    /**
     * Metodo que actualiza el arreglo de palabras de las que se crearan las criaturas
     * @param palabras de tipo ArrayList
     */
    public void setPalabras(ArrayList<String> palabras) {
        this.palabras = palabras;
    }
    
    /**
     * Metodo que devuleve el numero del nivel en el que se esta en el momento
     * @return nivel de tipo int
     */
    public int getNivel() {
        return nivel;
    }
    
    /**
     * Metodo que actualiza el numero del nivel en el que se esta en el momento
     * @param nivel de tipo int
     */
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
    /**
     * Metodo que devulve el numero de hordas restantes en el nivel
     * @return hordas de tipo int
     */
    public int getHordas() {
        return hordas;
    }
    
    /**
     * Metodo que actualiza el numero de hordas restantes en el nivel
     * @param hordas de tipo int
     */
    public void setHordas(int hordas) {
        this.hordas = hordas;
    }
    
    /**
     * Metodo que devuelve el label donde se muestran las vidas del jugador
     * @return vidas de tipo Label
     */
    public Label getVidas() {
        return vidas;
    }
    
    /**
     * Metodo que actualiza el label donde se muestran las vidas del jugador
     * @param vidas de tipo Label
     */
    public void setVidas(Label vidas) {
        this.vidas = vidas;
    }
    
    /**
     * Metodo que devuelve el label donde se muestran el nivel de mar
     * @return lNivel de tipo Label
     */
    public Label getlNivel() {
        return lNivel;
    }
    
    /**
     * Metodo que actualiza el label donde se muestran el nivel de mar
     * @param lNivel de tipo Label
     */
    public void setlNivel(Label lNivel) {
        this.lNivel = lNivel;
    }
    
    /**
     * Metodo que devuelve el label donde se el puntaje actual del jugador
     * @return lPuntaje de tipo Label
     */
    public Label getlPuntaje() {
        return lPuntaje;
    }
    
    /**
     * Metodo que actualiza el label donde se el puntaje actual del jugador
     * @param lPuntaje de tipo Label
     */
    public void setlPuntaje(Label lPuntaje) {
        this.lPuntaje = lPuntaje;
    }
    
    /**
     * Metodo que devuelve la imagen que representa el estado del poder Rojo inactivo, verde activo
     * @return poder de tipo ImageView
     */
    public ImageView getPoder() {
        return poder;
    }
    
    /**
     * Metodo que actualiza la imagen que representa el estado del poder Rojo inactivo, verde activo
     * @param poder de tipo ImageView
     */
    public void setPoder(ImageView poder) {
        this.poder = poder;
    }
    
    /**
     * Metodo que aumente el nivel del mar en uno
     */
    public void aumentarNivel(){
        this.nivel++;
    }
    
    /**
     * Metodo que disminuye en uno el numero de hordas restantes
     */
    public void pasarHorda(){
        this.hordas --;
    }
    
    /**
     * Metodo que agrega todas las criaturas al mar dependiendo de la horda y el nivel
     * @param palabras de tipo ArrayList
     */
    public void agregarCriaturas(ArrayList<String> palabras){
        this.palabras = palabras;
        int aux = this.hordas;
        if(this.hordas < 0){
            aux = 0;
        }
        while(this.criaturas.size() < 6 - aux){
            int posAleatoria = new Random().nextInt(38);
            //Pirania pirania = new Pirania(palabrasx,800, posAleatoria);
            Criatura criatura = Criatura.criaturaAleatoria(palabras, 800, posAleatoria *10 + 75);
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
    
    /**
     * Metodo que remueve una criatura del mar con sus respectivas componentes
     * @param criatura  de tipo Criatura
     */
    public void removerCriatura(Criatura criatura){
        for(Label l: criatura.getTexto()){
            this.panel.getChildren().remove(l);
        }
        this.panel.getChildren().remove(criatura.getImagen());
        this.criaturas.remove(criatura);
    }
    
    /**
    * Esta clase interna define el evento de tecleo
    * @author Oswaldo Aguilar , Jonathan Gorotiza y Eduardo Salazar
    * @version 24/08/2016
    * @see Criatura
    * @see Mar
    * @see Buzo
    * @see HiloBuzo
    * @see HiloCriatura
    */
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
                    if(buzo.getPuntaje() < 100){
                        poder.setImage(ConstantesyFunciones.PODER_INACTIVO);
                    }
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
                                c.crearLabels(c.getPalabras().get(0), Tiburon.posicionesLabel[c.getPalabras().get(0).length()-1], Color.VIOLET);
                                for(Label l: c.getTexto()){
                                    panel.getChildren().add(l);
                                }
                            }
                        }else{
                            c.getPalabras().add(1, c.getPalabras().get(0).substring(1));
                            c.getPalabras().remove(0);
                            for(Label l: c.getTexto()){
                                if(!l.getTextFill().equals(Color.RED)){
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