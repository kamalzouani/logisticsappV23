package ma.zyn.app.unit.service.impl.admin.delivery;

import ma.zyn.app.bean.core.delivery.DeliveryStatus;
import ma.zyn.app.dao.facade.core.delivery.DeliveryStatusDao;
import ma.zyn.app.service.impl.admin.delivery.DeliveryStatusAdminServiceImpl;

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
class DeliveryStatusAdminServiceImplTest {

    @Mock
    private DeliveryStatusDao repository;
    private AutoCloseable autoCloseable;
    private DeliveryStatusAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new DeliveryStatusAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllDeliveryStatus() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveDeliveryStatus() {
        // Given
        DeliveryStatus toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteDeliveryStatus() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetDeliveryStatusById() {
        // Given
        Long idToRetrieve = 1L; // Example DeliveryStatus ID to retrieve
        DeliveryStatus expected = new DeliveryStatus(); // You need to replace DeliveryStatus with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        DeliveryStatus result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private DeliveryStatus constructSample(int i) {
		DeliveryStatus given = new DeliveryStatus();
        given.setCode("code-"+i);
        given.setLabel("label-"+i);
        given.setStyle("style-"+i);
        return given;
    }

}
