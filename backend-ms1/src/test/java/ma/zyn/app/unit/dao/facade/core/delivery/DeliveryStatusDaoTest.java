package ma.zyn.app.unit.dao.facade.core.delivery;

import ma.zyn.app.bean.core.delivery.DeliveryStatus;
import ma.zyn.app.dao.facade.core.delivery.DeliveryStatusDao;

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
public class DeliveryStatusDaoTest {

@Autowired
    private DeliveryStatusDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        DeliveryStatus entity = new DeliveryStatus();
        entity.setCode(code);
        underTest.save(entity);
        DeliveryStatus loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        DeliveryStatus loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        DeliveryStatus entity = new DeliveryStatus();
        entity.setId(id);
        underTest.save(entity);
        DeliveryStatus loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        DeliveryStatus entity = new DeliveryStatus();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        DeliveryStatus loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<DeliveryStatus> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<DeliveryStatus> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        DeliveryStatus given = constructSample(1);
        DeliveryStatus saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private DeliveryStatus constructSample(int i) {
		DeliveryStatus given = new DeliveryStatus();
        given.setCode("code-"+i);
        given.setLabel("label-"+i);
        given.setStyle("style-"+i);
        return given;
    }

}
