package com.company;
import org.junit.*;
import java.io.Serializable;

import static org.junit.Assert.assertEquals;

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

    public User(String password, Float amount, String username){
        this.password = password;
        this.amount = amount;
        this.username = username;
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

    @Test
    public void UserCreationTest() {
        User tester = new User("badpassword", 0f, "john doe"); // MyClass is tested

        // assert statements
        assertEquals("badpassword", tester.getPassword());
        assertEquals("john doe", tester.getUsername());
    }

}
