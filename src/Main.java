import java.io.PrintStream; //Import necesarios
import java.nio.charset.StandardCharsets; 

public class Main { //Declaración de la clase principal
    public static void main(String[] args) throws Exception { //Método principal (lanza excepciones)
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8)); //Salida en UTF8
        int[][] mapa = LeerMatrizCSV.leerMapa("mapa.csv");  //Ubicación del archivo que contiene el mapa
        
        if (mapa != null){  //Si la matriz existe, mostrar mapa
            MostrarMapa.mostrarMapa(mapa);
            
            // problema 1
            
            // el calculo de los tiempos lo hacemos con nanos, nos quitamos de runnable y otras historias raras
            long inicio = System.nanoTime();
            int mejor = MarchaSilenciosa.resolver(mapa);
            long fin = System.nanoTime();
            long tiempoMarchaSilenciosa = fin - inicio;
            double tiempoMarchaMS = tiempoMarchaSilenciosa / 1000000.0;
            
            // imprimimos solucion problema 1
            System.out.println("El coste mínimo de la ruta es: " + mejor);
            System.out.println(mejor + " Es la suma de todos los pasos de la ruta por el que hemos pasado para llegar al final");
            
            System.out.println("El tiempo que tarda en encontrar la ruta de la marcha silenciosa es: " + tiempoMarchaMS);   
            
            // problema 2
            
            
          
       } else {             //Si no existe, dar mensaje de error
            System.out.println("No se ha podido leer el mapa. ");
       }
    }
} 