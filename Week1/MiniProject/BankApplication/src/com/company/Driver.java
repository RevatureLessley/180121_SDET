package com.company;

import com.connection.BankConnection;
import com.connection.User;
import com.management.BankManagement;

import java.util.logging.Logger;

public class Driver {

    private static final Logger LOGGER = Logger.getLogger( Driver.class.getName() );

    /* represents the programs current state */
    public enum ProgramState{
        USER_LOGIN, USER_CREATE, USER_LOGOUT, USER_PROMPT, USER_PROFILE
    }

    /* main program */
    public static void main(String[] args) {
        ProgramState currentState = ProgramState.USER_PROMPT;
        BankManagement bankManagement = new BankManagement();
        BankConnection bankConnection = new BankConnection();
        User user = bankConnection.getUser("adam", "adam");
        if(user== null){
            System.out.println("user not found");
        }else{
            System.out.println(user.toString());
        }
        //bankConnection.insertUser(new User("jim", 0f, "jim"));
        bankConnection.updateUserById(new User("jim", 40f, "jim", 60));

        while(currentState != ProgramState.USER_LOGOUT){
            currentState = bankManagement.process(currentState);
        }

        System.out.println("Thank you for banking with us. We hope you come again.");
    }
}
