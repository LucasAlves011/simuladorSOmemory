package arquivo;

import memoria.Cores;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class MainArquivo {
    public static ArrayList<Arquivo> controleArquivo = new ArrayList<>();
    public static ArrayList<Diretorio> controleDiretorio = new ArrayList<>();
    public static final int MEMORY_SIZE = 256;
    public static final int BLOCK_SIZE = 4;
    public static int[][] MEMORY = new int[MEMORY_SIZE][2];

    public static Random random = new Random();

    public static void main(String[] args) {

        Diretorio raiz = new Diretorio("/");
        Diretorio documentos = new Diretorio("documentos",raiz);
        Diretorio planilhas = new Diretorio("planilhas",documentos);
        Diretorio fotos = new Diretorio("fotos",raiz);

        Arquivo x1 = new Arquivo(raiz,"arquivoConfig.txt",10);
        Arquivo x2 = new Arquivo(fotos,"foto de Casamento",15);
        Arquivo x3 = new Arquivo(raiz,"sistemConfig",12);
        Arquivo x4 = new Arquivo(documentos,"relatório de Fim de Período",13);
        Arquivo x5 = new Arquivo(planilhas,"planilha",8);

        imprimir();
        listarDiretorio(fotos); //função que lista os arquivos dentro do diretório
        deletarArquivo(x2); // deleta arquivo
        imprimir();

        Arquivo x6 = new Arquivo(raiz,"temp",23);
        Arquivo x7 = new Arquivo(documentos,"Paradigmas.txt", 17);
        Arquivo x8 = new Arquivo(fotos,"printScreen",37);

        imprimir();

        deletarArquivo(x7);

        Arquivo x9 = new Arquivo(fotos,"arq9",7);

        imprimir();

        documentos.deletarRecursivamente(); // deleta recursivamente todos os arquivos e diretórios

        listarDiretorio(raiz);

        imprimir();

        System.out.println(raiz);

    }

    public static void alocar(Arquivo arquivo){
        int celulas =(int) Math.ceil((double) arquivo.getTamanho()/ (double)BLOCK_SIZE) ;
        int indexFreeInicio;

        indexFreeInicio = findIndex(celulas,0);

        int j = 0;
        for (int i = 0; i < (celulas*4); i++) {
            MEMORY[indexFreeInicio+ i][0] = arquivo.getId();
            if(j < arquivo.getTamanho()) {
                MEMORY[indexFreeInicio+ i][1] = 1;
                j++;
            }
            else
                MEMORY[indexFreeInicio+ i][1] = 0;
        }
    }

    public static void imprimir(){
        //memoria
        for (int i = 0; i < MEMORY_SIZE ; i++) {
            if (i % BLOCK_SIZE ==0 )
                System.out.print(" ");
            if (MEMORY[i][0] == 0)
                System.out.print(Cores.RESET.getValor()+MEMORY[i][0]);
            else {
                if (Objects.equals(findColor(MEMORY[i][0]), Cores.PRETO))
                    System.out.print(Cores.WHITE_BRIGHT.getValor());
                System.out.print(findColor(MEMORY[i][0]).getValor() + MEMORY[i][0]);
            }
        }


        System.out.println();

        //bit de alocação
        for (int i = 0; i < MEMORY_SIZE ; i++) {
            if (i % BLOCK_SIZE ==0 )
                System.out.print(" ");
            if (MEMORY[i][1] == 1)
                System.out.print(Cores.VERDE.getValor());
            else if (MEMORY[i][1] == 0 && MEMORY[i][0] != 0 )
                System.out.print(Cores.VERMELHO.getValor());
            else
                System.out.print(Cores.RESET.getValor());
            System.out.print(MEMORY[i][1]);
        }

        System.out.println("\n");
        fragIntExt();
    }

    public static void deletarArquivo(Arquivo arquivo){
        for (int i = 0; i < MEMORY_SIZE; i++) {
            if (MEMORY[i][0] == arquivo.getId()) {
                MEMORY[i][0] = 0;
                MEMORY[i][1] = 0;
            }
        }
        arquivo.getPai().deleteFile(arquivo);
    }

    public static void listarDiretorio(Diretorio diretorio){
        System.out.print("Arquivos do diretório: " + diretorio.getNome() + "        Tamanho: " + diretorio.getTamanho()+ "        Arquivos: \n") ;
        System.out.println(diretorio.getArquivos() + "\n");
    }
    
    public static void fragIntExt(){
        int exFrag = 0;
        int intFrag = 0;
        for (int i = 0; i < MEMORY_SIZE; i++)
            if (MEMORY[i][0] == 0)
                exFrag++;

        for (int i = MEMORY_SIZE -1 ;  i > 0 ; i--) {
            if (MEMORY[i][0] == 0)
                exFrag--;
            else
                break;
        }

        for (int i = 0; i < MEMORY_SIZE; i++) {
            if (MEMORY[i][0] != 0 && MEMORY[i][1] == 0)
                intFrag++;
        }

        System.out.println("Fragmentação Externa : "+exFrag+ " ou "+ (double)exFrag/BLOCK_SIZE+" BLOCOS "+"     " + " Fragmentação Interna : " + intFrag +" ou " + (double)intFrag/BLOCK_SIZE+" BLOCOS \n");
    }

    public static Cores findColor(int id){
        for (Arquivo x: controleArquivo) {
            if (x.getId() == id)
                return x.getColor();
        }
        return Cores.VERMELHO;
    }

    public static int findIndex(int tamanho, int start){
        int tempIndex = start ;
        for (int i = tempIndex; i < MEMORY_SIZE; i+=4) {
            if(MEMORY[i][0] == 0) {
                tempIndex = i;
                break;
            }
        }

        for (int i = tempIndex; i <= (tamanho*4) + tempIndex ; i+=4) {
            if (MEMORY[i][0] != 0)
               return findIndex(tamanho,i);
        }
        return tempIndex;
    }

}
