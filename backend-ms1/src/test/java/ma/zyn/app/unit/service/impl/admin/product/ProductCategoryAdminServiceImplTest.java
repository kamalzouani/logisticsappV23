package ma.zyn.app.unit.service.impl.admin.product;

import ma.zyn.app.bean.core.product.ProductCategory;
import ma.zyn.app.dao.facade.core.product.ProductCategoryDao;
import ma.zyn.app.service.impl.admin.product.ProductCategoryAdminServiceImpl;

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
class ProductCategoryAdminServiceImplTest {

    @Mock
    private ProductCategoryDao repository;
    private AutoCloseable autoCloseable;
    private ProductCategoryAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ProductCategoryAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllProductCategory() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveProductCategory() {
        // Given
        ProductCategory toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteProductCategory() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetProductCategoryById() {
        // Given
        Long idToRetrieve = 1L; // Example ProductCategory ID to retrieve
        ProductCategory expected = new ProductCategory(); // You need to replace ProductCategory with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        ProductCategory result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private ProductCategory constructSample(int i) {
		ProductCategory given = new ProductCategory();
        given.setName("name-"+i);
        return given;
    }

}
