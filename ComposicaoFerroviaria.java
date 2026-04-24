import java.io.Serializable;

class ComposicaoFerroviaria extends Deque implements Serializable {
    ObjetoPersistente ArqComp;
    

    public ComposicaoFerroviaria(int N, String nomeArquivo) {
        super(N);
        ArqComp = new ObjetoPersistente(nomeArquivo);
        carregar();
    }
    
    private void salvar() { 
        ArqComp.salvar(this); 
    }

    private void carregar() {
        ComposicaoFerroviaria cf = (ComposicaoFerroviaria) ArqComp.carregar();
        if (cf != null) {
            this.front = cf.front;
            this.rear = cf.rear;
            this.ptr = cf.ptr;
            this.size = cf.size;
            this.N = cf.N;
            this.data = cf.data;
        }
    }
    
    public void criarComposicaoPadrao() { 
        
        while (!isEmpty()) deleteFirst();

        // 1 Locomotiva (20m, 150t, 2500HP)
        addLast(new Locomotiva(20, 150, 2500));
        
        // 50 Passageiros (24m, 40t, 30 pass)
        for (int i = 0; i < 50; i++) {
            addLast(new Passageiro(24, 40, 30));
        }

        // 30 Carga (17m, 20t)
        for (int i = 0; i < 30; i++) {
            addLast(new Carga(17, 20));
        }
        
        System.out.println("Composição padrão criada com sucesso!");
        salvar();
    }
    
    public void adicionarVagaoInicio(Vagao v) {
        addFirst(v);
        salvar(); 
    }

    public void adicionarVagaoFim(Vagao v) { 
        addLast(v);
        salvar(); 
    }

    public Vagao removerVagaoInicio() { 
        Vagao v = (Vagao) deleteFirst();
        salvar(); 
        return v; 
    }

    public Vagao removerVagaoFim() { 
        Vagao v = (Vagao) deleteLast(); 
        salvar(); 
        return v; 
    }
    
    public void mostrarDiagnostico() {
        System.out.println("\n--- DIAGNÓSTICO DA COMPOSIÇÃO ---");
        
        int loc = 0, pas = 0, car = 0;
        double pesoTotal = 0, comprimentoTotal = 0, cargaTotal = 0, potTotal = 0;
        int passTotal = 0;
        double potUmaLocomotiva = 0;

        rewind();
        for (int i = 0; i < getSize(); i++) {
            Vagao v = (Vagao) next();
            pesoTotal += v.getPeso();
            comprimentoTotal += v.getComprimento();

            if (v instanceof Locomotiva) {
                //Contagem Locomotiva
                loc++;
                //Potencia locomotiva
                potTotal += ((Locomotiva) v).getPotencia();
                //Potencia de uma 
                potUmaLocomotiva = ((Locomotiva) v).getPotencia();
            } else if (v instanceof Passageiro) {
                //Contagem vagoes passeageiro
                pas++;
                //Total numero de passageiros
                passTotal += ((Passageiro) v).getNumPassageiros();
            } else if (v instanceof Carga) {
                //Contagem vagoes de carga
                car++;
                //Somatorio cargas efetiva
                cargaTotal += ((Carga) v).getCargaEfetiva();
            }
        }

        // Ajuste de comprimento: soma dos vagões + 2m de espaço entre eles
        if (getSize() > 1) {
            comprimentoTotal += (getSize() - 1) * 2;
        }

        System.out.println("1. Total de Vagões: " + getSize());
        System.out.println("   - Locomotivas: " + loc + " | Passageiros: " + pas + " | Carga: " + car);
        System.out.println("2. Peso Total: " + String.format("%.2f", pesoTotal) + " t");
        System.out.println("3. Comprimento Total (inc. engates): " + String.format("%.2f", comprimentoTotal) + " m");
        System.out.println("4. Capacidade: " + passTotal + " passageiros / " + String.format("%.2f", cargaTotal) + " t de carga");
        
        // Verificação de Potência (HPT >= 1.05)
        double hpt = (pesoTotal > 0) ? (potTotal / pesoTotal) : 0;
        System.out.println("5. Desempenho (HPT): " + String.format("%.2f", hpt) + " HP/Ton");
        
        if (hpt >= 1.05) {
            System.out.println("   STATUS: Potência Suficiente.");
        } else {
            double falta = (1.05 * pesoTotal) - potTotal;
            int extras = (potUmaLocomotiva > 0) ? (int) Math.ceil(falta / potUmaLocomotiva) : 0;
            System.out.println("   STATUS: Potência INSUFICIENTE (Faltam " + String.format("%.2f", falta) + " HP).");
            System.out.println("   SUGESTÃO: Adicionar mais " + extras + " locomotiva(s) do mesmo tipo.");
        }
        System.out.println("----------------------------------\n");
    }

    public Vagao getPrimeiro() { return (Vagao) peekFront(); }
    public Vagao getUltimo() { return (Vagao) peekRear(); }
}