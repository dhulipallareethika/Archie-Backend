package com.JavaBackend.archie_backend.service;

import com.JavaBackend.archie_backend.model.Project;
import com.JavaBackend.archie_backend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ProjectService1 {

    @Autowired private ProjectRepository projectRepository;
    @Autowired private AIService aiService;

    private final boolean MOCK_MODE = true; // Set to false to use Python AI

    public Project createProject(Project project) {
        // 1. Set metadata for the project
        project.setCreatedAt(LocalDateTime.now().toString());
        project.setUpdatedAt(LocalDateTime.now().toString());

        if (MOCK_MODE) {
            // Mock data matches the structure the frontend expects for rendering
            project.setClasses(List.of(
                Map.of("name", "User", "attributes", List.of("id", "username", "email")),
                Map.of("name", "Order", "attributes", List.of("orderId", "amount", "status"))
            ));
            project.setRelationships(List.of(
                Map.of("source", "User", "target", "Order", "type", "one-to-many")
            ));
        } else {
            // AI Logic
            Map<String, Object> aiResponse = aiService.analyzeBRD(project.getBrdText());
            
            // Safe casting and fallback to empty lists if AI fails
            project.setClasses((List<Object>) aiResponse.getOrDefault("classes", new ArrayList<>()));
            project.setRelationships((List<Object>) aiResponse.getOrDefault("relationships", new ArrayList<>()));
        }
        
        return projectRepository.save(project);
    }

    public List<Map<String, String>> getProjectNamesByUserId(String userId) {
        // We use LinkedHashMap to ensure the order of keys (projectid, projectname) is consistent
        return projectRepository.findByUserId(userId).stream()
                .map(p -> {
                    Map<String, String> map = new LinkedHashMap<>();
                    map.put("projectid", p.getProjectId());
                    map.put("projectname", p.getProjectName());
                    return map;
                }).toList();
    }

    public Project getProjectById(String projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new NoSuchElementException("Project not found with ID: " + projectId));
    }

    public void updateProjectStructure(String projectId, Project updates) {
        Project project = getProjectById(projectId);
        
        // Update only the architecture fields
        if (updates.getClasses() != null) project.setClasses(updates.getClasses());
        if (updates.getRelationships() != null) project.setRelationships(updates.getRelationships());
        
        project.setUpdatedAt(LocalDateTime.now().toString());
        projectRepository.save(project);
    }
}