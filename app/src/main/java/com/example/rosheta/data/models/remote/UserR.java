package com.example.rosheta.data.models.remote;

public class UserR {
    private String name;
    private String national_id;
    private String phone;
    private String email;
    private String password_confirmation;
    private String password;

    public UserR(String name, String national_id, String phone, String email, String password_confirmation, String password) {
        this.name = name;
        this.national_id = national_id;
        this.phone = phone;
        this.email = email;
        this.password_confirmation = password_confirmation;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getNational_id() {
        return national_id;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword_confirmation() {
        return password_confirmation;
    }

    public String getPassword() {
        return password;
    }
}
