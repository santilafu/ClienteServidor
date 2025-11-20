//Servidor que calcula el Índice de Masa Corporal (IMC) de un cliente y manda el resultado y consejos de salud segun el IMC calculado.
//Importamos las librerías necesarias para crear un servidor
import java.io.*;
import java.net.*;

public class ServidorIMC {
    public static void main(String[] args){
        try {
            //Creamos el socket del servidor en el puerto 12345
            ServerSocket servidor = new ServerSocket(12345);
            System.out.println("Servidor iniciado. Esperando conexiones...");

            while (true) {
                //Aceptamos la conexión del cliente
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());

                //Creamos los flujos de entrada y salida
                DataInputStream entrada = new DataInputStream(cliente.getInputStream());
                DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());

                //Recibimos el peso y la altura del cliente
                double peso = entrada.readDouble();
                double altura = entrada.readDouble();

                //Calculamos el IMC
                double imc = peso / (altura * altura);

                //Determinamos el consejo según el IMC
                String consejo;
                if (imc < 18.5) {
                    consejo = "Bajo peso: Se recomienda aumentar la ingesta calórica y consultar a un nutricionista.";
                } else if (imc >= 18.5 && imc < 24.9) {
                    consejo = "Peso normal: Mantén una dieta equilibrada y realiza ejercicio regularmente.";
                } else if (imc >= 25 && imc < 29.9) {
                    consejo = "Sobrepeso: Se recomienda reducir la ingesta calórica y aumentar la actividad física.";
                } else {
                    consejo = "Obesidad: Es importante consultar a un profesional de la salud para un plan adecuado.";
                }

                //Enviamos el IMC y el consejo al cliente
                salida.writeDouble(imc);
                salida.writeUTF(consejo);

                //Cerramos los flujos y el socket del cliente
                entrada.close();
                salida.close();
                cliente.close();
                System.out.println("Cliente desconectado.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
