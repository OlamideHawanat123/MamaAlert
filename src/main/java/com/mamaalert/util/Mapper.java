package com.mamaalert.util;

import com.mamaalert.data.model.Hospital;
import com.mamaalert.data.model.Role;
import com.mamaalert.data.model.SuperAdmin;
import com.mamaalert.dtos.requests.RegisterHospitalRequest;
import com.mamaalert.dtos.requests.RegisterSuperAdminRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Mapper {
    public static SuperAdmin mapRequestToSuperAdmin(RegisterSuperAdminRequest request, PasswordEncoder passwordEncoder){
        SuperAdmin superAdmin = new SuperAdmin();
        superAdmin.setName(request.getName());
        superAdmin.setEmail(request.getEmail());
        superAdmin.setPhoneNumber(request.getPhoneNumber());
        superAdmin.setPassword(passwordEncoder.encode(request.getPassword()));
        superAdmin.setRole(Role.SUPER_ADMIN);
        return superAdmin;
    }

    public static Hospital mapRequestToHospital(RegisterHospitalRequest request, PasswordEncoder passwordEncoder){
        Hospital hospital =  new Hospital();
        hospital.setName(request.getName());
        hospital.setEmail(request.getEmail());
        hospital.setPhoneNumber(request.getPhoneNumber());
        hospital.setPassword(passwordEncoder.encode(request.getPassword()));
        hospital.setAddress(request.getAddress());
        hospital.setRole(Role.HOSPITAL);
        return hospital;
    }
}
