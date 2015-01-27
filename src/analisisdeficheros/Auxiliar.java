package analisisdeficheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Auxiliar {
    public static void informeF(String nomF) {
        Palabra.setCaracter(Palabra.getESPACIO());
        Palabra.setFil(1);
        Palabra.setCol(0);
        Palabra.ContarCaracteres(nomF);
        Palabra.ContarPalabras(nomF);
        Palabra.ContarLineas(nomF);
        Palabra.setCaracter(Palabra.getESPACIO());
        Palabra.setFil(1);
        Palabra.setCol(0);
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

    public static void BuscarTexto(String nomF) {
        try {
            BufferedReader fichero = new BufferedReader(new FileReader("ficheros/"+ nomF));
            Palabra frase[] = new Palabra[Palabra.getMaxPalabras()];
            Palabra palComp = new Palabra();
            for (int i = 0; i < frase.length; i++) {
                frase[i] = new Palabra();
            }
            int col[] = new int[Palabra.getMaxPalabras()];
            for (int i = 0; i < col.length; i++) {
                col[i] = 0;
            }
            int fil[] = new int[Palabra.getMaxPalabras()];
            for (int i = 0; i < col.length; i++) {
                fil[i] = 0;
            }
            System.out.print("Introduce un texto a ser buscado(finalizado en punto): ");
            int y = 0, i = 0;
            while(Palabra.quedenPalabra()) {
                frase[y].lectura();
                y++;
            }
            boolean g = true;
            while((Palabra.quedenPalabra(fichero)) && (i < y)) {
                palComp.lectura(fichero);
                if (Palabra.iguales(frase[i], palComp)) {
                    col[i] = palComp.getCol();
                    fil[i] = palComp.getFil();
                    if(i == y-1){
                        for (int j = 0; j < y; j++) {
                            System.out.println("Palabra encontrada: " + frase[j]);
                            System.out.println("Fila: " + fil[j]);
                            System.out.println("Columna: " + (col[j]-frase[j].getCaracteres()));
                        }
                    }
                    i++;
                }
                else {
                    i = 0;
                }
            }
            if(i == 0) {
                System.out.println("No se ha encontrado coincidencia");
            }
            fichero.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Auxiliar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Auxiliar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Auxiliar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void FormatoPantalla() {
        System.out.println("----------------------------------");
        System.out.println();
        System.out.println();
        System.out.println();
    }
}

