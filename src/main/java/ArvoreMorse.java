/**
 * Árvore Binária para Código Morse Internacional
 * Ponto (.) = esquerda
 * Traço (-) = direita
 */
public class ArvoreMorse {
    private No raiz;
    
    public ArvoreMorse() {
        this.raiz = null;
    }
    
    /**
     * Retorna o código Morse padrão internacional para uma letra
     * @param letra Letra (A-Z, 0-9)
     * @return Código Morse padrão ou null se não existir
     */
    private String obterCodigoMorsePadrao(char letra) {
        letra = Character.toUpperCase(letra);
        
        switch (letra) {
            // Letras A-Z
            case 'A': return ".-";
            case 'B': return "-...";
            case 'C': return "-.-.";
            case 'D': return "-..";
            case 'E': return ".";
            case 'F': return "..-.";
            case 'G': return "--.";
            case 'H': return "....";
            case 'I': return "..";
            case 'J': return ".---";
            case 'K': return "-.-";
            case 'L': return ".-..";
            case 'M': return "--";
            case 'N': return "-.";
            case 'O': return "---";
            case 'P': return ".--.";
            case 'Q': return "--.-";
            case 'R': return ".-.";
            case 'S': return "...";
            case 'T': return "-";
            case 'U': return "..-";
            case 'V': return "...-";
            case 'W': return ".--";
            case 'X': return "-..-";
            case 'Y': return "-.--";
            case 'Z': return "--..";
            
            // Números 0-9
            case '0': return "-----";
            case '1': return ".----";
            case '2': return "..---";
            case '3': return "...--";
            case '4': return "....-";
            case '5': return ".....";
            case '6': return "-....";
            case '7': return "--...";
            case '8': return "---..";
            case '9': return "----.";
            
            default: return null;
        }
    }
    
    /**
     * Adiciona uma letra na árvore usando o código Morse padrão internacional
     * @param letra Letra a ser adicionada (A-Z, 0-9)
     * @return O código Morse da letra adicionada, ou null se letra inválida
     */
    public String adicionarLetra(char letra) {
        letra = Character.toUpperCase(letra);
        
        String codigoMorse = obterCodigoMorsePadrao(letra);
        if (codigoMorse == null) {
            return null; // Letra não tem código Morse padrão
        }
        
        if (raiz == null) {
            raiz = new No();
        }
        
        adicionarLetraRecursivo(raiz, letra, codigoMorse, 0);
        return codigoMorse;
    }
    
    private void adicionarLetraRecursivo(No atual, char letra, String codigo, int indice) {
        // Caso base: chegamos ao fim do código
        if (indice == codigo.length()) {
            atual.setLetra(letra);
            atual.setVazio(false);
            return;
        }
        
        char simbolo = codigo.charAt(indice);
        
        if (simbolo == '.') {
            // Ir para esquerda (ponto)
            if (atual.getEsquerda() == null) {
                atual.setEsquerda(new No());
            }
            adicionarLetraRecursivo(atual.getEsquerda(), letra, codigo, indice + 1);
        } else if (simbolo == '-') {
            // Ir para direita (traço)
            if (atual.getDireita() == null) {
                atual.setDireita(new No());
            }
            adicionarLetraRecursivo(atual.getDireita(), letra, codigo, indice + 1);
        }
    }
    
    /**
     * Remove uma letra da árvore (marca como vazia)
     * @param letra Letra a ser removida
     * @return true se removeu, false se não encontrou
     */
    public boolean removerLetra(char letra) {
        letra = Character.toUpperCase(letra);
        return removerLetraRecursivo(raiz, letra);
    }
    
    private boolean removerLetraRecursivo(No atual, char letra) {
        if (atual == null) {
            return false;
        }
        
        // Verifica se este nó contém a letra
        if (!atual.isVazio() && atual.getLetra() == letra) {
            atual.setVazio(true);
            return true;
        }
        
        // Busca recursivamente nos filhos
        boolean encontrado = removerLetraRecursivo(atual.getEsquerda(), letra);
        if (encontrado) {
            return true;
        }
        
        return removerLetraRecursivo(atual.getDireita(), letra);
    }
    
    /**
     * Busca o código Morse de uma letra
     * @param letra Letra a buscar
     * @return Código Morse ou null se não encontrado
     */
    public String buscarLetra(char letra) {
        letra = Character.toUpperCase(letra);
        return buscarLetraRecursivo(raiz, letra, "");
    }
    
    private String buscarLetraRecursivo(No atual, char letra, String caminhoAtual) {
        if (atual == null) {
            return null;
        }
        
        // Verifica se encontrou a letra neste nó
        if (!atual.isVazio() && atual.getLetra() == letra) {
            return caminhoAtual;
        }
        
        // Busca na esquerda (ponto)
        String resultado = buscarLetraRecursivo(atual.getEsquerda(), letra, caminhoAtual + ".");
        if (resultado != null) {
            return resultado;
        }
        
        // Busca na direita (traço)
        return buscarLetraRecursivo(atual.getDireita(), letra, caminhoAtual + "-");
    }
    
    /**
     * Codifica uma palavra para código Morse
     * @param palavra Palavra a codificar
     * @return Código Morse separado por espaços, ou mensagem de erro
     */
    public String codificarPalavra(String palavra) {
        palavra = palavra.toUpperCase();
        StringBuilder resultado = new StringBuilder();
        StringBuilder letrasNaoEncontradas = new StringBuilder();
        
        for (int i = 0; i < palavra.length(); i++) {
            char letra = palavra.charAt(i);
            
            if (letra == ' ') {
                resultado.append(" / "); // Separador de palavras em Morse
                continue;
            }
            
            String codigoMorse = buscarLetra(letra);
            
            if (codigoMorse == null) {
                if (letrasNaoEncontradas.length() > 0) {
                    letrasNaoEncontradas.append(", ");
                }
                letrasNaoEncontradas.append(letra);
            } else {
                if (resultado.length() > 0 && resultado.charAt(resultado.length() - 1) != ' ') {
                    resultado.append(" ");
                }
                resultado.append(codigoMorse);
            }
        }
        
        if (letrasNaoEncontradas.length() > 0) {
            return "ERRO: As seguintes letras não estão na árvore: " + letrasNaoEncontradas.toString();
        }
        
        return resultado.toString().trim();
    }
    
    /**
     * Decodifica um código Morse para texto
     * @param codigoMorse Código Morse (ex: ".- -... -.-.")
     * @return Texto decodificado ou mensagem de erro
     */
    public String decodificarPalavra(String codigoMorse) {
        String[] codigos = dividirString(codigoMorse, " ");
        StringBuilder resultado = new StringBuilder();
        StringBuilder codigosNaoEncontrados = new StringBuilder();
        
        for (int i = 0; i < codigos.length; i++) {
            String codigo = codigos[i].trim();
            
            if (codigo.isEmpty()) {
                continue;
            }
            
            // Separador de palavras
            if (codigo.equals("/")) {
                resultado.append(" ");
                continue;
            }
            
            char letra = decodificarCodigoRecursivo(raiz, codigo, 0);
            
            if (letra == '\0') {
                if (codigosNaoEncontrados.length() > 0) {
                    codigosNaoEncontrados.append(", ");
                }
                codigosNaoEncontrados.append(codigo);
            } else {
                resultado.append(letra);
            }
        }
        
        if (codigosNaoEncontrados.length() > 0) {
            return "ERRO: Os seguintes códigos não estão na árvore: " + codigosNaoEncontrados.toString();
        }
        
        return resultado.toString();
    }
    
    private char decodificarCodigoRecursivo(No atual, String codigo, int indice) {
        if (atual == null) {
            return '\0';
        }
        
        // Caso base: chegamos ao fim do código
        if (indice == codigo.length()) {
            if (!atual.isVazio()) {
                return atual.getLetra();
            }
            return '\0';
        }
        
        char simbolo = codigo.charAt(indice);
        
        if (simbolo == '.') {
            return decodificarCodigoRecursivo(atual.getEsquerda(), codigo, indice + 1);
        } else if (simbolo == '-') {
            return decodificarCodigoRecursivo(atual.getDireita(), codigo, indice + 1);
        }
        
        return '\0';
    }
    
    /**
     * Divide uma string por um separador (substitui split do java.util)
     */
    private String[] dividirString(String texto, String separador) {
        // Conta quantos separadores existem
        int count = 1;
        int pos = 0;
        while ((pos = texto.indexOf(separador, pos)) != -1) {
            count++;
            pos += separador.length();
        }
        
        String[] resultado = new String[count];
        int inicio = 0;
        int indice = 0;
        
        pos = texto.indexOf(separador);
        while (pos != -1) {
            resultado[indice++] = texto.substring(inicio, pos);
            inicio = pos + separador.length();
            pos = texto.indexOf(separador, inicio);
        }
        
        resultado[indice] = texto.substring(inicio);
        return resultado;
    }
    
    /**
     * Retorna a raiz da árvore (para visualização JavaFX)
     */
    public No getRaiz() {
        return raiz;
    }
    
    /**
     * Limpa completamente a árvore, removendo todos os nós
     */
    public void limparArvore() {
        this.raiz = null;
    }
}
