package com.trishasofttech.loginregistermysql;

public class Data {

    String name;
    String email;
    String mobile;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public Data(String name, String email, String mobile) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
    }


}
