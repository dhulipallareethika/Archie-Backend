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
     * Request body: { "projectId": "...", "diagramtype": "..." }
     */
   @PostMapping("/generate")
public ResponseEntity<Diagram> generate(
        @RequestBody Map<String, String> request, 
        @AuthenticationPrincipal UserDetails userDetails) {
    
    String projectId = request.get("projectId");
    
    // Check for both 'diagramtype' and 'diagramType' to prevent nulls
    String diagramType = request.get("diagramType");
    if (diagramType == null) {
        diagramType = request.get("diagramType");
    }

    // Default value if both are missing
    if (diagramType == null) {
        diagramType = "Unknown Diagram";
    }

    Diagram diagram = diagramService.generateAndSave(projectId, diagramType);
    return ResponseEntity.ok(diagram);
}

  @GetMapping("/{diagramId}")
public ResponseEntity<Diagram> viewParticular(@PathVariable String diagramId) {
    Diagram diagram = diagramService.getDiagramDetails(diagramId);
    if (diagram != null) {
        // Return the full object so you see the ID, Type, and Code for THIS record
        return ResponseEntity.ok(diagram);
    }
    return ResponseEntity.notFound().build();
}
}