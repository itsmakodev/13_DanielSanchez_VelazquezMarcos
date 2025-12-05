/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ASUS
 */
public class MatrizEmojis {
    public static void MatrizEmojis(){
        int[][] matriz = LeerMatrizCSV.leerMapa("mapa.csv");
        int n = matriz.length;
        int[][] matrizEmojis = new int[n][n];
        // Diccionario número → emoji
        String[] emojis = {
            "😵‍💫",     // 0
            "😀",      // 1
            "😅",      // 2
            "😂",      // 3
            "😍",      // 4
            "😎",      // 5
            "🤔",      // 6
            "😡",      // 7
            "😭",      // 8
            "😡"       // 9
        };

        // Rellenar matriz con valores del 1 al 9
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrizEmojis[i][j] = matriz[i][j];
            }
        }

        // Mostrar matriz usando emojis
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int val = matrizEmojis[i][j];
                System.out.print(emojis[val] + "  ");
            }
            System.out.println();
        }
    }
}