package com.JavaBackend.archie_backend.repository;

import com.JavaBackend.archie_backend.model.Diagram;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface DiagramRepository extends MongoRepository<Diagram, String> {

    @Query(
        value = "{ 'projectId' : ?0 }",
        fields = "{ 'diagramId' : 1, 'diagramType' : 1 }"
    )
    List<Diagram> findByProjectIdSummary(String projectId);
}
