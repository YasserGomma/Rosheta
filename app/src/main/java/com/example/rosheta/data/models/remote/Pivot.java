package com.example.rosheta.data.models.remote;

import java.util.Date;

public class Pivot {
    private int examination_id;
    private int medicine_id;
    private Date created_at;
    private Date updated_at;

    public int getExamination_id() {
        return examination_id;
    }

    public int getMedicine_id() {
        return medicine_id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }
}
