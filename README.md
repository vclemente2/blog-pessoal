# Blog Pessoal - Documentação do Projeto

Este é um projeto de blog pessoal em Java que possui endpoints para gerenciar postagens, usuários e temas.

## Endpoints

### Postagens

- **GET /postagens**: Obtém todas as postagens.
- **GET /postagens/{id}**: Obtém uma postagem pelo ID.
- **GET /postagens/titulo/{title}**: Obtém todas as postagens com um título que contenha a palavra-chave especificada.
- **POST /postagens**: Cria uma nova postagem.
- **PUT /postagens**: Atualiza uma postagem existente.
- **DELETE /postagens/{id}**: Exclui uma postagem pelo ID.

### Usuários

- **GET /usuarios**: Obtém todos os usuários cadastrados.
- **POST /usuarios**: Cria um novo usuário.
- **PUT /usuarios**: Atualiza um usuário existente.
- **DELETE /usuarios/{id}**: Exclui um usuário pelo ID.

### Temas

- **GET /temas**: Obtém todos os temas disponíveis.
- **POST /temas**: Cria um novo tema.
- **PUT /temas**: Atualiza um tema existente.
- **DELETE /temas/{id}**: Exclui um tema pelo ID.


## Executando o Projeto

1. Clone este repositório para o seu ambiente local.
2. Configure um banco de dados MySQL e atualize as configurações de conexão no arquivo `application.properties`.
3. Execute o aplicativo Spring Boot.

```shell
mvn spring-boot:run
```

