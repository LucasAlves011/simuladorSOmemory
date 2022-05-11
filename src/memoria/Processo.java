package memoria;

public class Processo {

    public static int idGeral = 64;
    public static int indice2 = 0;

    private int id;
    private String nome;
    private int tamanho;

    public Processo() {
        this.id = ++idGeral;
    }

    public Processo(String nome, int tamanho) {
        this.id = ++idGeral;
        this.nome = nome;
        this.tamanho = tamanho;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    @Override
    public String toString() {
        return "memoria.Processo{ " +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", tamanho=" + tamanho +
                '}';
    }
}
