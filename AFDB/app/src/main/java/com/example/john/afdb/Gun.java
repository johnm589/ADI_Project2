package com.example.john.afdb;

/**
 * Created by John on 5/1/16.
 */
public class Gun {

   String type;
    String brand, model;

    public Gun(String type, String brand, String model) {
        this.type = type;
        this.brand = brand;
        this.model = model;
    }

    @Override
    public String toString() {
        return "Gun{" +
                "type=" + type +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
