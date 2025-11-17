# Java Inventory System

This project is a simple inventory management system built with Java and MySQL.

## Structure
- `configs/`: Database connection setup
- `models/`: Entity classes representing database tables
- `repository/`: Data access layer (CRUD operatio# Sistema de Estoque de Capinha de Celular em Java

Este projeto tem como propósito criar um sistema de estoque para gerenciar capinhas de celular de uma loja, no qual possui vários modelos para atender diferentes aparelhos. O sistema utiliza Java e MySQL.

## Estrutura do sistema

- **`inventory/`:** Todo o sistema do banco de dados
    - **`configs/`:** Configuração de conexão do banco de dados.
        - **`Database.java`:** Estabelece uma conexão JDBC com o banco de dados MySQL, desta forma nas outras partes do sistema podemos chamá-la através de *Connection conn = Database.connect();*.
    - **`exceptions/`:** Definição de exceções.
        - **`InventoryException.java`:** Cria uma exceção personalizada chamada "InventoryException" para representar erros relacionados ao inventário do sistema.
    - **`interfaces/`:** Definição de regras.
        - **`CrudRepository.java`:** Define um padrão para repositórios CRUD, criando um conjunto de regras (contrato) para todas as classes que trabalham com operações do tipo CRUD (create, findByID, update e delete).
        - **`Filterable.java`:** Cria um conjunto de regras (contrato) para as classes que precisam de filtragens - em sua maioria repositórios, trazendo métodos de filtragem de nome (filterByName) e filtragem de preço (filterByPriceRange).
        - **`RunnableTask.java`:** Define uma regra no qual toda classe que a usá-la precisa ter um método *run()* que executa alguma tarefa. Tem a mesma função de *Runnable*, porém deixando claro que é uma tarefa do `inventory\`, evitando confusão com thread e permitindo expansão de métodos caso seja necessário.
    - **`models/`:** Representam as tabelas do banco de dados em forma de classes Java.
        - **`Category.java`:** Representa uma categoria na tabela do banco de dados, modelando-as com atributos como id, name e description.
        - **`Movement.java`:** Representa um movimento na tabela do banco de dados, armazenanando todas as informações sobre uma movimentação de estoque.
        - **`Product.java`:** Representa um produto na tabela do banco de dados, utilizado para armazenar as informações do produto.
        - **`Supplier.java`:** Representa um fornecedor na tabela do banco de dados, armazenando dados como id, name e contactInfo (número de contato).
        - **`User.java`:** Representa um usuário na tabela do banco de dados, armazenando dados como id, name e balance (saldo financeiro).
    - **`repository/`:** Reune as classes responsáveis por realizar operações no banco de dados, utilizando SQL e implementando as interfaces definidas em `interfaces\`. Cada repositório está diretamente associado a uma entidade presente em `models\`.
        - **`CategoryRepository.java`:** Fornece operações CRUD e operações de filtragem para a tabela *Category*.
        - **`MovementRepository.java`:** Fornece operações CRUD e operações de filtragem para a tabela *Movement*.
        - **`ProductRepository.java`:** Fornece operações CRUD e operações de filtragem para a tabela *Product*.
        - **`SupplierRepository.java`:** Fornece operações CRUD e operações de filtragem para a tabela *Supplier*.
        - **`UserRepository.java`:** Fornece operações CRUD e operações de filtragem para a tabela *User*.
    - **`threads/`:** Onde é criado a classe destinada a executar rotinas em threads separadas, sem bloquear a aplicação.
        - **`InventoryThread.java`:** Thread destinada para sincronização de dados do banco de dados e executar tarefas agendadas. Também pode ser utilizada para conferir periodicamente quantidades no estoque, registro de atividades ou ativar movimentações automatizadas.
    - **`utils/`:** Onde é criado a classe que reune diversas funções com intuito de reduzir repetição de código e centralizar as funções auxiliares.
        - **`GeneralUtils.java`:** Conjunto de funções utilitárias para diversas propostas (formatação de data, log, validação de dados).

- **`lib/`:** Armazenamento de biblitoecas externas utilizadas pelo projeto.
        - **`mysql-connector-j-9.5.0.jar`:** Driver JDBC necessário para a comunicação entre Java e MySQL.

- **`.gitignore`:** Arquivo de configuração do Git que define quais arquivos ou pastas devem ser ignorados pelo controle de versão, sendo elas: .class, .bak, .tmp e .temp.

- **`DriverCheck.java`:** Classe auxiliar destinada a testar e validar se o driver JDBC está corretamente instalado e acessível pela aplicação.

- **`inventory.sql`:** Script SQL para criar e inserir dados iniciais nas tabelas do banco de dados de inventário.

- **`Main.java`:** Ponto de entrada da aplicação. Inicializa o sistema e testa a conexão com o banco de dados.

- **`README.md`:** Documento principal de explicação do projeto (relatório).ns)
- `exceptions/`: Custom exception handling
- `threads/`: Background tasks and sync operations
- `utils/`: Helper classes for formatting, validation, etc.
- `lib/`: External libraries (JDBC driver)