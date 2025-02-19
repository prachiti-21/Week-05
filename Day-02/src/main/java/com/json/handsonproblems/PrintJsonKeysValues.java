package com.json.handsonproblems;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.Iterator;
import java.util.Map;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.Iterator;
import java.util.Map;

public class PrintJsonKeysValues {
    public static void main(String[] args) {
        try {
            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Read JSON file into JsonNode
            JsonNode rootNode = objectMapper.readTree(new File("data.json"));

            // Print all keys and values
            printJson(rootNode, "");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Recursive method to print keys and values
    public static void printJson(JsonNode node, String parentKey) {
        if (node.isObject()) {
            // Iterate over object fields
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                printJson(entry.getValue(), parentKey + entry.getKey() + ".");
            }
        } else if (node.isArray()) {
            // Iterate over array elements
            for (int i = 0; i < node.size(); i++) {
                printJson(node.get(i), parentKey + "[" + i + "].");
            }
        } else {
            // Print key-value pair
            System.out.println(parentKey.substring(0, parentKey.length() - 1) + " -> " + node.asText());
        }
    }
}
