package arquivo;

import lombok.Data;
import memoria.Cores;

import static arquivo.MainArquivo.controleArquivo;
import static memoria.Main.cores;
import static arquivo.MainArquivo.random;

public @Data class Arquivo {

    private static int INDEX = 0 ;

    private int id = ++INDEX;
    private Diretorio pai;
    private String nome;
    private int tamanho;
    private Cores color;

    public Arquivo(String nome,int tamanho){
       if( !MainArquivo.controleArquivo.stream().map(Arquivo::getNome).toList().contains(nome)){
           this.nome = nome;
           this.tamanho = tamanho;
           color =  cores[random.nextInt(8)];
           controleArquivo.add(this);
       }
       else
           System.err.println("Não foi possivel criar um Arquivo, nome já utiilizado !!");
    }

    public Arquivo(Diretorio pai ,String nome,int tamanho){
        if(!MainArquivo.controleArquivo.stream().map(Arquivo::getNome).toList().contains(nome)){
            this.nome = nome;
            this.pai = pai;
            this.tamanho = tamanho;
            color =  cores[random.nextInt(8)];
            pai.getArquivos().add(this);
            pai.setTamanho(pai.getTamanho() + tamanho);
            controleArquivo.add(this);
        }
        else
            System.err.println("Não foi possivel criar um Arquivo, nome já utiilizado !!");
    }

    public Arquivo() {
        color =  cores[random.nextInt(8)];
    }

    public String toString(){
        return "\n Nome: " + nome + "  Tamanho: " + tamanho ;
    }

}
