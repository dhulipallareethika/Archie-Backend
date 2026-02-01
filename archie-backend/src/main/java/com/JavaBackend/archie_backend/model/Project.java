package com.JavaBackend.archie_backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "projects")
public class Project {

    @Id
    private String projectId;
    private String userId; 
    private String projectName;
    private String brdText; 
    
    // This holds the structured data returned by the Python AI
    private List<EntityModel> extractedStructure; 

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    // Constructors
    public Project() {}

    public Project(String userId, String projectName, String brdText) {
        this.userId = userId;
        this.projectName = projectName;
        this.brdText = brdText;
    }

    // Getters and Setters
    public String getProjectId() { return projectId; }
    public void setProjectId(String projectId) { this.projectId = projectId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }

    public String getBrdText() { return brdText; }
    public void setBrdText(String brdText) { this.brdText = brdText; }

    public List<EntityModel> getExtractedStructure() { return extractedStructure; }
    public void setExtractedStructure(List<EntityModel> extractedStructure) { this.extractedStructure = extractedStructure; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}