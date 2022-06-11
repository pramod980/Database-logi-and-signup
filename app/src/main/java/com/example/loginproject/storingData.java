package com.example.loginproject;

public class storingData {

    String Email,password,passsword1;

    public storingData(String email, String password, String passsword1) {
        Email = email;
        this.password = password;
        this.passsword1 = passsword1;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasssword1() {
        return passsword1;
    }

    public void setPasssword1(String passsword1) {
        this.passsword1 = passsword1;
    }

}
