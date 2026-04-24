class Carga extends Vagao {
    private double cargaEfetiva;

    public Carga(double comprimento, double peso) {
        this.comprimento = comprimento;
        this.peso = peso;
        this.cargaEfetiva = peso * 0.75; 
    }

    public double getCargaEfetiva() { return cargaEfetiva; }

    @Override
    public void imprime() {
        System.out.print("[Carga] ");
        super.imprime();
        System.out.println(", Carga Útil: " + cargaEfetiva + "t");
    }
}
