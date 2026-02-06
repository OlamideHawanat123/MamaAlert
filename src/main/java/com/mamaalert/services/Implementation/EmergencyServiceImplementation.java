package com.mamaalert.services.Implementation;

import com.mamaalert.data.model.*;
import com.mamaalert.data.repository.*;
import com.mamaalert.dtos.requests.CreateEmergencyRequest;
import com.mamaalert.dtos.responses.CreateEmergencyResponse;
import com.mamaalert.data.model.EmergencyStatus;
import com.mamaalert.services.EmergencyService;
import com.mamaalert.services.WhatsAppService;
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
    private final WhatsAppService whatsAppService;

    @Override
    public CreateEmergencyResponse createEmergency(CreateEmergencyRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

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
            System.out.println("⚠️ Emergency " + emergency.getId() + " has no patient assigned. Skipping alert.");
            return;
        }

        String emergencyMessage = createEmergencyMessage(patient, emergency);

        System.out.println("🚨 Notifying relatives and drivers for emergency: " + emergency.getId());

        if (patient.getRelativeNumbers() != null && !patient.getRelativeNumbers().isEmpty()) {
            System.out.println("👨‍👩‍👧‍👦 Notifying " + patient.getRelativeNumbers().size() + " relatives");

            patient.getRelativeNumbers().forEach(phoneNumber -> {
                System.out.println("📞 Notifying relative: " + phoneNumber);
                boolean sent = whatsAppService.sendSms(phoneNumber, emergencyMessage);
                System.out.println("📤 Relative notification " + (sent ? "✅" : "❌"));
            });
        } else {
            System.out.println("⚠️ No relatives to notify for patient: " + patient.getName());
        }


        List<Driver> nearbyDrivers = findNearbyDrivers(emergency);
        System.out.println("🚗 Found " + nearbyDrivers.size() + " nearby drivers");

        nearbyDrivers.forEach(driver -> {
            System.out.println("📞 Notifying driver: " + driver.getName() + " - " + driver.getPhoneNumber());
            boolean sent = whatsAppService.sendSms(driver.getPhoneNumber(), emergencyMessage);
            System.out.println("📤 Driver notification " + (sent ? "✅" : "❌"));

            String driverMessage = emergencyMessage + "\n\nPlease proceed to location immediately!";
            whatsAppService.sendSms(driver.getPhoneNumber(), driverMessage);
        });

        System.out.println("🎯 Emergency notification process completed");
    }

    private String createEmergencyMessage(Patient patient, Emergency emergency) {
        return "🚨 EMERGENCY ALERT - MAMAALERT 🚨\n\n" +
                "Patient: " + patient.getName() + "\n" +
                "Location: " + emergency.getLatitude() + ", " + emergency.getLongitude() + "\n" +
                "Time: " + LocalDateTime.now() + "\n\n" +
                "Please respond immediately!";
    }

    private List<Driver> findNearbyDrivers(Emergency emergency) {
        return driverRepo.findAll().stream()
                .filter(driver -> isDriverNearby(emergency, driver))
                .limit(5)
                .toList();
    }

    private boolean isDriverNearby(Emergency emergency, Driver driver) {
        double distance = calculateDistance(
                emergency.getLatitude(),
                emergency.getLongitude(),
                driver.getLatitude(),
                driver.getLongitude()
        );

        boolean isNearby = distance <= 10.0;
        System.out.println("📍 Driver " + driver.getName() + " distance: " +
                String.format("%.2f", distance) + "km - " +
                (isNearby ? "✅ Nearby" : "❌ Too far"));

        return isNearby;
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371;

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
        System.out.println("🔄 Checking " + unresolved.size() + " unresolved emergencies");

        unresolved.forEach(emergency -> {
            if (emergency.getPatient() != null) {
                System.out.println("🔄 Resending alerts for emergency: " + emergency.getId());
                notifyRelativesAndDrivers(emergency);
            } else {
                System.out.println("⚠️ Skipping emergency " + emergency.getId() + " (no patient linked)");
            }
        });
    }
}