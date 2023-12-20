package com.daniel.crudspringangular.enums.converters;

import java.util.stream.Stream;

import com.daniel.crudspringangular.enums.Category;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)  // é usada para marcar classes como conversores JPA. Quando autoApply é definido como true, significa que o conversor será aplicado automaticamente a todos os atributos do tipo especificado, sem a necessidade de anotar individualmente cada atributo.
public class CategoryConverter implements AttributeConverter<Category, String> {

    @Override
    public String convertToDatabaseColumn(Category category) {

        if (category == null) {
            return null;
        }
        return category.getValue(); // o método getValue() retorna o valor do enumerador.

    }

    @Override
    public Category convertToEntityAttribute(String value) {

        if (value == null) {
            return null;
        }
        return Stream.of(Category.values())
                .filter(c -> c.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}

/** Explicando o método convertToEntityAttribute:
 * 
1. **`@Override`:**
   - Indica que o método a seguir está sobrescrevendo um método de uma interface pai. Este método faz parte da interface `AttributeConverter` em JPA.

2. **`public Category convertToEntityAttribute(String value) {`:**
   - Este método faz parte da interface `AttributeConverter` e é usado para converter um valor armazenado no banco de dados (representado como 
    uma `String`) em um tipo de entidade, neste caso, `Category`.

3. **`if (value == null) { return null; }`:**
   - Verifica se o valor passado como argumento (`value`) é nulo. Se for nulo, significa que o valor armazenado no banco de dados é nulo, então 
    o método retorna `null`.

4. **`return Stream.of(Category.values())...`:**
   - Cria um fluxo (`Stream`) a partir das constantes da enumeração `Category`. A enumeração `Category` deve ser uma enumeração com valores 
    específicos.

5. **`.filter(c -> c.getValue().equals(value))...`:**
   - Usa o método `filter` para encontrar a primeira constante da enumeração cujo valor associado (obtido através do método `getValue()`) é 
    igual ao valor passado como argumento (`value`). Este é o passo em que a correspondência do valor do banco de dados com uma constante da 
    enumeração é feita.

6. **`.findFirst()...`:**
   - Usa o método `findFirst` para obter a primeira constante que atende à condição especificada no filtro. Se nenhuma constante corresponder, 
    será retornado um `Optional.empty()`.

7. **`.orElseThrow(IllegalArgumentException::new);`:**
   - Usa `orElseThrow` para obter o valor encapsulado no `Optional`. Se o `Optional` estiver vazio (nenhuma constante encontrada), uma exceção 
    `IllegalArgumentException` é lançada. Isso indica que o valor do banco de dados não corresponde a nenhuma constante na enumeração `Category`.

Em resumo, este método é usado em uma implementação de `AttributeConverter` para converter uma `String` do banco de dados em um tipo de 
enumeração `Category`. Ele verifica se o valor é nulo, faz uma busca pela correspondência na enumeração usando um fluxo, e lança uma exceção se 
não encontrar uma correspondência válida. Este padrão é frequentemente usado em situações em que você deseja mapear valores específicos do banco 
de dados para enumerações em sua aplicação.
*/