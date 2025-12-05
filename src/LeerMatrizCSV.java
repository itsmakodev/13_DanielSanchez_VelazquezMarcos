import java.io.BufferedReader; //Imports necesarios
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LeerMatrizCSV {  //Clase que lee una matriz a partir de un .csv dado

    public static int[][] leerMapa(String archivo) { //Método que lee el mapa
        List<int[]> filas = new ArrayList<>();       //Declara y crea un Array dinámico de int
        try (InputStream is = LeerMatrizCSV.class.getResourceAsStream(archivo); //Intenta leer la matriz de entrada en el .csv
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) { //Instrucción para leer texto a partir de un
            //.csv dado
            String linea;   //Declara la variable línea como String
            while ((linea = br.readLine()) != null) { //Mientras que no hayamos llegado al final de la línea...
                String[] partes = linea.split(","); //Separa las líneas en un vector de String llamado partes
                   //que las partes están separadas por comas (',')
                int[] fila = new int[partes.length]; //Cada fila es un vector de enteros con la longitud del vector partes
                for (int i = 0; i < partes.length; i++) { //Bucle for
                    //Inicializa i = 0, mientras que i < partes.length, el bucle se ejecuta, al final de cada iteración, i++.
                    fila[i] = Integer.parseInt(partes[i].trim()); //Convertimos String a int y guardamos el valor en fila[i]
                }
                filas.add(fila);    //Añadimos la fila nueva al final de la matriz actual
            }

        } catch (IOException e) { //Si no se logra leer la matriz, capturamos excepción
            e.printStackTrace();
            return null;
        }

        int[][] matriz = new int[filas.size()][];  //Declaramos matriz como una matriz de enteros del tamaño del vector filas
        //.size() al tratarse de arrreglos bidimensionales de enteros
        for (int i = 0; i < filas.size(); i++) {   // Bucle for
            //Inicializa i = 0, mientras que i < filas.size(), el bucle se ejecuta, al final de cadad iteración, i++.
            matriz[i] = filas.get(i);              //Declara  que matriz[i] es el valor i-ésimo del arreglo dinámico filas.
        }

        return matriz;  //Return
    }
}
