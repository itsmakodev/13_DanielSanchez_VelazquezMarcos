/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DaniMaja
 */
public class MarchaSilenciosa {

    private static int N;                      
    private static int mejorCoste;            
    private static boolean[][] visitado;      

    // Arrays para almacenar caminos sin usar List
    private static Posicion[] caminoActual;
    private static Posicion[] mejorCamino;
    private static int caminoTam = 0;
    private static int mejorTam = 0;

    // Movimientos permitidos, igual que en la EPD1
    private static final Posicion[] MOVS = {
        new Posicion(1, 0),   // Abajo
        new Posicion(0, 1)    // Derecha
    };

    // Validar si la posición está dentro del mapa
    static boolean valida(Posicion p) {
        if (p.getFila() < 0 || p.getFila() >= N) {
            return false;
        }
        if (p.getColumna() < 0 || p.getColumna() >= N) {
            return false;
        }
        return true;
    }

    // Marcar posición como visitada y añadir al camino
    static void marcar(Posicion p) {
        visitado[p.getFila()][p.getColumna()] = true;
        caminoActual[caminoTam] = new Posicion(p.getFila(), p.getColumna());
        caminoTam++;
    }

    // Desmarcar posición del camino
    static void desmarcar(Posicion p) {
        visitado[p.getFila()][p.getColumna()] = false;
        caminoTam--;
    }

    // Comprobar si llegamos a la salida
    static boolean llegamos(Posicion a, Posicion b) {
        if (a.getFila() == b.getFila() && a.getColumna() == b.getColumna()) {
            return true;
        } else {
            return false;
        }
    }

    // Backtracking
    static void bt(int[][] mapa, Posicion actual, Posicion salida, int costeActual) {

        // Poda: si ya no puede mejorar el coste
        if (costeActual >= mejorCoste) {
            return;
        }

        if (llegamos(actual, salida)) {
            mejorCoste = costeActual;

            // Guardar mejor camino
            mejorTam = caminoTam;
            for (int i = 0; i < caminoTam; i++) {
                Posicion p = caminoActual[i];
                mejorCamino[i] = new Posicion(p.getFila(), p.getColumna());
            }
            return;
        }

        // Probar movimientos
        for (int i = 0; i < MOVS.length; i++) {
            Posicion mov = MOVS[i];
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

    // Resolver el mapa y mostrar resultado
    public static int resolver(int[][] mapa) {

        N = mapa.length;
        mejorCoste = Integer.MAX_VALUE;
        visitado = new boolean[N][N];

        caminoActual = new Posicion[2 * N]; // tamaño máximo posible
        mejorCamino = new Posicion[2 * N];
        caminoTam = 0;
        mejorTam = 0;

        Posicion entrada = new Posicion(0, 0);
        Posicion salida  = new Posicion(N - 1, N - 1);

        marcar(entrada);
        bt(mapa, entrada, salida, mapa[0][0]);

        mostrarASCII(mapa);

        return mejorCoste;
    }

    // Visualización ASCII del camino
    private static void mostrarASCII(int[][] mapa) {
        System.out.println("\nMapa con ruta óptima (X = camino):\n");

        boolean[][] caminoMarcado = new boolean[N][N];
        for (int i = 0; i < mejorTam; i++) {
            Posicion p = mejorCamino[i];
            caminoMarcado[p.getFila()][p.getColumna()] = true;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (caminoMarcado[i][j]) {
                    System.out.print(" X ");
                } else {
                    System.out.printf("%2d ", mapa[i][j]);
                }
            }
            System.out.println();
        }

        System.out.println("\nRuta (coordenadas):");
        for (int i = 0; i < mejorTam; i++) {
            Posicion p = mejorCamino[i];
            System.out.println("(" + p.getFila() + "," + p.getColumna() + ")");
        }
    }
}
