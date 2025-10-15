package com.mamaalert.services;

import com.mamaalert.dtos.requests.LoginRequest;
import com.mamaalert.dtos.responses.LoginResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AuthServiceImplementationTest {
    @Autowired
    private AuthService authService;

    @Test
    public void testThatUserCanLogin(){
        LoginRequest request = new LoginRequest();
        request.setEmail("divinejmeercy@gmail.com");
        request.setPassword("ola@gmail.com");

        LoginResponse response = authService.login(request);
        assertNotNull(response.getToken());
        assertEquals("Login successful", response.getMessage());
    }
}
