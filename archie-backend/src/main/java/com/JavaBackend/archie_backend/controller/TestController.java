package com.JavaBackend.archie_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/test-db")
    public String testDb() {
        try {
            // This attempts to reach out to Atlas and get the DB name
            String dbName = mongoTemplate.getDb().getName();
            return "🚀 Success! Your Spring Boot app is connected to Atlas database: " + dbName;
        } catch (Exception e) {
            // If there's a credential or whitelist issue, it will show up here
            return "❌ Connection failed! Error: " + e.getMessage();
        }
    }

    @GetMapping("/")
    public String home() {
        return "Welcome to the Archie Backend! Visit /test-db to check your MongoDB connection.";
    }
}
