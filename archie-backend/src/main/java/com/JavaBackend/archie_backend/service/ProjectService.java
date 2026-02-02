package com.JavaBackend.archie_backend.service;

import com.JavaBackend.archie_backend.model.Project;
import com.JavaBackend.archie_backend.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    // Temporary mock flag (remove when AI service is ready)
    private static final boolean MOCK_MODE = true;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project createProject(Project project) {

        project.setProjectId(null);
        project.setCreatedAt(LocalDateTime.now());
        project.setUpdatedAt(LocalDateTime.now());

        if (MOCK_MODE) {
            project.setClasses(generateMockClasses());
        }

        return projectRepository.save(project);
    }

    public List<Map<String, String>> getProjectNamesByUserId(String userId) {

        return projectRepository.findByUserId(userId).stream()
                .map(p -> Map.of(
                        "projectid", p.getProjectId(),
                        "projectname", p.getProjectName()
                ))
                .toList();
    }

    public Project getProjectById(String projectId) {

        return projectRepository.findById(projectId)
                .orElseThrow(() ->
                        new NoSuchElementException("Project not found: " + projectId)
                );
    }

    public void updateProjectStructure(String projectId, Project updates) {

        Project project = getProjectById(projectId);

        if (updates.getClasses() != null) {
            project.setClasses(updates.getClasses());
        }

        project.setUpdatedAt(LocalDateTime.now());
        projectRepository.save(project);
    }

    private List<Project.ClassModel> generateMockClasses() {

        Project.ClassModel userClass = new Project.ClassModel();
        userClass.setClassName("User");

        Project.Attribute id = new Project.Attribute();
        id.setName("id");
        id.setType("String");
        id.setNature(Project.Attribute.Nature.Identifying);
        id.setRequired(true);

        Project.Relationship rel = new Project.Relationship();
        rel.setSource("User");
        rel.setTarget("Order");
        rel.setNature(Project.Relationship.Nature.Association);
        rel.setSourceType(Project.Relationship.Type.One);
        rel.setTargetType(Project.Relationship.Type.Many);
        rel.setLabel("places");

        userClass.setAttributes(List.of(id));
        userClass.setRelationships(List.of(rel));

        return List.of(userClass);
    }
}
