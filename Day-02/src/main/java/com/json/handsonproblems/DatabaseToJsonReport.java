package com.json.handsonproblems;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseToJsonReport {
    public static void main(String[] args) {
        // Database credentials
        String url = "jdbc:mysql://localhost:3306/your_database"; // Change your database name
        String user = "your_username";
        String password = "your_password";

        // SQL Query
        String query = "SELECT id, name, email, age FROM users";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // Create a list to store JSON objects
            List<Map<String, Object>> records = new ArrayList<>();

            // Fetch data from the ResultSet
            while (rs.next()) {
                Map<String, Object> record = new HashMap<>();
                record.put("id", rs.getInt("id"));
                record.put("name", rs.getString("name"));
                record.put("email", rs.getString("email"));
                record.put("age", rs.getInt("age"));
                records.add(record);
            }

            // Convert list to JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonReport = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(records);

            // Print the JSON report
            System.out.println("Generated JSON Report:\n" + jsonReport);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

