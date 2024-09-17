package ma.zyn.app.unit.ws.facade.admin.order;

import ma.zyn.app.bean.core.order.OrderItem;
import ma.zyn.app.service.impl.admin.order.OrderItemAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.order.OrderItemRestAdmin;
import ma.zyn.app.ws.converter.order.OrderItemConverter;
import ma.zyn.app.ws.dto.order.OrderItemDto;
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
public class OrderItemRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private OrderItemAdminServiceImpl service;
    @Mock
    private OrderItemConverter converter;

    @InjectMocks
    private OrderItemRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllOrderItemTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<OrderItemDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<OrderItemDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveOrderItemTest() throws Exception {
        // Mock data
        OrderItemDto requestDto = new OrderItemDto();
        OrderItem entity = new OrderItem();
        OrderItem saved = new OrderItem();
        OrderItemDto savedDto = new OrderItemDto();

        // Mock the converter to return the orderItem object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved orderItem DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<OrderItemDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        OrderItemDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved orderItem DTO
        assertEquals(savedDto.getQuantity(), responseBody.getQuantity());
    }

}
