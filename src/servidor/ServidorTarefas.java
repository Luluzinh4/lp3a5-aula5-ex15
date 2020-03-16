package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorTarefas {

	public static void main(String[] args) throws IOException {
		System.out.println("------------- Iniciando Servidor -------------");
		
		@SuppressWarnings("resource")
		ServerSocket servidor = new ServerSocket(12345);
		
		ExecutorService poolThread = Executors.newCachedThreadPool();
		
		while(true) {
			Socket socket = servidor.accept();
			System.out.println("Cliente na porta: " + socket.getPort());
			
			DistribuicaoTarefas distribuirTarefa = new DistribuicaoTarefas(socket);
			
			poolThread.execute(distribuirTarefa);
		}
	}
}
