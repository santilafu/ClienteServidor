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
  
--------------------------------------------------------

# 📡 Actividad 4 – Servidor concurrente IMC
Aplicación cliente/servidor con atención a múltiples clientes

En esta actividad he modificado el servidor del ejercicio anterior para que pueda atender a varios clientes al mismo tiempo. Ahora el servidor lanza un hilo nuevo por cada cliente, lo que permite que varios usuarios calculen su IMC a la vez sin bloquear el sistema.

## 🧩 ¿Qué se ha hecho en esta actividad?
1. Creación de ManejadorCliente

- He creado una clase llamada ManejadorCliente, que extiende Thread.
Esta clase es la encargada de atender a un cliente concreto:

  - Recibe su peso y altura.

  - Calcula su IMC.

  - Genera el consejo correspondiente.

  - Devuelve los datos al cliente.

Cada cliente funciona de forma independiente porque cada uno funciona en su propio hilo.

2. Modificación del servidor para hacerlo concurrente

- En vez de atender a un cliente y bloquearse, el servidor ahora hace esto:

  - Acepta una conexión nueva.

  - Crea un hilo ManejadorCliente para ese cliente.

  - Ese hilo se encarga de todo.

  - El servidor vuelve a escuchar para atender a más clientes.

Esto permite que dos, tres o diez clientes hablen con el servidor de forma simultánea.

## 🧠 ¿Qué hemos conseguido?

- Un servidor iterativo: nunca para, siempre espera nuevos clientes.

- Un servidor concurrente: puede atender a varios clientes al mismo tiempo.

- Cada cliente recibe su IMC y su recomendación sin esperar al resto.

## 🧪 Pruebas realizadas

- He ejecutado varias ventanas del cliente a la vez:

  - Todos los clientes se conectan correctamente.

  - El servidor crea un hilo distinto por cliente.

  - La respuesta llega bien a todas las ventanas.

  - El orden de llegada cambia en cada ejecución (normal al tener varios hilos).

## 🗂 Estructura del proyecto
````css
src/
│── ServidorIMC.java        -> Servidor principal y concurrente
│── ClienteIMC.java         -> Programa cliente
│── ManejadorCliente.java   -> Hilo que atiende a cada cliente
````