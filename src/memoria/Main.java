package memoria;

import java.util.Random;

public class Main {
    public static final int MEMORY_SIZE = 160;
    public static boolean REALOC = true;
    public static boolean SWAP = true;
    public static char[] swap = new char[MEMORY_SIZE];

    public static int colorGetIndex = 0;

    public static final Cores[] cores = new Cores[]{Cores.PRETO, Cores.VERMELHO, Cores.VERDE,
            Cores.AMARELO, Cores.AZUL, Cores.ROXO, Cores.CIANO, Cores.BRANCO};
    public static void main(String[] args) {
        char[] memoria = new char[MEMORY_SIZE];

        for (int i = 0; i < MEMORY_SIZE; i++) {
            memoria[i] = '_';
            swap[i] = '_';
        }

        var p1 = new Processo("memoria.Processo 1", 7);//A
        var p2 = new Processo("memoria.Processo 2", 16);//B
        var p3 = new Processo("memoria.Processo 3", 20);//C
        var p4 = new Processo("memoria.Processo 4", 3);//D
        var p5 = new Processo("memoria.Processo 5",29);//E
        var p6 = new Processo("memoria.Processo 6" ,7);//F
        var p7 = new Processo("memoria.Processo 7", 27);//G
        var p8 = new Processo("memoria.Processo 8",19);//H
        var p9 = new Processo("memoria.Processo 9",19);//I
        var p10 = new Processo("memoria.Processo 10",19);//J
        var p11 = new Processo("memoria.Processo 10",19);//K
        var p12 = new Processo("memoria.Processo 10",19);//L

        imprimir(memoria);

        inserir(memoria, 15, 44, '%');//usado apenas para teste
        inserir(memoria, 74, 84, '#');//usado apenas para teste
        inserir(memoria, 132, 138, '@');//usado apenas para teste


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

        imprimir(memoria);
        alocar(memoria, p9);

        imprimir(memoria);
        alocar(memoria, p10);

        imprimir(memoria);
        alocar(memoria, p11);

        imprimir(memoria);
        alocar(memoria, p12);

        imprimir(memoria);

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

            } else if (!verifyCompact(memoria)){
                //SWAP
                desalocar(memoria,swap);
                SWAP = false;
                alocar(memoria,processo);
                imprimir(memoria);
                System.out.println("\u001B[31m\t\t\t\t\t\t\t\t\t\t\t O processo de ID  \""+(char) processo.getId() + "\" não pode ser alocado na memória. Realizando Swap."+Cores.RESET.getValor());
                System.out.print("Swap -->");
                imprimir(swap);
            }else
                System.err.println("Swap realizado com sucesso, mas não houve memória suficiente mesmo retirando um processo da memória principal");
        }
    }

    public static boolean verifyCompact(char[] memoria) {
        int index = 0;
        boolean gate = true;

        if (memoria[memoria.length - 1] == '_') {
            for (int i = memoria.length - 1; i > 0; i--) {
                if (memoria[i] != '_' && gate)
                    gate = false;
                else if (memoria[i] == '_' && !gate)
                    return true; // 3º
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

    public static void inserir(char[] memoria, int inicio, int fim, char simbolo) {
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
        float livre = 0;
        float ocupada = 0;
        for (char c : memoria) {
            if (c != '_')
                ocupada++;
            else
                livre++;
        }
        System.out.println();
        System.out.printf("\t\t\t\t\t\t\t\t" + (int) ocupada + " unidades ocupadas (%.2f%%)         ",(ocupada * 100 / MEMORY_SIZE)  );
        System.out.printf( (int) livre + " unidades livres  (%.2f%%)",(livre * 100 / MEMORY_SIZE) );
        System.out.print("         Fragmentação externa " + externalFragmentation(memoria) + " unidades" );
        System.out.printf("(%.2f%%)", (float) externalFragmentation(memoria)*100/MEMORY_SIZE);
    }

    public static void desalocar ( char[] memoria,  char[] swap){
        Random random = new Random();
        char[] vetorAux;
        int index = 0;
        int number = random.nextInt(MEMORY_SIZE);

        int temp = 0;
        int vFinal = 0;
        int vInicio=0;

        switch (memoria[number]) {
            case '[':
                //acha
                for (int i = number; i < memoria.length; i++) {
                    if(memoria[i] == ']'){
                        vFinal = i+1;
                        temp = (i+1) - number;
                        break;
                    }
                }

                vetorAux = new char[temp];
                index = 0;

                //copia memoria para aux e apaga memoria
                for (int i = number; i < vFinal ; i++) {
                    vetorAux[index++] = memoria[i];
                    memoria[i] = '_';
                }
                index = 0;
                try{
                    int swapPosition = returnSwapPosition();
                    if (swapPosition != -1)
                        for (int i = 0; i < vetorAux.length; i++)
                            swap[swapPosition++] = vetorAux[index++];
                    else
                        throw new ArrayIndexOutOfBoundsException();

                }catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println("Aconteceu uma desgraça, até o swap ta cheio meu amigo COMPRE UMA MEMÓRIA" +
                            "URGENTEMENTE PQ TA COMPLICADO PRA KCT");
                }
                break;

            case ']':
                for (int i = number; i >= 0; i--) {
                    if (memoria[i] == '[') {
                        vInicio = i  ;
                        temp = (number- i)+1;
                        break;
                    }
                }

                vetorAux = new char[temp];
                index = 0;
                for (int i = number; i >= vInicio ; i--) {
                    vetorAux[index++] = memoria[i];
                    memoria[i] = '_';
                }

                index = 0;
                try{
                    int swapPosition = returnSwapPosition();
                    if (swapPosition != -1)
                        for (int i = vetorAux.length-1; i >= 0; i--)
                            swap[swapPosition++] = vetorAux[i];
//                    for (int i = 0;i < vetorAux.length ; i++)
//                        swap[swapPosition++] = vetorAux[index++];
                    else
                        throw new ArrayIndexOutOfBoundsException();

                }catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println("Aconteceu uma desgraça, até o swap ta cheio meu amigo COMPRE UMA MEMÓRIA" +
                            "URGENTEMENTE PQ TA FODA PRA KRLLL");
                }
                break;

            default:
                int indexInicio = 0;
                for (int i = number; i >= 0; i--) {
                    if (memoria[i] == '[') {
                        indexInicio = i;
                        break;
                    }
                }

                for (int i = indexInicio; i < memoria.length; i++) {
                    if(memoria[i] == ']'){
                        vFinal = i+1;
                        temp = (i+1) - indexInicio;
                        break;
                    }
                }

                vetorAux = new char[temp];
                index = 0;

                //copia memoria para aux e apaga memoria
                for (int i = indexInicio; i < vFinal ; i++) {
                    vetorAux[index++] = memoria[i];
                    memoria[i] = '_';
                }
                index = 0;
                try{
                    int swapPosition = returnSwapPosition();
                    if (swapPosition != -1)
                        for (int i = 0; i < vetorAux.length; i++)
                            swap[swapPosition++] = vetorAux[index++];
                    else
                        throw new ArrayIndexOutOfBoundsException();

                }catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println("Aconteceu uma desgraça, até o swap ta cheio meu amigo COMPRE UMA MEMÓRIA" +
                            "URGENTEMENTE PQ TA FODA PRA KRLLL");
                }
                break;
        }
    }

    public static int returnSwapPosition(){
        for (int i = 0; i < swap.length; i++) {
            if (swap[i] == '_')
                return i;
        }
        return -1;
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

    public static int externalFragmentation(char[] memoria){
       int exFrag = 0;
        for (int i = 0; i < MEMORY_SIZE; i++) {
            if (memoria[i] == '_')
                exFrag++;
        }
        for (int i = MEMORY_SIZE - 1;  i > 0 ; i--)
            if (memoria[i] != '_')
                break;
            else
             exFrag--;

        return exFrag;
    }

    public static void incrementarColor () {
        if (colorGetIndex > 6) {
            colorGetIndex = 0;
        }
        colorGetIndex++;
    }

    public static void imprimir (char[] memoria){
        for (int i = 0; i < MEMORY_SIZE; i++) {
            if (memoria[i] != '_'){
                if (memoria[i] == '['){
                    System.out.print(cores[colorGetIndex].getValor());
                    System.out.print(memoria[i]);
                }else if (memoria[i] == ']'){
                    System.out.print(memoria[i]);
                    incrementarColor();
                    System.out.print(Cores.RESET.getValor());
                }
                else
                    System.out.print(memoria[i]);
            }else
                System.out.print(memoria[i]);
        }

        colorGetIndex = 0;
        logMemory(memoria);
        System.out.println("\n\n");
    }

}
