package ma.zyn.app.unit.dao.facade.core.delivery;

import ma.zyn.app.bean.core.delivery.Delivery;
import ma.zyn.app.dao.facade.core.delivery.DeliveryDao;

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

import ma.zyn.app.bean.core.address.Address ;
import ma.zyn.app.bean.core.order.Order ;
import ma.zyn.app.bean.core.delivery.DeliveryStatus ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class DeliveryDaoTest {

@Autowired
    private DeliveryDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        Delivery entity = new Delivery();
        entity.setId(id);
        underTest.save(entity);
        Delivery loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Delivery entity = new Delivery();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Delivery loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Delivery> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Delivery> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Delivery given = constructSample(1);
        Delivery saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Delivery constructSample(int i) {
		Delivery given = new Delivery();
        given.setAddress(new Address(1L));
        given.setDeliveryDate(LocalDateTime.now());
        given.setDeliveryStatus(new DeliveryStatus(1L));
        given.setOrder(new Order(1L));
        return given;
    }

}
