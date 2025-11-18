## Sistema de Estoque de Capinhas de Celular em Java

Este projeto tem como objetivo desenvolver um sistema de controle de estoque para uma loja de capinhas de celular, com suporte a diversos modelos e marcas. A aplicação foi construída em Java, com persistência de dados via MySQL.

### Diagrama de Classes

O diagrama abaixo representa as principais entidades do sistema e suas relações:

![alt text](image.png)

#### Estrutura do Projeto

##### `inventory/` – Núcleo do sistema

- `configs/` – Configuração da conexão com o banco de dados.
    
    - `Database.java`: Estabelece a conexão JDBC com o MySQL.
        
- `exceptions/` – Tratamento de erros personalizados.
    
    - `InventoryException.java`: Exceção específica para falhas no inventário.
        
- `interfaces/` – Regras e contratos para o sistema.
    
    - `CrudRepository.java`: Interface padrão para operações CRUD.
        
    - `Filterable.java`: Interface para filtragem por nome e faixa de preço.
        
    - `RunnableTask.java`: Interface para tarefas executáveis, usada em threads.
        
- `models/` – Representação das tabelas do banco em classes Java.
    
    - `Category.java`: Categoria de produtos.
        
    - `Movement.java`: Registro de movimentações de estoque.
        
    - `Product.java`: Produto disponível na loja.
        
    - `Supplier.java`: Fornecedor dos produtos.
        
    - `User.java`: Usuário do sistema com saldo financeiro.
        
- `repository/` – Camada de acesso ao banco de dados.
    
    - `CategoryRepository.java`: Operações com categorias.
        
    - `MovementRepository.java`: Operações com movimentações.
        
    - `ProductRepository.java`: Operações com produtos.
        
    - `SupplierRepository.java`: Operações com fornecedores.
        
    - `UserRepository.java`: Operações com usuários.
        
- `threads/` – Execução de tarefas paralelas.
    
    - `InventoryThread.java`: Verifica e sincroniza o estoque periodicamente.
        
- `utils/` – Funções auxiliares reutilizáveis.
    
    - `GeneralUtils.java`: Utilitários para log, formatação e validações.

##### Outros arquivos

- `lib/` – Bibliotecas externas utilizadas.
    
    - `mysql-connector-j-9.5.0.jar`: Driver JDBC para conexão com MySQL.
        
- `.gitignore` – Arquivo de configuração do Git para ignorar arquivos temporários e compilados.
    
- `DriverCheck.java` – Classe para testar se o driver JDBC está instalado corretamente.
    
- `inventory.sql` – Script para criação e povoamento inicial do banco de dados.
    
- `Main.java` – Ponto de partida da aplicação. Inicializa o sistema e executa testes de conexão e operações básicas.

#### 👥 Equipe responsável

- Elias (el-backendev)
    
- Rafael (cnlopo)
    
- Rodrigo (Rodrigo-Oliveiraa)
    
- Davi (Davyd78)