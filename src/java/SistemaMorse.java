import java.util.Scanner;

/**
 * Sistema de Código Morse com Menu Interativo
 * Implementa árvore binária para codificação/decodificação Morse
 */
public class SistemaMorse {
    private ArvoreMorse arvore;
    private Scanner scanner;
    
    public SistemaMorse() {
        this.arvore = new ArvoreMorse();
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Exibe o menu principal
     */
    private void exibirMenu() {
        System.out.println("\n========================================");
        System.out.println("     SISTEMA DE CÓDIGO MORSE");
        System.out.println("========================================");
        System.out.println("1 - Adicionar letra");
        System.out.println("2 - Remover letra");
        System.out.println("3 - Buscar letra");
        System.out.println("4 - Codificar palavra");
        System.out.println("5 - Decodificar palavra");
        System.out.println("6 - Exibir árvore (JavaFX)");
        System.out.println("7 - Carregar alfabeto padrão");
        System.out.println("0 - Sair");
        System.out.println("========================================");
        System.out.print("Escolha uma opção: ");
    }
    
    /**
     * Adiciona uma letra na árvore usando código Morse padrão internacional
     */
    private void adicionarLetra() {
        System.out.print("\nDigite a letra (A-Z ou 0-9): ");
        String entrada = scanner.nextLine().trim();
        
        if (entrada.isEmpty()) {
            System.out.println("ERRO: Letra não pode ser vazia!");
            return;
        }
        
        if (entrada.length() > 1) {
            System.out.println("ERRO: Digite apenas UMA letra por vez!");
            System.out.println("Você digitou: \"" + entrada + "\" (" + entrada.length() + " caracteres)");
            return;
        }
        
        char letra = entrada.charAt(0);
        String codigoRetornado = arvore.adicionarLetra(letra);
        
        if (codigoRetornado == null) {
            System.out.println("✗ ERRO: Letra '" + letra + "' não possui código Morse padrão internacional!");
            System.out.println("  Apenas letras A-Z e números 0-9 são suportados.");
        } else {
            System.out.println("✓ Letra '" + Character.toUpperCase(letra) + "' adicionada com código: " + codigoRetornado);
        }
    }
    
    /**
     * Remove uma letra da árvore
     */
    private void removerLetra() {
        System.out.print("\nDigite a letra a remover: ");
        String entrada = scanner.nextLine().trim();
        
        if (entrada.isEmpty()) {
            System.out.println("ERRO: Letra não pode ser vazia!");
            return;
        }
        
        char letra = entrada.charAt(0);
        boolean removeu = arvore.removerLetra(letra);
        
        if (removeu) {
            System.out.println("✓ Letra '" + Character.toUpperCase(letra) + "' removida (espaço marcado como vazio)");
        } else {
            System.out.println("✗ Letra '" + Character.toUpperCase(letra) + "' não encontrada na árvore");
        }
    }
    
    /**
     * Busca o código Morse de uma letra
     */
    private void buscarLetra() {
        System.out.print("\nDigite a letra a buscar: ");
        String entrada = scanner.nextLine().trim();
        
        if (entrada.isEmpty()) {
            System.out.println("ERRO: Letra não pode ser vazia!");
            return;
        }
        
        char letra = entrada.charAt(0);
        String codigo = arvore.buscarLetra(letra);
        
        if (codigo != null) {
            System.out.println("✓ Código Morse de '" + Character.toUpperCase(letra) + "': " + codigo);
        } else {
            System.out.println("✗ Letra '" + Character.toUpperCase(letra) + "' não encontrada na árvore");
        }
    }
    
    /**
     * Codifica uma palavra para Morse
     */
    private void codificarPalavra() {
        System.out.print("\nDigite a palavra a codificar: ");
        String palavra = scanner.nextLine().trim();
        
        if (palavra.isEmpty()) {
            System.out.println("ERRO: Palavra não pode ser vazia!");
            return;
        }
        
        String resultado = arvore.codificarPalavra(palavra);
        System.out.println("\nResultado:");
        System.out.println(resultado);
    }
    
    /**
     * Decodifica código Morse para texto
     */
    private void decodificarPalavra() {
        System.out.print("\nDigite o código Morse (separe letras com espaço, palavras com /): ");
        String codigo = scanner.nextLine().trim();
        
        if (codigo.isEmpty()) {
            System.out.println("ERRO: Código não pode ser vazio!");
            return;
        }
        
        String resultado = arvore.decodificarPalavra(codigo);
        System.out.println("\nResultado:");
        System.out.println(resultado);
    }
    
    /**
     * Placeholder para exibição com JavaFX
     */
    private void exibirArvore() {
        System.out.println("\n[FUNCIONALIDADE RESERVADA PARA JAVAFX]");
        System.out.println("Esta opção será implementada com interface gráfica JavaFX");
        System.out.println("para visualização da árvore binária.");
        
        if (arvore.getRaiz() == null) {
            System.out.println("\nATENÇÃO: A árvore está vazia!");
        } else {
            System.out.println("\nA árvore contém elementos e está pronta para visualização.");
        }
    }
    
    /**
     * Carrega o alfabeto Morse internacional padrão
     */
    private void carregarAlfabetoPadrao() {
        System.out.println("\nCarregando alfabeto Morse internacional padrão...");
        
        // Letras A-Z
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int adicionadas = 0;
        
        for (int i = 0; i < letras.length(); i++) {
            char letra = letras.charAt(i);
            if (arvore.adicionarLetra(letra) != null) {
                adicionadas++;
            }
        }
        
        System.out.println("✓ Alfabeto padrão carregado com sucesso!");
        System.out.println("  " + adicionadas + " caracteres adicionados (A-Z + 0-9)");
    }
    
    /**
     * Executa o sistema
     */
    public void executar() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║  BEM-VINDO AO SISTEMA DE CÓDIGO MORSE  ║");
        System.out.println("╚════════════════════════════════════════╝");
        
        boolean continuar = true;
        
        while (continuar) {
            exibirMenu();
            String entrada = scanner.nextLine().trim();
            
            if (entrada.isEmpty()) {
                continue;
            }
            
            switch (entrada) {
                case "1":
                    adicionarLetra();
                    break;
                case "2":
                    removerLetra();
                    break;
                case "3":
                    buscarLetra();
                    break;
                case "4":
                    codificarPalavra();
                    break;
                case "5":
                    decodificarPalavra();
                    break;
                case "6":
                    exibirArvore();
                    break;
                case "7":
                    carregarAlfabetoPadrao();
                    break;
                case "0":
                    System.out.println("\n✓ Encerrando sistema...");
                    System.out.println("Até logo!");
                    continuar = false;
                    break;
                default:
                    System.out.println("\n✗ Opção inválida! Tente novamente.");
            }
        }
        
        scanner.close();
    }
    
    /**
     * Método main para executar o sistema
     */
    public static void main(String[] args) {
        SistemaMorse sistema = new SistemaMorse();
        sistema.executar();
    }
}
