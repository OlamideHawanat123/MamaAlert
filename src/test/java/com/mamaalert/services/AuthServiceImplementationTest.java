package com.mamaalert.services;

import com.mamaalert.dtos.requests.LoginRequest;
import com.mamaalert.dtos.requests.RegisterSuperAdminRequest;
import com.mamaalert.dtos.responses.LoginResponse;
import com.mamaalert.dtos.responses.RegisterSuperAdminResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AuthServiceImplementationTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private SuperAdminService superAdminService;

    @Test
    public void testThatUserCanRegister(){
        RegisterSuperAdminRequest request = new RegisterSuperAdminRequest();
        request.setEmail("adedortmahan@gmail.com");
        request.setPassword("olamide");
        request.setName("Adewole Adedotun");
        request.setPhoneNumber("09123456785");

        RegisterSuperAdminResponse response = superAdminService.registerSuperAdmin(request);
        assertNotNull(response);
        assertNotNull(response.getId());
    }

    @Test
    public void testThatUserCanLogin(){
        LoginRequest request = new LoginRequest();
        request.setEmail("adedortmahan@gmail.com");
        request.setPassword("olamide");

        LoginResponse response = authService.login(request);
        assertNotNull(response.getToken());
        assertEquals("Login successful", response.getMessage());
    }

}
