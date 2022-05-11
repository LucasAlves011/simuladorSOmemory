package memoria;

public enum Cores {
    PRETO ( "\u001B[40m"),
    VERMELHO   ("\u001B[41m\u001B[30m"),
    VERDE  ("\u001B[42m\u001B[30m"),
    AMARELO   ("\u001B[43m\u001B[30m"),
    AZUL   ("\u001B[44m\u001B[30m"),
    ROXO   ("\u001B[45m\u001B[30m"),
    CIANO   ("\u001B[46m\u001B[30m"),
    BRANCO   ("\u001B[47m\u001B[30m"),
    RESET ("\u001B[0m")
    ,BLACK_BOLD  ("\033[1;30m")  // BLACK
    ,RED_BOLD  ("\033[1;31m" )   // RED
    ,GREEN_BOLD  ("\033[1;32m")  // GREEN
    ,YELLOW_BOLD  ("\033[1;33m") // YELLOW
    ,BLUE_BOLD  ("\033[1;34m") // BLUE
    ,PURPLE_BOLD  ("\033[1;35m") // PURPLE
    ,CYAN_BOLD ( "\033[1;36m")   // CYAN
    ,WHITE_BOLD  ("\033[1;37m")  // WHITE



    // Background
    , BLACK_BACKGROUND  ("\033[40m")  // BLACK
    , RED_BACKGROUND ( "\033[41m")    // RED
    , GREEN_BACKGROUND ( "\033[42m")  // GREEN
    , YELLOW_BACKGROUND ( "\033[43m") // YELLOW
    , BLUE_BACKGROUND  ("\033[44m")   // BLUE
    , PURPLE_BACKGROUND ( "\033[45m") // PURPLE
    , CYAN_BACKGROUND ( "\033[46m" )  // CYAN
    , WHITE_BACKGROUND ( "\033[47m" ) // WHITE

    // High Intensity
    , BLACK_BRIGHT  ("\033[0;90m")  // BLACK
    , RED_BRIGHT  ("\033[0;91m")    // RED
    , GREEN_BRIGHT ( "\033[0;92m")  // GREEN
    , YELLOW_BRIGHT ( "\033[0;93m") // YELLOW
    , BLUE_BRIGHT ( "\033[0;94m" )  // BLUE
    , PURPLE_BRIGHT ( "\033[0;95m") // PURPLE
    , CYAN_BRIGHT  ("\033[0;96m")   // CYAN
    , WHITE_BRIGHT ( "\033[0;97m")  // WHITE

    // Bold High Intensity
    , BLACK_BOLD_BRIGHT  ("\033[1;90m") // BLACK
    , RED_BOLD_BRIGHT  ("\033[1;91m")   // RED
    , GREEN_BOLD_BRIGHT  ("\033[1;92m") // GREEN
    , YELLOW_BOLD_BRIGHT  ("\033[1;93m")// YELLOW
    , BLUE_BOLD_BRIGHT  ("\033[1;94m")  // BLUE
    , PURPLE_BOLD_BRIGHT  ("\033[1;95m")// PURPLE
    , CYAN_BOLD_BRIGHT  ("\033[1;96m")  // CYAN
    , WHITE_BOLD_BRIGHT  ("\033[1;97m") // WHITE

    // High Intensity backgrounds
    , BLACK_BACKGROUND_BRIGHT ( "\033[0;100m")// BLACK
    , RED_BACKGROUND_BRIGHT ( "\033[0;101m")// RED
    , GREEN_BACKGROUND_BRIGHT ( "\033[0;102m")// GREEN
    , YELLOW_BACKGROUND_BRIGHT( "\033[0;103m")// YELLOW
    , BLUE_BACKGROUND_BRIGHT  ("\033[0;104m")// BLUE
    , PURPLE_BACKGROUND_BRIGHT  ("\033[0;105m") // PURPLE
    , CYAN_BACKGROUND_BRIGHT ( "\033[0;106m")  // CYAN
    , WHITE_BACKGROUND_BRIGHT  ("\033[0;107m");

    private final String valor;

    Cores(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
