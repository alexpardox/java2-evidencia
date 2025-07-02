
### 🏗️ **RESUMEN DEL TOTAL DE CLASES UTILIZADAS: 7 Clases Java + 4 Vistas HTML**

---

## 🎯 **MODELO (Model) - 4 Clases**

### **📦 Entidades (2 clases):**
| Clase | Ubicación | Función | Estado |
|-------|-----------|---------|--------|
| Usuario.java | `/entity/` | Entidad principal de usuarios con validaciones | ✅ **Principal IMC** |
| Records.java | `/entity/` | Registros de mediciones IMC | ✅ **Principal IMC** |

### **🗄️ Repositorios (2 clases):**
| Interfaz | Ubicación | Función | Estado |
|----------|-----------|---------|--------|
| UsuarioRepository.java | `/repositories/` | Acceso a datos de usuarios | ✅ **Principal IMC** |
| RecordsRepository.java | `/repositories/` | Acceso a datos de registros IMC | ✅ **Principal IMC** |

---

## 🎮 **CONTROLADOR (Controller) - 3 Clases**

### **🎛️ Controladores Web (1 clase):**
| Clase | Ubicación | Función | Estado |
|-------|-----------|---------|--------|
| IMCController.java | `/controller/` | **Controlador principal IMC** (registro, login, cálculos) | ✅ **Principal** |

### **⚙️ Servicios (2 clases):**
| Clase | Ubicación | Función | Estado |
|-------|-----------|---------|--------|
| UserService.java | `/services/` | **Lógica de negocio de usuarios** | ✅ **Principal IMC** |
| RecordsService.java | `/services/` | **Lógica de negocio de registros IMC** | ✅ **Principal IMC** |

---

## 👁️ **VISTA (View) - 4 Plantillas HTML**

### **🖥️ Vistas Principales IMC (4 plantillas):**
| Plantilla | Función | Estado |
|-----------|---------|--------|
| register.html | **Formulario de registro de usuarios** | ✅ **Principal** |
| imc-login.html | **Página de login IMC** | ✅ **Principal** |
| imc-form.html | **Formulario de cálculo IMC** | ✅ **Principal** |
| imc-history.html | **Historial de mediciones IMC** | ✅ **Principal** |

---

## 🔧 **CONFIGURACIÓN Y ARRANQUE (1 Clase)**

### **⚡ Clase Principal:**
| Clase | Ubicación | Función |
|-------|-----------|---------|
| AppMain.java | `/` | **Punto de entrada de Spring Boot** |

---

## 📋 **RESUMEN POR PATRÓN MVC:**

### **🎯 MODELO (4 clases):**
#### **Entidades JPA:**
- ✅ Usuario.java - Usuarios del sistema IMC
- ✅ Records.java - Registros de mediciones IMC  

#### **Repositorios (Data Access Layer):**
- ✅ UsuarioRepository.java - CRUD usuarios
- ✅ RecordsRepository.java - CRUD registros IMC

### **🎮 CONTROLADOR (3 clases):**
#### **Controladores Web:**
- ✅ IMCController.java - **Controlador principal** (8 endpoints)

#### **Servicios (Business Logic Layer):**
- ✅ UserService.java - Lógica de usuarios
- ✅ RecordsService.java - Lógica de registros IMC

### **👁️ VISTA (4 plantillas):**
#### **Aplicación IMC Principal:**
- ✅ register.html - Registro usuarios
- ✅ imc-login.html - Login
- ✅ imc-form.html - Cálculo IMC
- ✅ imc-history.html - Historial

---

## 🎯 **APLICACIÓN PRINCIPAL IMC:**

### **Modelo:** 4 clases activas
- Usuario.java, Records.java, UsuarioRepository.java, RecordsRepository.java

### **Controlador:** 3 clases activas  
- IMCController.java, UserService.java, RecordsService.java

### **Vista:** 4 plantillas activas
- register.html, imc-login.html, imc-form.html, imc-history.html

**🔥 Total aplicación IMC: 11 componentes principales**

La aplicación sigue correctamente el patrón **MVC** con una **arquitectura en capas** bien definida y **separación clara de responsabilidades**.
