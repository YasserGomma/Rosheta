package com.example.rosheta.data.models.remote;

public class Pharmacy {
    private int id;
    private String name;
    private double distance;
    private String lat;
    private String lng;

    public void setDistance(double distance) {
        this.distance = distance;
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

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }
}
