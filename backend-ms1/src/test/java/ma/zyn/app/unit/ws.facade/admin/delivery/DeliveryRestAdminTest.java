package ma.zyn.app.unit.ws.facade.admin.delivery;

import ma.zyn.app.bean.core.delivery.Delivery;
import ma.zyn.app.service.impl.admin.delivery.DeliveryAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.delivery.DeliveryRestAdmin;
import ma.zyn.app.ws.converter.delivery.DeliveryConverter;
import ma.zyn.app.ws.dto.delivery.DeliveryDto;
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
public class DeliveryRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private DeliveryAdminServiceImpl service;
    @Mock
    private DeliveryConverter converter;

    @InjectMocks
    private DeliveryRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllDeliveryTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<DeliveryDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<DeliveryDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveDeliveryTest() throws Exception {
        // Mock data
        DeliveryDto requestDto = new DeliveryDto();
        Delivery entity = new Delivery();
        Delivery saved = new Delivery();
        DeliveryDto savedDto = new DeliveryDto();

        // Mock the converter to return the delivery object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved delivery DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<DeliveryDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        DeliveryDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved delivery DTO
        assertEquals(savedDto.getDeliveryDate(), responseBody.getDeliveryDate());
    }

}
