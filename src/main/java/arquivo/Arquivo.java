package arquivo;

import lombok.Data;
import memoria.Cores;

import static memoria.Main.cores;
import static arquivo.MainArquivo.random;

public @Data class Arquivo {

    private static int INDEX = 0 ;

    private int id = ++INDEX;
    private Arquivo pai;
    private String nome;
    private int tamanho;
    private Cores color;

    public Arquivo(Arquivo pai,String nome,int tamanho){
        this.pai = pai;
        this.nome = nome;
        this.tamanho = tamanho;
        color =  cores[random.nextInt(8)];
    }

    public Arquivo(String nome,int tamanho){
        this.pai = null;
        this.nome = nome;
        this.tamanho = tamanho;
        color =  cores[random.nextInt(8)];
    }

    public Arquivo() {
        color =  cores[random.nextInt(8)];
    }
}
