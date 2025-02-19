package com.json.practiceproblems;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class MergeJson {
    public static void main(String[] args) {
        try {
            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Define two JSON strings
            String json1 = "{ \"name\": \"John Doe\", \"email\": \"johndoe@example.com\" }";
            String json2 = "{ \"age\": 30, \"city\": \"New York\" }";

            // Convert JSON strings to JsonNode
            JsonNode node1 = objectMapper.readTree(json1);
            JsonNode node2 = objectMapper.readTree(json2);

            // Merge the two JSON objects
            ObjectNode mergedNode = objectMapper.createObjectNode();
            mergedNode.setAll((ObjectNode) node1);
            mergedNode.setAll((ObjectNode) node2);

            // Convert merged JSON to string and print
            String mergedJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(mergedNode);
            System.out.println("Merged JSON: \n" + mergedJson);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

