import java.io.PrintStream; //Import necesarios
import java.nio.charset.StandardCharsets; 

public class Main { //Declaración de la clase principal
    public static void main(String[] args) throws Exception { //Método principal (lanza excepciones)
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8)); //Salida en UTF8
        int[][] mapa = LeerMatrizCSV.leerMapa("mapa.csv");  //Ubicación del archivo que contiene el mapa
        int C = 20; // ejemplo de coste máximo permitido
        
        if (mapa != null){  //Si la matriz existe, mostrar mapa
            MostrarMapa.mostrarMapa(mapa);
            
             //mostrar el mapa con emojis
        
            MatrizEmojis.MatrizEmojis();

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
            
            System.out.println("El tiempo que tarda en encontrar la ruta de la marcha silenciosa es: " + tiempoMarchaMS + "ms" );  
            
            
            // problema 2
              
            inicio = System.nanoTime();
            boolean[][] obligatorias = OjoCirithUngol.detectarObligatorias(mapa);
            fin = System.nanoTime();
            long tiempoOjoCirith = fin - inicio;
            double tiempoOjoCirithMS = tiempoOjoCirith / 1000000.0;
            
            // Imprimir coordenadas de las celdas obligatorias
            OjoCirithUngol.imprimirObligatorias(obligatorias);
            
            System.out.println("El tiempo empleado para el calculo del problema 2 es: " + tiempoOjoCirithMS + "ms" );  
            
            // problema 3
            
            inicio = System.nanoTime();
            SenderosDruadan.encontrarRutas(mapa, C);
            fin = System.nanoTime();
            long tiempoSenderosDruadan = fin - inicio;
            double tiempoSenderosDruadanMS = tiempoSenderosDruadan / 1000000.0;
            
            // imprimir rutas
            SenderosDruadan.imprimirRutas();
            
            System.out.println("El tiempo empleado para el calculo del problema 3 es: " + tiempoSenderosDruadanMS + "ms" );  

       } else {             //Si no existe, dar mensaje de error
            System.out.println("No se ha podido leer el mapa. ");
       }
    }
} 