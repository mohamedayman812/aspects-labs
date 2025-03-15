package com.example.demo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resource")
public class ResourceController {

    private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);

    @GetMapping
    public String getResource() {
        logger.info("GET request received");
        return "GET Response: Resource retrieved";
    }

    @PostMapping
    public String createResource(@RequestBody ResourceRequest resource) {
        if (resource == null || resource.getData() == null) {
            logger.error("POST request received with null body");
            return "POST Error: Invalid request body";
        }
        logger.info("POST request received with data: {}", resource.getData());
        return "POST Response: Resource created with data - " + resource.getData();
    }

    @PutMapping
    public String updateResource(@RequestBody ResourceRequest resource) {
        if (resource == null || resource.getData() == null) {
            logger.error("PUT request received with null body");
            return "PUT Error: Invalid request body";
        }
        logger.info("PUT request received with data: {}", resource.getData());
        return "PUT Response: Resource updated with data - " + resource.getData();
    }

    @DeleteMapping
    public String deleteResource() {
        logger.info("DELETE request received");
        return "DELETE Response: Resource deleted";
    }

    // Inner class to represent the request body
    public static class ResourceRequest {
        private String data;

        // Default constructor (important for JSON deserialization)
        public ResourceRequest() {}

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }
}
