/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typersharks;

import criaturas.Criatura;
import criaturas.MegaTiburon;
import criaturas.Pirania;
import criaturas.Tiburon;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import principales.Mar;
import util.ConstantesyFunciones;

/**
 *
 * @author novicompu
 */
public class TyperSharks extends Application {
    private Stage primaryStage;
    private ArrayList<String> palabras ;
    private Scene principal;
    @Override
    
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        palabras = cargarPalabras();        
        Scene scene = principal();
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
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
        titulo.setLayoutX(350);
        titulo.setLayoutY(10);
       
        Button iniciar = new Button("Iniciar Juego");
        iniciar.setLayoutX(350);
        iniciar.setLayoutY(150);
        iniciar.setMaxSize(225, 61);
        iniciar.setPrefSize(225, 61);
        iniciar.setBackground(new Background(new BackgroundImage(ConstantesyFunciones.BOTON_MENU_NO_SELECCIONADO, 
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        iniciar.setOnAction(new clickHandler(1));
   
        ToggleButton instrucciones = new ToggleButton("Instrucciones");
        instrucciones.setLayoutX(350);
        instrucciones.setLayoutY(300);
        instrucciones.setMaxSize(225, 61);
        instrucciones.setPrefSize(225, 61);
        instrucciones.setBackground(new Background(new BackgroundImage(ConstantesyFunciones.BOTON_MENU_NO_SELECCIONADO, 
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        instrucciones.setOnAction(new clickHandler(2));
        
        ToggleButton puntajes = new ToggleButton("Puntajes");
        puntajes.setLayoutX(360);
        puntajes.setLayoutY(450);
        puntajes.setMaxSize(225, 61);
        puntajes.setPrefSize(225, 61);
        puntajes.setBackground(new Background(new BackgroundImage(ConstantesyFunciones.BOTON_MENU_NO_SELECCIONADO, 
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        puntajes.setOnAction(new clickHandler(3));
        panel.getChildren().addAll(titulo,iniciar,instrucciones,puntajes);
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
                root.agregarCriaturas(palabras);
                Scene scene = new Scene(root.getPanel(), 1000, 600);
                primaryStage.setScene(scene);
            }
        }
        
    }
    
}
