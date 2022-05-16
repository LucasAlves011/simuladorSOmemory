package entrada_saida;

public class Bloco {

    public static int temporario = -1;

    private int id;
    private String conteudo;

    public Bloco() {
        id = ++temporario;
    }
    public Bloco(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id=id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo=conteudo;
    }

    @Override
    public String toString() {
         return "B" + id ;
    }
}
