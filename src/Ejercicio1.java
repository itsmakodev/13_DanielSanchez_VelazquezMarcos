public class Ejercicio1 {

    static int[][] mapa;
    static int N;
    static int mejorCoste;

    public static int encontrarCamino(int[][] m) {
        mapa = m;
        N = mapa.length;
        int mejorCamino = 0;    
        mejorCoste = Integer.MAX_VALUE;

        int[][] camino = new int [N][N];
        backtrack(0,0,camino,mapa[0][0]);

        return mejorCamino;
    }

    private static void backtrack(int i, int j, int[][]camino, int coste) {

        if (i == N-1 && j == N-1) {
            if (coste < mejorCoste) {
                mejorCoste = coste;
                mejorCamino = new ArrayList<>(camino);
            }
            return;
        }

        // Mover abajo
        if (i + 1 < N) {
            camino.add(new int[]{i+1,j});
            backtrack(i+1,j,camino,coste + mapa[i+1][j]);
            camino.remove(camino.size()-1);
        }

        // Mover derecha
        if (j + 1 < N) {
            camino.add(new int[]{i,j+1});
            backtrack(i,j+1,camino,coste + mapa[i][j+1]);
            camino.remove(camino.size()-1);
        }
    }
