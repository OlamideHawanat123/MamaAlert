package com.mamaalert.services;

import com.mamaalert.dtos.requests.RegisterHospitalRequest;
import com.mamaalert.dtos.requests.RegisterSuperAdminRequest;
import com.mamaalert.dtos.responses.RegisterHospitalResponse;
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

        System.out.println("Mongo URI: " + System.getProperty("spring.data.mongodb.uri"));
        System.out.println("Working directory: " + System.getProperty("user.dir"));

        RegisterSuperAdminRequest request = new RegisterSuperAdminRequest();
        request.setName("Olamide");
        request.setEmail("raheemhawanat@gmail.com");
        request.setPhoneNumber("09138146912");
        request.setPassword("Olaola123");

        RegisterSuperAdminResponse response = superAdminService.registerSuperAdmin(request);
        assertNotNull(response);
        assertEquals("You have been registered successfully", response.getMessage());
    }

    @Test
    public void testThatSuperAdminCanRegisterHospital(){
        RegisterHospitalRequest request = new RegisterHospitalRequest();
        request.setName("Divine Mercy Hospital");
        request.setEmail("divinemercy@gmail.com");
        request.setPassword("ola@gmail.com");
        request.setPhoneNumber("08094564448489");

        RegisterHospitalResponse response = superAdminService.registerHospital(request);
        assertNotNull(response.getId());
        assertEquals("Hospital was registered successfully", response.getMessage());


    }
}
