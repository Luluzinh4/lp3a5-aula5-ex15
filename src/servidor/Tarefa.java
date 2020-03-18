package servidor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Tarefa {
	
	private Socket socket;
		
	public Tarefa(Socket socket) {
		this.socket = socket;
	}

	//Métodos
	public void desenhar() throws IOException {
		PrintStream saidaCliente = new PrintStream(socket.getOutputStream());
		saidaCliente.println("Desenho");
		
		Random random = new Random();
		int num = random.nextInt(6) + 1;
		
		switch(num) {
			case 1:
				saidaCliente.println("Retângulo");
				saidaCliente.println("*******************");
				saidaCliente.println("*                 *");
				saidaCliente.println("*                 *");
				saidaCliente.println("*******************");
				break;
			case 2:
				saidaCliente.println("Quadrado");
				saidaCliente.println("**********");
				saidaCliente.println("*        *");
				saidaCliente.println("*        *");
				saidaCliente.println("*        *");
				saidaCliente.println("**********");
				break;
			case 3:
				saidaCliente.println("Triângulo Isósceles");
				saidaCliente.println("         *         ");
				saidaCliente.println("        * *        ");
				saidaCliente.println("       *   *       ");
				saidaCliente.println("      *     *      ");
				saidaCliente.println("     *       *     ");
				saidaCliente.println("    ***********    ");
				break;
			case 4:
				saidaCliente.println("Triângulo Equilátero");
				saidaCliente.println("          *         ");
				saidaCliente.println("        *   *       ");
				saidaCliente.println("      *       *     ");
				saidaCliente.println("    *           *   ");
				saidaCliente.println("  *               * ");
				saidaCliente.println("  ***************** ");
				break;
			case 5:
				saidaCliente.println("Triângulo Retângulo");
				saidaCliente.println("   *               ");
				saidaCliente.println("   * *             ");
				saidaCliente.println("   *   *           ");
				saidaCliente.println("   *     *         ");
				saidaCliente.println("   *       *       ");
				saidaCliente.println("   *         *     ");
				saidaCliente.println("   *           *   ");
				saidaCliente.println("   **************  ");
				break;
			case 6:
				saidaCliente.println("Triângulo Escaleno");
				saidaCliente.println(" *                ");
				saidaCliente.println("  *  *            ");
				saidaCliente.println("   *   *          ");
				saidaCliente.println("    *    *        ");
				saidaCliente.println("     *     *      ");
				saidaCliente.println("      *      *    ");
				saidaCliente.println("       *       *  ");
				saidaCliente.println("        ********* ");
				break;
			default:
				saidaCliente.println();
		}
		
	}
	
	public void listar() throws IOException {
		PrintStream saidaCliente = new PrintStream(socket.getOutputStream());
		saidaCliente.println("Lista de Clientes");
		
		try {
			Scanner scanner = new Scanner(new File("clientes.txt"));
			ArrayList<String> lista = new ArrayList<>();
			String[] listaClientes;
			
			while(scanner.hasNextLine()) {
				String linha = scanner.nextLine();
				lista.add(linha);
			}
			
			int tamanho = lista.size();
			listaClientes = new String[tamanho];
			
			for(int f = 0; f < tamanho; f++) {
				listaClientes[f] = lista.get(f);
			}
			
			int ind;
			String temp;
			for(int i = 0; i < tamanho - 1; i++) {
				ind = i;
				for(int j = i + 1; j < tamanho; j++) {
					if(listaClientes[ind].compareTo(listaClientes[j]) > 0) {
						ind = j;
					}
				}
				temp = listaClientes[i];
				listaClientes[i] = listaClientes[ind];
				listaClientes[ind] = temp;
			}
			
			for(String s: listaClientes) {
				saidaCliente.println(s);
			}
			
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public void media() throws IOException {
		PrintStream saidaCliente = new PrintStream(socket.getOutputStream());
		int soma = 0;
		int media = 0;
		int numLinhas = 0;
		
		try {
			Scanner scanner = new Scanner(new File("clientes.txt"));
			
			while(scanner.hasNextLine()) {
				String linha = scanner.nextLine();
				soma += linha.length();
				numLinhas++;
			}
			
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		media = soma/numLinhas;
		
		saidaCliente.println("Número médio de " + media +  " letras por palavra.");
	}
	
	public void buscar(String parametro) throws IOException {
		PrintStream saidaCliente = new PrintStream(socket.getOutputStream());
		saidaCliente.println("Busca de Cliente por Parâmetro");
		saidaCliente.println("Parâmetro: " + parametro);
		
		try {
			Scanner scanner = new Scanner(new File("clientes.txt"));
			
			while(scanner.hasNextLine()) {
				String linha = scanner.nextLine();
				
				if(linha.contains(parametro)) {
					saidaCliente.println(linha);
				}
				
			}
			
			scanner.close();
		} catch (FileNotFoundException e) {
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
