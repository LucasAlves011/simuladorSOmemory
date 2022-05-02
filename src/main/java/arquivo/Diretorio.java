package arquivo;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

public @Data class Diretorio {
    @ToString.Exclude
    private ArrayList<Arquivo> arquivos;
    private Diretorio pai;
    private String nome;
    private int tamanho;
    private StringBuilder caminho = new StringBuilder();
    public Diretorio(String nome) {
        if (!MainArquivo.controleDiretorio.stream().map(Diretorio::getNome).toList().contains(nome)){
            arquivos = new ArrayList<>();
            this.nome = nome;
            MainArquivo.controleDiretorio.add(this);
        }
        else
            System.err.println("Não foi possivel criar um Diretorio, nome já utiilizado !!");
    }

    public Diretorio(String nome,Diretorio pai) {
        if (!MainArquivo.controleDiretorio.stream().map(Diretorio::getNome).toList().contains(nome)){
            arquivos = new ArrayList<>();
            this.pai = pai;
            this.nome = nome;
            MainArquivo.controleDiretorio.add(this);
        }
        else
            System.err.println("Não foi possivel criar um Diretorio, nome já utiilizado !!");
    }

    public void deleteDiretorio(Arquivo arquivo){
        arquivos.remove(arquivo);
        tamanho -= arquivo.getTamanho();
    }

    @Override
    public String toString(){
        return nome;
    }
}
