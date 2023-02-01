package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	private final Socket socket;

	public Client() throws IOException {
		socket = new Socket("localhost", 9876);
	}

	public void iniciarCliente() throws IOException {
		DataInputStream servidor = new DataInputStream(socket.getInputStream());
		DataOutputStream cliente = new DataOutputStream(socket.getOutputStream());

		// Instancio la clase Scanner para recoger lo que el usuario introduce por
		// teclado
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println(servidor.readUTF());// El server pregunta por el nombre del usuario
			cliente.writeUTF(sc.nextLine());// El usuario escribe su nombre
			System.out.println(servidor.readUTF());// El server pregunta por el numero de tareas a realizar

			int tareas = Integer.parseInt(sc.nextLine()); // Guardo el numero de tareas en una variable
			cliente.writeInt(tareas); // Envío el número de tareas por el socket

			// Con este bucle recibo strings del server y el usuario describe las tareas
			for (int i = 0; i < tareas; i++) {
				System.out.println(servidor.readUTF()); // "Tarea nº"
				System.out.println(servidor.readUTF()); // "Descripción".
				cliente.writeUTF(sc.nextLine()); // Usuario introduce descrip.
				System.out.println(servidor.readUTF()); // "En qué estado se encuentra la tarea"
				cliente.writeUTF(sc.nextLine()); // Usuario introduce la tarea.
			}
			System.out.println(servidor.readUTF()); // "Procesando todas las tareas, envio inminente..."
			System.out.println(servidor.readUTF()); // "Listado de tareas"
			// Con este bucle le devuelvo al usuario un listado con las tareas
			for (int i = 0; i < tareas; i++) {
				System.out.println(servidor.readUTF());
			}
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		}
		cliente.close();
		servidor.close();
		socket.close();
	}

}
