package com.mamaalert.services.Implementation;

import com.mamaalert.data.model.*;
import com.mamaalert.data.repository.*;
import com.mamaalert.dtos.requests.CreateEmergencyRequest;
import com.mamaalert.dtos.responses.CreateEmergencyResponse;
import com.mamaalert.data.model.EmergencyStatus;
import com.mamaalert.services.EmergencyService;
import com.mamaalert.services.SmsService;
import com.mamaalert.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmergencyServiceImplementation implements EmergencyService {

    private final EmergencyRepo emergencyRepo;
    private final PatientRepo patientRepo;
    private final DriverRepo driverRepo;
    private final SmsService smsService;

    @Override
    public CreateEmergencyResponse createEmergency(CreateEmergencyRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // Extract the actual User object from the authentication principal
        User user = (User) auth.getPrincipal();
        String email = user.getEmail();

        Patient patient = patientRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Patient not found or not logged in"));

        Emergency emergency = Mapper.mapRequestToEmergency(request);
        emergency.setPatient(patient);

        emergencyRepo.save(emergency);
        notifyRelativesAndDrivers(emergency);

        CreateEmergencyResponse response = new CreateEmergencyResponse();
        response.setMessage("Emergency triggered successfully! Help is on the way!");
        response.setStatus(emergency.getStatus().name());
        response.setTimestamp(LocalDateTime.now());
        return response;
    }


    @Override
    public void resolveEmergency(String emergencyId, String driverEmail) {
        Emergency emergency = emergencyRepo.findById(emergencyId)
                .orElseThrow(() -> new RuntimeException("Emergency not found"));

        Driver driver = driverRepo.findByEmail(driverEmail)
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        emergency.setResolvedBy(driver);
        emergency.setStatus(EmergencyStatus.RESOLVED);
        emergency.setResolvedAt(LocalDateTime.now());
        emergencyRepo.save(emergency);
    }



    private void notifyRelativesAndDrivers(Emergency emergency) {
        Patient patient = emergency.getPatient();
        if (patient == null) {
            // log warning and skip this emergency
            System.out.println("Emergency " + emergency.getId() + " has no patient assigned. Skipping alert.");
            return;
        }

        String message = "🚨 EMERGENCY ALERT 🚨\n"
                + "Patient: " + patient.getName() + "\n"
                + "Location: " + emergency.getLatitude() + ", " + emergency.getLongitude() + "\n"
                + "Time: " + LocalDateTime.now();

        if (patient.getRelativeNumbers() != null) {
            emergency.getPatient().getRelativeNumbers().forEach(phoneNumber -> {
                smsService.sendSms(formatPhoneNumber(phoneNumber), message);
            });
        }

        List<Driver> nearbyDrivers = driverRepo.findAll().stream()
                .filter(driver -> distance(
                        emergency.getLatitude(),
                        emergency.getLongitude(),
                        driver.getLatitude(),
                        driver.getLongitude()) <= 10
                )
                .toList();

        nearbyDrivers.forEach(driver -> smsService.sendSms(driver.getPhoneNumber(), message));
    }

    private String formatPhoneNumber(String phone) {
        if (!phone.startsWith("+")) {
            // Add country code for Nigeria
            if (phone.startsWith("0")) {
                phone = "+234" + phone.substring(1);
            } else {
                phone = "+234" + phone;
            }
        }
        return phone;
    }


    private double distance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Earth radius
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }



    @Scheduled(fixedRate = 600000)
    public void resendAlertsIfUnresolved() {
        List<Emergency> unresolved = emergencyRepo.findByStatus(EmergencyStatus.UNRESOLVED);
        unresolved.forEach(emergency -> {
            if (emergency.getPatient() != null) {
                notifyRelativesAndDrivers(emergency);
            } else {
                System.out.println("Skipping emergency " + emergency.getId() + " (no patient linked)");
            }
        });
    }
}

