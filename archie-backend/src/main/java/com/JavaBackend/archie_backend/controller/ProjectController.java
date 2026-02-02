package com.JavaBackend.archie_backend.controller;

import com.JavaBackend.archie_backend.model.Project;
import com.JavaBackend.archie_backend.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    // CREATE PROJECT
    @PostMapping
    public ResponseEntity<Project> createProject(
            @RequestBody Project project,
            @AuthenticationPrincipal UserDetails userDetails) {

        project.setUserId(userDetails.getUsername());
        Project savedProject = projectService.createProject(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProject);
    }

    // LIST PROJECTS (Dashboard)
    @GetMapping
    public ResponseEntity<List<Map<String, String>>> getProjects(
            @AuthenticationPrincipal UserDetails userDetails) {

        return ResponseEntity.ok(
                projectService.getProjectNamesByUserId(userDetails.getUsername())
        );
    }

    // VIEW PROJECT
    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProject(@PathVariable String projectId) {
        return ResponseEntity.ok(projectService.getProjectById(projectId));
    }

    // UPDATE PROJECT STRUCTURE
    @PutMapping("/{projectId}")
    public ResponseEntity<Map<String, String>> updateProject(
            @PathVariable String projectId,
            @RequestBody Project updates) {

        projectService.updateProjectStructure(projectId, updates);
        return ResponseEntity.ok(Map.of("message", "Project updated successfully"));
    }
}
