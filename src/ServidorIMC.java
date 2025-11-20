import java.net.ServerSocket;
import java.net.Socket;

// Servidor que escucha en un puerto y atiende a múltiples clientes en paralelo.
// Cada cliente se gestiona con un hilo ManejadorCliente.
public class ServidorIMC {
    public static void main(String[] args) {

        try {
            // Creamos el servidor en el puerto 12345.
            // Ese será el puerto donde los clientes se conectarán.
            ServerSocket servidor = new ServerSocket(12345);
            System.out.println("Servidor IMC iniciado. Esperando clientes...");

            // Bucle infinito para que el servidor nunca deje de escuchar.
            while (true) {

                // accept() se queda esperando hasta que un cliente se conecte.
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado.");

                // Creamos un hilo para ese cliente.
                ManejadorCliente manejador = new ManejadorCliente(cliente);

                // Iniciamos el hilo para que atienda al cliente en paralelo.
                manejador.start();
            }

        } catch (Exception e) {
            // Capturamos errores del servidor: puerto ocupado, permisos, etc.
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}
