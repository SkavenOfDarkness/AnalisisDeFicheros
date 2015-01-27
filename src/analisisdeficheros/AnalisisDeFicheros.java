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
        Inicio();
    }
    
    public static void Inicio(){
        try {
            System.out.print("Introduce nombre del fichero a analizar: ");
            teclado = new BufferedReader(new InputStreamReader(System.in));
            t = teclado.readLine();
            Menu();
        } catch (IOException ex) {
            System.err.println("Error en la obtención de los datos del teclado. El error es: " + ex);
        }
    }
    
    private static void Menu() {
        try {
            //Generador de informes
            Auxiliar.informeF(t);
            //MENU
            System.out.print(
                     "----------------------------------\n"
                    +"*       _-Menu Principal-_       *\n"
                    +"----------------------------------\n"
                    +"*  1.- Letra mas repetida        *\n"
                    +"*  2.- Numero caracteres         *\n"
                    +"*  3.- Palabra mas repetida      *\n"
                    +"*  4.- Buscar palabra            *\n"
                    +"*  5.- Buscar texto              *\n"
                    +"*  6.- Buscar palabras repetidas *\n"
                    +"*  7.- Codificar fichero         *\n"
                    +"*  8.- Decodificar fichero       *\n"
                    +"*  0.- Salir                     *\n"
            );      
            System.out.print("Introduzca una opcion: ");
            char opcion=(char)teclado.read();
            teclado.skip(1);
            switch (opcion) {
                case '1':
                    Auxiliar.FormatoPantalla();
                    System.out.print(
                     "-------------------------------\n"
                    +"*  _-1.Letra mas repetida-_   *\n"
                    +"-------------------------------\n");
                    Palabra.RepetidaApariciones(t);
                    System.out.println();
                    Menu();
                    break;
                case '2':
                    Auxiliar.FormatoPantalla();
                    System.out.print(
                     "-------------------------------\n"
                    +"*  _-2.Letra mas repetida-_   *\n"
                    +"-------------------------------\n");
                    Palabra.numApariciones(t);
                    System.out.println();
                    Menu();
                    break;            
                case '3':
                    Auxiliar.FormatoPantalla();
                    System.out.print(
                     "-------------------------------\n"
                    +"* _-3.Palabra mas repetida-_  *\n"
                    +"-------------------------------\n");
                    Palabra.frecuenciaPalabras(t);
                    System.out.println();
                    Menu();
                    break;
                case '4':
                    Auxiliar.FormatoPantalla();
                    System.out.print(
                     "-------------------------------\n"
                    +"*    _-4.Buscar palabra-_     *\n"
                    +"-------------------------------\n");
                    Palabra.BuscarPalabra(t);
                    System.out.println();
                    Menu();
                    break;
                case '5':
                    Auxiliar.FormatoPantalla();
                    System.out.print(
                     "-------------------------------\n"
                    +"*    _-5.Buscar texto-_       *\n"
                    +"-------------------------------\n");
                    Auxiliar.BuscarTexto(t);
                    teclado.skip(1);
                    System.out.println();
                    Menu();
                    break;
                case '6':
                    Auxiliar.FormatoPantalla();
                    System.out.print(
                     "---------------------------------\n"
                    +"*_-6.Buscar palabras repetidas-_*\n"
                    +"---------------------------------\n");
                    Palabra.PalabrasRepetidas(t);
                    System.out.println();
                    Menu();
                    break;
                case '7':
                    Auxiliar.FormatoPantalla();
                    System.out.print(
                     "-------------------------------\n"
                    +"*   _-7.Codificar fichero-_   *\n"
                    +"-------------------------------\n");
                    Auxiliar.Codificar(t);
                    System.out.println();
                    Menu();
                    break;
                case '8':
                    Auxiliar.FormatoPantalla();
                    System.out.print(
                     "-------------------------------\n"
                    +"*   _-Decodificar fichero-_   *\n"
                    +"-------------------------------\n");
                    Auxiliar.Decodificar(t);
                    System.out.println();
                    Menu();
                    break;
                case '0':
                    System.exit(0);
                default:
                    System.out.println();
                    Menu();
                    break;
            }  
        } catch (IOException ex) {
            Logger.getLogger(AnalisisDeFicheros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
