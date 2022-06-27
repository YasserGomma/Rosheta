package com.example.rosheta.data.source.remote;

public class Clinc {
    private int id;
    private String name;
    private String SName;
    private String LName;
    private double distance;

    public Clinc(int id, String name, String SName, String LName, double distance) {
        this.id = id;
        this.name = name;
        this.SName = SName;
        this.LName = LName;
        this.distance = distance;
    }

    public Clinc() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSName() {
        return SName;
    }

    public void setSName(String SName) {
        this.SName = SName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
