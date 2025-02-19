package com.json.practiceproblems;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FilterJson {
    public static void main(String[] args) {
        try {
            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Read JSON file into JsonNode
            JsonNode rootNode = objectMapper.readTree(new File("data.json"));

            // List to store filtered records
            List<JsonNode> filteredRecords = new ArrayList<>();

            // Iterate through JSON array and filter records
            for (JsonNode node : rootNode) {
                if (node.get("age").asInt() > 25) {
                    filteredRecords.add(node);
                }
            }

            // Convert filtered list to JSON and print
            String filteredJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(filteredRecords);
            System.out.println("Filtered JSON (age > 25):\n" + filteredJson);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
