package com.JavaBackend.archie_backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "diagrams")
public class Diagram {
    @Id
    private String diagramId;
    private String projectId;
    private String diagramType; 
    private String umlCode;    
    private String status;      // PENDING, COMPLETED, FAILED

    @CreatedDate
    private LocalDateTime createdAt;

    public Diagram() {}

    public Diagram(String projectId, String diagramType, String umlCode, String status) {
        this.projectId = projectId;
        this.diagramType = diagramType;
        this.umlCode = umlCode;
        this.status = status;
    }

    // Getters and Setters
    public String getDiagramId() { return diagramId; }
    public void setDiagramId(String diagramId) { this.diagramId = diagramId; }
    public String getProjectId() { return projectId; }
    public void setProjectId(String projectId) { this.projectId = projectId; }
    public String getDiagramType() { return diagramType; }
    public void setDiagramType(String diagramType) { this.diagramType = diagramType; }
    public String getUmlCode() { return umlCode; }
    public void setUmlCode(String umlCode) { this.umlCode = umlCode; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}