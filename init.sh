#!/bin/bash

###############################################################################
# Script de Configuração e Execução - Sistema de Código Morse
# Compatível com Linux e macOS
###############################################################################

echo "╔════════════════════════════════════════════════════════════════╗"
echo "║  SISTEMA DE CÓDIGO MORSE - CONFIGURAÇÃO E EXECUÇÃO             ║"
echo "╚════════════════════════════════════════════════════════════════╝"
echo ""

# Cores para output
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Função para verificar se um comando existe
command_exists() {
    command -v "$1" >/dev/null 2>&1
}

# Verificar Java
echo -e "${BLUE}[1/5]${NC} Verificando instalação do Java..."
if command_exists java; then
    JAVA_VERSION=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}')
    echo -e "${GREEN}✓${NC} Java encontrado: versão ${JAVA_VERSION}"
else
    echo -e "${RED}✗${NC} Java não encontrado!"
    echo "Por favor, instale o Java JDK 11 ou superior:"
    echo "  - macOS: brew install openjdk@11"
    echo "  - Linux: sudo apt install openjdk-11-jdk"
    exit 1
fi

# Verificar Maven
echo ""
echo -e "${BLUE}[2/5]${NC} Verificando instalação do Maven..."
if command_exists mvn; then
    MAVEN_VERSION=$(mvn -version | head -n 1 | awk '{print $3}')
    echo -e "${GREEN}✓${NC} Maven encontrado: versão ${MAVEN_VERSION}"
else
    echo -e "${YELLOW}⚠${NC} Maven não encontrado!"
    echo "Instalando Maven..."
    
    if [[ "$OSTYPE" == "darwin"* ]]; then
        # macOS
        if command_exists brew; then
            brew install maven
        else
            echo -e "${RED}✗${NC} Homebrew não encontrado. Instale o Maven manualmente."
            exit 1
        fi
    elif [[ "$OSTYPE" == "linux-gnu"* ]]; then
        # Linux
        sudo apt-get update
        sudo apt-get install -y maven
    else
        echo -e "${RED}✗${NC} Sistema operacional não suportado para instalação automática."
        exit 1
    fi
fi

# Limpar builds anteriores
echo ""
echo -e "${BLUE}[3/5]${NC} Limpando builds anteriores..."
if [ -d "target" ]; then
    rm -rf target
    echo -e "${GREEN}✓${NC} Diretório target removido"
fi

if [ -f "*.class" ]; then
    rm -f *.class
    echo -e "${GREEN}✓${NC} Arquivos .class removidos"
fi

# Compilar projeto
echo ""
echo -e "${BLUE}[4/5]${NC} Compilando projeto Maven..."
mvn clean compile

if [ $? -eq 0 ]; then
    echo ""
    echo -e "${GREEN}✓ CONFIGURAÇÃO CONCLUÍDA COM SUCESSO!${NC}"
    echo ""
else
    echo ""
    echo -e "${RED}✗ Erro na compilação!${NC}"
    echo "Verifique os erros acima e tente novamente."
    exit 1
fi

# Executar o sistema
echo -e "${BLUE}[5/5]${NC} Executando sistema..."
echo ""
echo -e "${BLUE}╔════════════════════════════════════════════════════════════════╗${NC}"
echo -e "${BLUE}║       INICIANDO SISTEMA DE CÓDIGO MORSE                        ║${NC}"
echo -e "${BLUE}╚════════════════════════════════════════════════════════════════╝${NC}"
echo ""

mvn exec:java -q

echo ""
echo "════════════════════════════════════════════════════════════════"
echo -e "${GREEN}✓ Sistema encerrado${NC}"
echo ""
echo "Comandos disponíveis:"
echo -e "  ${YELLOW}./configurar.sh${NC}         - Configurar e executar o sistema"
echo -e "  ${YELLOW}mvn clean compile${NC}       - Compilar o projeto"
echo -e "  ${YELLOW}mvn package${NC}             - Criar JAR executável"
echo -e "  ${YELLOW}mvn exec:java${NC}           - Executar via Maven"
echo ""
