package analisisdeficheros;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AnalisisDeFicheros {
    public static void main(String[] args) throws Exception {
        try {
            System.out.print("Introduce nombre del fichero a analizar: ");
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            String t = teclado.readLine();
            //Generador de informes
            System.out.println("Informe del fichero");
            Palabra.informeF(t);           
            //MENU
            System.out.println("");
            System.out.print("_-Menu Principal-_\n"
                +"-------------------------------\n"
                +".  1.- Letra mas repetida \n"
                +".  2.- Numero caracteres \n"
                +".  3.- Palabra mas repetida \n"
                +".  4.- Buscar palabra \n"
                +".  5.- Buscar texto \n"
                +".  6.- Buscar palabras repetidas \n"
                +".  7.- Codificar fichero \n"
                +".  8.- Decodificar fichero \n"
                +".  0.- Salir \n"
                );      
            System.out.print("Introduzca una opcion: ");
            char opcion=(char)teclado.read();
            teclado.skip(1); 
            switch (opcion) {
                case '1':
                    Palabra.RepetidaApariciones(t);
                    break;
                case '2':
                    Palabra.numApariciones(t);
                    break;            
                case '3':
                    Auxiliar.frecuenciaPalabras(t);
                    break;
                case '4':
                    break;
                case '5':
                    break;
                case '6':
                    break;
                case '7':
                    Auxiliar.Codificar(t);
                    break;
                case '8':
                    break;
                default: break;
            }  
        } catch (IOException ex) {
            Logger.getLogger(AnalisisDeFicheros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
