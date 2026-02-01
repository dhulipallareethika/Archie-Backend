package com.JavaBackend.archie_backend.service;

import com.JavaBackend.archie_backend.model.*;
import com.JavaBackend.archie_backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DiagramService {

    @Autowired private DiagramRepository diagramRepository;
    @Autowired private ProjectRepository projectRepository;
    @Autowired private AIService aiService;

    public Diagram generateAndSaveDiagram(String projectId, String type) {
        // 1. Fetch Project to get BRD and Structure
        Project project = projectRepository.findById(projectId)
            .orElseThrow(() -> new RuntimeException("Project not found"));

        try {
            // 2. Call AI Service using the extracted classes and relationships
            String umlCode = aiService.getUmlFromAI(project.getExtractedStructure(), type);

            // 3. Create and Save Diagram
            Diagram diagram = new Diagram(projectId, type, umlCode, "COMPLETED");
            return diagramRepository.save(diagram);
        } catch (Exception e) {
            // Handle AI service failure
            Diagram failedDiagram = new Diagram(projectId, type, null, "FAILED");
            return diagramRepository.save(failedDiagram);
        }
    }

    public Diagram getDiagramDetails(String diagramId) {
        return diagramRepository.findById(diagramId).orElse(null);
    }

    public List<Diagram> getProjectDiagramList(String projectId) {
        return diagramRepository.findByProjectIdSummary(projectId);
    }
}