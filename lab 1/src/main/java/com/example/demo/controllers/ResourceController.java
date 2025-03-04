package com.example.demo.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resource")
public class ResourceController {

    @GetMapping
    public String getResource() {
        return "GET Response: Resource retrieved";
    }

    @PostMapping
    public String createResource(@RequestBody ResourceRequest resource) {
        return "POST Response: Resource created with data - " + resource.getData();
    }

    @PutMapping
    public String updateResource(@RequestBody ResourceRequest resource) {
        return "PUT Response: Resource updated with data - " + resource.getData();
    }

    @DeleteMapping
    public String deleteResource() {
        return "DELETE Response: Resource deleted";
    }

    // Inner class to represent the request body
    public static class ResourceRequest {
        private String data;

        // Getter and Setter
        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }
}