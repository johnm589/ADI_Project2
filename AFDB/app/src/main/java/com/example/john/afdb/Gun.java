package com.example.john.afdb;

/**
 * Created by John on 5/1/16.
 */
public class Gun {

    int id;
    String brand, model;

    public Gun(int id, String brand, String model) {
        this.id = id;
        this.brand = brand;
        this.model = model;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", ='" + brand + '\'' +
                ", year='" + model + '\'' +
                '}';
    }
}
