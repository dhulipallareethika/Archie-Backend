package com.JavaBackend.archie_backend.controller;

import com.JavaBackend.archie_backend.model.Diagram;
import com.JavaBackend.archie_backend.service.DiagramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/diagrams")
public class DiagramController {

    @Autowired
    private DiagramService diagramService;

    /**
     * POST /api/diagrams/generate
     * Secured by JWT. Generates code via Python AI.
     */
    @PostMapping("/generate")
    public ResponseEntity<Map<String, String>> generate(
            @RequestBody Map<String, Object> request,
            @AuthenticationPrincipal UserDetails userDetails) {
        
        String projectId = (String) request.get("projectId");
        String type = (String) request.get("diagramtype");
        
        // Passing the requirement context (classes/relationships) as text to Python
        String requirementsText = request.get("classes").toString() + " " + request.get("relationships").toString();

        Diagram diagram = diagramService.generateAndSave(projectId, type, requirementsText);
        
        return ResponseEntity.ok(Map.of("umlcode", diagram.getUmlCode()));
    }

    /**
     * GET /api/diagrams/{diagramId}
     * Returns the code (PlantUML, SQL, or YAML).
     */
    @GetMapping("/{diagramId}")
    public ResponseEntity<Map<String, String>> viewParticular(@PathVariable String diagramId) {
        Diagram diagram = diagramService.getDiagramDetails(diagramId);
        if (diagram != null) {
            return ResponseEntity.ok(Map.of("umlcode", diagram.getUmlCode()));
        }
        return ResponseEntity.notFound().build();
    }
}