package com.JavaBackend.archie_backend.service;

import com.JavaBackend.archie_backend.model.EntityModel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
public class AIService {

    private final String PYTHON_URL = "http://localhost:5000/generate-plantuml";

    public String getUmlFromAI(List<EntityModel> structure, String type) {
        RestTemplate restTemplate = new RestTemplate();

        // Prepare the payload for Python
        Map<String, Object> request = new HashMap<>();
        request.put("structure", structure);
        request.put("diagramType", type);

        // Send to Python and get the PlantUML string back
        try {
            Map<String, String> response = restTemplate.postForObject(PYTHON_URL, request, Map.class);
            return response.get("plantuml");
        } catch (Exception e) {
            return "' Error: AI Service unreachable\n@startuml\nclass Error\n@enduml";
        }
    }
}