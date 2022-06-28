package com.example.rosheta.data.models.remote;

import java.util.Date;

public class AllMedicines {
    private int id;
    private String name;
    private String description;
    private double price;
    private String photo;
    private String created_at;
    private String updated_at;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getPhoto() {
        return photo;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }
}
