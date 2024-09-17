package ma.zyn.app.unit.dao.facade.core.order;

import ma.zyn.app.bean.core.order.OrderStatus;
import ma.zyn.app.dao.facade.core.order.OrderStatusDao;

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
public class OrderStatusDaoTest {

@Autowired
    private OrderStatusDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        OrderStatus entity = new OrderStatus();
        entity.setCode(code);
        underTest.save(entity);
        OrderStatus loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        OrderStatus loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        OrderStatus entity = new OrderStatus();
        entity.setId(id);
        underTest.save(entity);
        OrderStatus loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        OrderStatus entity = new OrderStatus();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        OrderStatus loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<OrderStatus> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<OrderStatus> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        OrderStatus given = constructSample(1);
        OrderStatus saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private OrderStatus constructSample(int i) {
		OrderStatus given = new OrderStatus();
        given.setCode("code-"+i);
        given.setLabel("label-"+i);
        given.setStyle("style-"+i);
        return given;
    }

}
