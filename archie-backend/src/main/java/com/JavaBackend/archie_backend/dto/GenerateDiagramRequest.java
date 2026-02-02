package com.JavaBackend.archie_backend.dto;

import jakarta.validation.constraints.NotBlank;

public class GenerateDiagramRequest {

    @NotBlank
    private String projectId;

    @NotBlank
    private String diagramType;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getDiagramType() {
        return diagramType;
    }

    public void setDiagramType(String diagramType) {
        this.diagramType = diagramType;
    }
}
