
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.*;

// Clase para manejar las operaciones relacionadas con los clientes y atender a multiples clientes si es necesario.
public class ManejadorCliente extends Thread {

    // Aquí irían los atributos y métodos necesarios para manejar la comunicación con un cliente específico.
    private Socket socket;

    // Constructor que recibe el socket del cliente
    public ManejadorCliente(Socket socket) {
        this.socket = socket;
    }

    // Método run que se ejecutará cuando se inicie el hilo
    @Override
    public void run() {

        try {
            // Lógica para manejar la comunicación con el cliente
            // (similar a la lógica en ServidorIMC)
            //Creamos los flujos de entrada y salida

            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());

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
            System.out.println("Cliente desconectado.");

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
