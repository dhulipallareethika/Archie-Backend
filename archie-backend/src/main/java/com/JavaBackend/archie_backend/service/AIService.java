package com.JavaBackend.archie_backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.Map;
import java.util.List;

@Service
public class AIService {

    private final String BASE_URL = "http://localhost:8000"; 
    private final RestTemplate restTemplate = new RestTemplate();

    public Map<String, Object> analyzeBRD(String brdText) {
        // Correct endpoint for Sprint 1
        String url = BASE_URL + "/analyze-brd";
        Map<String, String> request = Map.of("brdText", brdText);
        
        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);
            if (response.getBody() != null && "SUCCESS".equals(response.getBody().get("status"))) {
                return (Map<String, Object>) response.getBody().get("data");
            }
        } catch (Exception e) {
            System.err.println("Analysis Error: " + e.getMessage());
        }
        return Map.of("classes", List.of(), "relationships", List.of());
    }

    public String getUmlFromAI(String requirements, String diagramType) {
        // Correct endpoint for Sprint 2
        String url = BASE_URL + "/generate";
        Map<String, String> request = Map.of(
            "diagramType", diagramType,
            "requirementsText", requirements
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(request, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(url, entity, Map.class);
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                Map<String, Object> data = (Map<String, Object>) response.getBody().get("data");
                return (String) data.get("diagramCode");
            }
        } catch (Exception e) {
            System.err.println("AI Generation Error: " + e.getMessage());
        }
        return null;
    }
}