package com.JavaBackend.archie_backend.model;

import org.springframework.data.annotation.Id;
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

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<ClassModel> classes;


    public static class ClassModel {
        private String className;
        private List<Attribute> attributes;
        private List<Relationship> relationships;

        public String getClassName() { return className; }
        public void setClassName(String className) { this.className = className; }
        public List<Attribute> getAttributes() { return attributes; }
        public void setAttributes(List<Attribute> attributes) { this.attributes = attributes; }
        public List<Relationship> getRelationships() { return relationships; }
        public void setRelationships(List<Relationship> relationships) { this.relationships = relationships; }
    }

    public static class Attribute {
        private String name;
        private String type;
        private Nature nature;
        private boolean required;

        public enum Nature { Identifying, Descriptive, Optional }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getType() { return type; }
        public void setType(String type) { this.type = type; }
        public Nature getNature() { return nature; }
        public void setNature(Nature nature) { this.nature = nature; }
        public boolean isRequired() { return required; }
        public void setRequired(boolean required) { this.required = required; }
    }

    public static class Relationship {
        private String source;
        private String target;
        private Nature nature;
        private Type sourceType;
        private Type targetType;
        private String label;

        public enum Nature { Association, Aggregation, Composition }
        public enum Type { One, Many }

        public String getSource() { return source; }
        public void setSource(String source) { this.source = source; }
        public String getTarget() { return target; }
        public void setTarget(String target) { this.target = target; }
        public Nature getNature() { return nature; }
        public void setNature(Nature nature) { this.nature = nature; }
        public Type getSourceType() { return sourceType; }
        public void setSourceType(Type sourceType) { this.sourceType = sourceType; }
        public Type getTargetType() { return targetType; }
        public void setTargetType(Type targetType) { this.targetType = targetType; }
        public String getLabel() { return label; }
        public void setLabel(String label) { this.label = label; }
    }


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
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
