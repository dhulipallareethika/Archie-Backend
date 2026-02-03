package com.JavaBackend.archie_backend.service;

import com.JavaBackend.archie_backend.model.Project;
import com.JavaBackend.archie_backend.model.Diagram;
import com.JavaBackend.archie_backend.repository.ProjectRepository;
import com.JavaBackend.archie_backend.repository.DiagramRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final DiagramRepository diagramRepository;

    public ProjectService(ProjectRepository projectRepository, DiagramRepository diagramRepository) {
        this.projectRepository = projectRepository;
        this.diagramRepository = diagramRepository;
    }

    public Map<String, Object> createProject(Project project) {
        if (projectRepository.existsByProjectNameAndUserId(project.getProjectName(), project.getUserId())) {
            throw new RuntimeException("Project name already exists");
        }
        project.setProjectId(null);
        project.setCreatedAt(LocalDateTime.now());
        
        Project saved = projectRepository.save(project);

        Map<String, Object> response = new HashMap<>();
        response.put("projectid", saved.getProjectId());
        response.put("projectname", saved.getProjectName());
        return response;
    }

    // THIS IS THE METHOD YOU WERE MISSING
    public Map<String, Object> getProjectDetailsWithDiagrams(String projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new NoSuchElementException("Project not found: " + projectId));

        List<Diagram> diagrams = diagramRepository.findByProjectIdSummary(projectId);

        List<Map<String, String>> diagramSummary = diagrams.stream()
                .map(d -> Map.of(
                        "diagramid", d.getDiagramId(),
                        "diagramtype", d.getDiagramType()
                ))
                .toList();

        Map<String, Object> response = new HashMap<>();
        response.put("projectid", project.getProjectId());
        response.put("projectname", project.getProjectName());
        response.put("diagrams", diagramSummary);

        return response;
    }

    public List<Map<String, String>> getProjectNamesByUserId(String userId) {
        return projectRepository.findByUserId(userId).stream()
                .map(p -> Map.of(
                        "projectid", p.getProjectId(),
                        "projectname", p.getProjectName()
                )).toList();
    }
}