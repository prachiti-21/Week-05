package com.json.practiceproblems;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;

class Carr {
    private String brand;
    private String model;
    private int year;

    // Constructor
    public Carr(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    // Getters (Required for Jackson to work)
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getYear() { return year; }
}

public class ListToJson{
    public static void main(String[] args) {
        try {
            // Create a list of Car objects
            List<Carr> cars = Arrays.asList(
                    new Carr("Toyota", "Corolla", 2022),
                    new Carr("Honda", "Civic", 2023),
                    new Carr("Ford", "Focus", 2021)
            );

            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Convert list to JSON array
            String jsonArray = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(cars);

            // Print the JSON array
            System.out.println(jsonArray);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

