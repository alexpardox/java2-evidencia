#!/bin/bash
# Script para cargar variables de entorno y ejecutar la aplicación

# Verificar si existe el archivo .env
if [ -f .env ]; then
    echo "Cargando variables de entorno desde .env..."
    export $(cat .env | grep -v '^#' | xargs)
else
    echo "Archivo .env no encontrado. Creando archivo de ejemplo..."
    cat > .env << EOF
# Variables de entorno para desarrollo local
# NUNCA SUBIR ESTE ARCHIVO A GIT

# Configuración de base de datos
DB_URL=jdbc:postgresql://localhost:5432/imc_db
DB_USERNAME=tu_usuario
DB_PASSWORD=tu_password

# Configuración de desarrollo  
SHOW_SQL=true
SPRING_PROFILES_ACTIVE=dev
EOF
    echo "Archivo .env creado. Por favor, configura tus credenciales."
    exit 1
fi

# Verificar si Maven está disponible
if command -v mvn &> /dev/null; then
    echo "Iniciando aplicación Spring Boot con Maven..."
    mvn spring-boot:run
elif command -v ./mvnw &> /dev/null; then
    echo "Iniciando aplicación Spring Boot con Maven Wrapper..."
    ./mvnw spring-boot:run
else
    echo "❌ Maven no encontrado."
    echo "📥 Instalando Maven con Homebrew..."
    if command -v brew &> /dev/null; then
        brew install maven
        echo "✅ Maven instalado. Ejecutando aplicación..."
        mvn spring-boot:run
    else
        echo "❌ Homebrew no encontrado."
        echo "🔧 Opciones para instalar Maven:"
        echo "   1. Instalar Homebrew: /bin/bash -c \"\$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)\""
        echo "   2. Descargar Maven manualmente: https://maven.apache.org/download.cgi"
        echo "   3. Usar IDE (VS Code con Java Extension Pack)"
        exit 1
    fi
fi
