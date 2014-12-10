/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisisdeficheros;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Javier
 */

public class Auxiliar {
    public static void ContarCaracteres(String nomF) throws IOException{
        FileReader fichero=new FileReader("ficheros/" + nomF);;
        try {
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
            }   System.out.println("El número de caracteres es: " + contador);
            fichero.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Auxiliar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fichero.close();
            } catch (IOException ex) {
                Logger.getLogger(Auxiliar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void frecuenciaPalabras(String nomF) throws IOException{
        try {
            Palabra pal=new Palabra();
            final int MAX_PALABRAS=500;
            Palabra [] palabras=new Palabra[MAX_PALABRAS];
            int [] contadores=new int[MAX_PALABRAS];
            for (int i=0; i<palabras.length;i++) {
                palabras[i]=new Palabra();
            }
            int numPalabras=0;
            
            System.out.print("INTRODUCIR TEXTO: ");
            while (Palabra.quedenPalabra()) {
                pal.lectura();
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
}

