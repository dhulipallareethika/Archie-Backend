package com.JavaBackend.archie_backend.repository;

import com.JavaBackend.archie_backend.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProjectRepository extends MongoRepository<Project, String> {
    List<Project> findByUserId(String userId);
    boolean existsByProjectNameAndUserId(String projectName, String userId);
}
