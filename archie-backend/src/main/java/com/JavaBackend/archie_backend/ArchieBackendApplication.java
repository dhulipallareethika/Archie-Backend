package com.JavaBackend.archie_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class ArchieBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(ArchieBackendApplication.class, args);
	}

}
