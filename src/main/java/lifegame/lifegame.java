package lifegame;

import java.util.Scanner;
import java.util.Random;

public class lifegame {

    private static Scanner entrada = new Scanner(System.in);

    public static int numLinha = 6;
    public static int numColuna = 6;
    public static boolean primeiraMatriz = true;
    public static int[][] geracaoAtual;

    public static void main(String[] args) {

        int opcao = 0;
        while (opcao != 2) {
            exibirMenu();
            opcao = entrada.nextInt();
            executarComando(opcao);
        }
    }

    private static void exibirMenu() {
        System.out.println("#### JOGO DA VIDA ####");
        System.out.println("1 - Nova Geração");
        System.out.println("2 - Finalizar Jogo");
        System.out.print("Escolha uma opcao: ");
    }

    private static void executarComando(int opcao) {
        switch (opcao) {
            case 1:
                int matrizAtual[][] = criaMatriz();
                System.out.println("Geracao Atual: ");
                imprimetMatriz(matrizAtual);
                System.out.print("\n");
                break;
            case 2:
                break;
            default:
                System.out.println("Opcao invalida!");
        }
    }

    private static int[][] criaMatriz() {
        Random gerador = new Random(); 
        int[][] geracaoAnterior = new int[numLinha][numColuna];

        if (primeiraMatriz == true) {
            primeiraMatriz = false;

            geracaoAtual = new int[numLinha][numColuna];
            
            //populando a matriz
            for (int i = 0; i < numLinha; i++) {
                for (int j = 0; j < numLinha; j++) {
                    geracaoAtual[i][j] = gerador.nextInt(2); //numeros aleatorios 0 ou 1
                }
            }
        } else { //caso nao seja a primeira repeticao 
            geracaoAnterior = geracaoAtual;
            System.out.println("Geracao Anterior:");
            imprimetMatriz(geracaoAnterior);
            System.out.print("\n");

            //populando a nova matriz
            for (int i = 0; i < numLinha; i++) {
                for (int j = 0; j < numLinha; j++) {
                    geracaoAtual[i][j] = gerador.nextInt(2); // numeros aleatorios 0 ou 1
                }
            }
        }

        //retorno da matriz atual
        return geracaoAtual; 
    }

    private static void imprimetMatriz(int matriz[][]) {
        for (int i = 0; i < numLinha; i++) {
            for (int j = 0; j < numLinha; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
}
