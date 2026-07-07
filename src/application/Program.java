package application;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import entities.*;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);


		ProductDAO prDAO = new ProductDAO();
		Map<Integer, Order> historico = new TreeMap<>();
		int proximoIdPedido = 1;
        Map<String, Product> produtos = new TreeMap<>();
		Order ord = new Order();
		Map<Integer, FileUser> historicoUsers = new TreeMap<>();

		int option = 0;
		while (option != 6) {

			System.out.println("------------------------------------------");
			System.out.println("Menu:");
			System.out.println();
			System.out.println("1) Cadastrar Produto");
			System.out.println("2) Listar Produtos");
			System.out.println("3) Criar Pedido");
			System.out.println("4) Ver Pedido");
			System.out.println("5) Criar arquivo do pedido");
			System.out.println("6) Sair");
			System.out.println("------------------------------------------");

			try {

				System.out.println();
				System.out.print("Escolha uma opção: ");
				option = sc.nextInt();
				System.out.println();
				
			} 
			catch (InputMismatchException e) {
				System.out.println("Entrada inválida!");
				sc.nextLine();
			}

			switch(option) {
			case 1: 
				

				int n = 0;
				try {
					System.out.print("Quantos produtos ira ser adicionado? ");
					n = sc.nextInt();

				} 
				catch (InputMismatchException e) {
					System.out.println("Voce precisar digitar um numero!");
				}

				for (int i = 0; i < n; i++) {
					try {
						System.out.print("Nome do produto: ");
						sc.nextLine();
						String name = sc.nextLine();
						System.out.print("Preco do produto: ");
						double price = sc.nextDouble();

						prDAO.addProduct(name, price);

					} 
					catch (InputMismatchException e) {
						System.out.println("Entrada inválida");
					}

				}
				break;
			 case 2:


				 System.out.println("Produtos: ");
				 for(Product p : prDAO.findAll()){
					 System.out.println(p);
				 }
				System.out.println();
				break;

			 case 3:


				 if(produtos.isEmpty()) {
					 System.out.println("Nenhum produto encontrado!");
					 break;
				 }


					 System.out.println("Produtos disponiveis: ");
					 for (Product pr : produtos.values()) {
						 System.out.println(pr);
					 }
					 try {
						 System.out.println("Qual o seu nome? ");
						 sc.nextLine();
						 String nameUser = sc.nextLine();
						 System.out.println("Insira a data de hoje no formato: (dd/MM/yyyy)");
						 LocalDate dateArq = LocalDate.parse(sc.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));


						 System.out.println("O que deseja pedir? ");
						 char respPedido = 's';

						 while (respPedido == 's') {
							 System.out.print("Escolha o NOME do produto: ");
							 sc.nextLine();
							 String nomeProd = sc.nextLine().toLowerCase();

							 Product p = produtos.get(nomeProd);

							 if (p != null) {
								 System.out.print("Digite a quantidade: ");
								 int qtdProd = sc.nextInt();
								 OrderItem item = new OrderItem(p, qtdProd);
								 ord.addList(item);
								 System.out.println("Item adicionado com sucesso!");
								 System.out.println("Deseja adicionar mais algum pedido? (s/n)");
								 respPedido = sc.next().charAt(0);
							 }
						 }


						 FileUser fu = new FileUser(nameUser, dateArq, ord);
						 historico.put(proximoIdPedido, ord);
						 historicoUsers.put(proximoIdPedido, fu);




				 } catch (InputMismatchException e) {
					 System.out.println("Entrada invalida!");
				 }

				 System.out.println("Pedido salvo com sucesso! Número: " + proximoIdPedido);
				 ord = new Order();
				 proximoIdPedido++;
				 break;

			
			 case 4: 
                System.out.print("Digite o número do pedido: ");
                int id = sc.nextInt();
                
                Order pedidoBuscado = historico.get(id);


                if(pedidoBuscado != null) {
                	System.out.println(pedidoBuscado);

                }
                else {
                	System.out.println("Pedido #" + id + "Não existe!");
                }
                 break;
			case 5:

				System.out.println("Qual diretório deseja salvar? ");
				sc.nextLine();
				String path = sc.nextLine();
				new File(path).mkdirs();

				File pathFile = new File(path);
				String folder = pathFile.getParent();

				System.out.print("Digite o número do pedido que deseja imprimir o arquivo: ");
				int idArq = sc.nextInt();

				boolean success = new File(folder + "/out").mkdirs();
				String createdFile = path + "/pedido-" + idArq + ".txt";

				FileUser fu = historicoUsers.get(idArq);

				if(fu != null){
					try(BufferedWriter bw = new BufferedWriter(new FileWriter(createdFile))){
							bw.write(fu.toString());
						    System.out.println("File created at: " + createdFile);

					}
					catch (IOException e){
						System.out.println(e.getMessage());
					}
				}
				else{
					System.out.println("Pedido: " + idArq + "# não existe!");
				}

				break;

			case 6:
				System.out.println("Obrigado!");
				System.out.println("Saindo do sistema...");
			break;

            }

		}
	

		sc.close();


	}

}
