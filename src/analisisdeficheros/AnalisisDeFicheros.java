package analisisdeficheros;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AnalisisDeFicheros {
    private static String t;
    private static BufferedReader teclado;
    
    public static void main(String[] args) throws Exception {
        try {
            System.out.print("Introduce nombre del fichero a analizar: ");
            teclado = new BufferedReader(new InputStreamReader(System.in));
            t = teclado.readLine();
            Menu();          
        } catch (IOException ex) {
            Logger.getLogger(AnalisisDeFicheros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void Menu() {
        try {
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
                    Menu();
                    break;
                case '2':
                    Palabra.numApariciones(t);
                    Menu();
                    break;            
                case '3':
                    Auxiliar.frecuenciaPalabras(t);
                    Menu();
                    break;
                case '4':
                    Palabra.BuscarPalabra(t);
                    Menu();
                    break;
                case '5':
                    Menu();
                    break;
                case '6':
                    Palabra.PalabrasRepetidas(t);
                    Menu();
                    break;
                case '7':
                    Auxiliar.Codificar(t);
                    Menu();
                    break;
                case '8':
                    Auxiliar.Decodificar(t);
                    Menu();
                    break;
                case '0':
                    break;
                default:
                    Menu();
                    break;
            }  
        } catch (IOException ex) {
            Logger.getLogger(AnalisisDeFicheros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
