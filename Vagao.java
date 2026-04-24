import java.io.Serializable;

class Vagao implements Serializable {
    protected double comprimento; 
    protected double peso;        

    public void imprime() {
        System.out.print("Comprimento: " + comprimento + "m, Peso: " + peso + "t");
    }

    public double getComprimento() { return comprimento; }
    public double getPeso() { return peso; }
}