package com.mamaalert.services;

import com.mamaalert.dtos.requests.RegisterDriverRequest;
import com.mamaalert.dtos.responses.RegisterDriverResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DriverAdminServiceImplementationTest {
    @Autowired
    private DriverAdminService driverAdminService;

    @Test
    public void testThatDriverAdminCanRegisterDriver(){
        RegisterDriverRequest request = new RegisterDriverRequest();
        request.setName("Olamide");
        request.setEmail("divinejmeercy@gmail.com");
        request.setPassword("ola@gmail.com");
        request.setPhoneNumber("08094564448489");
        request.setAddress("3, Ajegunle street");
        request.setBranch("Ikeja");

        RegisterDriverResponse response = driverAdminService.registerDriver(request);
        assertNotNull(response.getId());
        assertEquals("Driver has been registered successfully", response.getMessage());
    }
}
