package com.mamaalert.services;

import com.mamaalert.dtos.requests.RegisterSuperAdminRequest;
import com.mamaalert.dtos.responses.RegisterSuperAdminResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SuperAdminServiceImplementationTest {
    @Autowired
    private SuperAdminService superAdminService;

    @Test
    public void testThatSuperAdminCanRegister(){
        RegisterSuperAdminRequest request = new RegisterSuperAdminRequest();
        request.setName("Olamide");
        request.setEmail("raheemhawanat@gmail.com");
        request.setPhoneNumber("09138146912");
        request.setPassword("Olaola123");

        RegisterSuperAdminResponse response = superAdminService.registerSuperAdmin(request);
        assertEquals("You have been registered successfully", response.getMessage());
    }
}
