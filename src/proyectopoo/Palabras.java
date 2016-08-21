/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectopoo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author novicompu
 */
public class Palabras {
    
    public static void reducirPalabras() throws IOException{
        Random r = new Random();
        int aleatorio;
        ArrayList palabras = new ArrayList();
        File archivo = new File("src/archivos/palabras3.txt");
        try {
            Scanner sc = new Scanner(archivo);
            sc.useDelimiter("\n");
            while(sc.hasNext()){
                String linea = sc.nextLine();
                if (linea.length()!=0){
                    String[] campos = linea.split("\\\n");
                    if(campos[0].length() < 8 && 3 < campos[0].length() && !campos[0].contains("-")){
                        aleatorio = r.nextInt(10);
                        if(aleatorio%2 == 0 && !campos[0].contains("í") && !campos[0].contains("ó") && !campos[0].contains("ú"))
                            palabras.add(campos[0]);
                    }
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            
        }
        System.out.println("Termino de leer");
        File f;
        f = new File("src/archivos/palabras4.txt");
        BufferedWriter bw;
        for(Object palabra: palabras){
            if(f.exists()) {
                bw = new BufferedWriter(new FileWriter(f, true));
                bw.append("\n" + (String)palabra);
            } else {
                bw = new BufferedWriter(new FileWriter(f));
                bw.write((String)palabra);
            }
            bw.close();
        }
    }
    
    public static void main(String args[]){
        try {
            reducirPalabras();
        } catch (IOException ex) {
            Logger.getLogger(Palabras.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
