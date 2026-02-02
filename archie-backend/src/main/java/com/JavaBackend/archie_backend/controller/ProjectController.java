package com.JavaBackend.archie_backend.controller;

import com.JavaBackend.archie_backend.model.Project;
import com.JavaBackend.archie_backend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired 
    private ProjectService projectService;

    // 1. CREATE: Analyzes BRD and saves the project
@PostMapping
public ResponseEntity<?> createProject(@RequestBody Project project, 
                                      @AuthenticationPrincipal UserDetails userDetails) {
    try {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }
        // Take projectName and brdText from input, set userId from Token
        project.setUserId(userDetails.getUsername());
        
        Project savedProject = projectService.createProject(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProject);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
    }
}

    // 2. LIST ALL: Returns names and IDs for the dashboard
    @GetMapping
    public ResponseEntity<List<Map<String, String>>> getProjectsList(@AuthenticationPrincipal UserDetails userDetails) {
        // This extracts projects only for the logged-in User ID
        List<Map<String, String>> projects = projectService.getProjectNamesByUserId(userDetails.getUsername());
        return ResponseEntity.ok(projects);
    }

    // 3. VIEW SINGLE: Gets full details by ID
    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProjectById(@PathVariable String projectId) {
        return ResponseEntity.ok(projectService.getProjectById(projectId));
    }

    // 4. UPDATE: Edits classes/relationships
    @PutMapping("/{projectId}")
    public ResponseEntity<Map<String, String>> editProject(@PathVariable String projectId, @RequestBody Project updates) {
        projectService.updateProjectStructure(projectId, updates);
        return ResponseEntity.ok(Map.of("message", "Project updated successfully"));
    }
}