package servidor;

import java.net.Socket;

public class Tarefa {
	
	private Socket socket;
		
	public Tarefa(Socket socket) {
		this.socket = socket;
	}

	//M�todos
	//TODO: Implementar m�todos de tarefa
	public void desenhar() {
		
	}
	
	public void listar() {
		
	}
	
	public void media() {
		
	}
	
	public void buscar(String parametro) {
		
	}

	//Getters e Setters
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
}
