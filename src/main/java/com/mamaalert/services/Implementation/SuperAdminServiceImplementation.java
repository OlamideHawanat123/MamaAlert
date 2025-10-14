package com.mamaalert.services.Implementation;

import com.mamaalert.data.model.SuperAdmin;
import com.mamaalert.dtos.requests.RegisterSuperAdminRequest;
import com.mamaalert.dtos.responses.RegisterSuperAdminResponse;
import com.mamaalert.services.SuperAdminService;

public class SuperAdminServiceImplementation implements SuperAdminService {
    @Override
    public RegisterSuperAdminResponse registerSuperAdmin(RegisterSuperAdminRequest request) {
        SuperAdmin superAdmin = new SuperAdmin();
        superAdmin.setName(request.getName());
        superAdmin.setEmail(request.getEmail());
        superAdmin.setPhoneNumber(request.getPhoneNumber());
        superAdmin.setPassword(request.getPassword());

        RegisterSuperAdminResponse response = new RegisterSuperAdminResponse();
        response.setId(superAdmin.getId());
        response.setMessage("You have been registered successfully");
    }
}
