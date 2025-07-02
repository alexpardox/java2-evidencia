# Guía de Deployment en Railway

## Variables de Entorno Requeridas

Para que la aplicación funcione correctamente en Railway, debes configurar las siguientes variables de entorno en el dashboard de Railway:

### Variables Obligatorias

```bash
# Configuración de base de datos (Railway PostgreSQL)
DB_URL=jdbc:postgresql://[HOST]:[PORT]/railway
DB_USERNAME=postgres
DB_PASSWORD=[RAILWAY_DB_PASSWORD]

# Configuración de entorno
SPRING_PROFILES_ACTIVE=prod
SHOW_SQL=false
```

## Pasos para Configurar en Railway

1. **Accede a tu proyecto en Railway**
   - Ve a https://railway.app/
   - Selecciona tu proyecto

2. **Configura las Variables de Entorno**
   - Ve a "Settings" > "Variables"
   - Agrega cada variable una por una:
     - `DB_URL`: La URL de tu base de datos PostgreSQL de Railway
     - `DB_USERNAME`: Generalmente `postgres`
     - `DB_PASSWORD`: La contraseña generada por Railway
     - `SPRING_PROFILES_ACTIVE`: `prod`
     - `SHOW_SQL`: `false`

3. **Obtener Credenciales de Base de Datos**
   - En tu proyecto Railway, ve a la pestaña de PostgreSQL
   - Copia las credenciales desde "Connect"
   - Usa el formato JDBC para `DB_URL`

## Ejemplo de Variables en Railway

```
DB_URL=jdbc:postgresql://containers-us-west-xyz.railway.app:5432/railway
DB_USERNAME=postgres
DB_PASSWORD=tu-password-generado-por-railway
SPRING_PROFILES_ACTIVE=prod
SHOW_SQL=false
```

## Verificación del Deployment

1. **Logs de Railway**: Verifica que no hay errores de conexión a BD
2. **Health Check**: Accede a tu aplicación para verificar que funciona
3. **Variables**: Confirma que todas las variables están configuradas

## Troubleshooting

### Error de Conexión a Base de Datos
- Verifica que `DB_URL` esté en formato JDBC correcto
- Confirma que `DB_USERNAME` y `DB_PASSWORD` son correctos
- Asegúrate de que la base de datos PostgreSQL esté activa en Railway

### Error de Perfil Spring
- Confirma que `SPRING_PROFILES_ACTIVE=prod`
- Verifica que existe el archivo `application-prod.properties`

### Error de Variables de Entorno
- Todas las variables deben estar configuradas en Railway
- No uses espacios en los nombres de variables
- Redeploy después de cambiar variables

## Seguridad

- ✅ Variables sensibles solo en Railway (no en código)
- ✅ Archivo `.env` en `.gitignore`
- ✅ Configuración de producción optimizada
- ✅ Logging reducido en producción
