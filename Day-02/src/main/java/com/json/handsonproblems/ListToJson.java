package com.json.handsonproblems;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;

class Car {
    private String brand;
    private String model;
    private int year;

    // Constructor
    public Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    // Getters (Required for Jackson to work)
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getYear() { return year; }
}

public class ListToJson {
    public static void main(String[] args) {
        try {
            // Create a list of Car objects
            List<Car> cars = Arrays.asList(
                    new Car("Toyota", "Corolla", 2022),
                    new Car("Honda", "Civic", 2023),
                    new Car("Ford", "Focus", 2021)
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
