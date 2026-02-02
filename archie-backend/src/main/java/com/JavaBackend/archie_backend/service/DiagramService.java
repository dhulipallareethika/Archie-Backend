package com.JavaBackend.archie_backend.service;

import com.JavaBackend.archie_backend.model.Diagram;
import com.JavaBackend.archie_backend.repository.DiagramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiagramService {

    @Autowired private DiagramRepository diagramRepository;
    @Autowired private AIService aiService;

    public Diagram generateAndSave(String projectId, String type, String requirements) {
        try {
            // Call Python AI using the requirements text and diagram type
            String diagramCode = aiService.getUmlFromAI(requirements, type);

            Diagram diagram = new Diagram(projectId, type, diagramCode, "COMPLETED");
            return diagramRepository.save(diagram);
        } catch (Exception e) {
            Diagram failedDiagram = new Diagram(projectId, type, null, "FAILED");
            return diagramRepository.save(failedDiagram);
        }
    }

    public Diagram getDiagramDetails(String diagramId) {
        return diagramRepository.findById(diagramId).orElse(null);
    }
}