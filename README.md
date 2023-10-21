# Documentação da API - Projeto Blog Pessoal

Este é o README para a API REST desenvolvida no Projeto Blog Pessoal.

## Descrição do Projeto

Este projeto é uma API RESTful que oferece funcionalidades relacionadas a um blog pessoal. Ele fornece operações CRUD (Create, Read, Update, Delete) para usuários, temas e postagens.

## Versão da API

A versão atual da API é v0.0.1.

## Documentação Swagger

A documentação da API é fornecida usando o Swagger UI. Você pode acessá-la em:

- **Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

Além disso, você pode acessar os documentos do OpenAPI (OAS 3.0) em:

- **Documentação OpenAPI:** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

## Endpoints

A API possui os seguintes endpoints:

### Usuário (user-controller)

- **DELETE /usuarios/{id}**: Exclui um usuário por ID.
- **GET /usuarios/{id}**: Retorna um usuário por ID.
- **GET /usuarios**: Retorna a lista de todos os usuários.
- **POST /usuarios/logar**: Permite que os usuários façam login.
- **POST /usuarios/cadastrar**: Cadastra um novo usuário.
- **PUT /usuarios/{id}**: Atualiza as informações de um usuário.

### Tema (theme-controller)

- **DELETE /temas/{id}**: Exclui um tema por ID.
- **GET /temas**: Retorna a lista de todos os temas.
- **GET /temas/{id}**: Retorna um tema por ID.
- **GET /temas/descricao/{name}**: Retorna um tema com base no nome.
- **POST /temas**: Cadastra um novo tema.
- **PUT /temas**: Atualiza as informações de um tema.

### Postagem (post-controller)

- **DELETE /postagens/{id}**: Exclui uma postagem por ID.
- **GET /postagens**: Retorna a lista de todas as postagens.
- **GET /postagens/{id}**: Retorna uma postagem por ID.
- **GET /postagens/titulo/{title}**: Retorna uma postagem com base no título.
- **POST /postagens**: Cria uma nova postagem.
- **PUT /postagens**: Atualiza as informações de uma postagem.

## Esquemas (Schemas)

A API usa os seguintes esquemas (schemas) para os dados:

- BodyDataUpdateUserDto
- PostInfoDto
- ThemeInfoDto
- UserInfoDto
- UpdateThemeDto
- UpdatePostDto
- LoginDataDto
- CreateUserDto
- CreateThemeDto
- CreatePostDto

## Servidores

A API é hospedada em:

- **Servidor Local:** [http://localhost:8080](http://localhost:8080)


## Como Baixar e Rodar o Projeto Localmente

Para executar o projeto localmente em sua máquina, siga os passos abaixo:

### Pré-requisitos

- Certifique-se de ter o [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html) instalado em sua máquina. Recomendo o JDK 8 ou superior.

- Você também precisará do [Maven](https://maven.apache.org/download.cgi) instalado. Certifique-se de que o Maven está configurado corretamente em seu sistema.

- É necessário ter o [Git](https://git-scm.com/downloads) instalado para clonar o repositório.

### Passos

1. Abra o terminal ou prompt de comando.

2. Clone o repositório do GitHub para sua máquina usando o seguinte comando:

   ```bash
   git clone https://github.com/vclemente2/blog-pessoal.git
   ```
   
3. Navegue até o diretório do projeto:

	```bash
	cd blog-pessoal
	```

4. Compile o projeto usando o Maven. Execute o seguinte comando na raiz do projeto:

	```bash
	mvn clean install
	``
Isso irá baixar as dependências e compilar o projeto.

5. Agora você pode iniciar a aplicação Spring Boot localmente. Execute o seguinte comando:

	```bash
	mvn spring-boot:run
	```
A aplicação será iniciada e estará acessível em http://localhost:8080.

6. Você também pode acessar a documentação Swagger em http://localhost:8080/swagger-ui.html para explorar e testar os endpoints da API.

Agora você tem o projeto Blog Pessoal em execução localmente em sua máquina. Você pode começar a interagir com a API e fazer as alterações necessárias de acordo com suas necessidades.

Lembre-se de que, dependendo das configurações específicas do seu ambiente de desenvolvimento, alguns detalhes podem variar. Certifique-se de ter os pré-requisitos instalados e configurados corretamente antes de iniciar o projeto.

