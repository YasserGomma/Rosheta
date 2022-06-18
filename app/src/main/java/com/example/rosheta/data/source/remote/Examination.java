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
    public Examination(){}
    public Examination(int id, int patient_id, int doctor_id, String report, Date created_at, Date updated_at, String doctor_name, double price) {
        this.id = id;
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.report = report;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.doctor_name = doctor_name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
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

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Examination{" +
                "id=" + id +
                ", patient_id=" + patient_id +
                ", doctor_id=" + doctor_id +
                ", report='" + report + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", doctor_name='" + doctor_name + '\'' +
                ", price=" + price +
                '}';
    }
}
