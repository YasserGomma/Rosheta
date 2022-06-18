package com.example.rosheta.data.source.remote;

public class UserR {
    public String name;
    public String national_id;
    public String phone;
    public String email;
    public String password_confirmation;
    public String password;

    public UserR(String name, String national_id, String phone, String email, String password_confirmation, String password) {
        this.name = name;
        this.national_id = national_id;
        this.phone = phone;
        this.email = email;
        this.password_confirmation = password_confirmation;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserR{" +
                "name='" + name + '\'' +
                ", national_id='" + national_id + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password_confirmation='" + password_confirmation + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
