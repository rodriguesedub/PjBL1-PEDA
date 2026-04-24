import java.util.Scanner;

public class ProgramaFerrovia {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ComposicaoFerroviaria comp = new ComposicaoFerroviaria(100, "composicao.dat");
        
        char opcao;
        do {
            System.out.println("=== MENU FERROVIÁRIO ===");
            System.out.println("a) Criar Composição Padrão");
            System.out.println("b) Inserir Vagão");
            System.out.println("c) Remover Vagão");
            System.out.println("d) Apresentar Diagnóstico");
            System.out.println("e) Dados do Primeiro Vagão");
            System.out.println("f) Dados do Último Vagão");
            System.out.println("g) Terminar");
            System.out.print("Escolha: ");
            opcao = sc.next().toLowerCase().charAt(0);

            switch (opcao) {
                case 'a':
                    comp.criarComposicaoPadrao();
                    break;
                case 'b':
                    if (comp.isFull()) {
                        System.out.println("Erro: Deque cheio!");
                        break;
                    }
                    System.out.println("Tipo: 1-Locomotiva, 2-Passageiro, 3-Carga");
                    int tipo = sc.nextInt();
                    System.out.print("Comprimento (m): ");
                    double c = sc.nextDouble();
                    System.out.print("Peso (t): ");
                    double p = sc.nextDouble();
                    
                    Vagao novo = null;
                    if (tipo == 1) {
                        System.out.print("Potência (HP): ");
                        novo = new Locomotiva(c, p, sc.nextDouble());
                    } else if (tipo == 2) {
                        System.out.print("Qtd Passageiros: ");
                        novo = new Passageiro(c, p, sc.nextInt());
                    } else {
                        novo = new Carga(c, p);
                    }
                    
                    System.out.print("Onde inserir? (1-Início, 2-Fim): ");
                    if (sc.nextInt() == 1) comp.adicionarVagaoInicio(novo);
                    else comp.adicionarVagaoFim(novo);
                    break;
                    
                case 'c':
                    if (comp.isEmpty()) {
                        System.out.println("Erro: Deque vazio!");
                    } else {
                        System.out.print("Remover de onde? (1-Início, 2-Fim): ");
                        Vagao removido = (sc.nextInt() == 1) ? comp.removerVagaoInicio() : comp.removerVagaoFim();
                        System.out.print("Removido: ");
                        removido.imprime();
                    }
                    break;
                    
                case 'd':
                    comp.mostrarDiagnostico();
                    break;
                    
                case 'e':
                    Vagao prim = comp.getPrimeiro();
                    if (prim != null) prim.imprime();
                    else System.out.println("Vazio.");
                    break;
                    
                case 'f':
                    Vagao ult = comp.getUltimo();
                    if (ult != null) ult.imprime();
                    else System.out.println("Vazio.");
                    break;
            }
        } while (opcao != 'g');
        
        sc.close();
    }
}