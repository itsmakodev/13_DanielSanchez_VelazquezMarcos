/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DaniMaja
 */
public class MarchaSilenciosa {

    private static int N;                      // Tamaño del mapa
    private static int mejorCoste;            // Guarda el mejor coste encontrado
    private static boolean[][] visitado;      // Marca celdas usadas en el camino
    private static final Posicion[] MOVS = {
        new Posicion(1, 0),   // Abajo
        new Posicion(0, 1)    // Derecha
    };

    // Validar que la posición está dentro del mapa
    static boolean valida(Posicion p) {
        return (0 <= p.getFila() && p.getFila() < N &&
                0 <= p.getColumna() && p.getColumna() < N);
    }

    // Marca como usada
    static void marcar(Posicion p) {
        visitado[p.getFila()][p.getColumna()] = true;
    }

    // Desmarca
    static void desmarcar(Posicion p) {
        visitado[p.getFila()][p.getColumna()] = false;
    }

    // Caso base: llegamos a destino
    static boolean llegamos(Posicion a, Posicion b) {
        return a.getFila() == b.getFila() && a.getColumna() == b.getColumna();
    }

    //   MÉTODO RECURSIVO BACKTRACKING
    static void bt(int[][] mapa, Posicion actual, Posicion salida, int costeActual) {

        // Si ya superamos el mejor coste actual, cortamos (poda)
        if (costeActual >= mejorCoste) return;

        if (llegamos(actual, salida)) {
            mejorCoste = costeActual;
            return;
        }

        // Probar movimientos abajo y derecha
        for (Posicion mov : MOVS) {
            Posicion sig = new Posicion(
                actual.getFila() + mov.getFila(),
                actual.getColumna() + mov.getColumna()
            );

            if (valida(sig) && !visitado[sig.getFila()][sig.getColumna()]) {

                marcar(sig);
                bt(mapa, sig, salida, costeActual + mapa[sig.getFila()][sig.getColumna()]);
                desmarcar(sig);
            }
        }
    }

    //   MÉTODO PÚBLICO PARA LLAMAR AL ALGORITMO
    public static int resolver(int[][] mapa) {

        N = mapa.length;
        mejorCoste = Integer.MAX_VALUE;
        visitado = new boolean[N][N];

        Posicion entrada = new Posicion(0, 0);
        Posicion salida  = new Posicion(N - 1, N - 1);

        marcar(entrada);  // marcamos la entrada
        bt(mapa, entrada, salida, mapa[0][0]);

        return mejorCoste;
    }
}
