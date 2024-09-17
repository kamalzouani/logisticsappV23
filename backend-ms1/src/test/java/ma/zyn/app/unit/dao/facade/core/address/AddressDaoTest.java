package ma.zyn.app.unit.dao.facade.core.address;

import ma.zyn.app.bean.core.address.Address;
import ma.zyn.app.dao.facade.core.address.AddressDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.IntStream;
import java.time.LocalDateTime;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class AddressDaoTest {

@Autowired
    private AddressDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        Address entity = new Address();
        entity.setId(id);
        underTest.save(entity);
        Address loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Address entity = new Address();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Address loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Address> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Address> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Address given = constructSample(1);
        Address saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Address constructSample(int i) {
		Address given = new Address();
        given.setStreet("street-"+i);
        given.setNumber("number-"+i);
        given.setCity("city-"+i);
        given.setPostalCode("postalCode-"+i);
        given.setCountry("country-"+i);
        return given;
    }

}
