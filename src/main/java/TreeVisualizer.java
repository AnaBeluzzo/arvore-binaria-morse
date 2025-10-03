import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TreeVisualizer extends Application {

    private static ArvoreMorse arvore;

    // Método para injetar a árvore do sistema principal
    public static void setArvore(ArvoreMorse arvore) {
        TreeVisualizer.arvore = arvore;
    }

    // Calcula a altura da árvore
    public int getHeight(No node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(node.getEsquerda()), getHeight(node.getDireita()));
    }

    public void drawTree(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setStroke(Color.web("#333"));
        gc.setLineWidth(1.0); // Linha mais fina
        gc.setFont(new Font("Arial", 10)); // Fonte menor

        // Começa o desenho da árvore na raiz
        // Espaçamento horizontal inicial maior para o nó raiz
        drawNode(gc, arvore.getRaiz(), canvas.getWidth() / 2, 40, canvas.getWidth() / 4, 1);
    }

    private void drawNode(GraphicsContext gc, No node, double x, double y, double xOffset, int level) {
        if (node == null) {
            return;
        }

        // Desenha um círculo ao redor do nó (círculo muito menor)
        if (node.isVazio()) {
            gc.setStroke(Color.LIGHTGRAY);
            gc.setFill(Color.web("#f9f9f9"));
        } else {
            gc.setStroke(Color.web("#007bff"));
            gc.setFill(Color.web("#e7f3ff"));
        }
        
        gc.fillOval(x - 10, y - 10, 20, 20); // Círculo bem menor
        gc.strokeOval(x - 10, y - 10, 20, 20);

        // Desenha a letra dentro do círculo
        char letra = node.getLetra();
        if (letra == '\0') {
            gc.setFill(Color.GRAY);
            gc.fillText("ø", x - 3, y + 3);
        } else {
            gc.setFill(Color.BLACK);
            gc.fillText(String.valueOf(letra), x - 3, y + 3);
        }

        // Desenho das linhas para os nós filhos
        if (node.getEsquerda() != null) {
            double newX = x - xOffset;
            double newY = y + 40; // Espaçamento vertical muito menor
            gc.setStroke(Color.web("#ccc"));
            gc.strokeLine(x, y + 10, newX, newY - 10); // Conecta nas bordas dos círculos
            
            // Desenha o símbolo do caminho (ponto)
            gc.setFill(Color.BLACK);
            gc.fillText(".", (x + newX) / 2, (y + newY) / 2 - 3);
            
            // Reduz o offset horizontal mais agressivamente
            drawNode(gc, node.getEsquerda(), newX, newY, xOffset / 2, level + 1);
        }

        if (node.getDireita() != null) {
            double newX = x + xOffset;
            double newY = y + 40; // Espaçamento vertical muito menor
            gc.setStroke(Color.web("#ccc"));
            gc.strokeLine(x, y + 10, newX, newY - 10); // Conecta nas bordas dos círculos
            
            // Desenha o símbolo do caminho (traço)
            gc.setFill(Color.BLACK);
            gc.fillText("-", (x + newX) / 2, (y + newY) / 2 - 3);
            
            // Reduz o offset horizontal mais agressivamente
            drawNode(gc, node.getDireita(), newX, newY, xOffset / 2, level + 1);
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Visualizador de Árvore Binária Morse");

        if (arvore == null || arvore.getRaiz() == null) {
            System.out.println("A árvore está vazia. Adicione letras antes de visualizar.");
            primaryStage.close();
            return;
        }

        // Inicialização de Janela
        int height = getHeight(arvore.getRaiz());
        // Cálculo de altura e largura muito mais compacto
        int canvasHeight = 30 + height * 45; 
        int canvasWidth = (int) Math.pow(2, height) * 20;
        
        // Garante tamanho mínimo e máximo para telas pequenas
        canvasWidth = Math.max(canvasWidth, 400);
        canvasWidth = Math.min(canvasWidth, 800); // Limita largura máxima
        canvasHeight = Math.max(canvasHeight, 250);
        canvasHeight = Math.min(canvasHeight, 600); // Limita altura máxima

        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        drawTree(canvas);

        Group root = new Group();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root, canvasWidth, canvasHeight);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
