package com.example.rosheta.data.source.remote;

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

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Pivot_diseases getPivot() {
        return pivot;
    }

    public void setPivot(Pivot_diseases pivot) {
        this.pivot = pivot;
    }
}
