# 🧠 Actividad 3 – Arquitectura Cliente/Servidor (UD3 – Programación Distribuida)

Esta actividad consiste en crear una aplicación distribuida que siga el modelo cliente/servidor.
El objetivo es comunicar dos programas Java mediante Sockets, enviando datos desde el cliente y devolviendo una respuesta procesada desde el servidor.

El caso práctico elegido es el cálculo del Índice de Masa Corporal (IMC) y la devolución de un consejo de salud según el resultado.

## 🎯 Objetivo del programa

- El sistema está formado por dos aplicaciones:

### 🟦 Servidor IMC

- **Se queda escuchando en el puerto 12345 y:**

  - recibe peso y altura enviados por el cliente

  - calcula el IMC con la fórmula:
  IMC = peso / (altura * altura)

- determina el estado del usuario:

  - Bajo peso

  - Peso normal

  - Sobrepeso

  - Obesidad

- envía al cliente:

  - el IMC calculado

  - un consejo personalizado

El servidor funciona en bucle, atendiendo a todos los clientes que se conecten.

### 🟩 Cliente IMC

- El cliente:

  - pide al usuario su peso y altura

  - se conecta al servidor

  - envía los datos

  - recibe el IMC y el consejo

  - muestra por pantalla el resultado

## 📂 Estructura del proyecto
````css
Proyecto-IMC/
├── src/
│    ├── ServidorIMC.java
│    └── ClienteIMC.java
├── README.md
└── .gitignore
````

- Los códigos están dentro de /src para que puedan consultarse directamente.

## 🚀 Funcionamiento paso a paso
### 1️⃣ Se arranca el servidor

- Desde IntelliJ o desde consola:

  - java ServidorIMC


- El servidor muestra:

  - Servidor iniciado. Esperando conexiones...

### 2️⃣ Se ejecuta el cliente

- El cliente pregunta:

  - Ingrese su peso en kg:
  - Ingrese su altura en metros:


- Ejemplo con tus datos reales:

  - Peso: 75
  - Altura: 1.76


- El cliente recibe:

  - Su Índice de Masa Corporal (IMC) es: 24.21
  - Consejo de salud: Peso normal: Mantén una dieta equilibrada y realiza ejercicio regularmente.

## 🌐 ¿Qué se está usando internamente?

- Sockets TCP

- Socket → del lado del cliente

- ServerSocket → del lado del servidor

- Flujos de datos binarios

- DataInputStream

- DataOutputStream

Conexión por localhost (solo entre los dos programas dentro de mi a PC)

## ❓ Preguntas solicitadas en la actividad
### ▶ Rol del cliente

- Es el que inicia la comunicación enviando los datos (peso y altura) al servidor.

### ▶ Rol del servidor

- Es el que espera conexiones, recibe los datos, realiza el cálculo y devuelve una respuesta.

### ▶ Arquitectura utilizada

- La app utiliza el modelo Cliente/Servidor, donde:

- hay una clara separación de roles

  - el servidor actúa como “cerebro”

  - el cliente solo solicita información o servicios