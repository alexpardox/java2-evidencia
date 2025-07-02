#!/bin/bash
# Script alternativo para ejecutar con Java directamente

# Cargar variables de entorno
if [ -f .env ]; then
    echo "Cargando variables de entorno desde .env..."
    export $(cat .env | grep -v '^#' | xargs)
else
    echo "❌ Archivo .env no encontrado."
    exit 1
fi

echo "🔍 Buscando archivo JAR compilado..."

# Buscar el archivo JAR en target/
JAR_FILE=$(find target/ -name "*.jar" -not -name "*sources*" -not -name "*javadoc*" 2>/dev/null | head -1)

if [ -n "$JAR_FILE" ]; then
    echo "✅ Archivo JAR encontrado: $JAR_FILE"
    echo "🚀 Ejecutando aplicación con Java..."
    java -jar "$JAR_FILE"
else
    echo "❌ No se encontró archivo JAR compilado."
    echo "🔨 Compilando proyecto primero..."
    
    # Verificar si existe Maven
    if command -v mvn &> /dev/null; then
        mvn clean package -DskipTests
        JAR_FILE=$(find target/ -name "*.jar" -not -name "*sources*" -not -name "*javadoc*" 2>/dev/null | head -1)
        if [ -n "$JAR_FILE" ]; then
            echo "✅ Compilación exitosa. Ejecutando..."
            java -jar "$JAR_FILE"
        else
            echo "❌ Error en la compilación."
        fi
    else
        echo "❌ Maven no encontrado. Usa './run.sh' para instalar Maven automáticamente."
    fi
fi
