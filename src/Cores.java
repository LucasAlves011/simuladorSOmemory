public enum Cores {
    PRETO ( "\u001B[40m"),
    VERMELHO   ("\u001B[41m\u001B[30m"),
    VERDE  ("\u001B[42m\u001B[30m"),
    AMARELO   ("\u001B[43m\u001B[30m"),
    AZUL   ("\u001B[44m\u001B[30m"),
    ROXO   ("\u001B[45m\u001B[30m"),
    CIANO   ("\u001B[46m\u001B[30m"),
    BRANCO   ("\u001B[47m\u001B[30m"),
    RESET ("\u001B[0m");

    private final String valor;

    Cores(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
