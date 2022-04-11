import java.util.Random;
import java.text.DecimalFormat;


public class Main {
    public static final int MEMORY_SIZE = 160;
    public static final int MEMORY_SIZE2 = 45;
    public static boolean REALOC = true;
    public static DecimalFormat df = new DecimalFormat("#,###.00");

    public static Cores[] cores = new Cores[]{Cores.PRETO, Cores.VERMELHO, Cores.VERDE,
            Cores.AMARELO, Cores.AZUL, Cores.ROXO, Cores.CIANO, Cores.BRANCO};
    public static int colorGetIndex = 0;

    public static void main(String[] args) {
        df.format(1234.36);
        char[] memoria = new char[MEMORY_SIZE];

        char[] memoria1 = new char[MEMORY_SIZE2];
        char[] memoria2 = new char[MEMORY_SIZE2];
        char[] memoria3 = new char[MEMORY_SIZE2];
        char[] memoria4 = new char[MEMORY_SIZE2];
        char[] memoria5 = new char[MEMORY_SIZE2];

        for (int i = 0; i < MEMORY_SIZE; i++) {
            memoria[i] = '_';
        }

        for (int i = 0; i < MEMORY_SIZE2; i++) {
            memoria1[i] = '_';
            memoria2[i] = '_';
            memoria3[i] = '_';
            memoria4[i] = '_';
            memoria5[i] = '_';
        }


        var p1 = new Processo("Processo 1", 7);//A
        var p2 = new Processo("Processo 2", 16);//B
        var p3 = new Processo("Processo 3", 20);//C
        var p4 = new Processo("Processo 4", 3);//D
        var p5 = new Processo("Processo 5", 29);//E
        var p6 = new Processo("Processo 6", 7);//F
        var p7 = new Processo("Processo 7", 27);//G
        var p8 = new Processo("Processo 8", 30);//H

        enfiar(memoria, 15, 44, '%');//usado apenas para teste
        enfiar(memoria, 74, 84, '#');//usado apenas para teste
        enfiar(memoria, 132, 138, '@');//usado apenas para teste

        enfiar(memoria1, 0, 30, 'Z');//usado apenas para teste
        enfiar(memoria1, 31, 43, 'Y');//usado apenas para teste

        enfiar(memoria2, 0, 15, 'T');//usado apenas para teste
        enfiar(memoria2, 16, 22, 'U');//usado apenas para teste
        enfiar(memoria2, 28, 43, 'P');//usado apenas para teste

        enfiar(memoria3, 0, 15, 'T');//usado apenas para teste
        enfiar(memoria3, 18, 24, 'U');//usado apenas para teste
        enfiar(memoria3, 28, 41, 'P');//usado apenas para teste

        enfiar(memoria4, 0, 15, 'T');//usado apenas para teste
        enfiar(memoria4, 16, 22, 'U');//usado apenas para teste
        enfiar(memoria4, 23, 41, 'P');//usado apenas para teste


//       enfiar(memoria3,34,41,'Y');//usado apenas para teste
//       enfiar(memoria3,34,41,'Y');//usado apenas para teste


//        enfiar(memoria,85,117,'%');//usado apenas para teste
//        alocar(memoria,1,20,'A');
//        alocar(memoria,29,45,'B');
        imprimir(memoria);
        alocar(memoria, p1);

        imprimir(memoria);
        alocar(memoria, p2);

        imprimir(memoria);
        alocar(memoria, p3);

        imprimir(memoria);
        alocar(memoria, p4);

        imprimir(memoria);
        alocar(memoria, p5);

        imprimir(memoria);
        alocar(memoria, p6);

        imprimir(memoria);
        alocar(memoria, p7);
        imprimir(memoria);
        alocar(memoria, p8);

        logMemory(memoria);


//        alocar(memoria,11,18,'C');
//        alocar(memoria,52,60,'D');
//        alocar(memoria,61,66,'E');
//        alocar(memoria,69,75,'F');

//        var p1 = new Processo();
//
//        var p3 = new Processo("Teste 2",32,7);
//        System.out.print(Cores.PRETO);
//        System.out.println(p1);
//        System.out.println(p2);
//        System.out.println(p3);
//        System.out.println(firstFit(memoria,p2));
//        imprimir(memoria);
//        logMemory(memoria);


       /* imprimir(memoria1);
        System.out.println(verifyCompact(memoria1) + "\n\n");
        imprimir(memoria2);
        System.out.println(verifyCompact(memoria2) + "\n\n");

        imprimir(memoria3);
        System.out.println(verifyCompact(memoria3) + "\n\n");

        imprimir(memoria4);
        System.out.println(verifyCompact(memoria4) + "\n\n");

        imprimir(memoria5);
        System.out.println(verifyCompact(memoria5) + "\n\n");*/
//        imprimir(memoria2);
//        compactacao(memoria2);
//        imprimir(memoria2);


//   4    compactacao(memoria);
//        imprimir(memoria);
    }

    public static void compactacao(char[] memoria) {
        int vetorComeco = 0;
        int vetorFim = 0;
        int registradorBase = 0;
        boolean gate = true;

        for (int i = 0; i < memoria.length; i++) {
            if (memoria[i] == '_') {
                registradorBase = i;
                break;
            }
        }

        for (int i = registradorBase; i < memoria.length; i++) {
            if (memoria[i] != '_' && gate) {
                vetorComeco = i;
                gate = false;
            } else if (memoria[i] == '_' && !gate) {
                vetorFim = i;
                break;
            }
        }
        char[] vetorAux = new char[vetorFim - vetorComeco];
        int index = 0;

        for (int i = vetorComeco; i < vetorFim; i++) {
            vetorAux[index++] = memoria[i];
            memoria[i] = '_';
        }
        index = 0;

        for (int i = 0; i < vetorAux.length; i++) {
            memoria[registradorBase++] = vetorAux[index++];
        }
    }

    public static void alocar(char[] memoria, Processo processo) {
        int registradorBase = firstFit(memoria, processo);
        if (registradorBase >= 0) {
            int i = registradorBase;
            memoria[i++] = '[';
            int meio = (processo.getTamanho() / 2) + registradorBase;

            for (; i < processo.getTamanho() + registradorBase - 1; i++) {
                if (i == meio)
                    memoria[i] = (char) processo.getId();
                else
                    memoria[i] = ' ';
            }
            memoria[i] = ']';
            REALOC = true;
        } else {
            while (verifyCompact(memoria)) {
                compactacao(memoria);
            }
            if (REALOC) {
                REALOC = false;
                alocar(memoria, processo);
            } else
                System.err.println((char) processo.getId() + " não pode ser alocado na memória. Realizar Swap.");
            //TODO FAZER SWAP AQUI
        }
    }

    public static boolean verifyCompact(char[] memoria) {
        int index = 0;
        boolean gate = true;

        if (memoria[memoria.length - 1] == '_') {
            for (int i = memoria.length - 1; i > 0; i--) {
                if (memoria[i] != '_' && gate) {
                    gate = false;
                } else if (memoria[i] == '_' && !gate) {
                    return true; // 3º
                }
            }
            return false; // 4º
        } else {
            for (int i = memoria.length - 1; i > 0; i--) {
                if (memoria[i] != '_')
                    return true; // Compactação necessaria
            }
            return false; // Memoria cheia, fazer swap.
        }
    }

    public static void enfiar(char[] memoria, int inicio, int fim, char simbolo) {
        int i = inicio;
        memoria[i++] = '[';
        int meio = ((fim - inicio) / 2) + inicio;

        for (; i < fim; i++) {
            if (i == meio)
                memoria[i] = simbolo;
            else
                memoria[i] = ' ';
        }
        memoria[i] = ']';
    }

    public static void logMemory(char[] memoria) {
        double livre = 0;
        double ocupada = 0;
        for (char c : memoria) {
            if (c != '_')
                ocupada++;
            else
                livre++;
            System.out.println();
            System.out.print((int) ocupada + " unidades ocupadas" + "(" + (ocupada * 100 / memoria.length) + "%)");
            System.out.print((int) livre + " unidades livres" + "(" + (livre * 100 / memoria.length) + "%)");
            //@TODO ajeitar esses arredondamentos

        }
    }

        public static void desalocar ( char[] memoria, Processo processo){
            Random random = new Random();
            int number = random.nextInt(MEMORY_SIZE);

            switch (memoria[number]) {
                case '[':
                    //vai pra esquerda
                    break;
                case ']':
                    //vai pra direita
                    break;
                case ' ':
                    //S
                    break;
                default:
                    break;
            }

        }

        public static int firstFit ( char[] memoria, Processo processo){
            boolean gate = true;
            int index = 0;
            for (int i = 0; i < memoria.length; i++) {
                if (index >= processo.getTamanho() - 1 && !gate)
                    return (i - index);
                else if (memoria[i] == '_' && gate) {
                    index++;
                    gate = false;
                } else if (memoria[i] == '_' && !gate)
                    index++;
                else if (memoria[i] != '_' && !gate) {
                    index = 0;
                    gate = true;
                }
            }
            return -1; // Não tem um bloco grande o suficiente para alocar, tentar realizar compactação
        }

        public static void imprimir ( char[] memoria){
            //TODO Concertar impressões de caracteres fora do array de memória
            //TODO CONCERTAR ESSA PORRA DESSAS CORES
            int swapColor = 0;
            for (int i = 0; i < memoria.length; i++) {
                if (memoria[i] != '_') {
                    System.out.print(cores[(colorGetIndex)].getValor());
                    while (memoria[i] != '_') {
                        if (memoria[i] == '[' || memoria[i] == ']')
                            swapColor++;
                        if (swapColor >= 2) {
//                        colorGetIndex++;
                            incrementarColor();
                            swapColor = 0;

                        }
                        System.out.print(memoria[i++]);
                        System.out.print(cores[(colorGetIndex)].getValor());
                    }
                    System.out.print(Cores.RESET.getValor());
//                colorGetIndex++;
                    incrementarColor();
                } else
                    System.out.print(memoria[i]);
            }
            System.out.println();
            System.out.println();
            colorGetIndex = 0;
        }

        public static void incrementarColor () {
            if (colorGetIndex > 6) {
                colorGetIndex = 0;
            }
            colorGetIndex++;
        }

    }
