# Paginação Spring Boot

```java
@GetMapping
    public CoursePageDTO list(@RequestParam(defaultValue = "0") @PositiveOrZero int page,
            @RequestParam(defaultValue = "10") @Positive @Max(100) int pageSize) {
        return courseService.listWithPagination(page, pageSize);
    }
```

## @RequestParam(defaultValue = "0")

Essa anotação é utilizada para mapear um parâmetro da requisição HTTP para um parâmetro do método. O atributo `defaultValue` especifica um valor padrão caso o parâmetro não seja fornecido na requisição.

No exemplo, se o cliente não enviar o parâmetro `page` na URL, o valor padrão será `0`. Isso significa que, se a URL não incluir `?page=X`, o valor de `page` será definido como `0` automaticamente.

## @PositiveOrZero int page

Essa anotação, em conjunto com `@RequestParam`, garante que o valor de `page` seja um número inteiro positivo ou zero. Isso evita que valores negativos sejam enviados, o que poderia causar problemas na lógica da aplicação.

## @RequestParam(defaultValue = "10")

Assim como no caso do `page`, se o parâmetro `pageSize` não for fornecido na URL, o valor padrão será `10`. Isso significa que, se a URL não incluir `?pageSize=X`, o valor de `pageSize` será definido como `10` automaticamente.

## @Positive @Max(100) int pageSize

Aqui, temos duas anotações adicionais:

1. `@Positive`: Garante que o valor de `pageSize` seja um número inteiro positivo (maior que zero).
2. `@Max(100)`: Impõe um limite máximo de `100` para o valor de `pageSize`. Isso evita que valores excessivamente grandes sejam enviados, o que poderia sobrecarregar o sistema.

Portanto, com essas anotações, se a URL não incluir `?page=X&pageSize=Y`, os valores padrão serão utilizados: `page=0` e `pageSize=10`. Além disso, os valores de `page` e `pageSize` serão validados para garantir que sejam números inteiros positivos e que `pageSize` não exceda o limite de `100`.

Essa abordagem simplifica a implementação da paginação na API, pois o cliente não precisa enviar todos os parâmetros em cada requisição. Ela também melhora a segurança, pois impede a entrada de valores inválidos ou potencialmente prejudiciais.

## @Max(100) e sua importância no contexto de segurança da API
A anotação @Max(100) em validação é comumente utilizada em linguagens de programação para impor um limite máximo em um determinado campo de entrada. No contexto de segurança de API, essa anotação desempenha um papel crucial ao garantir que os dados recebidos pela API estejam dentro de limites aceitáveis, prevenindo possíveis ataques de injeção de dados maliciosos.

Ao definir um limite máximo de 100 para um campo específico, a anotação @Max(100) assegura que qualquer entrada recebida pela API não ultrapasse esse valor, evitando assim possíveis sobrecargas, estouro de memória ou tentativas de explorar vulnerabilidades através de dados excessivamente grandes.

Essa prática de validação ajuda a proteger a integridade dos dados, a prevenir ataques de negação de serviço (DoS) e a garantir que a API funcione de forma segura e eficiente. Portanto, ao utilizar a anotação @Max(100) e outras técnicas de validação adequadas, os desenvolvedores podem fortalecer a segurança da API, mitigando potenciais riscos e mantendo a confiabilidade do sistema.

---

# Autor 
## Feito por: `Daniel Penelva de Andrade`