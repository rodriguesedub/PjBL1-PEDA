class Locomotiva extends Vagao {
    private double potencia;

    public Locomotiva(double comprimento, double peso, double potencia) {
        this.comprimento = comprimento;
        this.peso = peso;
        this.potencia = potencia;
    }

    public double getPotencia() { return potencia; }

    @Override
    public void imprime() {
        System.out.print("[Locomotiva] ");
        super.imprime();
        System.out.println(", Potência: " + potencia + " HP");
    }
}