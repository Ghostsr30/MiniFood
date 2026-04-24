package application;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import entities.Order;
import entities.OrderItem;
import entities.Product;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		Map<Integer, Order> historico = new HashMap<>();
		int proximoIdPedido = 1;
        Map<String, Product> produtos = new HashMap<>();
		Order ord = new Order();
		int option = 0;
		while (option != 5) {

			System.out.println("------------------------------------------");
			System.out.println("Menu:");
			System.out.println();
			System.out.println("1) Cadastrar Produto");
			System.out.println("2) Listar Produtos");
			System.out.println("3) Criar Pedido");
			System.out.println("4) Ver Pedido");
			System.out.println("5) Sair");
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

			if (option == 1) {
				

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

						Product pr = new Product(name, price);
						produtos.put(pr.getName().toLowerCase(), pr);
					} 
					catch (InputMismatchException e) {
						System.out.println("Entrada inválida");
					}

				}
			} else if (option == 2) {
				System.out.println("Produtos: ");
				for (Product pr : produtos.values()) {
					System.out.println(pr);

				}
				System.out.println();

			} else if (option == 3) {


					System.out.println("Produtos disponiveis: ");
					for (Product pr : produtos.values()) {
						System.out.println(pr);
					}

					try {
						System.out.println("O que deseja pedir? ");
					    char respPedido = 's';
						while(respPedido == 's'){	
						System.out.print("Escolha o NOME do produto: ");
						sc.nextLine();
						String nomeProd = sc.next().toLowerCase();

                        Product p = produtos.get(nomeProd);
                        
                        if(p != null) {
                        	System.out.print("Digite a quantidade: ");
                        	int qtdProd = sc.nextInt();
                        	
                        	OrderItem item = new OrderItem(p, qtdProd);
                        	ord.addList(item);
                        	System.out.println("Item adicionado com sucesso!");
                        	System.out.println("Deseja adicionar mais algum pedido? (s/n)");
                        	respPedido = sc.next().charAt(0);
                            }
                        }
					} 
					catch (InputMismatchException e) {
						System.out.println("Entrada invalida!");
					}

					historico.put(proximoIdPedido, ord);
					System.out.println("Pedido salvo com sucesso! Número: " + proximoIdPedido);
					ord = new Order();
					proximoIdPedido++;

			} 
			else if (option == 4) {
                System.out.print("Digite o número do pedido: ");
                int id = sc.nextInt();
                
                Order pedidoBuscado = historico.get(id);
                
                if(pedidoBuscado != null) {
                	System.out.println(pedidoBuscado);
                }
                else {
                	System.out.println("Pedido #" + id + "Não existe!");
                }

				
			} 
			else if(option == 5){
				System.out.println("Obrigado!");
				System.out.println("Saindo do sistema...");
			}

		}

		sc.close();
	}

}
