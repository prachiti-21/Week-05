package com.json.handsonproblems;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class IPLCensorAnalyzer {
    public static void main(String[] args) {
        String jsonInputFile = "ipl_data.json";
        String jsonOutputFile = "ipl_censored.json";
        String csvInputFile = "ipl_data.csv";
        String csvOutputFile = "ipl_censored.csv";

        // Process JSON
        processJson(jsonInputFile, jsonOutputFile);

        // Process CSV
        processCsv(csvInputFile, csvOutputFile);
    }

    // Function to process JSON data
    private static void processJson(String inputFile, String outputFile) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // Read JSON into List of Maps
            List<Map<String, Object>> matches = objectMapper.readValue(new File(inputFile), List.class);

            // Apply censorship rules
            for (Map<String, Object> match : matches) {
                match.put("team1", censorTeamName((String) match.get("team1")));
                match.put("team2", censorTeamName((String) match.get("team2")));
                match.put("winner", censorTeamName((String) match.get("winner")));
                match.put("player_of_match", "REDACTED");

                // Update the score keys
                Map<String, Object> score = (Map<String, Object>) match.get("score");
                Map<String, Object> censoredScore = new HashMap<>();
                for (String key : score.keySet()) {
                    censoredScore.put(censorTeamName(key), score.get(key));
                }
                match.put("score", censoredScore);
            }

            // Write censored JSON data to file
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputFile), matches);
            System.out.println("Censored JSON saved as: " + outputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Function to process CSV data
    private static void processCsv(String inputFile, String outputFile) {
        try {
            CsvMapper csvMapper = new CsvMapper();

            // Define CSV schema (read headers)
            CsvSchema schema = CsvSchema.emptySchema().withHeader();

            // Read CSV data into a list of maps
            MappingIterator<Map<String, String>> iterator = csvMapper.readerFor(Map.class).with(schema).readValues(new File(inputFile));
            List<Map<String, String>> matches = iterator.readAll();

            // Apply censorship rules
            for (Map<String, String> match : matches) {
                match.put("team1", censorTeamName(match.get("team1")));
                match.put("team2", censorTeamName(match.get("team2")));
                match.put("winner", censorTeamName(match.get("winner")));
                match.put("player_of_match", "REDACTED");
            }

            // Define output schema
            CsvSchema outputSchema = CsvSchema.builder()
                    .addColumn("match_id")
                    .addColumn("team1")
                    .addColumn("team2")
                    .addColumn("score_team1")
                    .addColumn("score_team2")
                    .addColumn("winner")
                    .addColumn("player_of_match")
                    .setUseHeader(true)
                    .build();

            // Write censored CSV data to file
            csvMapper.writer(outputSchema).writeValue(new File(outputFile), matches);
            System.out.println("Censored CSV saved as: " + outputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Function to mask team names (Censorship)
    private static String censorTeamName(String teamName) {
        if (teamName == null || teamName.isEmpty()) return teamName;
        String[] words = teamName.split(" ");
        if (words.length > 1) {
            words[words.length - 1] = "***"; // Mask last word
        }
        return String.join(" ", words);
    }
}
