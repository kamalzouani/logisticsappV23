package ma.zyn.app.unit.ws.facade.admin.delivery;

import ma.zyn.app.bean.core.delivery.DeliveryStatus;
import ma.zyn.app.service.impl.admin.delivery.DeliveryStatusAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.delivery.DeliveryStatusRestAdmin;
import ma.zyn.app.ws.converter.delivery.DeliveryStatusConverter;
import ma.zyn.app.ws.dto.delivery.DeliveryStatusDto;
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
public class DeliveryStatusRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private DeliveryStatusAdminServiceImpl service;
    @Mock
    private DeliveryStatusConverter converter;

    @InjectMocks
    private DeliveryStatusRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllDeliveryStatusTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<DeliveryStatusDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<DeliveryStatusDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveDeliveryStatusTest() throws Exception {
        // Mock data
        DeliveryStatusDto requestDto = new DeliveryStatusDto();
        DeliveryStatus entity = new DeliveryStatus();
        DeliveryStatus saved = new DeliveryStatus();
        DeliveryStatusDto savedDto = new DeliveryStatusDto();

        // Mock the converter to return the deliveryStatus object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved deliveryStatus DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<DeliveryStatusDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        DeliveryStatusDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved deliveryStatus DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLabel(), responseBody.getLabel());
        assertEquals(savedDto.getStyle(), responseBody.getStyle());
    }

}
