package com.mamaalert.services.Implementation;

import com.mamaalert.services.WhatsAppService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.HashMap;
import java.util.Map;

@Service
public class WhatsAppServiceImplementation implements WhatsAppService {

    @Value("${green.api.id.instance}")
    private String instanceId;

    @Value("${green.api.token.instance}")
    private String instanceToken;

    private static final String GREEN_API_BASE_URL = "https://api.green-api.com";
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public boolean sendSms(String to, String message) {
        try {
            String formattedPhone = formatPhoneForWhatsApp(to);
            if (formattedPhone == null) {
                System.err.println("❌ Invalid phone number: " + to);
                return false;
            }

            System.out.println("🚀 Sending to WhatsApp: " + formattedPhone);
            System.out.println("💬 Message: " + message);

            return sendWhatsAppMessage(formattedPhone, message);

        } catch (Exception e) {
            System.err.println("💥 WhatsApp Error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private boolean sendWhatsAppMessage(String chatId, String message) {
        try {
            String url = GREEN_API_BASE_URL + "/waInstance" + instanceId + "/sendMessage/" + instanceToken;

            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("chatId", chatId);
            requestBody.put("message", message);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Accept", "application/json");

            HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    url, HttpMethod.POST, entity, String.class
            );

            System.out.println("📨 Green API Response: " + response.getBody());
            System.out.println("📨 Response Status: " + response.getStatusCode());

            if (response.getStatusCode() == HttpStatus.OK) {
                String responseBody = response.getBody();
                boolean success = responseBody != null && responseBody.contains("\"idMessage\"");

                if (success) {
                    System.out.println("✅ WhatsApp message sent successfully!");
                } else {
                    System.err.println("❌ WhatsApp API returned OK but no message ID");
                }
                return success;
            } else {
                System.err.println("❌ WhatsApp API returned non-OK status: " + response.getStatusCode());
                return false;
            }

        } catch (HttpClientErrorException e) {
            System.err.println("❌ Client Error (4xx): " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            return false;
        } catch (HttpServerErrorException e) {
            System.err.println("❌ Server Error (5xx): " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            return false;
        } catch (Exception e) {
            System.err.println("❌ Unexpected error: " + e.getMessage());
            return false;
        }
    }

    private String formatPhoneForWhatsApp(String phone) {
        if (phone == null) return null;

        String digits = phone.replaceAll("[^0-9]", "");
        if (digits.startsWith("234") && digits.length() == 13) {
            return digits + "@c.us";
        } else if (digits.startsWith("0") && digits.length() == 11) {
            return "234" + digits.substring(1) + "@c.us";
        } else if (digits.length() == 10) {
            return "234" + digits + "@c.us";
        } else if (digits.startsWith("234") && digits.length() > 13) {
            return digits.substring(0, 13) + "@c.us";
        }

        System.err.println("❌ Invalid WhatsApp phone format: " + phone + " → " + digits);
        return null;
    }
}