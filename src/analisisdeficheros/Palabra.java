/*****************
 * CLASE Palabra *
 *****************/
package analisisdeficheros;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Palabra {
    // Atributos
    private static final char FINAL_SECUENCIA = '.';
    private static final int MAXIMO = 50;
    private static final char ESPACIO = ' ';
    private static char caracter = ESPACIO;
    private final char [] caracteres = new char[MAXIMO];
    private int numCaracteres = 0;
    
    // Interface
    // Metodos constructores
    
    public Palabra() {}
    
    public void lectura() throws Exception {
        numCaracteres = 0;
        while (((caracter >= 'a') && (caracter <= 'z')) || ((caracter >= 'A') && (caracter <= 'Z'))) {
            caracteres[numCaracteres] = caracter;
            numCaracteres++;
            caracter = (char)System.in.read();
        }
    }
    
    public void lectura(BufferedReader f) {
        numCaracteres=0;
        try {
            while ((caracter!=FINAL_SECUENCIA)&&(caracter!=ESPACIO)) {
                caracteres[numCaracteres]=caracter;
                numCaracteres++;
                caracter=(char) f.read();
            }
        }catch (IOException e) {}
    }
    
    public void escritura(BufferedWriter f) {
        try {
            for (int i=0;i<numCaracteres;i++) {
                f.write(caracteres[i]);
            }
            f.write(ESPACIO);
          //f.newLine();
        }catch (IOException e) {}
    }
    
    private static void buscarPalabra() throws Exception {
        //while (caracter == ESPACIO)
        while ((caracter != FINAL_SECUENCIA) && !((caracter >= 'a') && (caracter <= 'z') && (caracter >= 'A') && (caracter <= 'Z'))) {
            caracter = (char)System.in.read();
        }
    }
    
    public  static void buscarPalabra(BufferedReader f) {
        try {
            while (caracter==ESPACIO) {
                caracter= (char) f.read();
            }
        } catch (IOException e) {}
    }
    
    public static boolean quedenPalabra() throws Exception {
        buscarPalabra();
        return (caracter != FINAL_SECUENCIA);
    }
    
    public static boolean quedenPalabra(BufferedReader f) {
        buscarPalabra(f);
        return (caracter!=(char) -1);
    }
    
    @Override
    public String toString() {
        String pal = "";
        for (int i = 0; i < numCaracteres; i++) {
            pal = pal + caracteres[i];
        }
        return pal;
    }
    
    public String toStringInvertido() {
        String pal = "";
        for (int i = numCaracteres - 1; i >= 0; i--) {
            pal = pal + caracteres[i];
        }
        return pal;
    }
    
    public int getCaracteres() {
        return numCaracteres;
    }
    
    public int numVocales() {
        int contador = 0;
        for (int i = 0; i < numCaracteres; i++) {
            if ((caracteres[i] == 'a') || (caracteres[i] == 'e') || (caracteres[i] == 'i') 
                    || (caracteres[i] == 'o') || (caracteres[i] == 'u')) {
                contador++;
            }
        }
        return contador;
    }
    
    public boolean esCapicua() throws Exception {
        int inicio = 0;
        int fin = numCaracteres - 1;
        while ((caracteres[inicio] == caracteres[fin]) && (inicio < fin - 2)) {
            inicio++;
            fin--;
        }
        return (caracteres[inicio] == caracteres[fin]);
    }
    
    public boolean tieneTodasLetras() throws Exception {
        char [] abecedario = {'a','b','c','d','e','f','g','h','i','j','k','l',
            'm','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        int contador = 0;
        if (numCaracteres >= 26) {
            for (int i = 0; i < numCaracteres; i++) {
                for (int j = 0; j < abecedario.length; j++) {
                    if (caracteres[i] == abecedario[j]) {
                        contador++;
                        abecedario[j] = '.';
                        break;
                    }
                }
            }
            return (contador == abecedario.length);
        }
        else {
            return false;
        }
    }
    
    public boolean tieneTodasVocales() throws Exception {
        int[] contadoresVocales = {0,0,0,0,0};
        for (int indice = 0; indice < numCaracteres; indice++) {
            switch (caracteres[indice]) {
                case 'a': 
                    contadoresVocales[0]++;
                    break;
                case 'e':
                    contadoresVocales[1]++;
                    break;
                case 'i':
                    contadoresVocales[2]++;
                    break;
                case 'o':
                    contadoresVocales[3]++;
                    break;
                case 'u':
                    contadoresVocales[4]++;
                    break;
                default:
            }
        }
        
        for (int i = 0; i < contadoresVocales.length; i++) {
            if (contadoresVocales[i] == 0) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean iguales(Palabra a, Palabra b) throws Exception {
        if (a.numCaracteres == b.numCaracteres) {
            int indice = 0;
            while ((a.caracteres[indice] == b.caracteres[indice]) && (indice < a.numCaracteres - 1)) {
                indice++;
            }
            return (a.caracteres[indice] == b.caracteres[indice]);
        }
        else {
            return false;
        }
    }
    
    public static void copia(Palabra a, Palabra b) {
        for (int i = 0; i < a.numCaracteres; i++) {
            b.caracteres[i] = a.caracteres[i];
        }
        b.numCaracteres = a.numCaracteres;
    }
    
    public static String informeF(BufferedReader buffer) {
        int teclado, numCaracteres = 0, numLineas = 0, numPalabras = 0;
        try {          
            while((teclado = buffer.read()) != -1) {
                if(((char)teclado != ' ') && ((char)teclado != '\n')) {
                    numCaracteres++;
                }
                if((char)teclado == '\n') {
                    numLineas++;
                }
                if(((char)teclado == ' ') || ((char)teclado == '\n')) {
                    numPalabras++;
                }
            }    
        } catch (IOException ex) {
            Logger.getLogger(Palabra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ("  Numero de caracteres: " + numCaracteres + "\n  Numero de palabras: " + numPalabras + "\n  Numero de lineas: " + numLineas);
    }
}
