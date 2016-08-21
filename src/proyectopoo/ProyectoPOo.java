/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectopoo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author novicompu
 */
public class ProyectoPOo extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        ArrayList<String> palabras = cargarPalabras();        
        Mar root = new Mar("imagenes/fondoMarino.jpg");
        root.agregarCriaturas(palabras);
        Scene scene = new Scene(root.getPanel(), 1000, 600);
        Scene principal = this.principal();
        primaryStage.setTitle("TyperShark");
        primaryStage.setScene(principal);
        primaryStage.show();
    }

    public Scene principal(){
        Pane panel = new Pane();
        panel.setMaxSize(800, 600);
        panel.setPrefSize(800, 600);
        panel.setManaged(true);
        panel.setBackground(new Background(new BackgroundImage(new Image("imagenes/portada.jpg"), 
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        Label titulo = new Label("TyperShark");
        titulo.setLayoutX(350);
        titulo.setLayoutY(10);
        Button iniciar = new Button("Iniciar Juego");
        iniciar.setLayoutX(350);
        iniciar.setLayoutY(150);
        iniciar.setMaxSize(225, 61);
        iniciar.setPrefSize(225, 61);
        iniciar.setBackground(new Background(new BackgroundImage(new Image("imagenes/botonesPrincipal.png"), 
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        iniciar.setOnAction(new ClickHandler());
        Button instrucciones = new Button("Instrucciones");
        instrucciones.setLayoutX(350);
        instrucciones.setLayoutY(300);
        instrucciones.setMaxSize(225, 61);
        instrucciones.setPrefSize(225, 61);
        instrucciones.setBackground(new Background(new BackgroundImage(new Image("imagenes/botonesPrincipal.png"), 
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        instrucciones.setOnAction(new ClickHandler());
        Button puntajes = new Button("Puntajes");
        puntajes.setLayoutX(360);
        puntajes.setLayoutY(450);
        puntajes.setMaxSize(225, 61);
        puntajes.setPrefSize(225, 61);
        puntajes.setBackground(new Background(new BackgroundImage(new Image("imagenes/botonesPrincipal.png"), 
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        puntajes.setOnAction(new ClickHandler());
        panel.getChildren().addAll(titulo,iniciar,instrucciones,puntajes);
        Scene principal= new Scene(panel);
        return principal;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public static ArrayList<String> cargarPalabras(){
        ArrayList<String> palabras = new ArrayList();
        File archivo = new File("src/archivos/palabras.txt");
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
    
    private class ClickHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            System.out.println("holaaaa");
        }
        
    }
}
