package com.JavaBackend.archie_backend.service;

import com.JavaBackend.archie_backend.model.Project;
import com.JavaBackend.archie_backend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    // Create a new project
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    // Get all projects for a specific user
    public List<Project> getProjectsByUserId(String userId) {
        return projectRepository.findByUserId(userId);
    }

    // Get a single project by ID
    public Optional<Project> getProjectById(String projectId) {
        return projectRepository.findById(projectId);
    }

    // Update existing project
    public Project updateProject(String projectId, Project projectDetails) {
        return projectRepository.findById(projectId).map(project -> {
            project.setProjectName(projectDetails.getProjectName());
            project.setBrdText(projectDetails.getBrdText());
            // You can add more fields to update here
            return projectRepository.save(project);
        }).orElseThrow(() -> new RuntimeException("Project not found with id " + projectId));
    }
}