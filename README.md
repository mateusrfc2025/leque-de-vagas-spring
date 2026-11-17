# Leque de Vagas — a API

Projeto do curso **Introdução ao Spring Boot** (NickDev). É o outro lado do
mural de vagas: o programa que guarda as vagas, as empresas e as pessoas, e as
entrega para quem pedir.

A tela que consome esta API é
[lequedevagas-web](https://github.com/Um-Leque-de-Tecnologia/lequedevagas-web).

## Rodar

Antes da primeira vez, configure o banco (seção **O banco**, logo abaixo).

```bash
./mvnw spring-boot:run     # Mac e Linux
mvnw.cmd spring-boot:run   # Windows
```

Precisa de **JDK 21**. Sobe em <http://localhost:8080> — procure a linha
`Tomcat started on port 8080` no log.

Depois de subir, abra o `requisicoes.http` e clique em **Send Request** em cima
de cada chamada (IntelliJ roda direto; no VS Code, instale a extensão REST Client).

## O banco

Os dados ficam num PostgreSQL no Supabase e sobrevivem ao reinício da aplicação.

1. Crie um projeto em <https://supabase.com/dashboard>, região **South America (São Paulo)**.
   Copie a senha do banco na hora: ela não aparece de novo.
2. No projeto, clique em **Connect → Session pooler** e anote host, porta (5432),
   database (`postgres`) e user (formato `postgres.<referência-do-projeto>`).
   Use o pooler, não a conexão direta, que pode falhar em redes só IPv4.
3. Copie o `.env.example` para `.env` (na raiz, ao lado do `pom.xml`) e preencha
   `DB_URL`, `DB_USER` e `DB_PASSWORD`. O `.env` está no `.gitignore` e **nunca**
   vai para o GitHub. Se a senha vazar, troque-a no Supabase.
4. `spring.jpa.hibernate.ddl-auto=update`: cria as tabelas e colunas que faltam e
   nunca apaga dados, então o que foi gravado sobrevive ao reinício.

Na subida, os scripts `dados/empresas.sql`, `dados/vagas.sql` e `dados/pessoas.sql`
carregam os dados iniciais só se eles ainda não existirem, então nada se duplica.

### Medição do SQL (frente 4, aula 05)

Medido com a carga inicial (12 vagas), contando os comandos que aparecem no log
como `Hibernate:`.

| Chamada                                          | SELECTs | Tem `where`? | Linhas lidas do banco | Linhas devolvidas |
| ------------------------------------------------ | ------- | ------------ | --------------------- | ----------------- |
| `GET /vagas/busca?area=Dados`                    | 1       | não          | 12                    | 3                 |
| `GET /vagas/busca?area=Front-end&aceitaIniciante=true` | 1 | não          | 12                    | 3                 |
| `GET /vagas/busca?area=Design`                   | 1       | não          | 12                    | 0                 |
| `GET /estatisticas`                              | 1       | não          | 12                    | 4 números         |

Nas quatro chamadas o banco devolve **todas** as vagas (`findAll()` não tem
`where`) e quem filtra ou conta é o Java, com `stream`. Funciona com 12 vagas, mas
com 100 mil o banco mandaria tudo pela rede para devolver três. A correção
(consulta com `where` e contagem no banco) fica para a aula 08.

## As quatro frentes

| Aluno          | Frente | Recurso | Arquivos |
|----------------| --- | --- | --- |
| Lucas Ramalho  | 1 | Vaga | `Vaga`, `VagaRepository`, `VagaService`, `VagaController` |
| Mateus Ramalho | 2 | Empresa | `Empresa`, `EmpresaRepository`, `EmpresaService`, `EmpresaController` |
| Mateus Ramalho | 3 | Pessoa | `Pessoa`, `PessoaRepository`, `PessoaService`, `PessoaController` |
| Lucas Ramalho  | 4 | Busca e números | `BuscaService`, `BuscaController`, `EstatisticasService`, `EstatisticasController` |

## Estrutura

```
requisicoes.http                 todas as chamadas, com o status esperado
.env.example                     modelo do .env (sem senha)
pom.xml                          Spring Boot 3.5.6, Java 21, Data JPA, PostgreSQL
src/main/
├── java/br/edu/faculdade/vagas/
│   ├── VagasApplication.java
│   ├── Vaga.java                frente 1 · @Entity, id Long gerado pelo banco
│   ├── VagaRepository.java      JpaRepository<Vaga, Long>
│   ├── VagaService.java
│   ├── VagaController.java
│   ├── Empresa*.java            frente 2 · identificada pelo slug (único)
│   ├── Pessoa*.java             frente 3 · e-mail único, repetido vira 409
│   ├── BuscaService.java        frente 4 · filtra em Java (aula 08 corrige)
│   ├── EstatisticasService.java frente 4 · conta em Java (aula 08 corrige)
│   ├── RecursoJaExiste.java     vira 409
│   └── TratadorDeErros.java     Problem Details para 400 e 409
└── resources/
    ├── application.properties
    └── dados/                   empresas.sql, vagas.sql, pessoas.sql
```

## O que foi aprendido na Frente 1 — Lucas Ramalho

- Aula 02: CRUD completo de vagas (GET, POST, PUT e DELETE) com `ResponseEntity`,
  `201 Created` + `Location`, `204 No Content` e `404 Not Found`.
- Aula 05: a vaga saiu da lista em memória e foi para o PostgreSQL com JPA.
  O id deixou de ser `UUID` (String) e passou a ser `Long`, numerado pelo banco.
  Um id que não é número (`/vagas/abc`) devolve 400 em Problem Details.

## Licença

Material didático do NickDev. Use para estudar, cite quando reaproveitar.