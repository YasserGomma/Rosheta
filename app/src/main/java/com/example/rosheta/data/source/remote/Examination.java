package com.example.rosheta.data.source.remote;

import java.util.Date;

public class Examination {
    private int id;
    private int patient_id;
    private int doctor_id;
    private String report;
    private Date created_at;
    private Date updated_at;
    private String doctor_name;
    private double price;

    public Examination() {
    }

    public int getId() {
        return id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public String getReport() {
        return report;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public double getPrice() {
        return price;
    }
}
