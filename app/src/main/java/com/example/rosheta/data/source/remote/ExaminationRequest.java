package com.example.rosheta.data.source.remote;

import java.util.Date;

public class ExaminationRequest {
    private String name;
    private String lat;
    private String lng;
    private String status;
    private String created_at;

    public ExaminationRequest() {
    }

    public String getName() {
        return name;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    public String getStatus() {
        return status;
    }

    public String getCreated_at() {
        return created_at;
    }
}
