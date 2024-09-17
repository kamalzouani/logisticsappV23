package ma.zyn.app.unit.service.impl.admin.address;

import ma.zyn.app.bean.core.address.City;
import ma.zyn.app.dao.facade.core.address.CityDao;
import ma.zyn.app.service.impl.admin.address.CityAdminServiceImpl;

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
class CityAdminServiceImplTest {

    @Mock
    private CityDao repository;
    private AutoCloseable autoCloseable;
    private CityAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new CityAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllCity() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveCity() {
        // Given
        City toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteCity() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetCityById() {
        // Given
        Long idToRetrieve = 1L; // Example City ID to retrieve
        City expected = new City(); // You need to replace City with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        City result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private City constructSample(int i) {
		City given = new City();
        given.setName("name-"+i);
        given.setPostalCode("postalCode-"+i);
        return given;
    }

}
