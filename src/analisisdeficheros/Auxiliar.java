/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisisdeficheros;

import java.io.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Javier
 */

public class Auxiliar {
    public static void ContarCaracteres(String nomF){    
        try {
            FileReader fichero=new FileReader("ficheros/" + nomF);;
            int contador=0;
            int entrada=fichero.read();
            while (entrada!=-1) {
                // EL CASTING (char) no es necesario
                if ((((char)entrada >= 'a') && ((char)entrada <= 'z')) || ((char)entrada >= 'A') && ((char)entrada <= 'Z')
                   ||((char)entrada=='.')||((char)entrada==',')||((char)entrada==':')||((char)entrada=='@')
                   ||((char)entrada=='?')||((char)entrada=='!')||((char)entrada=='"')||((char)entrada=='(')
                   ||((char)entrada==')')||((char)entrada=='<')||((char)entrada=='>')){
                    //  . , : @ ? ! " ( ) < >
                    contador++;
                }
                entrada=fichero.read();
            }
            System.out.println("El número de caracteres es: " + contador);
            fichero.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Auxiliar.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Auxiliar.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("El numero de palabras es: "+ contador);
    }
    
    public static void ContarLineas(String nomF){
        try {
            BufferedReader fichero = new BufferedReader(new FileReader("ficheros/" + nomF));
            int numLineas = 0;
            while(fichero.readLine()!=null){
                numLineas++;
            }
            System.out.println("El número de lineas es: "+ numLineas);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Auxiliar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Auxiliar.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    //FALLA bucle no finaliza
    public static void frecuenciaPalabras(String nomF){
        try {
            BufferedReader fichero = new BufferedReader(new FileReader("ficheros/" + nomF));
            Palabra pal=new Palabra();
            final int MAX_PALABRAS=500;
            Palabra [] palabras=new Palabra[MAX_PALABRAS];
            int [] contadores=new int[MAX_PALABRAS];
            for (int i=0; i<palabras.length;i++) {
                palabras[i]=new Palabra();
            }
            int numPalabras=0;
            
            while (Palabra.quedenPalabra(fichero)) {
                pal.lectura(fichero);
                Palabra.copia(pal,palabras[numPalabras]);
                int indice=0;     
                while (!(Palabra.iguales(pal, palabras[indice]))) {
                    indice++;
                }
                if (indice<numPalabras) {
                    contadores[indice]++;
                }
                else {
                    contadores[indice]=1;
                    numPalabras++;
                }
            }
            
            for (int i=0; i< numPalabras; i++) {
                System.out.println("LA FRECUENCIA DE LA PALABRA " + palabras[i].toString()+
                        " ES " + contadores[i] + ".");
            }} catch (Exception ex) {
            Logger.getLogger(Auxiliar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void Codificar(String nomF){
        try {
            BufferedReader fichero = new BufferedReader(new FileReader("ficheros/" + nomF));
            BufferedWriter fCodificado = new BufferedWriter(new FileWriter("ficheros/"+ nomF +".cod.txt"));
            final char [] ALFABETO = "abcdefghijklmnopqrstuvwxyz.,:@?!\"()<>".toCharArray(); 
            int entrada=fichero.read();
            int SEMILLA = Aleatorio(ALFABETO.length-1);
            while (entrada!=-1) {
                int posicion = 0;
                // EL CASTING (char) no es necesario
                if ((((char)entrada >= 'a') && ((char)entrada <= 'z')) || ((char)entrada >= 'A') && ((char)entrada <= 'Z')
                   ||((char)entrada=='.')||((char)entrada==',')||((char)entrada==':')||((char)entrada=='@')
                   ||((char)entrada=='?')||((char)entrada=='!')||((char)entrada=='"')||((char)entrada=='(')
                   ||((char)entrada==')')||((char)entrada=='<')||((char)entrada=='>')){
                    while((char)entrada!=ALFABETO[posicion]){
                        posicion++;
                    }  
                    if((posicion + SEMILLA) < ALFABETO.length) {
                        fCodificado.write(ALFABETO[posicion + SEMILLA]);
                    }
                    else{
                        if((posicion + SEMILLA) <= ALFABETO.length){
                            fCodificado.write(ALFABETO[ALFABETO.length - (posicion + SEMILLA)]);
                        }
                        else{
                            fCodificado.write(ALFABETO[(posicion + SEMILLA) - ALFABETO.length]);
                        }
                    }   
                }
                if(entrada == 13) {
                    fCodificado.write("\r\n");
                }
                if((char)entrada == ' '){
                    fCodificado.write(' ');
                }
                entrada=fichero.read();
            }
            fichero.close();
            fCodificado.close();
            System.out.println("La semilla usada es: " + SEMILLA);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Auxiliar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Auxiliar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void Decodificar(String nomF){
        try {
            BufferedReader fichero = new BufferedReader(new FileReader("ficheros/"+ nomF +".cod.txt"));
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            final char [] ALFABETO = "abcdefghijklmnopqrstuvwxyz.,:@?!\"()<>".toCharArray(); 
            System.out.print("Introduce semilla: ");
            int SEMILLA = Integer.parseInt(teclado.readLine());
            int entrada=fichero.read();
            while (entrada!=-1) {
                int posicion = 0;
                if ((((char)entrada >= 'a') && ((char)entrada <= 'z')) || ((char)entrada >= 'A') && ((char)entrada <= 'Z')
                   ||((char)entrada=='.')||((char)entrada==',')||((char)entrada==':')||((char)entrada=='@')
                   ||((char)entrada=='?')||((char)entrada=='!')||((char)entrada=='"')||((char)entrada=='(')
                   ||((char)entrada==')')||((char)entrada=='<')||((char)entrada=='>')){
//                    //  . , : @ ? ! " ( ) < >
                    while((char)entrada!=ALFABETO[posicion]){
                        posicion++;
                    }  
                    if((posicion - SEMILLA) < 0) {
                        System.out.print(ALFABETO[ALFABETO.length + (posicion - SEMILLA)]);
                    }
                    else{
                        System.out.print(ALFABETO[posicion - SEMILLA]);
                    }   
                }
                if(entrada == 13) {
                    System.out.println();
                }
                if((char)entrada == ' '){
                    System.out.print(' ');
                }
                    entrada=fichero.read();
            }
            fichero.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Auxiliar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Auxiliar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static int Aleatorio(int tamaño) {
        Random rnd = new Random();
        return (1 + (int)(rnd.nextInt(tamaño - 1)));
    }
}

