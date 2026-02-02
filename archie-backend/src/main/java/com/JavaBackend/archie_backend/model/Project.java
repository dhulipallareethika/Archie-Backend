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
    
    // AI Structured data fields
    private List<Object> classes; 
    private List<Object> relationships;

    @CreatedDate
    private String createdAt;

    @LastModifiedDate
    private String updatedAt;

    public Project() {}

    // Getters and Setters
    public String getProjectId() { return projectId; }
    public void setProjectId(String projectId) { this.projectId = projectId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }
    public String getBrdText() { return brdText; }
    public void setBrdText(String brdText) { this.brdText = brdText; }
    public List<Object> getClasses() { return classes; }
    public void setClasses(List<Object> classes) { this.classes = classes; }
    public List<Object> getRelationships() { return relationships; }
    public void setRelationships(List<Object> relationships) { this.relationships = relationships; }
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
}