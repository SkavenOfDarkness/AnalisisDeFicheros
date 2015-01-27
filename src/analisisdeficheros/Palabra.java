/*****************
 * CLASE Palabra *
 *****************/
package analisisdeficheros;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    private static int col = 0, fil = 1;
    private int numCaracteres = 0; 
    
    // Interface
    // Metodos constructores
    public Palabra() {
    }
    
    public Palabra(String p) {
        char [] c = new char[p.toCharArray().length];
        numCaracteres = 0;
        for (int i = 0; i < p.toCharArray().length; i++) {
            caracteres[numCaracteres] = p.toCharArray()[i];
            numCaracteres++;
        }
    }

    public static int getCol() {
        return col;
    }

    public static int getFil() {
        return fil;
    }
    
    public static int getMaxPalabras() {
        return MAXIMOPALABRAS;
    }
    
    public int getCaracteres() {
        return numCaracteres;
    }

    public static char getESPACIO() {
        return ESPACIO;
    }

    public static void setCaracter(char caracter) {
        Palabra.caracter = caracter;
    }

    public static void setCol(int col) {
        Palabra.col = col;
    }

    public static void setFil(int fil) {
        Palabra.fil = fil;
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
            while ((caracter!=(char)FINAL_FICHERO)&&(caracter!=ESPACIO)&&(caracter !='\n')&&(caracter!='\r')
                    &&(caracter!=',')&&(caracter!=':')&&(caracter!='@')&&(caracter!='?')&&(caracter!='!')
                    &&(caracter!='"')&&(caracter!='(')&&(caracter!=')')&&(caracter!='<')&&(caracter!='>')) {
                col++;
                caracteres[numCaracteres]=caracter;
                numCaracteres++;
                caracter=(char) f.read();
            }
        }catch (IOException e) {
        
        }
    }
    
    private static void buscarPalabra() throws Exception {
        while ((caracter==(char)ESPACIO)||(caracter =='\n')||(caracter=='\r')||(caracter==',')||(caracter==':')
                ||(caracter=='@')||(caracter=='?')||(caracter=='!')||(caracter=='"')||(caracter=='(')
                ||(caracter==')')||(caracter=='<')||(caracter=='>')) {
            caracter = (char)System.in.read();
        }
    }
    
    public  static void buscarPalabra(BufferedReader f) {
        try {
            while ((caracter==(char)ESPACIO)||(caracter =='\n')||(caracter=='\r')||(caracter==',')||(caracter==':')
                    ||(caracter=='@')||(caracter=='?')||(caracter=='!')||(caracter=='"')||(caracter=='(')
                    ||(caracter==')')||(caracter=='<')||(caracter=='>')) {
                if((caracter == '\r') || (caracter == '\n')) {
                    fil++;
                    col = 1;
                }
                if(caracter == (char)ESPACIO) {
                    col++;
                }
                caracter= (char) f.read();      
            }
        } catch (IOException e) {
            System.err.println("Error en buscarPalabra para fila |||||| " + e);
        }
    }
    
    public static boolean quedenPalabra() throws Exception {
        buscarPalabra();
        return (caracter != (char)FINAL_SECUENCIA);
    }
    
    public static boolean quedenPalabra(BufferedReader f) {
        caracter = ' ';
        buscarPalabra(f);
        return (caracter!=(char) FINAL_FICHERO);
    }
    
    @Override
    public String toString() {
        String pal = "";
        for (int i = 0; i < numCaracteres; i++) {
            pal = pal + caracteres[i];
        }
        return pal;
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
                System.out.println("Caracter: "+caracPosibles[i]+" - Cantidad: "+ contCaracteres[i]);
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
                    System.out.println("Fila: " + (fil));
                    System.out.println("Columna: " + (col-a.numCaracteres));
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
                    System.out.println("Columna: " + (col-b.numCaracteres));
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
            boolean existe = true;
            /* Usamos un array de strings, en lugar de uno de palabras, devido a que el array de palabras
            coge el valor de las palabra que está leyendo del fichero ya que al ser de la misma clase comparten
            las variables y no distingue en cual debe asignarlo, por ello lo asigna en todas las palabras.*/
            String [] Spalabras = new String[MAXIMOPALABRAS];
            int [] contadores = new int[MAXIMOPALABRAS];
            //Inicializacion de arrays palabras y contadores
            for (int i = 0; i < Spalabras.length; i++) {
                Spalabras[i] = "";
            }
            for (int i = 0; i < contadores.length; i++) {
                contadores[i] = 0;
            }
            int numPalabras=0;
            int identificador = contadores[0];
            while (Palabra.quedenPalabra(buffer)) {
                pal.lectura(buffer);
                existe = true;
                for (int i = 0; i < MAXIMOPALABRAS; i++) {
                    if(pal.toString().equals(Spalabras[i])) {
                        contadores[i]++;
                        existe = false;
                    }
                }
                if(existe) {
                    Spalabras[numPalabras] = pal.toString();
                    contadores[numPalabras]++;
                    numPalabras++;
                }
            }
            for (int i = 0; i < contadores.length-1; i++) {
                int num = 0;
                if(contadores[num]<contadores[i+1]){
                    num = contadores[i+1];
                    identificador = i+1;
                }
            }
            System.out.println("Palabra: " +Spalabras[identificador] + " - Numero de veces repetidas: "+ contadores[identificador]);
            buffer.close();
        } catch (Exception ex) {
            Logger.getLogger(Auxiliar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public static void ContarCaracteres(String nomF){    
        try {
            BufferedReader buffer=new BufferedReader(new FileReader("ficheros/" + nomF));
            int contador=0;
            int entrada=buffer.read();
            while (entrada!=-1) {
                // EL CASTING (char) no es necesario
                if ((((char)entrada >= 'a') && ((char)entrada <= 'z')) || ((char)entrada >= 'A') && ((char)entrada <= 'Z')
                   ||((char)entrada=='.')||((char)entrada==',')||((char)entrada==':')||((char)entrada=='@')
                   ||((char)entrada=='?')||((char)entrada=='!')||((char)entrada=='"')||((char)entrada=='(')
                   ||((char)entrada==')')||((char)entrada=='<')||((char)entrada=='>')){
                    //  . , : @ ? ! " ( ) < >
                    contador++;
                }
                entrada=buffer.read();
            }
            System.out.println("----------------------------------");
            System.out.println("       Informe del fichero        ");
            System.out.println("----------------------------------");
            System.out.println("El número de caracteres es: " + contador);
            buffer.close();
        } catch (FileNotFoundException ex) {
            System.err.println("Fichero no encontrado");
            AnalisisDeFicheros.Inicio();
        } catch (IOException ex) {
            Logger.getLogger(Auxiliar.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    public static void ContarPalabras(String nomF){    
        int contador = 0;
        try {
            BufferedReader buffer=new BufferedReader(new FileReader("ficheros/" + nomF));
            Palabra pal=new Palabra();
            while(Palabra.quedenPalabra(buffer)){
                pal.lectura(buffer);
                contador++;
            }
            if(contador <= MAXIMOPALABRAS){
                System.out.println("El numero de palabras es: " + contador);
            }
            else{
                System.out.println("El fichero contiene más de " + MAXIMOPALABRAS + " palabras, exactamente: " + contador);
                System.out.println();
                AnalisisDeFicheros.Inicio();
            }
            buffer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Auxiliar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Auxiliar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void ContarLineas(String nomF){
        try {
            BufferedReader buffer = new BufferedReader(new FileReader("ficheros/" + nomF));
            int numLineas = 0;
            while(buffer.readLine()!=null){
                numLineas++;
            }
            System.out.println("El número de lineas es: "+ numLineas);
            buffer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Auxiliar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Auxiliar.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}
