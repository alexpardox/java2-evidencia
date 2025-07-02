-- Este archivo se ejecuta solo si spring.jpa.hibernate.ddl-auto=create o create-drop
-- No es necesario con update, pero puede ser útil para datos iniciales

-- Usuarios de ejemplo (solo para testing inicial - eliminar en producción)
-- INSERT INTO users (full_name, username, password, age, gender, height, created_at) 
-- VALUES ('Usuario Test', 'admin', 'admin123', 25, 'M', 1.75, CURRENT_DATE)
-- ON CONFLICT (username) DO NOTHING;

-- Comentarios:
-- 1. Este archivo es opcional y solo para referencia
-- 2. Con ddl-auto=update, las tablas se mantienen
-- 3. Los datos insertados manualmente permanecerán
