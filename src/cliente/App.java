package cliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class App {

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		
		Socket socket = new Socket("localhost", 12345);
		
		System.out.println("Conexão Estabelecida");

		Thread envio = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("Aguardando envio...");
				
				try {
					PrintStream saida = new PrintStream(socket.getOutputStream());
					
					Scanner scanner = new Scanner(System.in);
					while(scanner.hasNextLine()) {
						String linha = scanner.nextLine();
						
						if(linha.trim().equals("")) {
							break;
						}
						
						saida.println(linha);
					}
					scanner.close();
					saida.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		});
		
		Thread resposta = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("Recebendo dados do servidor...");
				
				try {
					Scanner scannerResposta = new Scanner(socket.getInputStream());
					
					while(scannerResposta.hasNextLine()) {
						String linha = scannerResposta.nextLine();
						
						System.out.println(linha);
					}
					
					scannerResposta.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		});
		
		envio.start();
		resposta.start();
		
		envio.join();
		
		System.out.println("Fechando socket do cliente");
		
		socket.close();
	}

}
