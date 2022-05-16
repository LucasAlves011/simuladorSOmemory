package entrada_saida;

import java.util.*;

public class Main {
    public static final int MAX = 64;
    public static final int MEIO = 32;
    public static Bloco[] array = new Bloco[MAX];
    public static void main(String[] args) {

        Random x = new Random();
        int tamanho = x.nextInt(5) +5;

        for (int i=0; i < array.length; i++) {
            array[i] = new Bloco();
        }

        Bloco[] solicitacoes = new Bloco[tamanho];

        for (int i=0; i < tamanho; i++) {
            solicitacoes[i] = array[x.nextInt(64)];
        }

//        Bloco[] entrada = {array[60],array[10],array[30],array[26],array[40]};
        System.out.println(Arrays.toString(array) + "\n");
        System.out.println("Solicitações :" + Arrays.toString(solicitacoes));




       // elevator(solicitacoes);


       sstf(solicitacoes);


    }


    public static void elevator(Bloco[] solicitacao ){
        if(solicitacao.length == 0) return ;
        int posicaoAtual = MEIO;
        ArrayList<Alinhamento> resultante = new ArrayList<>();

        var maiores = Arrays.stream(solicitacao).filter( n -> n.getId() >= MEIO).sorted(Comparator.comparingInt(Bloco::getId)).toArray(Bloco[]::new);
        var menores = Arrays.stream(solicitacao).filter( n -> n.getId() < MEIO).sorted(Comparator.comparingInt(Bloco::getId).reversed()).toArray(Bloco[]::new);


        if (maiores.length > 0) resultante.add(new Alinhamento(new Bloco(MEIO),maiores[0]));
        else resultante.add(new Alinhamento(new Bloco(MEIO),menores[menores.length-1]));

        Bloco fimMaior = maiores[maiores.length-1];
        for (int i=1; i < solicitacao.length; i++) { //subindo
            try {
                resultante.add(new Alinhamento(maiores[i - 1], maiores[i]));
            }catch (ArrayIndexOutOfBoundsException x){
            }
        }

        for (int i=0; i < solicitacao.length; i++) { //descendo
            if (i == 0 ) resultante.add(new Alinhamento(fimMaior, menores[i]));
            try {
                resultante.add(new Alinhamento(menores[i-1], menores[i]));
            }catch (ArrayIndexOutOfBoundsException c){

            }
        }
        Bloco x = menores[0];

        mostrarAtt(resultante, menores);
    }

    private static void mostrarAtt(ArrayList<Alinhamento> seila, Bloco[] menores) {
        var a =seila.stream().map((Alinhamento::getInicio)).toList();
        ArrayList<Bloco> pa = new ArrayList<>(a);
        pa.add(menores[menores.length-1]);
        System.out.print("\nOrdem dos blocos visitados : ");
        System.out.println(pa);

        System.out.println("Tempo gasto com seek foi de " + seila.stream().mapToInt(Alinhamento::getDistancia).sum() + " unidades");
    }

    private static void mostrarAtt2(ArrayList<Alinhamento> seila, Bloco bloco){
        ArrayList<Bloco> pa = new ArrayList<>(seila.stream().map((Alinhamento::getInicio)).toList());
        pa.add(bloco);
        System.out.println("Ordem dos blocos visitados" + pa);

        System.out.println("Tempo gasto com seek foi de " + seila.stream().mapToInt(Alinhamento::getDistancia).sum() + " unidades");
    }

    public static void sstf(Bloco[] solicitacao){
        if(solicitacao.length == 0) return ;
        int posicaoAtual = MEIO;
        ArrayList<Alinhamento> listasstf = new ArrayList<>();

        ArrayList<Bloco> copiaSolicitacao =  new ArrayList<Bloco>(List.of(solicitacao));
        Bloco temporario;
        Bloco atual = array[MEIO];
        Bloco passar = null;
        while(copiaSolicitacao.size() > 0){
            temporario = copiaSolicitacao.get(0);

            for (int j=0; j < copiaSolicitacao.size(); j++)
                if (Math.abs(atual.getId() - copiaSolicitacao.get(j).getId()) < Math.abs(atual.getId() - temporario.getId()))
                    temporario=copiaSolicitacao.get(j);

            listasstf.add(new Alinhamento(atual,temporario));
            atual = temporario;
            if (copiaSolicitacao.size() == 1)
                passar = temporario;
            copiaSolicitacao.remove(temporario);

        }

        mostrarAtt2(listasstf,passar);
    }

}
