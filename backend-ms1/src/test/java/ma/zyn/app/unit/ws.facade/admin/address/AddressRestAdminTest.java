package ma.zyn.app.unit.ws.facade.admin.address;

import ma.zyn.app.bean.core.address.Address;
import ma.zyn.app.service.impl.admin.address.AddressAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.address.AddressRestAdmin;
import ma.zyn.app.ws.converter.address.AddressConverter;
import ma.zyn.app.ws.dto.address.AddressDto;
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
public class AddressRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private AddressAdminServiceImpl service;
    @Mock
    private AddressConverter converter;

    @InjectMocks
    private AddressRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllAddressTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<AddressDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<AddressDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveAddressTest() throws Exception {
        // Mock data
        AddressDto requestDto = new AddressDto();
        Address entity = new Address();
        Address saved = new Address();
        AddressDto savedDto = new AddressDto();

        // Mock the converter to return the address object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved address DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<AddressDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        AddressDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved address DTO
        assertEquals(savedDto.getStreet(), responseBody.getStreet());
        assertEquals(savedDto.getNumber(), responseBody.getNumber());
        assertEquals(savedDto.getCity(), responseBody.getCity());
        assertEquals(savedDto.getPostalCode(), responseBody.getPostalCode());
        assertEquals(savedDto.getCountry(), responseBody.getCountry());
    }

}
