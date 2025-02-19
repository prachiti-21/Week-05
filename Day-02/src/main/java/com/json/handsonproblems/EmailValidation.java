package com.json.handsonproblems;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileInputStream;

public class EmailValidation {
    public static void main(String[] args) {
        try {
            // Load JSON Schema from file
            FileInputStream schemaStream = new FileInputStream(new File("schema.json"));
            JSONObject schemaObject = new JSONObject(new JSONTokener(schemaStream));
            Schema schema = SchemaLoader.load(schemaObject);

            // JSON input with an email field
            String jsonString = "{ \"name\": \"John Doe\", \"email\": \"johndoe@example.com\" }";
            JSONObject jsonObject = new JSONObject(jsonString);

            // Validate JSON against schema
            schema.validate(jsonObject);
            System.out.println("JSON is valid ");

        } catch (Exception e) {
            System.out.println("JSON validation failed : " + e.getMessage());
        }
    }
}

