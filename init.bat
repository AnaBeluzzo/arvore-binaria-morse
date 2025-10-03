@echo off
REM ###########################################################################
REM Script de Configuração e Execução - Sistema de Código Morse
REM Compatível com Windows
REM ###########################################################################

title Sistema de Codigo Morse - Configuracao e Execucao
color 0B

echo ================================================================
echo   SISTEMA DE CODIGO MORSE - CONFIGURACAO E EXECUCAO
echo ================================================================
echo.

REM Verificar Java
echo [1/5] Verificando instalacao do Java...
java -version >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    color 0C
    echo [X] Java nao encontrado!
    echo.
    echo Por favor, instale o Java JDK 11 ou superior:
    echo   - Download: https://adoptium.net/
    echo.
    pause
    exit /b 1
)

for /f "tokens=3" %%g in ('java -version 2^>^&1 ^| findstr /i "version"') do (
    set JAVA_VERSION=%%g
)
echo [OK] Java encontrado: versao %JAVA_VERSION%
echo.

REM Verificar Maven
echo [2/5] Verificando instalacao do Maven...
mvn -version >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    color 0E
    echo [!] Maven nao encontrado!
    echo.
    echo Por favor, instale o Maven:
    echo   1. Download: https://maven.apache.org/download.cgi
    echo   2. Extrair para C:\Program Files\Maven
    echo   3. Adicionar ao PATH: C:\Program Files\Maven\bin
    echo.
    pause
    exit /b 1
)

for /f "tokens=3" %%g in ('mvn -version 2^>^&1 ^| findstr /i "Apache Maven"') do (
    set MAVEN_VERSION=%%g
)
echo [OK] Maven encontrado: versao %MAVEN_VERSION%
echo.

REM Limpar builds anteriores
echo [3/5] Limpando builds anteriores...
if exist "target" (
    rmdir /s /q target
    echo [OK] Diretorio target removido
)

if exist "*.class" (
    del /q *.class
    echo [OK] Arquivos .class removidos
)
echo.

REM Compilar projeto
echo [4/5] Compilando projeto Maven...
call mvn clean compile

if %ERRORLEVEL% EQU 0 (
    color 0A
    echo.
    echo [OK] CONFIGURACAO CONCLUIDA COM SUCESSO!
    echo.
) else (
    color 0C
    echo.
    echo [X] Erro na compilacao!
    echo Verifique os erros acima e tente novamente.
    echo.
    pause
    exit /b 1
)

REM Executar o sistema
echo [5/5] Executando sistema...
echo.
echo ================================================================
echo        INICIANDO SISTEMA DE CODIGO MORSE
echo ================================================================
echo.

call mvn exec:java -q

echo.
echo ================================================================
echo [OK] Sistema encerrado
echo.
echo Comandos disponiveis:
echo   configurar.bat        - Configurar e executar o sistema
echo   mvn clean compile     - Compilar o projeto
echo   mvn package           - Criar JAR executavel
echo   mvn exec:java         - Executar via Maven
echo.
pause
