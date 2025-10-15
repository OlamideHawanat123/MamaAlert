package com.mamaalert.services.RegistrationServices;

import com.mamaalert.dtos.requests.RegisterPatientRequest;
import com.mamaalert.dtos.responses.RegisterPatientResponse;
import com.mamaalert.services.HospitalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class HospitalServiceImplementationTest {
    @Autowired
    private HospitalService hospitalService;

    @Test
    public void testThatHospitalCanRegisterPatient(){
        RegisterPatientRequest request = new RegisterPatientRequest();
        request.setName("Shulu");
        request.setEmail("divinemercy@gmail.com");
        request.setPassword("ohgtula@gmail.com");
        request.setPhoneNumber("08094564448489");
        request.setAddress("3, Ajegunle street");

        RegisterPatientResponse response = hospitalService.registerPatient(request);
        assertNotNull(response.getId());
        assertEquals("Patient has been registered successfully", response.getMessage());
    }
}
