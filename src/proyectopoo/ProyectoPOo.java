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
        Mar root = new Mar("src/imagenes/fondoMarino.jpg");
        root.agregarCriaturas(palabras);
        Scene scene = new Scene(root.getPanel(), 1000, 600);
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
    
}
