// Cliente que envía su peso y altura al servidor para recibir su Índice de Masa Corporal (IMC) y consejos de salud.
// Importamos las librerías necesarias para crear un cliente
import java.util.Scanner;
import java .io.*;
import java.net.*;
public class ClienteIMC {
    public static void main(String[] args) {
        try {
            //Creamos el socket del cliente y nos conectamos al servidor en localhost y puerto 12345
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Conectado al servidor.");

            //Creamos los flujos de entrada y salida
            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());

            //Solicitamos al usuario su peso y altura
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese su peso en kg: ");
            double peso = scanner.nextDouble();
            System.out.print("Ingrese su altura en metros: ");
            double altura = scanner.nextDouble();

            //Enviamos el peso y la altura al servidor
            salida.writeDouble(peso);
            salida.writeDouble(altura);

            //Recibimos el IMC y el consejo del servidor
            double imc = entrada.readDouble();
            String consejo = entrada.readUTF();

            //Mostramos el resultado al usuario
            System.out.printf("Su Índice de Masa Corporal (IMC) es: %.2f%n", imc);
            System.out.println("Consejo de salud: " + consejo);

            //Cerramos los flujos y el socket
            entrada.close();
            salida.close();
            socket.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
