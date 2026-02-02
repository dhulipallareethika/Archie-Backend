package com.JavaBackend.archie_backend.controller;

import com.JavaBackend.archie_backend.dto.GenerateDiagramRequest;
import com.JavaBackend.archie_backend.model.Diagram;
import com.JavaBackend.archie_backend.service.DiagramService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/diagrams")
public class DiagramController {

    private final DiagramService diagramService;

    public DiagramController(DiagramService diagramService) {
        this.diagramService = diagramService;
    }

    // GENERATE DIAGRAM
    @PostMapping("/generate")
    public ResponseEntity<Diagram> generateDiagram(
            @RequestBody GenerateDiagramRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {

        Diagram diagram = diagramService.generateAndSave(
                request.getProjectId(),
                request.getDiagramType()
        );

        return ResponseEntity.ok(diagram);
    }

    // VIEW DIAGRAM
    @GetMapping("/{diagramId}")
    public ResponseEntity<Diagram> getDiagram(@PathVariable String diagramId) {

        return ResponseEntity.ok(
                diagramService.getDiagramDetails(diagramId)
        );
    }
}
