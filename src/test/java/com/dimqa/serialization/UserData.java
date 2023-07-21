package com.dimqa.serialization;

public class UserData {
    private String email;
    private String name;

    public UserData(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public UserData() {
    }

    public static UserData from(User user) {
        UserData userData = new UserData();
        userData.setEmail(user.getEmail());
        userData.setName(user.getName());
        return userData;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
