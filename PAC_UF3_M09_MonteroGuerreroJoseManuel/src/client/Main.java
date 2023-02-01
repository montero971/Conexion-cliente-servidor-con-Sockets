package client;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		try {
			Client client = new Client();
			System.out.println("Cliente conectado");
			client.iniciarCliente();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		

	}

}
