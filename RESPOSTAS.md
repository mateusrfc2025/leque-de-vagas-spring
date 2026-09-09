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
