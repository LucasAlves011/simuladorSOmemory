public class Processo {

    public static int idGeral = 64;
    public static int indice2 = 0;

    private int id;
    private String nome;
    private int tamanho;
    private int duracao;
    private int cor;

    public Processo() {
        this.id = ++idGeral;
        this.duracao = -13;
    }

    public Processo(String nome, int tamanho) {
        this.id = ++idGeral;
        this.nome = nome;
        this.tamanho = tamanho;
        this.duracao = -13;
        this.cor = (indice2++) % 8;
    }

    public Processo(String nome, int tamanho, int duracao) {
        this.id = ++idGeral;
        this.nome = nome;
        this.tamanho = tamanho;
        this.duracao = duracao;
    }

    public int getCor() {
        return cor;
    }

    public void setCor(int cor) {
        this.cor = cor;
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

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    @Override
    public String toString() {
        return "Processo{ " +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", tamanho=" + tamanho +
                ", duracao=" + duracao +
                '}';
    }
}
