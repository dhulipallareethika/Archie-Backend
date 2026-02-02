package com.JavaBackend.archie_backend.service;

import com.JavaBackend.archie_backend.model.Diagram;
import com.JavaBackend.archie_backend.repository.DiagramRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DiagramService {

    private final DiagramRepository diagramRepository;

    public DiagramService(DiagramRepository diagramRepository) {
        this.diagramRepository = diagramRepository;
    }

    public Diagram generateAndSave(String projectId, String diagramType) {

        String umlType = "plantuml";
        String umlCode = "@startuml\nclass User\n@enduml";

        Diagram.DiagramCode code = new Diagram.DiagramCode(umlType, umlCode);

        Diagram diagram = new Diagram();
        diagram.setProjectId(projectId);
        diagram.setDiagramType(diagramType);
        diagram.setUmlCode(code);
        diagram.setStatus("COMPLETED");
        diagram.setCreatedAt(LocalDateTime.now());

        return diagramRepository.save(diagram);
    }

    public Diagram getDiagramDetails(String diagramId) {

        return diagramRepository.findById(diagramId)
                .orElseThrow(() ->
                        new RuntimeException("Diagram not found: " + diagramId)
                );
    }
}
