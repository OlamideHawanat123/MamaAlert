package com.mamaalert.services.Implementation;

import com.mamaalert.services.SmsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class SmsServiceImplementation implements SmsService {

    @Value("${sendchamp.api.key}")
    private String apiKey;

    private static final String SENDCHAMP_URL = "https://api.sendchamp.com/api/v1/sms/send";
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public boolean sendSms(String to, String message) {
        try {
            String formattedPhone = formatPhoneForSendChamp(to);
            if (formattedPhone == null) {
                System.err.println("❌ Invalid phone number: " + to);
                return false;
            }

            System.out.println("🚀 Sending to Airtel: " + formattedPhone);
            System.out.println("💬 Message: " + message);

            String requestBody = String.format(
                    "to=%s&message=%s&sender_name=%s&route=%s",
                    formattedPhone,
                    URLEncoder.encode(message, StandardCharsets.UTF_8.toString()),
                    "SC-OTP",  // Pre-approved for Airtel
                    "dnd"
            );

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.set("Authorization", "Bearer " + apiKey);
            headers.set("Accept", "application/json");

            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
            ResponseEntity<String> response = restTemplate.exchange(
                    SENDCHAMP_URL, HttpMethod.POST, entity, String.class
            );

            System.out.println("📨 Response: " + response.getBody());

            boolean success = response.getBody() != null &&
                    response.getBody().contains("\"status\":\"success\"");

            if (success) {
                System.out.println("✅ SMS sent to Airtel successfully!");
            } else {
                System.err.println("❌ SMS failed");
            }

            return success;

        } catch (Exception e) {
            System.err.println("💥 SMS Error: " + e.getMessage());
            return false;
        }
    }

    private String formatPhoneForSendChamp(String phone) {
        if (phone == null) return null;
        String digits = phone.replaceAll("[^0-9]", "");

        // Convert 07010080477 → 2347010080477
        if (digits.startsWith("234") && digits.length() == 13) return digits;
        if (digits.startsWith("0") && digits.length() == 11) return "234" + digits.substring(1);
        if (digits.length() == 10) return "234" + digits;

        System.err.println("❌ Invalid format: " + phone + " → " + digits);
        return null;
    }
}