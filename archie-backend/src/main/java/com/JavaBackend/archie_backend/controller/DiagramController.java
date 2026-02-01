package com.JavaBackend.archie_backend.controller;

import com.JavaBackend.archie_backend.model.*;
import com.JavaBackend.archie_backend.repository.*;
import com.JavaBackend.archie_backend.service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diagrams")
public class DiagramController {

    @Autowired private DiagramRepository diagramRepository;
    @Autowired private ProjectRepository projectRepository;
    @Autowired private AIService aiService;

    /**
     * 1. GENERATE UML: Uses the classes and relationships stored in the Project table
     */
    @PostMapping("/generate/{projectId}")
    public ResponseEntity<Diagram> generate(@PathVariable String projectId, @RequestParam String type) {
        return projectRepository.findById(projectId).map(project -> {
            // Fetch the classes/relationships you stored in the Project entity
            List<EntityModel> structure = project.getExtractedStructure();
            
            // Call Python AI to convert this structure into PlantUML code
            String generatedUml = aiService.getUmlFromAI(structure, type);

            Diagram diagram = new Diagram(projectId, type, generatedUml, "COMPLETED");
            return ResponseEntity.ok(diagramRepository.save(diagram));
        }).orElse(ResponseEntity.notFound().build());
    }

    /**
     * 2. VIEW PARTICULAR: Get the specific diagram including the full umlCode
     */
    @GetMapping("/{diagramId}")
    public ResponseEntity<Diagram> viewParticular(@PathVariable String diagramId) {
        return diagramRepository.findById(diagramId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * 3. VIEW ALL: Response contains only diagramId and diagramType
     */
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Diagram>> viewAll(@PathVariable String projectId) {
        return ResponseEntity.ok(diagramRepository.findByProjectIdSummary(projectId));
    }
}