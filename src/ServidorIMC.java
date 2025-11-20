//Servidor que calcula el Índice de Masa Corporal (IMC) de un cliente y manda el resultado y consejos de salud segun el IMC calculado.
//Modificado para manejar multiples clientes de forma secuencial.
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

                //Lanzamos el hilo para manejar al cliente
                ManejadorCliente mc = new ManejadorCliente(cliente);
                mc.start();


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
