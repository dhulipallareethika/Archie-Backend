package com.JavaBackend.archie_backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "diagrams")
public class Diagram {

    @Id
    private String diagramId;

    private String projectId;
    private String diagramType;
    private DiagramCode umlCode;
    private String status;

    private LocalDateTime createdAt;

    public static class DiagramCode {

        private String type;   
        private String code;   

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

    public String getDiagramId() { return diagramId; }
    public void setDiagramId(String diagramId) { this.diagramId = diagramId; }
    public String getProjectId() { return projectId; }
    public void setProjectId(String projectId) { this.projectId = projectId; }
    public String getDiagramType() { return diagramType; }
    public void setDiagramType(String diagramType) { this.diagramType = diagramType; }
    public DiagramCode getUmlCode() { return umlCode; }
    public void setUmlCode(DiagramCode umlCode) { this.umlCode = umlCode; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
