package com.example.rosheta.data.models.remote;

import java.util.Date;

public class Report {
    private int id;
    private String name;
    private String description;
    private Date created_at;
    private Date updated_at;
    private Pivot_diseases pivot;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public Pivot_diseases getPivot() {
        return pivot;
    }
}
