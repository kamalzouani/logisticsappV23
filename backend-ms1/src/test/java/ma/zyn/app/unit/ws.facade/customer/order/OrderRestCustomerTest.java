package ma.zyn.app.unit.ws.facade.customer.order;

import ma.zyn.app.bean.core.order.Order;
import ma.zyn.app.service.impl.customer.order.OrderCustomerServiceImpl;
import ma.zyn.app.ws.facade.customer.order.OrderRestCustomer;
import ma.zyn.app.ws.converter.order.OrderConverter;
import ma.zyn.app.ws.dto.order.OrderDto;
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
public class OrderRestCustomerTest {

    private MockMvc mockMvc;

    @Mock
    private OrderCustomerServiceImpl service;
    @Mock
    private OrderConverter converter;

    @InjectMocks
    private OrderRestCustomer controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllOrderTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<OrderDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<OrderDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveOrderTest() throws Exception {
        // Mock data
        OrderDto requestDto = new OrderDto();
        Order entity = new Order();
        Order saved = new Order();
        OrderDto savedDto = new OrderDto();

        // Mock the converter to return the order object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved order DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<OrderDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        OrderDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved order DTO
        assertEquals(savedDto.getOrderDate(), responseBody.getOrderDate());
    }

}
