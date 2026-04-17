import java.io.Serializable;

// --- FRENTE 1: VAGÕES E PERSISTÊNCIA ---

abstract class Vagao implements Serializable {
    protected double comprimento; // [cite: 176]
    protected double peso; // [cite: 177]
    
    public void imprime() {
        System.out.println("Comprimento: " + comprimento + "m, Peso: " + peso + " toneladas."); // [cite: 181, 182]
    }
}

class Locomotiva extends Vagao { // [cite: 186]
    private double potencia; // [cite: 187]
    
    public Locomotiva(double comprimento, double peso, double potencia) {
        this.comprimento = comprimento;
        this.peso = peso;
        this.potencia = potencia;
    }
    
    @Override
    public void imprime() { // [cite: 188, 189]
        System.out.println("Tipo: Locomotiva. Potência: " + potencia + " HP."); // [cite: 191, 192]
        super.imprime(); // [cite: 193]
    }
}

class Passageiro extends Vagao {
    private int numPassageiros;
    
    public Passageiro(double comprimento, double peso, int numPassageiros) {
        this.comprimento = comprimento;
        this.peso = peso;
        this.numPassageiros = numPassageiros;
    }
    
    @Override
    public void imprime() {
        System.out.println("Tipo: Passageiro. Lotação: " + numPassageiros + " passageiros.");
        super.imprime(); // [cite: 124]
    }
}

class Carga extends Vagao {
    protected double carga; 
    
    public Carga(double comprimento, double peso) {
        this.comprimento = comprimento;
        this.peso = peso;
        this.carga = peso * 0.75; // 75% do peso total é carga [cite: 100]
    }
    
    @Override
    public void imprime() {
        System.out.println("Tipo: Carga. Carga suportada: " + carga + " toneladas.");
        super.imprime(); // [cite: 124]
    }
}

// A classe ObjetoPersistente já foi dada no PDF[cite: 19]. Basta copiá-la.

// --- FRENTE 2: O MOTOR DO DEQUE ---

class ComposicaoFerroviaria extends Deque implements Serializable { // [cite: 125]
    ObjetoPersistente ArqComp; // [cite: 65]
    
    public ComposicaoFerroviaria(int N, String nomeArquivo) { // [cite: 66]
        super(N); // [cite: 68]
        ArqComp = new ObjetoPersistente(nomeArquivo); // [cite: 69]
        carregar(); // [cite: 70]
    }
    
    private void salvar() { ArqComp.salvar(this); } // [cite: 71]
    private void carregar() { /* Implementação do PDF */ } // [cite: 73]
    
    public void criarComposicaoPadrao() { /* Lógica de limpar deque e adicionar 81 vagões */ salvar(); } // [cite: 108, 111, 198]
    
    public void inserirVagaoFrente(Vagao v) { /* Usa insertFront() do Deque */ salvar(); } // [cite: 112, 113]
    public void inserirVagaoFim(Vagao v) { /* Usa insertLast() do Deque */ salvar(); } // [cite: 112, 113]
    public Vagao removerVagaoFrente() { Vagao v = (Vagao) deleteFront(); salvar(); return v; } // [cite: 112, 113]
    public Vagao removerVagaoFim() { Vagao v = (Vagao) deleteLast(); salvar(); return v; } // [cite: 112, 113]
    
    public void recuperarNumerosVagoes() { /* Conta total e subtipos */ } // [cite: 114]
    public double contabilizarComprimento() { return 0.0; /* Lembrar do espaço de 2m */ } // 
    public double contabilizarPesoTotal() { return 0.0; } // 
    public void contabilizarPassageirosCarga() { /* Prints da soma */ } // [cite: 118]
    public boolean verificarPotencia() { return false; /* HPT mínimo 1.05 */ } // [cite: 103, 119]
    
    public void diagnosticoComposicao() { // [cite: 121]
        recuperarNumerosVagoes();
        contabilizarComprimento();
        contabilizarPassageirosCarga();
        verificarPotencia();
    }
    
    public Vagao getPrimeiroVagao() { return null; /* Fazer lógica */ } // [cite: 122]
    public Vagao getUltimoVagao() { return null; /* Fazer lógica */ } // [cite: 122]
}

// --- FRENTE 3: INTERFACE E MENU ---

public class ProgramaFerrovia { // [cite: 151]
    public static void main(String[] args) {
        // Criar o Scanner
        // Instanciar ComposicaoFerroviaria
        // Criar loop while com switch case para opções de 'a' até 'g' [cite: 130, 131, 132, 133, 134, 135, 136, 137]
    }
}