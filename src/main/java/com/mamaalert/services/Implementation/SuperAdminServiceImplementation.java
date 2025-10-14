package com.mamaalert.services.Implementation;

import com.mamaalert.data.model.SuperAdmin;
import com.mamaalert.data.repository.SuperAdminRepo;
import com.mamaalert.dtos.requests.RegisterHospitalRequest;
import com.mamaalert.dtos.requests.RegisterSuperAdminRequest;
import com.mamaalert.dtos.responses.RegisterHospitalResponse;
import com.mamaalert.dtos.responses.RegisterSuperAdminResponse;
import com.mamaalert.services.SuperAdminService;
import com.mamaalert.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SuperAdminServiceImplementation implements SuperAdminService {
    @Autowired
    private SuperAdminRepo superAdminRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public RegisterSuperAdminResponse registerSuperAdmin(RegisterSuperAdminRequest request) {
        SuperAdmin superAdmin = Mapper.mapRequestToSuperAdmin(request, passwordEncoder);
        SuperAdmin savedSuperAdmin = superAdminRepo.save(superAdmin);

        RegisterSuperAdminResponse response = new RegisterSuperAdminResponse();
        response.setId(superAdmin.getId());
        response.setMessage("You have been registered successfully");
        return response;
    }

    @Override
    public RegisterHospitalResponse registerHospital(RegisterHospitalRequest request) {
        return null;
    }
}
