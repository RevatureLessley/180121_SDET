package com.company;

import javax.jws.soap.SOAPBinding;
import java.io.Serializable;

public class User implements Serializable{
    private String password;
    private Float amount;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;

    public User(String password, Float amount){
        this.password = password;
        this.amount = amount;
    }

    public User(){
        amount = 0f;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

}
