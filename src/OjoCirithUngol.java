/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DaniMaja
 */
public class OjoCirithUngol {
    
    private static int N;
    private static int[][] costeDesdeInicio;
    private static int[][] costeHastaSalida;
    private static int costeMinimoTotal;

    // Método principal que devuelve un boolean[][] donde true = celda obligatoria
    public static boolean[][] detectarObligatorias(int[][] mapa) {
        N = mapa.length;

        // Inicializar arrays de costes
        costeDesdeInicio = new int[N][N];
        costeHastaSalida = new int[N][N];

        // Paso 1: Coste mínimo desde inicio a cada celda
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int costeArriba = (i > 0) ? costeDesdeInicio[i - 1][j] : Integer.MAX_VALUE;
                int costeIzquierda = (j > 0) ? costeDesdeInicio[i][j - 1] : Integer.MAX_VALUE;

                if (i == 0 && j == 0) {
                    costeDesdeInicio[i][j] = mapa[i][j];
                } else {
                    int minCoste = Math.min(costeArriba, costeIzquierda);
                    costeDesdeInicio[i][j] = mapa[i][j] + minCoste;
                }
            }
        }

        // Paso 2: Coste mínimo desde cada celda hasta la salida
        for (int i = N - 1; i >= 0; i--) {
            for (int j = N - 1; j >= 0; j--) {
                int costeAbajo = (i < N - 1) ? costeHastaSalida[i + 1][j] : Integer.MAX_VALUE;
                int costeDerecha = (j < N - 1) ? costeHastaSalida[i][j + 1] : Integer.MAX_VALUE;

                if (i == N - 1 && j == N - 1) {
                    costeHastaSalida[i][j] = mapa[i][j];
                } else {
                    int minCoste = Math.min(costeAbajo, costeDerecha);
                    costeHastaSalida[i][j] = mapa[i][j] + minCoste;
                }
            }
        }

        // Coste mínimo total
        costeMinimoTotal = costeDesdeInicio[N - 1][N - 1];

        // Paso 3: Detectar celdas obligatorias
        boolean[][] obligatorias = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int sumaParcial = costeDesdeInicio[i][j] + costeHastaSalida[i][j] - mapa[i][j];
                if (sumaParcial == costeMinimoTotal) {
                    obligatorias[i][j] = true;
                } else {
                    obligatorias[i][j] = false;
                }
            }
        }

        return obligatorias;
    }

    // Método auxiliar para imprimir las coordenadas de celdas obligatorias
    public static void imprimirObligatorias(boolean[][] obligatorias) {
        System.out.println("Celdas obligatorias en todas las rutas de coste mínimo:");
        for (int i = 0; i < obligatorias.length; i++) {
            for (int j = 0; j < obligatorias[i].length; j++) {
                if (obligatorias[i][j]) {
                    System.out.println("(" + i + "," + j + ")");
                }
            }
        }
    }
    
}
