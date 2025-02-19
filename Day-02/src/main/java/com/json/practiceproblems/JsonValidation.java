package com.json.practiceproblems;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonValidation {
    public static void main(String[] args) {
        String jsonString = "{ \"name\": \"John Doe\", \"email\": \"johndoe@example.com\" }"; // Valid JSON
        // String jsonString = "{ name: \"John Doe\", email: \"johndoe@example.com\" }"; // Invalid JSON

        if (isValidJson(jsonString)) {
            System.out.println("Valid JSON");
        } else {
            System.out.println("Invalid JSON");
        }
    }

    public static boolean isValidJson(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.readTree(json); // Parse JSON
            return true; // JSON is valid
        } catch (Exception e) {
            return false; // JSON is invalid
        }
    }
}
