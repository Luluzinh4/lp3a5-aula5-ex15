package servidor;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class DistribuicaoTarefas implements Runnable {
	
	private Socket socket;

	public DistribuicaoTarefas(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		System.out.println("Distribuindo a tarefa do cliente: " + socket.getPort());
		
		try {
			Scanner entradaCliente = new Scanner(socket.getInputStream());
			PrintStream saidaCliente = new PrintStream(socket.getOutputStream());
			
			while(entradaCliente.hasNextLine()) {
				String comando = entradaCliente.nextLine();
				System.out.println("Comando: "+ comando);
				
				Tarefa tarefa = new Tarefa(socket);
				
				switch(comando){
					case "desenhar":
						tarefa.desenhar();
						break;
					case "listar":
						tarefa.listar();
						break;
					case "media":
						tarefa.media();
						break;
					case "buscar":
						saidaCliente.println("Comando Insuficiente. "
								+ "Insira algum parâmetro após \"buscar \"");
						break;
					default:
						if(comando.contains("buscar")) {
							String parametro = comando.substring(6).trim();
							if(!parametro.equals("")) {
								tarefa.buscar(parametro);
								break;
							}
							saidaCliente.println("Comando Inválido! Sem parâmetro");
							break;
						}
						saidaCliente.println("Comando Inválido. Digite novamente!");
				}
			}
			entradaCliente.close();
			saidaCliente.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Getters e Setters
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

}
