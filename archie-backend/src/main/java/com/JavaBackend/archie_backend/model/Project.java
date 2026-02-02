package com.JavaBackend.archie_backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "projects")
public class Project {

    @Id
    private String projectId;
    private String userId; 
    private String projectName;
    private String brdText; 
    private String createdAt;
    private String updatedAt;

    // Use the specific class structure instead of List<Object>
    private List<ClassModel> classes;

    public static class ClassModel {
        public String className;
        public List<Attribute> attributes;
        public List<Relationship> relationships;
    }

    public static class Attribute {
        public String name;
        public String type;
        public Nature nature;
        public boolean required;

        public enum Nature { Identifying, Descriptive, Optional }
    }

    public static class Relationship {
        public String source;
        public String target;
        public Nature nature;
        public Type sourcetype;
        public Type targettype;
        public String label;

        public enum Nature { Association, Aggregation, Composition }
        public enum Type { One, Many }
    }

    // --- Getters and Setters ---
    public String getProjectId() { return projectId; }
    public void setProjectId(String projectId) { this.projectId = projectId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }
    public String getBrdText() { return brdText; }
    public void setBrdText(String brdText) { this.brdText = brdText; }
    public List<ClassModel> getClasses() { return classes; }
    public void setClasses(List<ClassModel> classes) { this.classes = classes; }
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
}