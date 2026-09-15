# Leque de Vagas — a API

# Leque de Vagas — a API

Projeto de referência do curso **Introdução ao Spring Boot** (NickDev). É o
outro lado do mural de vagas: o programa que guarda as vagas, as empresas e as
pessoas, e as entrega para quem pedir.

A tela que consome esta API é
[lequedevagas-web](https://github.com/Um-Leque-de-Tecnologia/lequedevagas-web),
projeto da disciplina de Introdução ao Next.js. Os dados daqui são os mesmos de
lá — é de propósito.

## Rodar

```bash
./mvnw spring-boot:run     # Mac e Linux
mvnw.cmd spring-boot:run   # Windows
```

Precisa de **JDK 21**. Sobe em <http://localhost:8080> — procure a linha
`Tomcat started on port 8080` no log.

Depois de subir, abra o `requisicoes.http` e clique em **Send Request** em cima
de cada chamada. O IntelliJ roda direto; no VS Code, instale a extensão REST
Client. Nada de Insomnia ou Postman: um arquivo de texto faz o mesmo e vai
versionado no Git, o que Insomnia nenhum faz de graça.

## As quatro frentes

O projeto é de equipe, e cada pessoa carrega uma frente o semestre inteiro. Este
repositório é de referência, então tem as quatro juntas — no da sua equipe, cada
uma é de alguém, e o `git log` é a evidência disso.

| Aluno           | Frente | Recurso | Arquivos |
|-----------------| --- | --- | --- |
| Lucas Ramalho   | 1 | Vaga | `Vaga`, `VagaRepository`, `VagaService`, `VagaController` |
| Mateus Ramalho  | 2 | Empresa | `Empresa`, `EmpresaRepository`, `EmpresaService`, `EmpresaController` |
| Italo Formiga   | 3 | Pessoa | `Pessoa`, `PessoaRepository`, `PessoaService`, `PessoaController` |
| Matheus Moreira | 4 | Busca e números | `EstatisticasService`, `EstatisticasController` — e, na aula 03, `BuscaService` e `BuscaController` |

## Estrutura

```
requisicoes.http                 as chamadas da aula 02, versionadas
idempotencia.http                a evidência da frente 3 (aula 03, comentado)
pom.xml                          Spring Boot 4.1.1, Java 21
src/main/
├── java/br/edu/faculdade/vagas/
│   ├── VagasApplication.java    veio pronta do Initializr
│   ├── Vaga.java                frente 1 · record, sem anotação
│   ├── VagaRepository.java      @Repository — onde o dado está
│   ├── VagaService.java         @Service — onde a decisão mora
│   ├── VagaController.java      @RestController — o único que fala HTTP
│   ├── Empresa*.java            frente 2 · o identificador é o slug
│   ├── Pessoa*.java             frente 3 · a mesma forma, terceira vez
│   ├── EstatisticasService.java frente 4 · lê o repository da frente 1
│   ├── EstatisticasController.java
│   ├── BuscaService.java        frente 4 · aula 03, inteiro comentado
│   └── BuscaController.java     frente 4 · aula 03, inteiro comentado
└── resources/
    └── application.properties
```

Tudo no mesmo pacote da `Application` — é o que o `@ComponentScan` embutido no
`@SpringBootApplication` enxerga. Subpastas por camada também funcionam, desde
que fiquem dentro dele.

## O que foi aprendido na Frente 1 - Aula 2 - Lucas Ramalho

- Implementação do CRUD completo de vagas (GET, POST, PUT e DELETE).
- Geração automática de IDs utilizando `UUID`.
- Uso de `ResponseEntity` para controlar as respostas da API.
- Tratamento de vagas inexistentes com `404 Not Found`.
- Retorno de `201 Created` e cabeçalho `Location` na criação de vagas.
- Retorno de `204 No Content` na exclusão de vagas.
- Utilização de `Optional` para busca segura por ID.

## Licença

Material didático do NickDev. Use para estudar, cite quando reaproveitar.

