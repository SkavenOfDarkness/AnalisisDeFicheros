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
    private static final int MAXIMOPALABRAS=500;
    private static final char ESPACIO = ' ';
    private static final int FINAL_FICHERO=-1;
    private static char caracter = ESPACIO;
    private final char [] caracteres = new char[MAXIMO];
    private static int col = 1, fil = 1;
    private int numCaracteres = 0; 
    
    // Interface
    // Metodos constructores
    
    public Palabra() {}
    
    public Palabra(String p) {
        char [] c = new char[p.toCharArray().length];
        numCaracteres = 0;
        for (int i = 0; i < p.toCharArray().length; i++) {
            caracteres[numCaracteres] = p.toCharArray()[i];
            numCaracteres++;
        }
    }
    
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
            while ((caracter!=(char) FINAL_FICHERO)&&(caracter!=ESPACIO)&&(caracter !='\n')&&(caracter!='\r')) {
                col++;
                caracteres[numCaracteres]=caracter;
                numCaracteres++;
                caracter=(char) f.read();
                if((caracter == '\r') || (caracter == '\n')) {
                    fil++;
                    col = 1;
                }
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
        while ((caracter==ESPACIO)||(caracter =='\n')||(caracter=='\r')) {
            caracter = (char)System.in.read();
        }
    }
    
    public  static void buscarPalabra(BufferedReader f) {
        try {
//            while ((caracter == (char)ESPACIO)||(caracter =='\n')||(caracter=='\r')) {
            while ((caracter == (char)ESPACIO)||(caracter =='\n')||(caracter=='\r')) {
                caracter= (char) f.read();      
            }
        } catch (IOException e) {
            System.err.println("Error en buscarPalabra para fila |||||| " + e);
        }
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
    
    public static void informeF(String nomF) {
        caracter = ESPACIO;
        fil = 1;
        col = 1;
        Auxiliar.ContarCaracteres(nomF);
        Auxiliar.ContarPalabras(nomF);
        Auxiliar.ContarLineas(nomF);
        caracter = ESPACIO;
        fil = 1;
        col = 1;
    }
    
    public static void cantidaPalabras(BufferedReader buffer){
        Palabra pal[] = new Palabra[MAXIMOPALABRAS];
        for (int i = 0; i < pal.length; i++) {
          pal[i]=new Palabra();
        }
        int i = 0;
        while(quedenPalabra(buffer)){
            pal[i].lectura(buffer);
            System.out.println(" Palabra: "+ pal[i] );
            i++;
        }
    }
    
    // Opcion 1
    public static void RepetidaApariciones(String nomF){
        BufferedReader fichero = null;
        char [] abecedario = {'a','b','c','d','e','f','g','h','i','j','k','l',
                'm','n','o','p','q','r','s','t','u','v','w','x','y','z'};
            int [] numAbecedario = new int[abecedario.length] ;
            int teclado;
            int identificador = numAbecedario[0];
        try {
            fichero = new BufferedReader(new FileReader("ficheros/" + nomF));
            while((teclado = fichero.read()) != -1) {
                for (int i = 0; i < numAbecedario.length; i++) {
                    if((char)teclado == abecedario[i]){
                        numAbecedario[i]= numAbecedario[i]+1;
                    }
                }
            }
            for (int e = 0; e < numAbecedario.length-1; e++) {
                int num = 0;
                if(numAbecedario[num] <(numAbecedario[e+1])){
                    num = numAbecedario[e+1];
                    identificador =e+1;
                }
            }
            fichero.close();
        } catch (FileNotFoundException ex){
            Logger.getLogger(Palabra.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Palabra.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Letra: " +abecedario[identificador] + " - Numero de veces repetidas: "+ numAbecedario[identificador]);
    }
    
    //Opcion 2
    public static String numApariciones(String nomF){
        int teclado;
        char [] caracPosibles = {'a','b','c','d','e','f','g','h','i','j','k','l',
        'm','n','o','p','q','r','s','t','u','v','w','x','y','z','.',',',':','@','?','!','"','(',')','<','>',' '};
        int [] contCaracteres = new int[caracPosibles.length];
        BufferedReader fichero = null;
        try {
            fichero = new BufferedReader(new FileReader("ficheros/" + nomF));
            while((teclado = fichero.read()) != -1) {
                for (int i = 0; i < caracPosibles.length; i++) {
                    if((char)teclado == caracPosibles[i]){   
                        contCaracteres[i]++;
                    }
                }
            }
            for (int i = 0; i < caracPosibles.length; i++) {
                System.out.println("Caracter:"+caracPosibles[i]+" - Cantidad: "+ contCaracteres[i]);
            }
            fichero.close();
        } catch (IOException ex){
            Logger.getLogger(Palabra.class.getName()).log(Level.SEVERE, null, ex);
        }   
        return "";
    }
    
    public static void PalabrasRepetidas(String nomF){
        try {
            BufferedReader buffer = new BufferedReader(new FileReader("ficheros/" + nomF));
            Palabra a = new Palabra();
            Palabra b = new Palabra();
            if(Palabra.quedenPalabra(buffer)){
                a.lectura(buffer);
            }
            while(Palabra.quedenPalabra(buffer)){
                b.lectura(buffer);
                if(Palabra.iguales(a, b)) {
                    System.out.println("Palabra igual es: " + a);
                    System.out.println("Fila: " + fil);
                    System.out.println("Columna: " + col);
                }
                Palabra.copia(b, a);
            }
            buffer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Palabra.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Palabra.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Palabra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public static void BuscarPalabra(String nomF) {
        try {
            BufferedReader buffer = new BufferedReader(new FileReader("ficheros/" + nomF));
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Introduce una palabra para ser buscada: ");
            Palabra a = new Palabra(teclado.readLine());
            Palabra b = new Palabra();
            while(Palabra.quedenPalabra(buffer)){
                b.lectura(buffer);
                if(Palabra.iguales(a, b)) {
                    System.out.println("Palabra encontrada: " + b);
                    System.out.println("Fila: " + fil);
                    System.out.println("Columna: " + col);
                }
            }
            buffer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Palabra.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Palabra.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Palabra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public static void frecuenciaPalabras(String nomF){
        try {
            BufferedReader buffer = new BufferedReader(new FileReader("ficheros/" + nomF));
            Palabra pal = new Palabra();
            final int MAX_PALABRAS=10;
            boolean existe = true;
            Palabra [] palabras = new Palabra[MAX_PALABRAS];
            int [] contadores = new int[MAX_PALABRAS];
            //Inicializacion de arrays palabras y contadores
            for (int i = 0; i < palabras.length; i++) {
                palabras[i] = new Palabra();
            }
            for (int i = 0; i < contadores.length; i++) {
                contadores[i] = 0;
            }
            int numPalabras=0;
            while (Palabra.quedenPalabra(buffer)) {
                pal.lectura(buffer);
                existe = true;
                for (int i = 0; i < MAX_PALABRAS; i++) {
                    if(iguales(pal,palabras[i])) {
                        contadores[i]++;
                        existe = false;
                    }
                }
                if(existe) {
                    palabras[numPalabras] = pal;
                    contadores[numPalabras]++;
                    numPalabras++;
                }
            }
            for (int i = 0; i < numPalabras; i++) {
                System.out.println("Palabra: " + palabras[i] + " y su cantidad es: "+ contadores[i] );
            }
            buffer.close();
        } catch (Exception ex) {
            Logger.getLogger(Auxiliar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
