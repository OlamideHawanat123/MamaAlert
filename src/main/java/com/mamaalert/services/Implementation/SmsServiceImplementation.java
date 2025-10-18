package com.mamaalert.services.Implementation;

import com.mamaalert.services.SmsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class SmsServiceImplementation implements SmsService {

    @Value("${termii.api.key}")
    private String apiKey;

    @Value("${termii.api.url}")
    private String termiiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void sendSms(String to, String message) {
        // ✅ Ensure the phone number is in +234 format
        if (to.startsWith("0")) {
            to = "+234" + to.substring(1);
        } else if (!to.startsWith("+")) {
            to = "+234" + to;
        }

        // Build request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("to", to);
        requestBody.put("from", "MamaAlert"); // your approved sender ID or "TERMII" for now
        requestBody.put("sms", message);
        requestBody.put("type", "plain");
        requestBody.put("channel", "generic");
        requestBody.put("api_key", apiKey);

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Send request
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response =
                restTemplate.postForEntity(termiiUrl, entity, String.class);

        System.out.println("Termii response: " + response.getBody());
    }
}
