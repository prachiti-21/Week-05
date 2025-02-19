package com.json.practiceproblems;

import com.fasterxml.jackson.databind.ObjectMapper;

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

    // Getters and Setters (Optional, required for some frameworks)
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getYear() { return year; }
}

public class ConvertJavaObject {
    public static void main(String[] args) {
        try {
            Car car = new Car("Toyota", "Corolla", 2022);
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(car);

            System.out.println(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

