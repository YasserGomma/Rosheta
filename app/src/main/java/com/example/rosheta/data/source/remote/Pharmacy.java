package com.example.rosheta.data.source.remote;

public class Pharmacy {
    private int id;
    private String name;
    private double distance;

    public Pharmacy(int id, String name, double distance) {
        this.id = id;
        this.name = name;
        this.distance = distance;
    }

    public Pharmacy() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getDistance() {
        return distance;
    }
}
