import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

// Esta clase se encarga de atender a UN cliente.
// Cada vez que un cliente se conecte al servidor, se creará un hilo de esta clase.
// Así podemos atender a varios clientes a la vez sin bloquear el servidor.
public class ManejadorCliente extends Thread {

    // Socket que representa la conexión con el cliente.
    private Socket socket;

    // Constructor: recibe el socket que viene del servidor.
    public ManejadorCliente(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        // Guardamos el tiempo en el que empezamos a atender al cliente.
        // Esto servirá para calcular cuánto ha tardado el servidor en atenderlo.
        long inicioAtencion = System.currentTimeMillis();

        try {
            // Mensaje informativo para saber quién se ha conectado.
            System.out.println("Atendiendo cliente desde: "
                    + socket.getInetAddress().getHostAddress());

            // Flujos para comunicarnos con el cliente.
            // entrada -> lo que el cliente nos envía
            // salida  -> lo que nosotros devolvemos al cliente
            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());

            // Leemos peso y altura enviados por el cliente.
            // Si el cliente se desconecta de golpe, aquí saltaría una excepción.
            double peso = entrada.readDouble();
            double altura = entrada.readDouble();

            // Calculamos el IMC con la fórmula:
            // IMC = peso / (altura * altura)
            double imc = peso / (altura * altura);

            // Creamos un mensaje según el rango del IMC.
            String consejo;

            if (imc < 18.5) {
                consejo = "Bajo peso: necesitas comer más y mejor.";
            } else if (imc < 24.9) {
                consejo = "Peso normal: sigue como vas.";
            } else if (imc < 29.9) {
                consejo = "Sobrepeso: reduce calorías y muévete más.";
            } else {
                consejo = "Obesidad: consulta con un profesional.";
            }

            // Enviamos los resultados al cliente.
            salida.writeDouble(imc);
            salida.writeUTF(consejo);

            // Cerramos los flujos.
            entrada.close();
            salida.close();

        } catch (Exception e) {
            // Cualquier excepción que ocurra al atender al cliente aparecerá aquí.
            System.out.println("Error atendiendo al cliente: " + e.getMessage());
        } finally {
            try {
                // Cerramos el socket del cliente pase lo que pase.
                socket.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar el socket: " + e.getMessage());
            }

            // Tiempo final
            long finAtencion = System.currentTimeMillis();

            // Diferencia entre el inicio y el final → tiempo total de atención.
            long tiempoTotal = finAtencion - inicioAtencion;

            // Mostramos el tiempo en la consola del servidor.
            System.out.println("Cliente atendido en " + tiempoTotal + " ms.");
            System.out.println("----------------------------------------------");
        }
    }
}
