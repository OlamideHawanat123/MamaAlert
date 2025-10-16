package com.mamaalert.util;

import com.mamaalert.data.model.*;
import com.mamaalert.dtos.requests.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

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
        driverAdmin.setPassword(passwordEncoder.encode(request.getPassword()));
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
        patient.setPassword(passwordEncoder.encode(request.getPassword()));
        patient.setHospitalId(request.getHospitalId());
        patient.setRelativeNumbers(request.getRelativeNumber());
        return patient;
    }

    public static Driver mapRequestToDriver(RegisterDriverRequest request, PasswordEncoder passwordEncoder) {
        Driver driver = new Driver();
        driver.setName(request.getName());
        driver.setBranchLocation(request.getBranch());
        driver.setAddress(request.getAddress());
        driver.setPassword(passwordEncoder.encode(request.getPassword()));
        driver.setEmail(request.getEmail());
        driver.setRole(Role.DRIVER);
        driver.setPhoneNumber(request.getPhoneNumber());
        return driver;
    }

    public static Emergency mapRequestToEmergency(CreateEmergencyRequest request) {
        Emergency emergency = new Emergency();
        emergency.setLatitude(request.getLatitude());
        emergency.setLongitude(request.getLongitude());
        emergency.setCreatedAt(LocalDateTime.now());
        emergency.setStatus(EmergencyStatus.UNRESOLVED);
        return emergency;
    }
}
