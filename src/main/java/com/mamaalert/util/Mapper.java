package com.mamaalert.util;

import com.mamaalert.data.model.*;
import com.mamaalert.dtos.requests.RegisterDriverAdminRequest;
import com.mamaalert.dtos.requests.RegisterHospitalRequest;
import com.mamaalert.dtos.requests.RegisterPatientRequest;
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

    public static DriverAdmin mapRequestToDriverAdmin(RegisterDriverAdminRequest request, PasswordEncoder passwordEncoder) {
        DriverAdmin driverAdmin = new DriverAdmin();
        driverAdmin.setName(request.getName());
        driverAdmin.setBranchLocation(request.getBranch());
        driverAdmin.setAddress(request.getAddress());
        driverAdmin.setPassword(request.getPassword());
        driverAdmin.setEmail(request.getEmail());
        driverAdmin.setRole(Role.DRIVER_ADMIN);
        driverAdmin.setPhoneNumber(request.getPhoneNumber());
        return driverAdmin;
    }

    public static Patient mapRequestToPatient(RegisterPatientRequest request, PasswordEncoder passwordEncoder) {
        Patient patient = new Patient();
        patient.setEmail(request.getEmail());
        patient.setRole(Role.PATIENT);
        patient.setName(request.getName());
        patient.setPhoneNumber(request.getPhoneNumber());
        patient.setPassword(request.getPassword());
        patient.setHospitalId(request.getHospitalId());
        patient.setRelativeNumbers(request.getRelativeNumber());
        return patient;
    }
}
