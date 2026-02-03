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

    @PostMapping
    public ResponseEntity<Map<String, Object>> createProject(
            @RequestBody Project project,
            @AuthenticationPrincipal UserDetails userDetails) {
        project.setUserId(userDetails.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.createProject(project));
    }

    @GetMapping
    public ResponseEntity<List<Map<String, String>>> getProjects(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(projectService.getProjectNamesByUserId(userDetails.getUsername()));
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Map<String, Object>> getProject(@PathVariable String projectId) {
        // Calls the new method that includes the diagram list
        return ResponseEntity.ok(projectService.getProjectDetailsWithDiagrams(projectId));
    }
}