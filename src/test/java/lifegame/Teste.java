package lifegame;

import org.junit.Test;

public class Teste {

    lifegame meuJogo = new lifegame();

    @Test
    public void testeFuncionalUm() {
        int matriz[][] = new int[6][6];

        // populando matriz

        // linha 0
        matriz[0][0] = 0;
        matriz[0][1] = 0;
        matriz[0][2] = 1;
        matriz[0][3] = 1;
        matriz[0][4] = 0;
        matriz[0][5] = 1;

        // linha 1
        matriz[1][0] = 0;
        matriz[1][1] = 1;
        matriz[1][2] = 1;
        matriz[1][3] = 1;
        matriz[1][4] = 0;
        matriz[1][5] = 1;

        // linha 2
        matriz[2][0] = 1;
        matriz[2][1] = 0;
        matriz[2][2] = 0;
        matriz[2][3] = 0;
        matriz[2][4] = 1;
        matriz[2][5] = 0;

        // linha 3
        matriz[3][0] = 0;
        matriz[3][1] = 1;
        matriz[3][2] = 0;
        matriz[3][3] = 1;
        matriz[3][4] = 0;
        matriz[3][5] = 1;

        // linha 4
        matriz[4][0] = 0;
        matriz[4][1] = 0;
        matriz[4][2] = 1;
        matriz[4][3] = 0;
        matriz[4][4] = 1;
        matriz[4][5] = 0;

        // linha 5
        matriz[5][0] = 1;
        matriz[5][1] = 1;
        matriz[5][2] = 0;
        matriz[5][3] = 0;
        matriz[5][4] = 1;
        matriz[5][5] = 1;

        meuJogo.criaMatriz(matriz);
    }

    @Test
    public void testeFuncionalDois() {
        int matriz[][] = new int[6][6];

        // populando matriz

        // linha 0
        matriz[0][0] = 1;
        matriz[0][1] = 0;
        matriz[0][2] = 1;
        matriz[0][3] = 0;
        matriz[0][4] = 1;
        matriz[0][5] = 1;

        // linha 1
        matriz[1][0] = 0;
        matriz[1][1] = 0;
        matriz[1][2] = 1;
        matriz[1][3] = 0;
        matriz[1][4] = 0;
        matriz[1][5] = 0;

        // linha 2
        matriz[2][0] = 1;
        matriz[2][1] = 0;
        matriz[2][2] = 0;
        matriz[2][3] = 0;
        matriz[2][4] = 0;
        matriz[2][5] = 1;

        // linha 3
        matriz[3][0] = 0;
        matriz[3][1] = 0;
        matriz[3][2] = 0;
        matriz[3][3] = 1;
        matriz[3][4] = 0;
        matriz[3][5] = 0;

        // linha 4
        matriz[4][0] = 1;
        matriz[4][1] = 1;
        matriz[4][2] = 0;
        matriz[4][3] = 1;
        matriz[4][4] = 1;
        matriz[4][5] = 1;

        // linha 5
        matriz[5][0] = 1;
        matriz[5][1] = 0;
        matriz[5][2] = 1;
        matriz[5][3] = 0;
        matriz[5][4] = 1;
        matriz[5][5] = 0;

        meuJogo.criaMatriz(matriz);
    }

}
