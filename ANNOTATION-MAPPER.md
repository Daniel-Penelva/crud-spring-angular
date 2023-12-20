# Mapper

O termo "Mapper" é frequentemente utilizado em programação para descrever componentes ou padrões de design que têm a finalidade de mapear ou transformar dados de um formato para outro. Existem diferentes contextos em que o termo "Mapper" pode ser aplicado. Dois dos contextos mais comuns são:

1. **Object Mapper:**
   - **Definição:** Um Object Mapper é uma ferramenta ou componente que mapeia objetos de um tipo para objetos de outro tipo.
   - **Uso Comum:** Muito comum em projetos onde há a necessidade de converter objetos de um formato para outro, como em sistemas de persistência de dados, serviços web, e manipulação de dados em geral.
   - **Exemplo:** Em Java, bibliotecas como MapStruct, ModelMapper e Dozer são exemplos de Object Mappers. Eles ajudam a reduzir a quantidade de código necessário para converter objetos de uma classe para outra.

2. **Data Mapper (ou Object-Relational Mapper - ORM):**
   - **Definição:** Um Data Mapper é um componente que realiza a transferência de dados entre o sistema de gerenciamento de banco de dados (DBMS) e a aplicação, mapeando dados entre objetos da aplicação e registros do banco de dados.
   - **Uso Comum:** Amplamente utilizado em aplicações que precisam persistir dados em um banco de dados relacional, onde os objetos da aplicação precisam ser mapeados para as tabelas do banco de dados e vice-versa.
   - **Exemplo:** Em Java, frameworks ORM como Hibernate, EclipseLink e MyBatis são exemplos de Data Mappers. Eles facilitam a manipulação de dados no banco de dados sem a necessidade de escrever consultas SQL manualmente.

### Object Mapper em Detalhes:

O Object Mapper geralmente envolve a criação de mapeadores (ou transformadores) que definem como os campos de um objeto de origem devem ser mapeados para um objeto de destino. Esses mapeadores podem ser escritos manualmente, mas muitas vezes são gerados automaticamente por ferramentas como MapStruct ou ModelMapper.

Exemplo simpls de uso do MapStruct em Java:

```java
// Definição de DTO e Entidade
public class PersonDto {
    private String name;
    private int age;
    // getters e setters
}

public class PersonEntity {
    private String fullName;
    private int years;
    // getters e setters
}

// Interface do Mapper
@Mapper
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(source = "name", target = "fullName")
    @Mapping(source = "age", target = "years")
    PersonEntity dtoToEntity(PersonDto dto);

    PersonDto entityToDto(PersonEntity entity);
}
```

Neste exemplo, o MapStruct gerará automaticamente a implementação dos métodos `dtoToEntity` e `entityToDto` com base nas regras de mapeamento definidas.

### Data Mapper (ORM) em Detalhes:

O Data Mapper, por sua vez, lida com a persistência de dados em um banco de dados. Ele mapeia objetos de domínio para registros no banco de dados e vice-versa. Essa abordagem ajuda a abstrair a lógica de persistência, permitindo que os desenvolvedores trabalhem com objetos na aplicação, sem se preocupar diretamente com as operações de banco de dados.

Exemplo simples de uso do Hibernate em Java:

```java
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private int age;
    // getters e setters
}

// Uso do Hibernate para persistir e recuperar objetos
Session session = sessionFactory.openSession();
Transaction transaction = session.beginTransaction();

// Persistindo uma nova pessoa no banco de dados
Person newPerson = new Person();
newPerson.setName("Alice");
newPerson.setAge(30);
session.save(newPerson);

// Recuperando uma pessoa do banco de dados
Person retrievedPerson = session.get(Person.class, 1L);

transaction.commit();
session.close();
```

No exemplo acima, a classe `Person` é uma entidade gerenciada pelo Hibernate. O Hibernate cuida automaticamente da geração de SQL, persistência e recuperação de objetos no banco de dados.

Em resumo, mappers desempenham um papel crucial na transformação e transferência de dados em aplicações. Seja mapeando objetos entre camadas da aplicação, convertendo dados para representações diferentes, ou facilitando a interação entre a aplicação e o banco de dados, o uso de mappers ajuda a manter o código mais modular, legível e eficiente.

# Autor
## Feito por: `Daniel Penelva de Andrade`