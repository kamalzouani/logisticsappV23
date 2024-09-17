package ma.zyn.app.unit.dao.facade.core.product;

import ma.zyn.app.bean.core.product.ProductCategory;
import ma.zyn.app.dao.facade.core.product.ProductCategoryDao;

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
public class ProductCategoryDaoTest {

@Autowired
    private ProductCategoryDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        ProductCategory entity = new ProductCategory();
        entity.setId(id);
        underTest.save(entity);
        ProductCategory loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        ProductCategory entity = new ProductCategory();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        ProductCategory loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<ProductCategory> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<ProductCategory> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        ProductCategory given = constructSample(1);
        ProductCategory saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private ProductCategory constructSample(int i) {
		ProductCategory given = new ProductCategory();
        given.setName("name-"+i);
        return given;
    }

}
