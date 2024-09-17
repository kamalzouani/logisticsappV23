package ma.zyn.app.unit.service.impl.admin.order;

import ma.zyn.app.bean.core.order.OrderItem;
import ma.zyn.app.dao.facade.core.order.OrderItemDao;
import ma.zyn.app.service.impl.admin.order.OrderItemAdminServiceImpl;

import ma.zyn.app.bean.core.order.Order ;
import ma.zyn.app.bean.core.product.Product ;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;



import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class OrderItemAdminServiceImplTest {

    @Mock
    private OrderItemDao repository;
    private AutoCloseable autoCloseable;
    private OrderItemAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new OrderItemAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllOrderItem() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveOrderItem() {
        // Given
        OrderItem toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteOrderItem() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetOrderItemById() {
        // Given
        Long idToRetrieve = 1L; // Example OrderItem ID to retrieve
        OrderItem expected = new OrderItem(); // You need to replace OrderItem with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        OrderItem result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private OrderItem constructSample(int i) {
		OrderItem given = new OrderItem();
        given.setQuantity(i);
        given.setProduct(new Product(1L));
        given.setOrder(new Order(1L));
        return given;
    }

}
