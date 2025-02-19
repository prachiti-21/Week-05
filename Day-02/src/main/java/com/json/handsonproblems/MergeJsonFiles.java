package com.json.handsonproblems;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class MergeJsonFiles {
    public static void main(String[] args) {
        try {
            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Read both JSON files into JsonNode objects
            JsonNode jsonNode1 = objectMapper.readTree(new File("data.json"));
            JsonNode jsonNode2 = objectMapper.readTree(new File("data2.json"));

            // Merge JSON objects
            JsonNode mergedJson = mergeJson(jsonNode1, jsonNode2);

            // Convert merged JSON to string and print
            String mergedJsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(mergedJson);
            System.out.println("Merged JSON:\n" + mergedJsonString);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to merge two JSON objects
    public static JsonNode mergeJson(JsonNode mainNode, JsonNode updateNode) {
        if (mainNode.isObject() && updateNode.isObject()) {
            // Create a mutable copy of the first JSON object
            ObjectMapper mapper = new ObjectMapper();
            JsonNode mergedNode = mainNode.deepCopy();

            // Iterate over fields of updateNode and add/overwrite values
            Iterator<Map.Entry<String, JsonNode>> fields = updateNode.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                ((com.fasterxml.jackson.databind.node.ObjectNode) mergedNode).set(field.getKey(), field.getValue());
            }

            return mergedNode;
        }
        return mainNode;
    }
}

