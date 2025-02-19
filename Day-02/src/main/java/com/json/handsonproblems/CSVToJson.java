package com.json.handsonproblems;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CSVToJson {
    public static void main(String[] args) {
        try {
            // Create CSV Mapper instance
            CsvMapper csvMapper = new CsvMapper();

            // Define CSV Schema (using the first row as column names)
            CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();

            // Read CSV file into List of Maps
            MappingIterator<Map<String, String>> iterator = csvMapper.readerFor(Map.class)
                    .with(csvSchema)
                    .readValues(new File("data.csv"));

            List<Map<String, String>> dataList = iterator.readAll();

            // Convert List of Maps to JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonOutput = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(dataList);

            // Print the JSON output
            System.out.println("Converted JSON:\n" + jsonOutput);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

