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

    public static boolean[][] detectarObligatorias(int[][] mapa) {

        N = mapa.length;
        costeDesdeInicio = new int[N][N];
        costeHastaSalida = new int[N][N];

        // COSTE DESDE INICIO (0,0)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                // Si sale del mapa → devuelve Integer.MAX_VALUE, esto lo vimos en las condiciones base de la anterior EPD evaluable
                int costeArriba = Integer.MAX_VALUE;
                int costeIzquierda = Integer.MAX_VALUE;

                // Celda inicial
                if (i == 0 && j == 0) {
                    costeDesdeInicio[i][j] = mapa[i][j];
                } 
                else {
                    // movimiento hacia abajo
                    if (i > 0) {
                        costeArriba = costeDesdeInicio[i - 1][j];
                    }

                    // movimiento hacia derecha
                    if (j > 0) {
                        costeIzquierda = costeDesdeInicio[i][j - 1];
                    }

                    int min;
                    if (costeArriba < costeIzquierda) min = costeArriba;
                    else min = costeIzquierda;

                    costeDesdeInicio[i][j] = mapa[i][j] + min;
                }
            }
        }


        //COSTE HASTA SALIDA (N-1,N-1)
        for (int i = N - 1; i >= 0; i--) {
            for (int j = N - 1; j >= 0; j--) {

                 // Si sale del mapa → devuelve Integer.MAX_VALUE, esto lo vimos en las condiciones base de la anterior EPD evaluable
                int costeAbajo = Integer.MAX_VALUE;
                int costeDerecha = Integer.MAX_VALUE;

                if (i == N - 1 && j == N - 1) {
                    costeHastaSalida[i][j] = mapa[i][j];
                }
                else {
                    // movimiento hacia abajo desde la celda equivale a venir desde arriba en el análisis inverso
                    if (i < N - 1) {
                        costeAbajo = costeHastaSalida[i + 1][j];
                    }

                    // movimiento hacia derecha desde la celda equivale a venir desde izquierda en el análisis inverso
                    if (j < N - 1) {
                        costeDerecha = costeHastaSalida[i][j + 1];
                    }

                    int min;
                    if (costeAbajo < costeDerecha) min = costeAbajo;
                    else min = costeDerecha;

                    costeHastaSalida[i][j] = mapa[i][j] + min;
                }
            }
        }

        //COSTE MINIMO TOTAL
        costeMinimoTotal = costeDesdeInicio[N - 1][N - 1];

        //CELDAS OBLIGATORIAS
        boolean[][] obligatorias = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                int suma = costeDesdeInicio[i][j] + costeHastaSalida[i][j] - mapa[i][j];

                if (suma == costeMinimoTotal) {
                    obligatorias[i][j] = true;
                }
            }
        }

        return obligatorias;
    }

    public static void imprimirObligatorias(boolean[][] obligatorias) {
        System.out.println("Celdas obligatorias en todas las rutas mínimas:");
        for (int i = 0; i < obligatorias.length; i++) {
            for (int j = 0; j < obligatorias[i].length; j++) {
                if (obligatorias[i][j]) {
                    System.out.println("(" + i + "," + j + ")");
                }
            }
        }
    }
}

