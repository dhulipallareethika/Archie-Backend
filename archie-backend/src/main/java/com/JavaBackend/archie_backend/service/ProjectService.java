package com.JavaBackend.archie_backend.service;

import com.JavaBackend.archie_backend.model.Project;
import com.JavaBackend.archie_backend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ProjectService {

    @Autowired private ProjectRepository projectRepository;

    private final boolean MOCK_MODE = true; 

    public Project createProject(Project project) {
        project.setProjectId(null); // Force unique ID
        project.setCreatedAt(LocalDateTime.now().toString());
        project.setUpdatedAt(LocalDateTime.now().toString());

        if (MOCK_MODE) {
            // Create a Mock ClassModel
            Project.ClassModel userClass = new Project.ClassModel();
            userClass.className = "User";

            // Create a Mock Attribute
            Project.Attribute idAttr = new Project.Attribute();
            idAttr.name = "id";
            idAttr.type = "String";
            idAttr.nature = Project.Attribute.Nature.Identifying;
            idAttr.required = true;

            // Create a Mock Relationship
            Project.Relationship rel = new Project.Relationship();
            rel.source = "User";
            rel.target = "Order";
            rel.nature = Project.Relationship.Nature.Association;
            rel.sourcetype = Project.Relationship.Type.One;
            rel.targettype = Project.Relationship.Type.Many;
            rel.label = "places";

            userClass.attributes = List.of(idAttr);
            userClass.relationships = List.of(rel);

            project.setClasses(List.of(userClass));
        }
        
        return projectRepository.save(project);
    }

    public List<Map<String, String>> getProjectNamesByUserId(String userId) {
        if (userId == null) return Collections.emptyList();
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
                .orElseThrow(() -> new NoSuchElementException("Project not found: " + projectId));
    }

    public void updateProjectStructure(String projectId, Project updates) {
        Project project = getProjectById(projectId);
        if (updates.getClasses() != null) project.setClasses(updates.getClasses());
        project.setUpdatedAt(LocalDateTime.now().toString());
        projectRepository.save(project);
    }
}