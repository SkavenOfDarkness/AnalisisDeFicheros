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
    public static void ContarCaracteres(String pal) throws IOException{
        FileReader fichero=new FileReader("ficheros/" + pal);;
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
    
}

