package com.mamaalert.services.Implementation;

import com.mamaalert.data.model.DriverAdmin;
import com.mamaalert.data.model.Hospital;
import com.mamaalert.data.model.SuperAdmin;
import com.mamaalert.data.repository.DriverAdminRepo;
import com.mamaalert.data.repository.HospitalRepo;
import com.mamaalert.data.repository.SuperAdminRepo;
import com.mamaalert.dtos.requests.RegisterDriverAdminRequest;
import com.mamaalert.dtos.requests.RegisterHospitalRequest;
import com.mamaalert.dtos.requests.RegisterSuperAdminRequest;
import com.mamaalert.dtos.responses.RegisterDriverAdminResponse;
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
    private HospitalRepo hospitalRepo;

    @Autowired
    private DriverAdminRepo driverAdminRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public RegisterSuperAdminResponse registerSuperAdmin(RegisterSuperAdminRequest request) {
        if(superAdminRepo.count() == 1) throw new IllegalArgumentException("You cannot register for the role of a super admin!");

        SuperAdmin superAdmin = Mapper.mapRequestToSuperAdmin(request, passwordEncoder);
        SuperAdmin savedSuperAdmin = superAdminRepo.save(superAdmin);

        RegisterSuperAdminResponse response = new RegisterSuperAdminResponse();
        response.setId(savedSuperAdmin.getId());
        response.setMessage("You have been registered successfully");
        return response;
    }

    @Override
    public RegisterHospitalResponse registerHospital(RegisterHospitalRequest request) {
        Hospital hospital = Mapper.mapRequestToHospital(request, passwordEncoder);
        Hospital savedHospital = hospitalRepo.save(hospital);

        RegisterHospitalResponse response = new RegisterHospitalResponse();
        response.setId(savedHospital.getId());
        response.setMessage("Hospital was registered successfully");
        return response;
    }

    @Override
    public RegisterDriverAdminResponse registerDriverAdmin(RegisterDriverAdminRequest request) {
        DriverAdmin driverAdmin = Mapper.mapRequestToDriverAdmin(request, passwordEncoder);
        DriverAdmin savedDriverAdmin = driverAdminRepo.save(driverAdmin);

        RegisterDriverAdminResponse response = new RegisterDriverAdminResponse();
        response.setId(savedDriverAdmin.getId());
        response.setMessage("Driver Admin has been registered successfully");
        return response;
    }
}
