package entrada_saida;

public class Alinhamento extends Thread{
    private Bloco inicio;
    private Bloco fim;
    private int distancia;
    private boolean direcao;

    public Alinhamento(Bloco inicio, Bloco fim) {
        this.inicio=inicio;
        this.fim=fim;
        int temp = inicio.getId() - fim.getId();
        if (inicio.getId() == fim.getId()) distancia =0;
        else if (temp > 0){
            distancia = temp-1;
            direcao = true;
        } else {
            distancia = Math.abs(temp)-1;
            direcao = false;
        }
        System.out.println(this.toString());
        try{
            sleep(1000);
        }catch (InterruptedException x){
            x.printStackTrace();
        }
    }

    public Bloco getInicio() {
        return inicio;
    }

    public void setInicio(Bloco inicio) {
        this.inicio=inicio;
    }

    public Bloco getFim() {
        return fim;
    }

    public void setFim(Bloco fim) {
        this.fim=fim;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia=distancia;
    }

    public boolean isDirecao() {
        return direcao;
    }

    public void setDirecao(boolean direcao) {
        this.direcao=direcao;
    }

    @Override
    public String toString() {
        if (!direcao)
          return inicio + "\033[1;32m" + " -> " + "\u001B[0m" + fim + " = " + distancia+"\t";
        else
            return fim +"\033[1;31m" +" <- "+ "\u001B[0m" +  inicio + " = " + distancia+"\t";
    }
}
