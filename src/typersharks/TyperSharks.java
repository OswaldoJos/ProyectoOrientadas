/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typersharks;

import com.sun.scenario.effect.Effect;
import hilos.HiloRegresiva;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import principales.Mar;
import util.ConstantesyFunciones;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.TextAlignment;
import javafx.stage.WindowEvent;
import principales.Buzo;

/**
 *
 * @author novicompu
 */
public class TyperSharks extends Application {
    private Stage primaryStage;
    private ArrayList<String> palabras ;
    private Scene principal;
    public MediaPlayer media;
    
    @Override
    
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>(){
            @Override public void handle(WindowEvent event) {
                System.exit(0);
            }
        });
        palabras = cargarPalabras();        
        Scene scene = principal();
        primaryStage.setTitle("TyperShark!");
        primaryStage.setScene(scene);
        primaryStage.show();
        File archivo = new File("src/util/audios/CheapThrills.mp3");
        Media m = new Media(archivo.toURI().toString());
        media = new MediaPlayer(m);
        media.setCycleCount(MediaPlayer.INDEFINITE);
        media.play();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public Scene principal(){
        Pane panel = new Pane();
        panel.setMaxSize(800, 600);
        panel.setPrefSize(800, 600);
        panel.setManaged(true);
        panel.setBackground(new Background(new BackgroundImage(ConstantesyFunciones.FONDO_PRINCIPAL, 
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        
        Label titulo = new Label("TyperShark");
        titulo.setLayoutX(275);
        titulo.setLayoutY(5);
        titulo.setTextFill(Color.RED);
        titulo.setFont(Font.font(null, FontWeight.BOLD, 42));
        titulo.setEffect(new DropShadow(5, Color.CORAL));
        titulo.setTextFill(Color.RED);
       
        Button iniciar = new Button("Jugar");
        iniciar.setBackground(Background.EMPTY);
        iniciar.setBackground(new Background(new BackgroundImage(ConstantesyFunciones.BOTON, 
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        iniciar.setLayoutX(290);
        iniciar.setLayoutY(200);
        iniciar.setMaxSize(200, 80);
        iniciar.setPrefSize(200, 80);
        iniciar.setOnAction(new clickHandler(1));
        iniciar.setOnMouseEntered(new estimular(iniciar, true));
        iniciar.setOnMouseExited(new estimular(iniciar, false));
        iniciar.setFont(Font.font(null, FontWeight.BOLD, 20));
        iniciar.setTextFill(Color.DARKBLUE);
        iniciar.setTextAlignment(TextAlignment.LEFT);
   
        Button puntaje = new Button("Puntajes");
        puntaje.setLayoutX(290);
        puntaje.setLayoutY(325);
        puntaje.setMaxSize(200, 80);
        puntaje.setPrefSize(200, 80);
        puntaje.setBackground(new Background(new BackgroundImage(ConstantesyFunciones.BOTON, 
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        puntaje.setOnAction(new clickHandler(2));
        puntaje.setOnMouseEntered(new estimular(puntaje, true));
        puntaje.setOnMouseExited(new estimular(puntaje, false));
        puntaje.setFont(Font.font(null, FontWeight.BOLD, 20));
        puntaje.setTextFill(Color.DARKBLUE);
        
        Button salir = new Button("Salir");
        salir.setLayoutX(290);
        salir.setLayoutY(450);
        salir.setMaxSize(200, 80);
        salir.setPrefSize(200, 80);
        salir.setBackground(new Background(new BackgroundImage(ConstantesyFunciones.BOTON, 
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        salir.setOnAction(new clickHandler(3));
        salir.setOnMouseEntered(new estimular(salir, true));
        salir.setOnMouseExited(new estimular(salir, false));
        salir.setFont(Font.font(null, FontWeight.BOLD, 20));
        salir.setTextFill(Color.DARKBLUE);
        
        panel.getChildren().addAll(titulo,iniciar,puntaje,salir);
        Scene principal= new Scene(panel, 800, 600);
        return principal;
    }

    
    public static ArrayList<String> cargarPalabras(){
        ArrayList<String> palabras = new ArrayList();
        File archivo = new File("src/util/archivos/palabras.txt");
        try {
            Scanner sc = new Scanner(archivo);
            sc.useDelimiter("\n");
            while(sc.hasNext()){
                String linea = sc.nextLine();
                if (linea.length()!=0){
                    String[] campos = linea.split("\\\n");
                    palabras.add(campos[0]);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {}
        return palabras;
    }
    
    private class clickHandler implements EventHandler<ActionEvent>{
        int opcion;

        public clickHandler(int opcion) {
            this.opcion = opcion;
        }
        
        @Override
        public void handle(ActionEvent event) {
            if(opcion == 1){
                Mar root = new Mar();
                root.getBt().setOnAction(new regresar());
                System.out.println(root.getHiloBuzo().getState());
                Scene scene = new Scene(root.getPanel(), 1000, 600);
                File archivo = new File("src/util/audios/Jaws.mp3");
                Media m = new Media(archivo.toURI().toString());
                media.stop();
                media = new MediaPlayer(m);
                media.setCycleCount(MediaPlayer.INDEFINITE);
                media.play();
                primaryStage.setScene(scene);
                root.agregarCriaturas(palabras);
            }
            if(opcion == 2){
                BorderPane panel = new BorderPane();
                VBox puntajes = new VBox(10);
                panel.setMaxSize(800, 600);
                panel.setPrefSize(800, 600);
                Button btn = new Button("Regresar");
                btn.setAlignment(Pos.CENTER);
                btn.setOnAction(new regresar());
                panel.setBackground(new Background(new BackgroundImage(ConstantesyFunciones.FONDO_PRINCIPAL,
                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                LinkedList<Buzo> top10 = Buzo.cargarTop10();
                Label[] labels = new Label[top10.size()];
                for (int i = 0; i < labels.length; i++) {
                    labels[i] = new Label();
                    labels[i].setText(top10.get(i).getNombre() + "      " + top10.get(i).getPuntaje());
                    puntajes.getChildren().add(labels[i]);
                    labels[i].setTextFill(Color.ANTIQUEWHITE);
                    labels[i].setFont(Font.font(null, FontWeight.BOLD, 20));
                    labels[i].setAlignment(Pos.CENTER);
                }
                puntajes.setAlignment(Pos.CENTER);
                panel.setCenter(puntajes);
                VBox caja = new VBox(btn);
                caja.setAlignment(Pos.CENTER);
                panel.setBottom(caja);
                
                principal = new Scene(panel, 800, 600);
                primaryStage.setScene(principal);
            }
            if (opcion == 3) {
                System.exit(opcion);
            }
        }
    }
    
    private class estimular implements EventHandler<MouseEvent> {
        Button btn;
        boolean estimulo;

        public estimular(Button btn, boolean estimulo) {
            this.btn = btn;
            this.estimulo = estimulo;
        }
        
        @Override
        public void handle(MouseEvent event) {
            DropShadow shadow = new DropShadow();
            shadow.setColor(Color.CORAL);
            if (estimulo) {
                btn.setEffect(shadow);
            }
            else {
                btn.setEffect(null);
            }
        }
    }
    
    private class regresar implements EventHandler<ActionEvent>{
        
        @Override
        public void handle(ActionEvent event) {
            primaryStage.setScene(principal());
        }
        
    }
}
