class Passageiro extends Vagao {
    private int numPassageiros;

    public Passageiro(double comprimento, double peso, int numPassageiros) {
        this.comprimento = comprimento;
        this.peso = peso;
        this.numPassageiros = numPassageiros;
    }

    public int getNumPassageiros() { return numPassageiros; }

    @Override
    public void imprime() {
        System.out.print("[Passageiro] ");
        super.imprime();
        System.out.println(", Passageiros: " + numPassageiros);
    }
}