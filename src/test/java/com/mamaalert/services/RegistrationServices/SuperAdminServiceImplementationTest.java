package com.mamaalert.services.RegistrationServices;

import com.mamaalert.dtos.requests.RegisterDriverAdminRequest;
import com.mamaalert.dtos.requests.RegisterHospitalRequest;
import com.mamaalert.dtos.requests.RegisterSuperAdminRequest;
import com.mamaalert.dtos.responses.RegisterDriverAdminResponse;
import com.mamaalert.dtos.responses.RegisterHospitalResponse;
import com.mamaalert.dtos.responses.RegisterSuperAdminResponse;
import com.mamaalert.services.SuperAdminService;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SuperAdminServiceImplementationTest {
    @Autowired
    private SuperAdminService superAdminService;

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

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
        request.setAddress("3, Ajegunle street");

        RegisterHospitalResponse response = superAdminService.registerHospital(request);
        assertNotNull(response.getId());
        assertEquals("Hospital was registered successfully", response.getMessage());
    }

    @Test
    public void testThatSuperAdminCanRegisterDriverAdmin(){
        RegisterDriverAdminRequest request = new RegisterDriverAdminRequest();
        request.setName("Olaide");
        request.setEmail("sha@gmail.com");
        request.setPassword("Eriife");
        request.setPhoneNumber("09345678383");
        request.setAddress("Saba street");
        request.setBranch("Ikeja");

        RegisterDriverAdminResponse response = superAdminService.registerDriverAdmin(request);
        assertNotNull(response.getId());
        assertEquals("Driver Admin has been registered successfully", response.getMessage());
    }
}
