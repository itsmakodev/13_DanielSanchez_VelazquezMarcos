    public class MedicionTiempos {  //Clase que mide tiempos
        //Método que recibe un atributo de tipo runnable y calcula el tiempo en nanosegundos
       public static long medirTiempo(Runnable f){ 
            long inicio = System.nanoTime();
            f.run();
            long fin = System.nanoTime();
            return fin - inicio;
        }
    }
