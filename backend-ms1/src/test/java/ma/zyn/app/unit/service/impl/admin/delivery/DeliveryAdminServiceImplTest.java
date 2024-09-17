package ma.zyn.app.unit.service.impl.admin.delivery;

import ma.zyn.app.bean.core.delivery.Delivery;
import ma.zyn.app.dao.facade.core.delivery.DeliveryDao;
import ma.zyn.app.service.impl.admin.delivery.DeliveryAdminServiceImpl;

import ma.zyn.app.bean.core.address.Address ;
import ma.zyn.app.bean.core.order.Order ;
import ma.zyn.app.bean.core.delivery.DeliveryStatus ;
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
class DeliveryAdminServiceImplTest {

    @Mock
    private DeliveryDao repository;
    private AutoCloseable autoCloseable;
    private DeliveryAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new DeliveryAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllDelivery() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveDelivery() {
        // Given
        Delivery toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteDelivery() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetDeliveryById() {
        // Given
        Long idToRetrieve = 1L; // Example Delivery ID to retrieve
        Delivery expected = new Delivery(); // You need to replace Delivery with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Delivery result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
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
