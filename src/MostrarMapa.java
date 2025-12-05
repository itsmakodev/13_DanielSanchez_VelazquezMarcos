public class MostrarMapa {
    public static void mostrarMapa(int[][] mapa) {
        for (int[] i : mapa) {
            for (int j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}