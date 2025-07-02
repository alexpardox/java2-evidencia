# Monitor IMC - Spring Boot Application

## 🔒 Configuración de Seguridad

### Variables de Entorno Requeridas

Esta aplicación utiliza variables de entorno para proteger información sensible.

#### Configuración Local:

1. **El archivo `.env` ya está configurado** con las credenciales de desarrollo.

2. **Ejecutar la aplicación**:
```bash
# Opción 1: Script automático (recomendado)
./run.sh

# Opción 2: Si hay problemas con Maven
./run-java.sh

# Opción 3: Manual
export $(cat .env | grep -v '^#' | xargs)
mvn spring-boot:run
```

## 🛠️ Solución de Problemas

### Error: "mvn: command not found"

**✅ SOLUCIONADO:** Maven se instaló automáticamente con Homebrew.

Si aún tienes problemas:

1. **Reinicia la terminal:**
```bash
# Cerrar y abrir nueva terminal, o:
source ~/.zshrc
```

2. **Verificar Maven:**
```bash
mvn --version
# Debería mostrar: Apache Maven 3.9.10
```

3. **Usar script alternativo:**
```bash
./run-java.sh
```

### Otros Problemas Comunes:

#### Java no encontrado:
```bash
# Instalar Java si no está disponible
brew install openjdk
```

#### Variables de entorno no cargadas:
```bash
# Verificar que .env existe y tiene contenido
cat .env

# Cargar manualmente
export $(cat .env | grep -v '^#' | xargs)
```

## 🚀 Ejecución

### Desarrollo (ACTUAL):
```bash
# Método principal
./run.sh

# Métodos alternativos
./run-java.sh
mvn spring-boot:run
```

### Producción:
```bash
# Con variables configuradas en el sistema
export DB_URL="jdbc:postgresql://servidor-prod:5432/imc"
export DB_USERNAME="usuario_prod"  
export DB_PASSWORD="password_seguro"
export SPRING_PROFILES_ACTIVE="prod"

mvn spring-boot:run -Dspring.profiles.active=prod
```

## ⚠️ Seguridad

- ❌ **NUNCA** subir archivos `.env` a Git
- ❌ **NUNCA** hardcodear credenciales en código
- ✅ Usar variables de entorno para secrets
- ✅ Configurar `.gitignore` apropiadamente

## 📱 Endpoints Principales

- **Base URL:** `http://localhost:8080`
- `/imc/register` - Registro de usuarios
- `/imc/login` - Inicio de sesión  
- `/imc/form` - Cálculo de IMC
- `/imc/history` - Historial de mediciones

## 🎯 Estado del Proyecto

- ✅ Maven instalado y funcionando
- ✅ Variables de entorno configuradas
- ✅ Scripts de ejecución listos
- ✅ Base de datos PostgreSQL conectada
- ✅ Aplicación lista para ejecutar
