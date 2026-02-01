package com.JavaBackend.archie_backend.model;

import java.util.List;

public class EntityModel {
    private String className;
    private List<String> attributes;
    private List<String> methods;
    private List<Relationship> relationships;

    // Standard Getters and Setters
    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }
    public List<String> getAttributes() { return attributes; }
    public void setAttributes(List<String> attributes) { this.attributes = attributes; }
    public List<String> getMethods() { return methods; }
    public void setMethods(List<String> methods) { this.methods = methods; }
    public List<Relationship> getRelationships() { return relationships; }
    public void setRelationships(List<Relationship> relationships) { this.relationships = relationships; }
}

class Relationship {
    private String targetClass;
    private String relationType; // e.g., "One-to-Many", "Inheritance"

    public String getTargetClass() { return targetClass; }
    public void setTargetClass(String targetClass) { this.targetClass = targetClass; }
    public String getRelationType() { return relationType; }
    public void setRelationType(String relationType) { this.relationType = relationType; }
}