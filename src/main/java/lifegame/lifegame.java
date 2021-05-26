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
                System.out.println("Nova Geração: ");
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

            // populando a matriz
            for (int i = 0; i < numLinha; i++) {
                for (int j = 0; j < numLinha; j++) {
                    geracaoAtual[i][j] = gerador.nextInt(2); // numeros aleatorios 0 ou 1
                }
            }
        } else { // caso nao seja a primeira repeticao
            geracaoAnterior = geracaoAtual;
            System.out.println("Geracao Anterior:");
            imprimetMatriz(geracaoAnterior);
            System.out.print("\n");

            // populando a nova matriz
            for (int linha = 0; linha < numLinha; linha++) {
                // SE FOR A PRIMEIRA LINHA
                if (linha == 0) {
                    // Esse laço passa por cada coluna da linha atual
                    for (int coluna = 0; coluna < numLinha; coluna++) {

                        // PRIMEIRO ELEMENTO PRIMEIRA LINHA
                        if (coluna == 0) {
                            // Se o elemento estiver morto e tiver três vizinhos
                            if (geracaoAnterior[linha][coluna] == 0
                                    && ((geracaoAnterior[linha][coluna + 1] + geracaoAnterior[linha + 1][coluna]
                                            + geracaoAnterior[linha + 1][coluna + 1]) == 3)) {
                                geracaoAtual[linha][coluna] = 1;
                            }
                            // Se o elemento estiver vivo e menos do que dois vizinhos
                            else if (geracaoAnterior[linha][coluna] == 1
                                    && ((geracaoAnterior[linha][coluna + 1] + geracaoAnterior[linha + 1][coluna]
                                            + geracaoAnterior[linha + 1][coluna + 1]) < 2)) {
                                geracaoAtual[linha][coluna] = 0;
                            } else {
                                geracaoAtual[linha][coluna] = geracaoAnterior[linha][coluna];
                            }
                        }
                        // ELEMENTOS DA PRIMEIRA LINHA, APÓS A 1ª COLUNA A ANTES DA ÚLTIMA
                        else if (coluna > 0 && coluna < numLinha - 1) {
                            // Se o elemento estiver morto e três vizinhos
                            if (geracaoAnterior[linha][coluna] == 0 && ((geracaoAnterior[linha][coluna - 1]
                                    + geracaoAnterior[linha + 1][coluna - 1] + geracaoAnterior[linha + 1][coluna]
                                    + geracaoAnterior[linha + 1][coluna + 1]
                                    + geracaoAnterior[linha][coluna + 1]) == 3)) {
                                geracaoAtual[linha][coluna] = 1;
                            }
                            // Vivo e mais do que 3 vizinhos, morte por superpopulação, ou menos do que 2
                            // vizinhos solidão
                            else if (geracaoAnterior[linha][coluna] == 1 && ((geracaoAnterior[linha][coluna - 1]
                                    + geracaoAnterior[linha + 1][coluna - 1] + geracaoAnterior[linha + 1][coluna]
                                    + geracaoAnterior[linha + 1][coluna + 1] + geracaoAnterior[linha][coluna + 1]) > 3)

                                    || ((geracaoAnterior[linha][coluna - 1] + geracaoAnterior[linha + 1][coluna - 1]
                                            + geracaoAnterior[linha + 1][coluna]
                                            + geracaoAnterior[linha + 1][coluna + 1]
                                            + geracaoAnterior[linha][coluna + 1]) < 2)) {
                                geracaoAtual[linha][coluna] = 0;
                            } else {
                                geracaoAtual[linha][coluna] = geracaoAnterior[linha][coluna];
                            }
                        }

                        // ÚLTIMO ELEMENTO, PRIMEIRA LINHA
                        else if (coluna == numLinha - 1) {
                            // Se o elemento estiver morto
                            if (geracaoAnterior[linha][coluna] == 0
                                    && ((geracaoAnterior[linha][coluna - 1] + geracaoAnterior[linha + 1][coluna - 1]
                                            + geracaoAnterior[linha + 1][coluna]) == 3)) {
                                geracaoAtual[linha][coluna] = 1;
                            }
                            // Se o elemento estiver vivo e superpopulação ou solidão
                            else if (geracaoAnterior[linha][coluna] == 1 && ((geracaoAnterior[linha][coluna - 1]
                                    + geracaoAnterior[linha + 1][coluna - 1] + geracaoAnterior[linha + 1][coluna]) > 3
                                    || (geracaoAnterior[linha][coluna - 1] + geracaoAnterior[linha + 1][coluna - 1]
                                            + geracaoAnterior[linha + 1][coluna]) < 2)) {
                                geracaoAtual[linha][coluna] = 0;
                            } else {
                                geracaoAtual[linha][coluna] = geracaoAnterior[linha][coluna];
                            }
                        }
                    }

                    // SE NÃO FOR A PRIMEIRA LINHA E NÃO FOR A ÚLTIMA
                } else if (linha > 0 && linha < numLinha - 1) {
                    // Percorre todas as colunas
                    for (int coluna = 0; coluna < numLinha; coluna++) {
                        // SE FOR A PRIMEIRA COLUNA
                        if (coluna == 0) {
                            // Se o elemento estiver morto e tiver três vizinhos
                            if (geracaoAnterior[linha][coluna] == 0 && ((geracaoAnterior[linha - 1][coluna]
                                    + geracaoAnterior[linha - 1][coluna + 1] + geracaoAnterior[linha][coluna + 1]
                                    + geracaoAnterior[linha + 1][coluna + 1]
                                    + geracaoAnterior[linha + 1][coluna]) == 3)) {
                                geracaoAtual[linha][coluna] = 1;
                            } // Se o elemento estiver vivo e mais do que 3 vizinhos ou menos que 2
                            else if (geracaoAnterior[linha][coluna] == 1 && ((geracaoAnterior[linha - 1][coluna]
                                    + geracaoAnterior[linha - 1][coluna + 1] + geracaoAnterior[linha][coluna + 1]
                                    + geracaoAnterior[linha + 1][coluna + 1] + geracaoAnterior[linha + 1][coluna]) > 3)
                                    || ((geracaoAnterior[linha - 1][coluna] + geracaoAnterior[linha - 1][coluna + 1]
                                            + geracaoAnterior[linha][coluna + 1]
                                            + geracaoAnterior[linha + 1][coluna + 1]
                                            + geracaoAnterior[linha + 1][coluna]) < 2)) {
                                geracaoAtual[linha][coluna] = 0;
                            } else {
                                geracaoAtual[linha][coluna] = geracaoAnterior[linha][coluna];
                            }
                        } // SE NÃO FOR A PRIMEIRA COLUNA E NÃO FOR A ÚLTIMA
                        else if (coluna > 0 && coluna < numLinha - 1) {
                            // Se o elemento estiver morto e tiver mais três vizinhos
                            if (geracaoAnterior[linha][coluna] == 0 && ((geracaoAnterior[linha][coluna - 1]
                                    + geracaoAnterior[linha - 1][coluna - 1] + geracaoAnterior[linha - 1][coluna]
                                    + geracaoAnterior[linha - 1][coluna + 1] + geracaoAnterior[linha][coluna + 1]
                                    + geracaoAnterior[linha + 1][coluna + 1] + geracaoAnterior[linha + 1][coluna]
                                    + geracaoAnterior[linha + 1][coluna - 1]) == 3)) {
                                geracaoAtual[linha][coluna] = 1;
                            }
                            // Se o elemento estiver vivo e tiver menos do que 2 vizinhos ou mais do que 3
                            else if (geracaoAnterior[linha][coluna] == 1 &&

                                    ((geracaoAnterior[linha][coluna - 1] + geracaoAnterior[linha - 1][coluna - 1]
                                            + geracaoAnterior[linha - 1][coluna]
                                            + geracaoAnterior[linha - 1][coluna + 1]
                                            + geracaoAnterior[linha][coluna + 1]
                                            + geracaoAnterior[linha + 1][coluna + 1]
                                            + geracaoAnterior[linha + 1][coluna]
                                            + geracaoAnterior[linha + 1][coluna - 1]) < 2)

                                    || ((geracaoAnterior[linha][coluna - 1] + geracaoAnterior[linha - 1][coluna - 1]
                                            + geracaoAnterior[linha - 1][coluna]
                                            + geracaoAnterior[linha - 1][coluna + 1]
                                            + geracaoAnterior[linha][coluna + 1]
                                            + geracaoAnterior[linha + 1][coluna + 1]
                                            + geracaoAnterior[linha + 1][coluna]
                                            + geracaoAnterior[linha + 1][coluna - 1]) > 3)) {
                                geracaoAtual[linha][coluna] = 0;
                            } else {
                                geracaoAtual[linha][coluna] = geracaoAnterior[linha][coluna];
                            }
                        }
                        // SE FOR A ÚLTIMA COLUNA
                        else if (coluna == numLinha - 1) {
                            // Se o elemento estiver morto e três vizinhos
                            if (geracaoAnterior[linha][coluna] == 0 && ((geracaoAnterior[linha - 1][coluna]
                                    + geracaoAnterior[linha - 1][coluna - 1] + geracaoAnterior[linha][coluna - 1]
                                    + geracaoAnterior[linha + 1][coluna - 1]
                                    + geracaoAnterior[linha + 1][coluna]) == 3)) {
                                geracaoAtual[linha][coluna] = 1;
                            } // Se o elemento estiver vivo, mais que 3 ou menos que 2 vizinhos
                            else if (geracaoAnterior[linha][coluna] == 1 && ((geracaoAnterior[linha - 1][coluna]
                                    + geracaoAnterior[linha - 1][coluna - 1] + geracaoAnterior[linha][coluna - 1]
                                    + geracaoAnterior[linha + 1][coluna - 1] + geracaoAnterior[linha + 1][coluna]) > 3)

                                    || ((geracaoAnterior[linha - 1][coluna] + geracaoAnterior[linha - 1][coluna - 1]
                                            + geracaoAnterior[linha][coluna - 1]
                                            + geracaoAnterior[linha + 1][coluna - 1]
                                            + geracaoAnterior[linha + 1][coluna]) < 2)) {
                                geracaoAtual[linha][coluna] = 0;
                            } else {
                                geracaoAtual[linha][coluna] = geracaoAnterior[linha][coluna];
                            }
                        }
                    }
                } // SE FOR A ÚLTIMA LINHA
                else if (linha == numLinha - 1) {
                    // Percorre as colunas
                    for (int coluna = 0; coluna < numLinha; coluna++) {

                        // SE FOR A PRIMEIRA COLUNA
                        if (coluna == 0) {
                            // Se o elemento estiver morto e três vizinhos
                            if (geracaoAnterior[linha][coluna] == 0
                                    && ((geracaoAnterior[linha - 1][coluna] + geracaoAnterior[linha - 1][coluna + 1]
                                            + geracaoAnterior[linha][coluna + 1]) == 3)) {
                                geracaoAtual[linha][coluna] = 1;
                            } // Se o elemento estiver vivo, mais que 3 ou menos que 2 vizinhos
                            else if (geracaoAnterior[linha][coluna] == 1
                                    && ((geracaoAnterior[linha - 1][coluna] + geracaoAnterior[linha - 1][coluna + 1]
                                            + geracaoAnterior[linha][coluna + 1]) > 3)

                                    || ((geracaoAnterior[linha - 1][coluna] + geracaoAnterior[linha - 1][coluna + 1]
                                            + geracaoAnterior[linha][coluna + 1]) < 2)) {
                                geracaoAtual[linha][coluna] = 0;
                            } else {
                                geracaoAtual[linha][coluna] = geracaoAnterior[linha][coluna];
                            }
                        }
                        // SE NÃO FOR A PRIMEIRA COLUNA E NÃO FOR A ÚLTIMA
                        else if (coluna > 0 && coluna < numLinha - 1) {
                            // Se o elemento estiver morto e três vizinhos
                            if (geracaoAnterior[linha][coluna] == 0 && ((geracaoAnterior[linha][coluna - 1]
                                    + geracaoAnterior[linha - 1][coluna - 1] + geracaoAnterior[linha - 1][coluna]
                                    + geracaoAnterior[linha - 1][coluna + 1]
                                    + geracaoAnterior[linha][coluna + 1]) == 3)) {
                                geracaoAtual[linha][coluna] = 1;
                            } // Se o elemento estiver vivo, mais que 3 ou menos que 2 vizinhos
                            else if (geracaoAnterior[linha][coluna] == 1 && ((geracaoAnterior[linha][coluna - 1]
                                    + geracaoAnterior[linha - 1][coluna - 1] + geracaoAnterior[linha - 1][coluna]
                                    + geracaoAnterior[linha - 1][coluna + 1] + geracaoAnterior[linha][coluna + 1]) > 3)

                                    || ((geracaoAnterior[linha][coluna - 1] + geracaoAnterior[linha - 1][coluna - 1]
                                            + geracaoAnterior[linha - 1][coluna]
                                            + geracaoAnterior[linha - 1][coluna + 1]
                                            + geracaoAnterior[linha][coluna + 1]) < 2)) {
                                geracaoAtual[linha][coluna] = 0;
                            } else {
                                geracaoAtual[linha][coluna] = geracaoAnterior[linha][coluna];
                            }
                        }
                        // SE FOR A ÚLTIMA COLUNA
                        else {
                            // Se o elementos estiver morto e 3 vizinhos
                            if (geracaoAnterior[linha][coluna] == 0
                                    && ((geracaoAnterior[linha - 1][coluna] + geracaoAnterior[linha - 1][coluna - 1]
                                            + geracaoAnterior[linha][coluna - 1]) == 3)) {
                                geracaoAtual[linha][coluna] = 1;
                            } // Se o elemento estiver vivo, mais que 3 ou menos que 2 vizinhos
                            else if (geracaoAnterior[linha][coluna] == 1
                                    && ((geracaoAnterior[linha - 1][coluna] + geracaoAnterior[linha - 1][coluna - 1]
                                            + geracaoAnterior[linha][coluna - 1]) > 3)

                                    || ((geracaoAnterior[linha - 1][coluna] + geracaoAnterior[linha - 1][coluna - 1]
                                            + geracaoAnterior[linha][coluna - 1]) < 2)) {
                                geracaoAtual[linha][coluna] = 0;
                            } else {
                                geracaoAtual[linha][coluna] = geracaoAnterior[linha][coluna];
                            }
                        }
                    }
                }
            }
        }
        // retorno da matriz atual
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

    
    
    
    //PARA TESTE
    public int[][] criaMatriz(int[][] matrizAlterada) {
        int[][] geracaoAnterior = new int[numLinha][numColuna];

        geracaoAnterior = matrizAlterada;
        System.out.println("Geracao Anterior:");
        imprimetMatriz(geracaoAnterior);
        System.out.print("\n");

        // populando a nova matriz
        for (int linha = 0; linha < numLinha; linha++) {
            // SE FOR A PRIMEIRA LINHA
            if (linha == 0) {
                // Esse laço passa por cada coluna da linha atual
                for (int coluna = 0; coluna < numLinha; coluna++) {

                    // PRIMEIRO ELEMENTO PRIMEIRA LINHA
                    if (coluna == 0) {
                        // Se o elemento estiver morto e tiver três vizinhos
                        if (geracaoAnterior[linha][coluna] == 0 && ((geracaoAnterior[linha][coluna + 1]
                                + geracaoAnterior[linha + 1][coluna] + geracaoAnterior[linha + 1][coluna + 1]) == 3)) {
                            matrizAlterada[linha][coluna] = 1;
                        }
                        // Se o elemento estiver vivo e menos do que dois vizinhos
                        else if (geracaoAnterior[linha][coluna] == 1 && ((geracaoAnterior[linha][coluna + 1]
                                + geracaoAnterior[linha + 1][coluna] + geracaoAnterior[linha + 1][coluna + 1]) < 2)) {
                            matrizAlterada[linha][coluna] = 0;
                        } else {
                            matrizAlterada[linha][coluna] = geracaoAnterior[linha][coluna];
                        }
                    }
                    // ELEMENTOS DA PRIMEIRA LINHA, APÓS A 1ª COLUNA A ANTES DA ÚLTIMA
                    else if (coluna > 0 && coluna < numLinha - 1) {
                        // Se o elemento estiver morto e três vizinhos
                        if (geracaoAnterior[linha][coluna] == 0 && ((geracaoAnterior[linha][coluna - 1]
                                + geracaoAnterior[linha + 1][coluna - 1] + geracaoAnterior[linha + 1][coluna]
                                + geracaoAnterior[linha + 1][coluna + 1] + geracaoAnterior[linha][coluna + 1]) == 3)) {
                            matrizAlterada[linha][coluna] = 1;
                        }
                        // Vivo e mais do que 3 vizinhos, morte por superpopulação, ou menos do que 2
                        // vizinhos solidão
                        else if (geracaoAnterior[linha][coluna] == 1
                                && ((geracaoAnterior[linha][coluna - 1] + geracaoAnterior[linha + 1][coluna - 1]
                                        + geracaoAnterior[linha + 1][coluna] + geracaoAnterior[linha + 1][coluna + 1]
                                        + geracaoAnterior[linha][coluna + 1]) > 3)

                                || ((geracaoAnterior[linha][coluna - 1] + geracaoAnterior[linha + 1][coluna - 1]
                                        + geracaoAnterior[linha + 1][coluna] + geracaoAnterior[linha + 1][coluna + 1]
                                        + geracaoAnterior[linha][coluna + 1]) < 2)) {
                            matrizAlterada[linha][coluna] = 0;
                        } else {
                            matrizAlterada[linha][coluna] = geracaoAnterior[linha][coluna];
                        }
                    }

                    // ÚLTIMO ELEMENTO, PRIMEIRA LINHA
                    else if (coluna == numLinha - 1) {
                        // Se o elemento estiver morto
                        if (geracaoAnterior[linha][coluna] == 0 && ((geracaoAnterior[linha][coluna - 1]
                                + geracaoAnterior[linha + 1][coluna - 1] + geracaoAnterior[linha + 1][coluna]) == 3)) {
                            matrizAlterada[linha][coluna] = 1;
                        }
                        // Se o elemento estiver vivo e superpopulação ou solidão
                        else if (geracaoAnterior[linha][coluna] == 1 && ((geracaoAnterior[linha][coluna - 1]
                                + geracaoAnterior[linha + 1][coluna - 1] + geracaoAnterior[linha + 1][coluna]) > 3
                                || (geracaoAnterior[linha][coluna - 1] + geracaoAnterior[linha + 1][coluna - 1]
                                        + geracaoAnterior[linha + 1][coluna]) < 2)) {
                            matrizAlterada[linha][coluna] = 0;
                        } else {
                            matrizAlterada[linha][coluna] = geracaoAnterior[linha][coluna];
                        }
                    }
                }

                // SE NÃO FOR A PRIMEIRA LINHA E NÃO FOR A ÚLTIMA
            } else if (linha > 0 && linha < numLinha - 1) {
                // Percorre todas as colunas
                for (int coluna = 0; coluna < numLinha; coluna++) {
                    // SE FOR A PRIMEIRA COLUNA
                    if (coluna == 0) {
                        // Se o elemento estiver morto e tiver três vizinhos
                        if (geracaoAnterior[linha][coluna] == 0 && ((geracaoAnterior[linha - 1][coluna]
                                + geracaoAnterior[linha - 1][coluna + 1] + geracaoAnterior[linha][coluna + 1]
                                + geracaoAnterior[linha + 1][coluna + 1] + geracaoAnterior[linha + 1][coluna]) == 3)) {
                            matrizAlterada[linha][coluna] = 1;
                        } // Se o elemento estiver vivo e mais do que 3 vizinhos ou menos que 2
                        else if (geracaoAnterior[linha][coluna] == 1
                                && ((geracaoAnterior[linha - 1][coluna] + geracaoAnterior[linha - 1][coluna + 1]
                                        + geracaoAnterior[linha][coluna + 1] + geracaoAnterior[linha + 1][coluna + 1]
                                        + geracaoAnterior[linha + 1][coluna]) > 3)
                                || ((geracaoAnterior[linha - 1][coluna] + geracaoAnterior[linha - 1][coluna + 1]
                                        + geracaoAnterior[linha][coluna + 1] + geracaoAnterior[linha + 1][coluna + 1]
                                        + geracaoAnterior[linha + 1][coluna]) < 2)) {
                            matrizAlterada[linha][coluna] = 0;
                        } else {
                            matrizAlterada[linha][coluna] = geracaoAnterior[linha][coluna];
                        }
                    } // SE NÃO FOR A PRIMEIRA COLUNA E NÃO FOR A ÚLTIMA
                    else if (coluna > 0 && coluna < numLinha - 1) {
                        // Se o elemento estiver morto e tiver mais três vizinhos
                        if (geracaoAnterior[linha][coluna] == 0 && ((geracaoAnterior[linha][coluna - 1]
                                + geracaoAnterior[linha - 1][coluna - 1] + geracaoAnterior[linha - 1][coluna]
                                + geracaoAnterior[linha - 1][coluna + 1] + geracaoAnterior[linha][coluna + 1]
                                + geracaoAnterior[linha + 1][coluna + 1] + geracaoAnterior[linha + 1][coluna]
                                + geracaoAnterior[linha + 1][coluna - 1]) == 3)) {
                            matrizAlterada[linha][coluna] = 1;
                        }
                        // Se o elemento estiver vivo e tiver menos do que 2 vizinhos ou mais do que 3
                        else if (geracaoAnterior[linha][coluna] == 1 &&

                                ((geracaoAnterior[linha][coluna - 1] + geracaoAnterior[linha - 1][coluna - 1]
                                        + geracaoAnterior[linha - 1][coluna] + geracaoAnterior[linha - 1][coluna + 1]
                                        + geracaoAnterior[linha][coluna + 1] + geracaoAnterior[linha + 1][coluna + 1]
                                        + geracaoAnterior[linha + 1][coluna]
                                        + geracaoAnterior[linha + 1][coluna - 1]) < 2)

                                || ((geracaoAnterior[linha][coluna - 1] + geracaoAnterior[linha - 1][coluna - 1]
                                        + geracaoAnterior[linha - 1][coluna] + geracaoAnterior[linha - 1][coluna + 1]
                                        + geracaoAnterior[linha][coluna + 1] + geracaoAnterior[linha + 1][coluna + 1]
                                        + geracaoAnterior[linha + 1][coluna]
                                        + geracaoAnterior[linha + 1][coluna - 1]) > 3)) {
                            matrizAlterada[linha][coluna] = 0;
                        } else {
                            matrizAlterada[linha][coluna] = geracaoAnterior[linha][coluna];
                        }
                    }
                    // SE FOR A ÚLTIMA COLUNA
                    else if (coluna == numLinha - 1) {
                        // Se o elemento estiver morto e três vizinhos
                        if (geracaoAnterior[linha][coluna] == 0 && ((geracaoAnterior[linha - 1][coluna]
                                + geracaoAnterior[linha - 1][coluna - 1] + geracaoAnterior[linha][coluna - 1]
                                + geracaoAnterior[linha + 1][coluna - 1] + geracaoAnterior[linha + 1][coluna]) == 3)) {
                            matrizAlterada[linha][coluna] = 1;
                        } // Se o elemento estiver vivo, mais que 3 ou menos que 2 vizinhos
                        else if (geracaoAnterior[linha][coluna] == 1
                                && ((geracaoAnterior[linha - 1][coluna] + geracaoAnterior[linha - 1][coluna - 1]
                                        + geracaoAnterior[linha][coluna - 1] + geracaoAnterior[linha + 1][coluna - 1]
                                        + geracaoAnterior[linha + 1][coluna]) > 3)

                                || ((geracaoAnterior[linha - 1][coluna] + geracaoAnterior[linha - 1][coluna - 1]
                                        + geracaoAnterior[linha][coluna - 1] + geracaoAnterior[linha + 1][coluna - 1]
                                        + geracaoAnterior[linha + 1][coluna]) < 2)) {
                            matrizAlterada[linha][coluna] = 0;
                        } else {
                            matrizAlterada[linha][coluna] = geracaoAnterior[linha][coluna];
                        }
                    }
                }
            } // SE FOR A ÚLTIMA LINHA
            else if (linha == numLinha - 1) {
                // Percorre as colunas
                for (int coluna = 0; coluna < numLinha; coluna++) {

                    // SE FOR A PRIMEIRA COLUNA
                    if (coluna == 0) {
                        // Se o elemento estiver morto e três vizinhos
                        if (geracaoAnterior[linha][coluna] == 0 && ((geracaoAnterior[linha - 1][coluna]
                                + geracaoAnterior[linha - 1][coluna + 1] + geracaoAnterior[linha][coluna + 1]) == 3)) {
                            matrizAlterada[linha][coluna] = 1;
                        } // Se o elemento estiver vivo, mais que 3 ou menos que 2 vizinhos
                        else if (geracaoAnterior[linha][coluna] == 1
                                && ((geracaoAnterior[linha - 1][coluna] + geracaoAnterior[linha - 1][coluna + 1]
                                        + geracaoAnterior[linha][coluna + 1]) > 3)

                                || ((geracaoAnterior[linha - 1][coluna] + geracaoAnterior[linha - 1][coluna + 1]
                                        + geracaoAnterior[linha][coluna + 1]) < 2)) {
                            matrizAlterada[linha][coluna] = 0;
                        } else {
                            matrizAlterada[linha][coluna] = geracaoAnterior[linha][coluna];
                        }
                    }
                    // SE NÃO FOR A PRIMEIRA COLUNA E NÃO FOR A ÚLTIMA
                    else if (coluna > 0 && coluna < numLinha - 1) {
                        // Se o elemento estiver morto e três vizinhos
                        if (geracaoAnterior[linha][coluna] == 0 && ((geracaoAnterior[linha][coluna - 1]
                                + geracaoAnterior[linha - 1][coluna - 1] + geracaoAnterior[linha - 1][coluna]
                                + geracaoAnterior[linha - 1][coluna + 1] + geracaoAnterior[linha][coluna + 1]) == 3)) {
                            matrizAlterada[linha][coluna] = 1;
                        } // Se o elemento estiver vivo, mais que 3 ou menos que 2 vizinhos
                        else if (geracaoAnterior[linha][coluna] == 1
                                && ((geracaoAnterior[linha][coluna - 1] + geracaoAnterior[linha - 1][coluna - 1]
                                        + geracaoAnterior[linha - 1][coluna] + geracaoAnterior[linha - 1][coluna + 1]
                                        + geracaoAnterior[linha][coluna + 1]) > 3)

                                || ((geracaoAnterior[linha][coluna - 1] + geracaoAnterior[linha - 1][coluna - 1]
                                        + geracaoAnterior[linha - 1][coluna] + geracaoAnterior[linha - 1][coluna + 1]
                                        + geracaoAnterior[linha][coluna + 1]) < 2)) {
                            matrizAlterada[linha][coluna] = 0;
                        } else {
                            matrizAlterada[linha][coluna] = geracaoAnterior[linha][coluna];
                        }
                    }
                    // SE FOR A ÚLTIMA COLUNA
                    else {
                        // Se o elementos estiver morto e 3 vizinhos
                        if (geracaoAnterior[linha][coluna] == 0 && ((geracaoAnterior[linha - 1][coluna]
                                + geracaoAnterior[linha - 1][coluna - 1] + geracaoAnterior[linha][coluna - 1]) == 3)) {
                            matrizAlterada[linha][coluna] = 1;
                        } // Se o elemento estiver vivo, mais que 3 ou menos que 2 vizinhos
                        else if (geracaoAnterior[linha][coluna] == 1
                                && ((geracaoAnterior[linha - 1][coluna] + geracaoAnterior[linha - 1][coluna - 1]
                                        + geracaoAnterior[linha][coluna - 1]) > 3)

                                || ((geracaoAnterior[linha - 1][coluna] + geracaoAnterior[linha - 1][coluna - 1]
                                        + geracaoAnterior[linha][coluna - 1]) < 2)) {
                            matrizAlterada[linha][coluna] = 0;
                        } else {
                            matrizAlterada[linha][coluna] = geracaoAnterior[linha][coluna];
                        }
                    }
                }
            }
        }
        
        System.out.println("Geracao Atual:");
        imprimetMatriz(matrizAlterada);
        
        return null;
    }

}
