package ma.zyn.app.unit.service.impl.admin.address;

import ma.zyn.app.bean.core.address.Address;
import ma.zyn.app.dao.facade.core.address.AddressDao;
import ma.zyn.app.service.impl.admin.address.AddressAdminServiceImpl;

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
class AddressAdminServiceImplTest {

    @Mock
    private AddressDao repository;
    private AutoCloseable autoCloseable;
    private AddressAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new AddressAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllAddress() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveAddress() {
        // Given
        Address toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteAddress() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetAddressById() {
        // Given
        Long idToRetrieve = 1L; // Example Address ID to retrieve
        Address expected = new Address(); // You need to replace Address with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Address result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
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
