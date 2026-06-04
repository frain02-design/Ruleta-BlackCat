# 🎰 Ruleta BlackCat

Sistema completo de ruleta desarrollado en Java con Swing, aplicando principios de Programación Orientada a Objetos y patrones de diseño.

---

## 📋 Laboratorios completados

| Laboratorio | Tema | Estado |
|-------------|------|--------|
| Lab 01 | Ruleta en consola (CLI) | ✅ |
| Lab 02 | Login y registro con Swing | ✅ |
| Lab 03 | Separar lógica de la vista (MVC básico) | ✅ |
| Lab 04 | MVC completo + encapsulamiento | ✅ |
| Lab 05 | Asociaciones y dependencias (TipoApuesta enum) | ✅ |
| Lab 06 | Round-trip y módulo de estadísticas | ✅ |
| Lab 07 | Herencia y polimorfismo (jerarquía de apuestas) | ✅ |
| Lab 08 | Interfaces y persistencia (archivos CSV) | ✅ |

---

## 🚀 Características

### Funcionalidades principales
- Login de usuarios con credenciales hardcodeadas
- Registro de nuevos usuarios
- Juego de ruleta con apuestas: Rojo, Negro, Par, Impar
- Historial de jugadas por usuario
- Estadísticas detalladas (racha máxima, tipo más jugado, etc.)
- Persistencia en archivo CSV (el historial se guarda entre sesiones)

### Arquitectura (MVC)
<img width="556" height="803" alt="image" src="https://github.com/user-attachments/assets/d31df225-6f74-466e-8112-b756ca5163c2" />


## 🎮 Cómo jugar

### 1. Ejecutar el programa
```bash
git clone https://github.com/frain02-design/Ruleta-BlackCat.git
cd Ruleta-BlackCat
# Abrir con IntelliJ IDEA y ejecutar Principal.java

### 2. Login
Usuario	Contraseña
admin	1234
juan	abc123
### 3. Menú principal
-Jugar a la ruleta - Realizar apuestas y girar la ruleta
-Ver historial - Historial completo de jugadas
-Ver estadísticas - Métricas de juego (racha máxima, etc.)
-Cerrar sesión - Volver al login
### 4. Juego de ruleta
1-Selecciona tipo de apuesta: Rojo, Negro, Par o Impar
2-Ingresa el monto a apostar
3-Haz clic en "Girar ruleta"
4-El sistema muestra:
  Número obtenido (0-36)
  Color del número
  Si ganaste o perdiste
### 📊 Persistencia
El historial se guarda automáticamente en historial.csv en la raíz del proyecto.
Formato CSV:
usuario,nombreCompleto,numero,tipoApuesta,acierto,monto,fecha
admin,Administrador,7,Rojo,true,1000,2025-06-03T10:30:00

### 📚 Principios aplicados
Principio	Aplicación
SRP	Una clase tiene una sola responsabilidad
OCP	Abierto para extensión, cerrado para modificación
LSP	Las subclases pueden reemplazar a la superclase
DIP	Depender de abstracciones (interfaz IRepositorioResultados)
MVC	Separación Modelo-Vista-Controlador
Encapsulamiento	Getters, setters, listas inmutables

# Login correcto
Usuario: admin
Clave: 1234
→ Mensaje: "Bienvenido Administrador"

# Jugar una ronda
Apuesta: Rojo
Monto: 100
→ Resultado: número y color

# Ver historial
→ Lista de jugadas

# Ver estadísticas
→ Métricas: racha máxima, tipo más jugado, etc.

# Cerrar sesión
→ Vuelve a la pantalla de login

# Cerrar programa
→ El historial persiste en historial.csv

👨‍💻 Autor
Proyecto académico - Programación Orientada a Objetos

