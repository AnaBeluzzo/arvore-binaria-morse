# Sistema de Código Morse - Árvore Binária

Sistema de codificação e decodificação de Código Morse implementado com árvore binária em Java, incluindo interface gráfica para visualização da árvore.

## 📋 Pré-requisitos

- **Java 11** ou superior
- **Maven 3.6** ou superior
- **JavaFX** (incluído nas dependências do Maven)

## 🚀 Como executar

### Opção 1: Usando o script de inicialização (Recomendado)

#### No macOS/Linux:
```bash
chmod +x init.sh
./init.sh
```

#### No Windows:
```batch
init.bat
```

### Opção 2: Usando Maven diretamente

1. **Compilar o projeto:**
```bash
mvn clean compile
```

2. **Executar a aplicação:**
```bash
mvn exec:java
```

### Opção 3: Gerar e executar JAR

1. **Gerar JAR com dependências:**
```bash
mvn clean package
```

2. **Executar o JAR:**
```bash
java -jar target/arvore-morse-1.0.0-jar-with-dependencies.jar
```

## 🎮 Funcionalidades

O sistema oferece um menu interativo com as seguintes opções:

1. **Adicionar letra** - Adiciona uma nova letra com seu código Morse
2. **Remover letra** - Remove uma letra da árvore
3. **Buscar letra** - Procura uma letra na árvore
4. **Codificar palavra** - Converte texto para código Morse
5. **Decodificar palavra** - Converte código Morse para texto
6. **Exibir árvore (JavaFX)** - Visualização gráfica da árvore binária
7. **Carregar alfabeto padrão** - Carrega todas as letras A-Z com códigos Morse
8. **Exibir árvore (console)** - Mostra a estrutura da árvore no terminal
9. **Sair** - Encerra o programa

## 📁 Estrutura do projeto

```
arvore-binaria-morse/
├── src/main/java/
│   ├── ArvoreMorse.java      # Implementação da árvore binária
│   ├── No.java               # Classe do nó da árvore
│   ├── SistemaMorse.java     # Classe principal com menu
│   └── TreeVisualizer.java   # Interface gráfica JavaFX
├── pom.xml                   # Configuração Maven
├── init.sh                   # Script de execução (macOS/Linux)
├── init.bat                  # Script de execução (Windows)
└── README.md                 # Este arquivo
```

## 🔧 Troubleshooting

### Problema com JavaFX
Se encontrar erros relacionados ao JavaFX, certifique-se de que:
- Está usando Java 11 ou superior
- As dependências Maven foram baixadas corretamente

### Erro de permissão no script (macOS/Linux)
```bash
chmod +x init.sh
```

### Limpar cache do Maven
```bash
mvn clean
```

## 💡 Exemplo de uso

1. Execute o programa
2. Escolha a opção **7** para carregar o alfabeto padrão
3. Digite **4** para codificar uma palavra
4. Digite uma palavra como "HELLO"
5. O sistema retornará: `.... . .-.. .-.. ---`
6. Use a opção **6** para ver a árvore graficamente

## 📖 Sobre o Código Morse

O Código Morse representa letras e números usando sequências de pontos (.) e traços (-):
- Ponto = sinal curto
- Traço = sinal longo
- Na árvore binária: esquerda = ponto, direita = traço

Exemplo: A letra **A** = `.-` (ponto-traço)