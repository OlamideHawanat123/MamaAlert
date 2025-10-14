package com.mamaalert.services.Implementation;

import com.mamaalert.data.model.SuperAdmin;
import com.mamaalert.data.repository.SuperAdminRepo;
import com.mamaalert.dtos.requests.RegisterSuperAdminRequest;
import com.mamaalert.dtos.responses.RegisterSuperAdminResponse;
import com.mamaalert.services.SuperAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuperAdminServiceImplementation implements SuperAdminService {
    @Autowired
    private SuperAdminRepo superAdminRepo;

    @Override
    public RegisterSuperAdminResponse registerSuperAdmin(RegisterSuperAdminRequest request) {
        SuperAdmin superAdmin = new SuperAdmin();
        superAdmin.setName(request.getName());
        superAdmin.setEmail(request.getEmail());
        superAdmin.setPhoneNumber(request.getPhoneNumber());
        superAdmin.setPassword(request.getPassword());

        SuperAdmin savedSuperAdmin = superAdminRepo.save(superAdmin);

        RegisterSuperAdminResponse response = new RegisterSuperAdminResponse();
        response.setId(superAdmin.getId());
        response.setMessage("You have been registered successfully");
        return response;
    }
}
