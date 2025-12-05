/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DaniMaja
 */
public class SenderosDruadan {

    private static int N;
    private static int C;  // Coste máximo permitido

    // Arrays para almacenar rutas
    private static Posicion[][] rutas;      // Máximo de rutas posibles (estimación)
    private static int[] tamRutas;          // Tamaño de cada ruta
    private static int rutaCount = 0;

    private static Posicion[] caminoActual;
    private static int caminoTam = 0;

    private static final Posicion[] MOVS = {
        new Posicion(1, 0), // Abajo
        new Posicion(0, 1)  // Derecha
    };

    // Método principal para enumerar rutas
    public static void encontrarRutas(int[][] mapa, int costeMax) {
        N = mapa.length;
        C = costeMax;

        // Estimación máxima de rutas (puede ajustarse según el tamaño)
        rutas = new Posicion[10000][2 * N]; 
        tamRutas = new int[10000];

        caminoActual = new Posicion[2 * N];
        caminoTam = 0;

        Posicion entrada = new Posicion(0, 0);
        marcar(entrada);
        bt(mapa, entrada, mapa[0][0]);
    }

    // Marcar posición en camino actual
    private static void marcar(Posicion p) {
        caminoActual[caminoTam] = new Posicion(p.getFila(), p.getColumna());
        caminoTam++;
    }

    private static void desmarcar() {
        caminoTam--;
    }

    // Backtracking con poda por coste
    private static void bt(int[][] mapa, Posicion actual, int costeActual) {

        if (costeActual > C) return; // poda

        if (actual.getFila() == N - 1 && actual.getColumna() == N - 1) {
            // Guardar ruta
            rutas[rutaCount] = new Posicion[caminoTam];
            for (int i = 0; i < caminoTam; i++) {
                rutas[rutaCount][i] = new Posicion(caminoActual[i].getFila(), caminoActual[i].getColumna());
            }
            tamRutas[rutaCount] = caminoTam;
            rutaCount++;
            return;
        }

        for (Posicion mov : MOVS) {
            int ni = actual.getFila() + mov.getFila();
            int nj = actual.getColumna() + mov.getColumna();

            if (ni < N && nj < N) {
                Posicion sig = new Posicion(ni, nj);
                marcar(sig);
                bt(mapa, sig, costeActual + mapa[ni][nj]);
                desmarcar();
            }
        }
    }

    // Imprimir todas las rutas encontradas
    public static void imprimirRutas() {
        System.out.println("Se han encontrado " + rutaCount + " rutas con coste <= " + C);
        for (int r = 0; r < rutaCount; r++) {
            System.out.print("Ruta " + (r + 1) + ": ");
            for (int i = 0; i < tamRutas[r]; i++) {
                Posicion p = rutas[r][i];
                System.out.print("(" + p.getFila() + "," + p.getColumna() + ") ");
            }
            System.out.println();
        }
    }
}