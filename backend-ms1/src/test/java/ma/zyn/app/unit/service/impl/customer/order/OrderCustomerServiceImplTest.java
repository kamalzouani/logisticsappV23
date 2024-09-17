package ma.zyn.app.unit.service.impl.admin.order;

import ma.zyn.app.bean.core.order.Order;
import ma.zyn.app.dao.facade.core.order.OrderDao;
import ma.zyn.app.service.impl.admin.order.OrderAdminServiceImpl;

import ma.zyn.app.bean.core.order.Order ;
import ma.zyn.app.bean.core.customer.Customer ;
import ma.zyn.app.bean.core.order.OrderItem ;
import ma.zyn.app.bean.core.product.Product ;
import ma.zyn.app.bean.core.order.OrderStatus ;
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
class OrderCustomerServiceImplTest {

    @Mock
    private OrderDao repository;
    private AutoCloseable autoCloseable;
    private OrderCustomerServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new OrderAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllOrder() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveOrder() {
        // Given
        Order toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteOrder() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetOrderById() {
        // Given
        Long idToRetrieve = 1L; // Example Order ID to retrieve
        Order expected = new Order(); // You need to replace Order with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Order result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Order constructSample(int i) {
		Order given = new Order();
        given.setOrderDate(LocalDateTime.now());
        given.setOrderStatus(new OrderStatus(1L));
        given.setCustomer(new Customer(1L));
        List<OrderItem> orderItems = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                OrderItem element = new OrderItem();
                                                element.setId((long)id);
                                                element.setQuantity(1);
                                                element.setProduct(new Product(Long.valueOf(2)));
                                                element.setOrder(new Order(Long.valueOf(3)));
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setOrderItems(orderItems);
        return given;
    }

}
