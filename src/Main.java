import java.io.PrintStream; //Import necesarios
import java.nio.charset.StandardCharsets; 

public class Main { //Declaración de la clase principal
    public static void main(String[] args) throws Exception { //Método principal (lanza excepciones)
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8)); //Salida en UTF8
        int[][] mapa = LeerMatrizCSV.leerMapa("mapa.csv");  //Ubicación del archivo que contiene el mapa
        
        if (mapa != null){  //Si la matriz existe, mostrar mapa
            MostrarMapa.mostrarMapa(mapa);
       } else {             //Si no existe, dar mensaje de error
            System.out.println("No se ha podido leer el mapa. ");
       }
    }
} 