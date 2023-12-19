# DTO (Data Transfer Object)

## Classe DTO 

### 1. Encapsulamento de Dados 

É uma classe que vai representar a informação, a requisição que está vindo por parte do navegador e das respostas que estão vindo da aplicação. 
Ou seja, ou Data Transfer Object, é um padrão de projeto utilizado no desenvolvimento de software para transferência de dados entre camadas ou 
componentes de um sistema. O principal objetivo do DTO é encapsular dados e transferi-los de uma parte do sistema para outra, muitas vezes entre 
a camada de apresentação (frontend) e a camada de serviço (backend) ou vice-versa. Esse padrão visa otimizar a comunicação, reduzir o acoplamento 
e melhorar a eficiência na transferência de dados.

DTOs são classes que encapsulam dados. Eles geralmente consistem em atributos que representam os dados a serem transferidos. Esses atributos 
podem ser primitivos, objetos simples ou outros DTOs.

```java
package com.daniel.crudspringangular.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CourseDto {

    private Long _id;

    @NotBlank
    @NotNull
    @Length(min = 3, max = 100)
    private String name;

    @NotNull
    @Length(max = 10)
    @Pattern(regexp = "Back-end|Front-end")
    private String category;

    @NotNull
    @Length(max = 10)
    @Pattern(regexp = "Ativo|Inativo")
    private String status = "Ativo";
}
```

### 2. Transferência de Dados

Esse padrão também economiza o número de requisição por servidor e para simplificar essa requisição. Ou seja, quando se tem um objeto mais 
complexo na sua requisição, por exemplo, com vários campos e objetos alinhados, fica mais facil criar um DTO para receber toda as informações 
de uma só vez e o servidor consome aquela informação e aplica a lógica necessária para poder ler e processar toda aquela informação que está 
vindo no DTO.

DTOs são projetados para transferir apenas os dados necessários entre camadas do sistema, evitando o envio de informações desnecessárias. Isso 
pode resultar em uma comunicação mais eficiente, especialmente em ambientes distribuídos.

### 3. Redução de Acoplamento
O uso de DTOs ajuda a reduzir o acoplamento entre diferentes partes do sistema. Isso ocorre porque as camadas do sistema não precisam conhecer 
diretamente a estrutura interna dos objetos uns dos outros. Em vez disso, elas se comunicam por meio de DTOs, que atuam como contratos de 
comunicação.

### 4. Adaptação a Requisitos de Exibição
Em aplicações web, por exemplo, os DTOs podem ser usados para adaptar os dados conforme necessário para a exibição na interface do usuário. Isso 
permite que a camada de serviço envie apenas os dados relevantes para a interface do usuário, otimizando a largura de banda e melhorando o 
desempenho.

### 5. Proteção dos Dados Internos:
DTOs também ajudam a proteger os dados internos do sistema. Eles permitem que você exponha apenas os dados necessários sem expor a implementação 
interna ou a estrutura dos objetos.

### 6. Mapeamento para Entidades do Domínio:
Em muitos casos, é necessário converter DTOs de ida e volta para as entidades do domínio (por exemplo, entidades JPA). Essa conversão é 
frequentemente realizada por ferramentas como o MapStruct, que simplifica o processo de mapeamento entre DTOs e entidades.

### 7. Padrões e Boas Práticas:
Ao usar DTOs, é importante seguir boas práticas de design e manter o foco nos requisitos específicos do sistema. Evite a tentação de criar DTOs 
muito genéricos que possam se tornar difíceis de manter.

Em resumo, portanto, DTOs são uma ferramenta valiosa para facilitar a transferência eficiente de dados entre diferentes partes de um sistema. Eles promovem uma comunicação mais eficiente, reduzem o acoplamento e permitem adaptações flexíveis às necessidades específicas de cada camada do sistema.

## Classe record

É uma classe java que contém um construtor com todos os campos, não contém os métodos getters e setters. A única forma de obter as informações
das suas propriedades é através do construtor. Uma vez que criou o construtor e instanciou a classe não consegue mais modificar a informação, 
por esse fato a record é uma classe imutável do java.

Portanto, Os records são uma característica introduzida no Java 16 que simplifica a criação de classes para armazenar dados imutáveis. Eles 
foram projetados para serem uma alternativa mais concisa e legível às classes tradicionais que contêm apenas dados (como classes de transferência 
de dados, DTOs, etc.).

Alguns pontos-chave sobre records:

### 1. Declaratividade:
Records são altamente declarativos. Ao contrário das classes tradicionais, onde precisa definir explicitamente construtores, métodos toString(), 
equals(), e hashCode(), os records geram esses métodos automaticamente com base nos campos declarados.

```java
public record CourseDto(
    @JsonProperty("_id") Long id, 
    @NotBlank @NotNull @Length(min = 3, max = 100) String name, 
    @NotNull @Length(max = 10) @Pattern(regexp = "Back-end|Front-end") String category, 
    @NotNull @Length(max = 10) @Pattern(regexp = "Ativo|Inativo") String status) {
    
}
```

### 2. Imutabilidade:
Por padrão, todos os campos em um record são final e o record é imutável. Isso significa que, após a criação de um record, seus valores não podem 
ser alterados. Isso contribui para um modelo de programação mais seguro.

### 3. Métodos Gerados Automaticamente:
Além dos métodos padrão (toString(), equals(), e hashCode()), records geram automaticamente um método copy() que permite criar uma cópia 
modificada do record, alterando alguns ou todos os seus campos.

Por exemplo: 

```java
Course course = new Course(1, "HTML", "Front-end", "Ativo")
Course modifiedCourse = course.withCategory("Front-end");
```

### 4. Sintaxe Concisa:
A sintaxe para definir records é muito concisa. Na maioria dos casos, você pode declarar um record em uma única linha, o que reduz a quantidade 
de código boilerplate.

Compatibilidade com Padrões Existente:
Records podem ser usados em conjunto com padrões existentes de desestruturação, facilitando o trabalho com valores encapsulados.

Por exemplo: 

```java
Course course = new Course(1, "HTML", "Front-end", "Ativo")
String name = course.name();
```

### 5. Maior Clareza no Código:
A utilização de records geralmente resulta em um código mais claro e legível, uma vez que elimina grande parte do código boilerplate associado à 
criação de classes para armazenar dados.

Uso em APIs:
Records são especialmente úteis ao projetar APIs que precisam transmitir dados. Eles facilitam a criação de objetos imutáveis e com uma interface 
clara.

### 6. Herança Limitada:
Records suportam herança, mas a herança é limitada a interfaces e a uma única classe. Eles não podem estender outras classes.

Por exemplo:

```java
public record ExtendedCourse(Long id, String name, String category, String status) implements CourseInterface {}
```

Portanto, os records são uma adição bem-vinda ao Java, proporcionando uma maneira mais concisa e legível de criar classes para armazenar dados 
imutáveis. Eles são especialmente úteis em situações onde a imutabilidade e a clareza do código são prioridades.


## DTO de Request X DTO de Response

DTOs (Data Transfer Objects) são objetos utilizados para transferir dados entre diferentes partes de um sistema, geralmente entre camadas 
distintas, como a camada de controle (controladores), a camada de serviço e a camada de persistência. Dois tipos comuns de DTOs são DTOs de 
Request (entrada) e DTOs de Response (saída).

### DTO de Request:

O DTO de Request, muitas vezes chamado de DTO de entrada, é usado para encapsular os dados recebidos de uma requisição, seja de um cliente HTTP, 
de um serviço externo ou de qualquer fonte de entrada. Eles têm a finalidade de representar os dados necessários para realizar uma operação 
específica. Além disso, os DTOs de Request são úteis para validar e estruturar os dados recebidos antes de serem processados pela camada de 
serviço.

Exemplo de DTO de Request:

```java
public class CreateUserRequest {
    private String username;
    private String password;
    private String email;

    // getters e setters
}
```

No exemplo acima, `CreateUserRequest` é um DTO de Request que pode ser usado para receber os dados necessários para criar um novo usuário.

### DTO de Response:

Os DTOs de Response, também conhecidos como DTOs de saída, são utilizados para encapsular os dados que serão retornados como resposta de uma 
operação. Eles são projetados para representar as informações que são enviadas de volta ao cliente ou consumidor do serviço. A estrutura desses 
DTOs é geralmente adaptada para atender às necessidades da camada de apresentação ou do consumidor, facilitando a transmissão de informações 
específicas sem expor detalhes internos.

Exemplo de DTO de Response:

```java
public class UserResponse {
    private Long userId;
    private String username;
    private String email;

    // getters e setters
}
```

No exemplo acima, `UserResponse` é um DTO de Response que pode ser usado para enviar informações sobre um usuário em resposta a uma operação.

### Avaliação no Projeto:

Ao avaliar o uso de DTOs de Request e Response em um projeto, considere os seguintes pontos:

#### 1. **Separação de Responsabilidades:**
   DTOs ajudam a manter uma separação clara de responsabilidades entre as camadas do sistema. Eles garantem que a camada de serviço não precise 
   conhecer os detalhes da representação de dados nas requisições ou respostas HTTP.

#### 2. **Validação de Dados:**
   Os DTOs de Request são frequentemente usados para validar e estruturar os dados recebidos antes de serem processados pela lógica de negócios. 
   Isso ajuda a garantir a integridade e a consistência dos dados.

#### 3. **Flexibilidade na Apresentação:**
   Os DTOs de Response proporcionam flexibilidade na apresentação dos dados, permitindo que a camada de serviço forneça informações formatadas 
   de maneira específica para o consumidor. Isso é útil em cenários onde diferentes partes do sistema ou clientes podem precisar de representações 
   diferentes dos mesmos dados.

#### 4. **Manutenção e Evolução:**
   O uso de DTOs facilita a manutenção e evolução do sistema, pois as mudanças na representação interna dos dados não afetam diretamente a 
   interface de entrada ou saída.

#### 5. **Documentação Automática:**
   DTOs bem definidos podem ser utilizados por ferramentas de geração de documentação automática (por exemplo, Swagger) para criar documentação 
   precisa e atualizada da API.

#### 6. **Versionamento:**
   DTOs podem ser úteis ao lidar com versionamento de API. Se houver uma mudança na representação dos dados, é possível criar uma nova versão do 
   DTO sem afetar os clientes existentes.

Em resumo, DTOs de Request e Response são ferramentas valiosas para a transferência estruturada de dados entre diferentes partes de um sistema. 
Ao avaliar seu uso em um projeto, considere as necessidades específicas de comunicação entre camadas e a importância de manter uma interface 
clara e flexível.

## Limitação para usar records como entidades JPA (Java Persistence API) 

Vale ressaltar que não se consegue utilizar record ainda como uma entidade por conta das regras do JPA que fala que para ter uma classe como 
entidade é preciso seguir algumas regras, que são: possuir um construtor vazio, ou seja, quando o JPA/Hibernate vai instanciar (buscar informações
da base) e depois vai utilizar os métodos getters e setters para poder capturar e atribuir os dados (ou valor) em cada propriedade.

A limitação para usar records como entidades JPA (Java Persistence API) reside na necessidade de algumas características específicas que records, 
por padrão, não fornecem. 

Algumas razões principais:

### 1. **Imutabilidade:**
   Records são projetados para serem imutáveis por padrão. Isso significa que todos os campos em um record são `final` e não podem ser modificados 
   após a criação do objeto. Entretanto, as entidades JPA geralmente requerem que seus campos sejam mutáveis, pois o framework precisa poder 
   modificar os valores dos campos para refletir as alterações no banco de dados.

### 2. **Construtores Completos:**
   Entidades JPA muitas vezes requerem construtores sem argumentos e construtores que inicializem todos os campos. Os records, por outro lado, 
   geram automaticamente um construtor que apenas inicializa os campos da declaração. Isso pode ser limitante ao tentar usá-los como entidades 
   JPA, que exigem uma flexibilidade maior na inicialização dos campos.

### 3. **Herança Limitada:**
   Records suportam herança, mas com algumas limitações. Entidades JPA frequentemente usam herança, e a limitação de records em relação à herança 
   pode tornar mais difícil ou impossível usá-los em alguns cenários de modelagem de entidades.

### 4. **Comportamento Personalizado:**
   Entidades JPA frequentemente requerem métodos personalizados (por exemplo, para calcular valores derivados) ou lógica específica para o 
   framework de persistência. Records, por sua natureza mais focada na imutabilidade e simplicidade, podem não fornecer a flexibilidade 
   necessária para comportamentos específicos de persistência.

### 5. **Compatibilidade com Frameworks Existentes:**
   Os frameworks de persistência, como o Hibernate (usado frequentemente com JPA), foram originalmente projetados para trabalhar com classes 
   JavaBeans padrão. Embora tenham evoluído para suportar outras formas de classes, alguns recursos e funcionalidades podem ser mais diretamente 
   aplicáveis a classes tradicionais.

Em resumo, embora records sejam uma adição valiosa ao Java e proporcionem simplicidade e clareza em muitos casos, algumas características 
específicas das entidades JPA podem tornar difícil ou impraticável o uso direto de records em certos contextos. Pode ser necessário recorrer a 
classes tradicionais ou adotar estratégias específicas para contornar essas limitações ao usar records como entidades JPA.

# Autor
## Feito por: `Daniel Penelva de Andrade`