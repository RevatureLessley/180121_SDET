package com.company;

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

        while(currentState != ProgramState.USER_LOGOUT){
            currentState = bankManagement.process(currentState);
        }

        System.out.println("Thank you for banking with us. We hope you come again.");
    }
}
