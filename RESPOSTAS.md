# Respostas — Aula 3

## LAB 1

### 1. Qual o status da terceira chamada?

`200 OK`.

### 2. O corpo dela veio vazio. Quem está do outro lado consegue saber por quê?

Não. Um `200` com corpo vazio é ambíguo: não dá para saber se o recurso não existe ou se existe e o resultado está vazio.

### 3. Se essa API alimentasse uma tela, o que a pessoa veria?

Provavelmente uma tela vazia ou sem uma mensagem clara de erro, porque para o cliente a requisição aparentaria ter dado certo.

## LAB 2

### 1. Por que o service devolve Optional em vez de já devolver o 404?

Porque o service trabalha com a regra de negócio e não deve conhecer detalhes de HTTP. O `Optional` informa apenas se o recurso foi encontrado ou não.

### 2. O que `.build()` faz, e por que ele aparece no `notFound` mas não no `ok`?

`build()` finaliza uma resposta sem corpo. No `ok`, o próprio objeto já foi fornecido como corpo da resposta; no `notFound`, não existe corpo, então é necessário finalizar a resposta com `build()`.

### 3. Não apareceu nenhum `if`. Onde foi parar a decisão?

A decisão está dentro do `Optional`. O `map` é executado quando existe um valor e o `orElse` quando não existe.

## LAB 3

### 1. O que acontece se você esquecer `Content-Type: application/json`?

O Spring pode responder `415 Unsupported Media Type`, porque o framework não sabe que o corpo enviado deve ser interpretado como JSON.

### 2. Por que o id é gerado no service, e não recebido do cliente?

Porque a identidade do recurso deve ser definida pela própria aplicação. Se o cliente pudesse escolher o ID, poderiam ocorrer conflitos e sobrescritas.

### 3. Para que serve o `Location`, se o objeto criado já veio no corpo?

Ele informa o endereço do recurso criado. Assim, o cliente não precisa descobrir ou montar a URL sozinho.

## LAB 4

### 1. DELETE 204 → 404 quebra idempotência?

Não. Idempotência significa que repetir a operação produz o mesmo estado final. Depois que o recurso foi removido, ele continua removido, mesmo que a segunda tentativa resulte em `404`.

### 2. Por que DELETE usa 204?

Porque a operação foi realizada com sucesso e não há necessidade de devolver um corpo na resposta.

### 3. POST pode ser repetido com segurança? E PUT?

POST normalmente não é idempotente, porque cada chamada pode criar um novo recurso. PUT é idempotente, porque repetir a mesma alteração mantém o recurso no mesmo estado final.


## Frente 3 · Pessoa: e-mail único e o 500 que vira 409 (aula 05)

- `Pessoa` virou entidade (`@Entity`, `@Id`, `@GeneratedValue`), com `email` em `@Column(nullable = false, unique = true)`.
- `PessoaRepository` agora é uma interface que estende `JpaRepository<Pessoa, Long>`, sem nenhum método escrito à mão.
- As 6 pessoas saíram do Java e moram em `src/main/resources/dados/pessoas.sql`, sem a coluna `id`, para o banco numerar a partir de 1.
- Não há checagem de e-mail repetido no `PessoaService`: quem recusa é o banco, e o `TratadorDeErros` traduz a recusa em **409**.

### ddl-auto

Usamos `spring.jpa.hibernate.ddl-auto=create-drop`. O banco é H2 em memória e some quando a aplicação para, então faz sentido o Hibernate criar as tabelas na subida e apagá-las na descida. Em produção isso nunca seria usado, porque apagaria os dados.

### O que aconteceu com o e-mail repetido

| Situação | Status | Corpo da resposta |
|---|---|---|
| Segundo POST com o mesmo e-mail, **sem** o handler | **500** | Entregava a pilha inteira, com o nome da tabela (`PESSOA`), da coluna (`EMAIL`), da constraint e o SQL do `insert`. |
| Segundo POST com o mesmo e-mail, **com** o handler | **409** em `application/problem+json` | `"O dado conflita com um que já está gravado."`, sem SQL. O detalhe fica só no log. |

O corpo do 500 conta para quem chamou a API como as nossas tabelas são organizadas. Isso é informação que o cliente não precisa e que um atacante aproveitaria. Por isso o handler escreve uma frase própria em vez de repassar `e.getMessage()`.

### Perguntas do PR

**A frente 2 checa antes de gravar e a frente 3 deixa o banco recusar. Qual continua certo quando dois cadastros com o mesmo e-mail chegam no mesmo instante?**
O jeito da frente 3. Com a checagem prévia, os dois pedidos podem passar pelo "esse e-mail já existe?" antes de qualquer um gravar, e os dois entrariam. O `unique` do banco é atômico: um INSERT entra e o outro é recusado. O ideal é combinar os dois: a checagem dá uma mensagem amigável e o `unique` é a garantia.

**Por que o id pulou um número?**
O INSERT recusado já tinha pedido um id ao banco antes de falhar, e id pedido não volta. Por exemplo: a pessoa 7 foi criada, o INSERT repetido consumiu o 8 e a próxima pessoa ficou com o 9. Id identifica, não conta.

## Frente 4 · medição de SQL (aula 05)

| Rota | Comandos SQL | Tem `where`? |
|---|---|---|
| GET /vagas/busca?area=Dados | 1 (`select` de todas as vagas) | Não |
| GET /estatisticas | 1 (`select` de todas as vagas) | Não |
| GET /vagas/1 | 1 (`select` por id) | Sim, `where id=?` |
| DELETE /vagas/12 | 3 (`select` do `existsById`, `select` do `deleteById` e o `delete`) | Sim, todos pelo id |

`/vagas/busca?area=Dados` traz 12 linhas do banco para devolver 3 porque o banco devolve todas as vagas (`findAll` não tem `where`). Quem filtra por área é o Java, com `stream`, depois que as 12 já foram lidas.

Em `/estatisticas`, quem conta também é o Java, não o banco. No `DELETE`, o `deleteById` primeiro carrega a vaga com um `select` e só depois apaga.