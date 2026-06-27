🍔 MiniFood
Sistema de gerenciamento de pedidos desenvolvido em Java, com funcionalidades de cadastro de produtos, criação de pedidos e geração de arquivos de pedido.
📋 Funcionalidades

Cadastrar produtos — adiciona produtos com nome e preço
Listar produtos — exibe todos os produtos ordenados por preço
Criar pedido — monta um pedido com múltiplos itens e calcula o total automaticamente
Ver pedido — consulta um pedido pelo número
Gerar arquivo do pedido — salva o pedido em um arquivo .txt no diretório escolhido

🛠️ Tecnologias

Java
Git & GitHub

📁 Estrutura do Projeto
MiniFood/
├── src/
│   ├── application/
│   │   └── Program.java       # Menu principal e lógica de navegação
│   └── entities/
│       ├── Product.java       # Entidade produto
│       ├── Order.java         # Entidade pedido
│       ├── OrderItem.java     # Entidade item do pedido
│       └── FileUser.java      # Entidade para geração do arquivo
▶️ Como executar

Clone o repositório:

bashgit clone https://github.com/Ghostsr30/MiniFood.git

Abra o projeto no IntelliJ IDEA ou outra IDE Java
Execute a classe Program.java

📌 Exemplo de uso
Menu:
1) Cadastrar Produto
2) Listar Produtos
3) Criar Pedido
4) Ver Pedido
5) Criar arquivo do pedido
6) Sair
Ao criar um pedido, o sistema solicita o nome do cliente, a data e os itens desejados. Ao final, é possível exportar o pedido como arquivo .txt.

👨‍💻 Autor
Luan Vedovoto — GitHub
