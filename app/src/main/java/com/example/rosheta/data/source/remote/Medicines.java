package com.example.rosheta.data.source.remote;

import java.util.Date;

public class Medicines {
    private int id;
    private String name;
    private String description;
    private Object photo;
    private double price;
    private Date created_at;
    private Date updated_at;
    private Pivot pivot;

    public Medicines() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Object getPhoto() {
        return photo;
    }

    public double getPrice() {
        return price;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public Pivot getPivot() {
        return pivot;
    }
}
