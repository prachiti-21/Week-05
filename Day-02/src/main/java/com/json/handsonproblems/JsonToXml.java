package com.json.handsonproblems;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.io.IOException;

public class JsonToXml {
    public static void main(String[] args) {
        try {
            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Read JSON file into JsonNode
            JsonNode jsonNode = objectMapper.readTree(new File("data.json"));

            // Convert JSON to XML using XmlMapper
            XmlMapper xmlMapper = new XmlMapper();
            String xmlOutput = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);

            // Print the XML output
            System.out.println("Converted XML:\n" + xmlOutput);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
