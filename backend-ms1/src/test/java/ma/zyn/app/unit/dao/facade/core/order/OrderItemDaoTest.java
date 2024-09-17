package ma.zyn.app.unit.dao.facade.core.order;

import ma.zyn.app.bean.core.order.OrderItem;
import ma.zyn.app.dao.facade.core.order.OrderItemDao;

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

import ma.zyn.app.bean.core.order.Order ;
import ma.zyn.app.bean.core.product.Product ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class OrderItemDaoTest {

@Autowired
    private OrderItemDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        OrderItem entity = new OrderItem();
        entity.setId(id);
        underTest.save(entity);
        OrderItem loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        OrderItem entity = new OrderItem();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        OrderItem loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<OrderItem> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<OrderItem> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        OrderItem given = constructSample(1);
        OrderItem saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private OrderItem constructSample(int i) {
		OrderItem given = new OrderItem();
        given.setQuantity(i);
        given.setProduct(new Product(1L));
        given.setOrder(new Order(1L));
        return given;
    }

}
