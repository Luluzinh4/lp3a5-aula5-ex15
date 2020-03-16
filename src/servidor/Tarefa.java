package servidor;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class Tarefa {
	
	private Socket socket;
		
	public Tarefa(Socket socket) {
		this.socket = socket;
	}

	//Métodos
	//TODO: Implementar métodos de tarefa
	public void desenhar() throws IOException {
		PrintStream saidaCliente = new PrintStream(socket.getOutputStream());
		saidaCliente.println("Desenho");
	}
	
	public void listar() throws IOException {
		PrintStream saidaCliente = new PrintStream(socket.getOutputStream());
		saidaCliente.println("Lista");
	}
	
	public void media() throws IOException {
		PrintStream saidaCliente = new PrintStream(socket.getOutputStream());
		saidaCliente.println("Média");
	}
	
	public void buscar(String parametro) throws IOException {
		PrintStream saidaCliente = new PrintStream(socket.getOutputStream());
		saidaCliente.println("Busca");
	}

	//Getters e Setters
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
}
