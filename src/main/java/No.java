/**
 * Classe que representa um nó da árvore binária de Morse
 * Esquerda = ponto (.)
 * Direita = traço (-)
 */
public class No {
    private char letra;
    private boolean vazio; // Indica se o nó está vazio (letra removida)
    private No esquerda; // Filho esquerdo (ponto)
    private No direita;   // Filho direito (traço)
    
    public No() {
        this.letra = '\0';
        this.vazio = true;
        this.esquerda = null;
        this.direita = null;
    }
    
    public No(char letra) {
        this.letra = letra;
        this.vazio = false;
        this.esquerda = null;
        this.direita = null;
    }
    
    public char getLetra() {
        return letra;
    }
    
    public void setLetra(char letra) {
        this.letra = letra;
        this.vazio = false;
    }
    
    public boolean isVazio() {
        return vazio;
    }
    
    public void setVazio(boolean vazio) {
        this.vazio = vazio;
        if (vazio) {
            this.letra = '\0';
        }
    }
    
    public No getEsquerda() {
        return esquerda;
    }
    
    public void setEsquerda(No esquerda) {
        this.esquerda = esquerda;
    }
    
    public No getDireita() {
        return direita;
    }
    
    public void setDireita(No direita) {
        this.direita = direita;
    }
}
