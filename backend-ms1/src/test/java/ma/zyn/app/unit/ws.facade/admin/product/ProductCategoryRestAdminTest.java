package ma.zyn.app.unit.ws.facade.admin.product;

import ma.zyn.app.bean.core.product.ProductCategory;
import ma.zyn.app.service.impl.admin.product.ProductCategoryAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.product.ProductCategoryRestAdmin;
import ma.zyn.app.ws.converter.product.ProductCategoryConverter;
import ma.zyn.app.ws.dto.product.ProductCategoryDto;
import org.aspectj.lang.annotation.Before;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductCategoryRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private ProductCategoryAdminServiceImpl service;
    @Mock
    private ProductCategoryConverter converter;

    @InjectMocks
    private ProductCategoryRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllProductCategoryTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<ProductCategoryDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<ProductCategoryDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveProductCategoryTest() throws Exception {
        // Mock data
        ProductCategoryDto requestDto = new ProductCategoryDto();
        ProductCategory entity = new ProductCategory();
        ProductCategory saved = new ProductCategory();
        ProductCategoryDto savedDto = new ProductCategoryDto();

        // Mock the converter to return the productCategory object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved productCategory DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<ProductCategoryDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        ProductCategoryDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved productCategory DTO
        assertEquals(savedDto.getName(), responseBody.getName());
    }

}
