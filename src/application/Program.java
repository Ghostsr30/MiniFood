package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Order;
import entities.OrderItem;
import entities.Product;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		List<Product> list = new ArrayList<>();

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
						list.add(pr);
					} 
					catch (InputMismatchException e) {
						System.out.println("Entrada inválida");
					}

				}
			} else if (option == 2) {
				System.out.println("Produtos: ");
				for (Product pr : list) {
					System.out.println(pr);

				}
				System.out.println();

			} else if (option == 3) {
				char resp = 's';

				while (resp == 's') {

					System.out.println("Produtos disponiveis: ");
					for (Product pr : list) {
						System.out.println(pr);
					}

					try {
						System.out.println("O que deseja pedir? ");
						System.out.print("Escolha o item da lista: ");
						int index = sc.nextInt();

						Product pr = list.get(index);
						System.out.print("Quantos deseja? ");
						int quantity = sc.nextInt();
						System.out.println();

						System.out.print("Deseja adicionar mais algum pedido? (s/n) ");
						resp = sc.next().charAt(0);
						System.out.println();

						OrderItem item = new OrderItem(pr, quantity);

						ord.addList(item);
					} 
					catch (InputMismatchException e) {
						System.out.println("Entrada invalida!");
					}

				}

				System.out.println("Pedido criado");
				System.out.println();
			} 
			else if (option == 4) {
				if(ord.isEmpty()) {
					System.out.println("Não existe pedidos feito ainda");
					System.out.println("Por favor faça um em CRIAR PEDIDO!");
				}
				else {
				System.out.println("PEDIDO:");
				System.out.println(ord);
				}
			} 
			else if(option == 5){
				System.out.println("Obrigado!");
				System.out.println("Saindo do sistema...");
			}

		}

	}

}
