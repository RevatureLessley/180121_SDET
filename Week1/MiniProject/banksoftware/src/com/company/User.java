package com.company;
import org.junit.*;
import java.io.Serializable;

import static org.junit.Assert.assertEquals;

public class User implements Serializable{
    private String password;
    private Float amount;
    private String username;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User(String password, Float amount){
        super();
        this.password = password;
        this.amount = amount;
    }

    public User(String password, Float amount, String username){
        super();
        this.password = password;
        this.amount = amount;
        this.username = username;
    }

    public User(String password, Float amount, String username, int id){
        super();
        this.password = password;
        this.amount = amount;
        this.username = username;
        this.id = id;
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

    @Override
    public String toString() {
        return "Bank User [username=" + getUsername() + ", password=" + getPassword() + ", amount=" + getAmount() + "]";
    }

}
