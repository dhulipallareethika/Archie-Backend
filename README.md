# Archie-Backend
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
I have created the user signup and user login with jwt authentication.now, i am giving the overview of my project clearly.First, I am doing the sprint-1.my project is about creating the uml diagrams using the ai.in this, the work was divided into three parts.the first part is for frontend using react.the second part is for java backend and the third part is for ai service using python.my part is java backend.as part of that,i need three tables in mongodb database the users table,the projects table and the diagrams table.i already created the user's table with fields userid,username,password,email,created at fileds.in my project the user needs to login into his/her account.after logging in to the account, the user can able to creae the new project.the user can able to see his projects list.the user can nodify the project information.after that, in every project the user can have the options to create the diagrams like class,usecase,sequence,component and erd diagrams.by clicking on the generate diagram button he can able to generate the particular diagram.the diagram was generated in the frontend.the ai service of python needs to give the plantuml code to backend,i which the backend stores the uml code in diagram table and then gives it to react,which will produces the diagrams