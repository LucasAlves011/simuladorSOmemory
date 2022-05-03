package arquivo;

import lombok.Data;
import lombok.ToString;
import memoria.Cores;

import java.util.ArrayList;
import java.util.Objects;

public @Data class Diretorio {
    @ToString.Exclude
    private ArrayList<Arquivo> arquivos;
    private ArrayList<Diretorio> diretorios;
    private Diretorio pai;
    private String nome;
    private int tamanho;

    public Diretorio(String nome) {
        if (!MainArquivo.controleDiretorio.stream().map(Diretorio::getNome).toList().contains(nome)){
            arquivos = new ArrayList<>();
            diretorios = new ArrayList<>();
            this.nome = nome;
            if (pai != null)
                pai.adicionarDiretorioAoDiretorio(this);
            MainArquivo.controleDiretorio.add(this);
        }
        else
            System.err.println("Não foi possivel criar um Diretorio, nome já utiilizado !!");
    }

    public Diretorio(String nome,Diretorio pai) {
        if (!MainArquivo.controleDiretorio.stream().map(Diretorio::getNome).toList().contains(nome)){
            arquivos = new ArrayList<>();
            diretorios = new ArrayList<>();
            this.pai = pai;
            this.nome = nome;
            if (pai != null)
                pai.adicionarDiretorioAoDiretorio(this);
            MainArquivo.controleDiretorio.add(this);
        }
        else
            System.err.println("Não foi possivel criar um Diretorio, nome já utiilizado !!");
    }

    public void deleteFile(Arquivo arquivo){
        arquivos.remove(arquivo);
        tamanho -= arquivo.getTamanho();
    }

    public void deletarRecursivamente(){
        int indexArquivo = arquivos.size();
        for (int i = 0; i < indexArquivo; i++) {
            MainArquivo.deletarArquivo(arquivos.get(0));
        }

        int indexDiretorio = diretorios.size();
        if (!diretorios.isEmpty())
            for (int i = 0; i < indexDiretorio; i++) {
                diretorios.get(0).deletarRecursivamente();
                if (diretorios.get(0).getPai() != null)
                    diretorios.get(0).getPai().diretorios.remove(diretorios.get(0));

                MainArquivo.controleDiretorio.remove(MainArquivo.controleDiretorio.stream().filter(
                        n -> Objects.equals(n.getNome(), diretorios.get(0).getNome())));
            }
    }

    public void adicionarDiretorioAoDiretorio(Diretorio diretorio){
        this.diretorios.add(diretorio);
    }

    @Override
    public String toString(){
        if(diretorios.isEmpty())
            return Cores.VERDE.getValor() + nome + Cores.RESET.getValor()+"\n " + arquivos;
        else
            return Cores.VERDE.getValor() + nome + Cores.RESET.getValor()+"\n "+ arquivos + "\n " +diretorios;

    }

}
