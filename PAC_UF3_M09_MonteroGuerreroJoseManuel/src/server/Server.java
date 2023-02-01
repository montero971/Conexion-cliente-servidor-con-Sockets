package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private final int Port = 9876;
	private ServerSocket serverSocket;
	private Socket socket;

	public Server() throws IOException {
		serverSocket = new ServerSocket(Port);
		socket = new Socket();
	}

	public void iniciarServidor() throws IOException {

		while (true) {
			System.out.println("Servidor iniciado, esperando al cliente");
			socket = serverSocket.accept();

			System.out.println("Cliente conectado");
			DataOutputStream servidor = new DataOutputStream(socket.getOutputStream());
			DataInputStream cliente = new DataInputStream(socket.getInputStream());

			servidor.writeUTF("Servidor: Hola, ¿como te llamas?");
			String nombreCliente = cliente.readUTF();// Guardamos el nombre que pasa el usuario para concatenarlo en el
														// siguiente String
			servidor.writeUTF("Servidor: Bien, " + nombreCliente + " dime el número de tareas que quieres realizar");

			int tareas = (cliente.readInt());
			System.out.println("Número de tareas marcadas por el cliente: " + tareas);

			Tarea[] arrayTareas = new Tarea[tareas];

			// Con este bucle recorreo las tareas que el usuario le pasa al cliente
			for (int i = 0; i < tareas; i++) {
				Tarea instanciaTarea = new Tarea();
				servidor.writeUTF("Tarea " + (i + 1) + ":");
				servidor.writeUTF("Descripción de la tarea: ");
				instanciaTarea.setDescripcion(cliente.readUTF()); // A través del setter de la clase Tarea guardo la
																	// descrip.
				servidor.writeUTF("En que estado se encuentra la tarea: ");
				instanciaTarea.setEstado(cliente.readUTF()); // Hago lo mismo que antes pero con el estado
				arrayTareas[i] = instanciaTarea; // Guaro en el array la descrip. y el estado
			}
			servidor.writeUTF("Procesando todas las tareas, envio inminente...");
			servidor.writeUTF("Listado de tareas: ");
			for (int i = 0; i < arrayTareas.length; i++) { // Con este bucle saco en el cliente el listado de tareas
				servidor.writeUTF("Tarea " + (i + 1) + ":" + " " + arrayTareas[i].getDescripcion() + "\nEstado: "
						+ arrayTareas[i].getEstado());
			}

			socket.close();
			System.out.println("Cliente desconectado");// uso syso para que este string solo me salga en el server
			cliente.close();
			servidor.close();
			break;
		}
	}

}
