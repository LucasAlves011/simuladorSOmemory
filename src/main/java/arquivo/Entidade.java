package arquivo;

import lombok.Data;

public @Data
abstract class Entidade {

    private String nome;
    private int tamanho;
    private Diretorio pai;
}
