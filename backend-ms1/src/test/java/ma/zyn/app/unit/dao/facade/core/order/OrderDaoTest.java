package ma.zyn.app.unit.dao.facade.core.order;

import ma.zyn.app.bean.core.order.Order;
import ma.zyn.app.dao.facade.core.order.OrderDao;

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

import ma.zyn.app.bean.core.customer.Customer ;
import ma.zyn.app.bean.core.order.OrderStatus ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class OrderDaoTest {

@Autowired
    private OrderDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        Order entity = new Order();
        entity.setId(id);
        underTest.save(entity);
        Order loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Order entity = new Order();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Order loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Order> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Order> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Order given = constructSample(1);
        Order saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Order constructSample(int i) {
		Order given = new Order();
        given.setOrderDate(LocalDateTime.now());
        given.setOrderStatus(new OrderStatus(1L));
        given.setCustomer(new Customer(1L));
        return given;
    }

}
