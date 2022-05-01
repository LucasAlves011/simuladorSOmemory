package arquivo;

import memoria.Cores;

import java.util.ArrayList;
import java.util.Random;

public class MainArquivo {
    private static ArrayList<Arquivo> controle = new ArrayList<>();
    public static final int MEMORY_SIZE = 256;
    public static final int BLOCK_SIZE = 4;
    public static int[][] tabela = new int[256][2];


//    public static final Cores[] cores = new Cores[]{Cores.PRETO, Cores.VERMELHO, Cores.VERDE,
//            Cores.AMARELO, Cores.AZUL, Cores.ROXO, Cores.CIANO, Cores.BRANCO};
    public static Random random = new Random();

    public static void main(String[] args) {
       /* tabela[0][0] = 1;
        tabela[0][1] = 1;
        tabela[1][0] = 1;
        tabela[1][1] = 1;
        tabela[2][0] = 1;
        tabela[2][1] = 1;
        tabela[3][0] = 1;
        tabela[3][1] = 1;

        tabela[4][0] = 1;
        tabela[4][1] = 1;
        tabela[5][0] = 1;
        tabela[5][1] = 0;
        tabela[6][0] = 1;
        tabela[6][1] = 0;
        tabela[7][0] = 1;
        tabela[7][1] = 0;

        for (int i = 0; i < 256; i++) {
            System.out.print(tabela[i][0]);
        }
        System.out.println();
        for (int i = 0; i < 256; i++) {
            System.out.print(tabela[i][1]);
        }*/

        Arquivo x = new Arquivo();
        Arquivo x1 = new Arquivo();
        Arquivo x2 = new Arquivo();
        Arquivo x3 = new Arquivo();
    }
}
