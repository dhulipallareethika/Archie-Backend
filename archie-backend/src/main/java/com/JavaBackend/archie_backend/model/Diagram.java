package com.JavaBackend.archie_backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "diagrams")
public class Diagram {
    @Id
    private String diagramId;
    private String projectId;
    private String diagramType; 
    private DiagramCode umlcode;
    private String status;

    public static class DiagramCode {
        private String type; // Will be "plantuml" or "mermaid"
        private String code; // The actual UML string

        public DiagramCode() {}
        public DiagramCode(String type, String code) {
            this.type = type;
            this.code = code;
        }

        public String getType() { return type; }
        public void setType(String type) { this.type = type; }
        public String getCode() { return code; }
        public void setCode(String code) { this.code = code; }
    }

    // Getters and Setters
    public String getDiagramId() { return diagramId; }
    public void setDiagramId(String diagramId) { this.diagramId = diagramId; }
    public String getProjectId() { return projectId; }
    public void setProjectId(String projectId) { this.projectId = projectId; }
    public String getDiagramType() { return diagramType; }
    public void setDiagramType(String diagramType) { this.diagramType = diagramType; }
    public DiagramCode getUmlcode() { return umlcode; }
    public void setUmlcode(DiagramCode umlcode) { this.umlcode = umlcode; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}