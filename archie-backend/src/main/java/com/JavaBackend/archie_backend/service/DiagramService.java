package com.JavaBackend.archie_backend.service;

import com.JavaBackend.archie_backend.model.Diagram;
import com.JavaBackend.archie_backend.repository.DiagramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiagramService {

    @Autowired private DiagramRepository diagramRepository;
    @Autowired private AIService aiService; // This service calls your Python AI

    public Diagram generateAndSave(String projectId, String diagramType) {
    try {
        // 1. AI decides the code format (logic remains internal)
        String aiDecidedType = "plantuml"; 
        String generatedCode = "@startuml\nclass User\n@enduml";

        Diagram.DiagramCode codeObj = new Diagram.DiagramCode();
        codeObj.setType(aiDecidedType);
        codeObj.setCode(generatedCode);

        // 2. Create the Diagram object
        Diagram diagram = new Diagram();
        diagram.setProjectId(projectId);
        
        // 3. Set the diagramType EXACTLY as received from the controller
        diagram.setDiagramType(diagramType); 
        
        diagram.setUmlcode(codeObj);
        diagram.setStatus("COMPLETED");

        return diagramRepository.save(diagram);
    } catch (Exception e) {
        Diagram failed = new Diagram();
        failed.setProjectId(projectId);
        failed.setDiagramType(diagramType);
        failed.setStatus("FAILED");
        return diagramRepository.save(failed);
    }
}

    public Diagram getDiagramDetails(String diagramId) {
        return diagramRepository.findById(diagramId).orElse(null);
    }
}