package com.mamaalert.services.Implementation;

import com.mamaalert.data.model.Patient;
import com.mamaalert.data.repository.PatientRepo;
import com.mamaalert.dtos.requests.RegisterPatientRequest;
import com.mamaalert.dtos.responses.RegisterPatientResponse;
import com.mamaalert.services.HospitalService;
import com.mamaalert.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class HospitalServiceImplementation implements HospitalService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PatientRepo patientRepo;

    @Override
    public RegisterPatientResponse registerPatient(RegisterPatientRequest request) {
        Patient patient = Mapper.mapRequestToPatient(request, passwordEncoder);
        Patient savedPatient = patientRepo.save(patient);

        RegisterPatientResponse response = new RegisterPatientResponse();
        response.setId(savedPatient.getId());
        response.setMessage("Patient has been registered successfully");
        return response;
    }
}
