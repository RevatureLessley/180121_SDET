package com.company;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
/*
* Name : Ernst Nathaniel blanchard
* Project : Mini Project Bank Account
*
 */
public class Main {

    /* print informative prompt to the user */
    private static void PrintUserPrompt (){
        System.out.println("---------------------------------------");
        System.out.println("WELCOME TO NATHANS BANK");
        System.out.println("---------------------------------------");
        System.out.println("Please select one of the following : ");
        System.out.println("0 - create an account");
        System.out.println("1 - login to you account :");
        System.out.println("2 - exit the application");
        System.out.println("---------------------------------------");
        System.out.println("Please select a choice below");
    }

    /* print informative prompt to the user */
    private static void PrintUserCreationPrompt(){
        System.out.println("---------------------------------------");
        System.out.println("Enter exit at anytime to exit.");
        System.out.println("---------------------------------------");
        System.out.print("Please enter your username :");
    }

    /* save data */
    private static void Save(HashMap<String, User> bankAccounts){
        try
        {
            FileOutputStream fos = new FileOutputStream("BankAccount.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(bankAccounts);
            oos.close();
            fos.close();
        }catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    /* load data */
    private static HashMap<String, User>  Load(){
        HashMap<String, User> bankAccounts = new HashMap<String, User>();
        try {
            FileInputStream fis = new FileInputStream("BankAccount.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            bankAccounts = (HashMap<String, User>) ois.readObject();
            ois.close();
            fis.close();
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
        catch(ClassNotFoundException c) {
            c.printStackTrace();
        }

        return bankAccounts;
    }

    /* represents the programs current state */
    public enum ProgramState{
        USER_LOGIN, USER_CREATE, USER_LOGOUT, USER_PROMPT, USER_PROFILE
    }

    /* main program */
    public static void main(String[] args) {
        //-- holds the programs current state. By default you are directed to the
        //-- user prompt.
        ProgramState programState = ProgramState.USER_PROMPT;
        //-- represents the bank account of all users.
        HashMap<String, User> bankAccounts = Load();
        //-- username and password.
        String username = "exit";
        String password = "exit";

        //-- as long as the user doesn't desire to logout...
        while(programState != ProgramState.USER_LOGOUT){

            //-- this is the default screen the user is displayed
            //-- upon entering the application.
            if(programState==ProgramState.USER_PROMPT) {
                PrintUserPrompt();
                try{
                    Scanner scanner = new Scanner(System.in);
                    int userInput = scanner.nextInt();
                    switch (userInput){
                        case 0:
                            System.out.println("\n\n\n\n\n");
                            programState = ProgramState.USER_CREATE;
                            break;
                        case 1:
                            System.out.println("\n\n\n\n\n");
                            programState = ProgramState.USER_LOGIN;
                            break;
                        case 2:
                            //-- enable the user to logout of the application.
                            programState = ProgramState.USER_LOGOUT;
                            break;
                        default:
                            System.out.println("The numeric input you entered is invalid please enter another input");
                            break;
                    }
                }catch(Exception ex){
                    // ex.printStackTrace();
                    System.out.println("\n\n\n\n\n");
                    System.out.println("The input you entered is invalid please enter another input");
                }
            }

            //-- displayed if the user decides to create an account.
            else if(programState==ProgramState.USER_CREATE){
                PrintUserCreationPrompt();
                try{
                    Scanner scanner = new Scanner(System.in);
                    username = scanner.next();
                    if(username.toLowerCase().contains("exit")){
                        programState = ProgramState.USER_LOGOUT;
                    }else{
                        System.out.print("enter password: ");
                        password = scanner.next();
                        if(password.toLowerCase().contains("exit")){
                            programState = ProgramState.USER_LOGOUT;
                        }else{
                            //-- check if user user exist the tell them to enter another bank account.
                            if(bankAccounts.containsKey(username)){
                                System.out.println("That username already exist");
                            }else{
                                User tempUser = new User();
                                tempUser.setUsername(username);
                                tempUser.setPassword(password);
                                bankAccounts.put(username, tempUser);
                                Save(bankAccounts);
                                System.out.println("Account created!!!");
                                System.out.println("username :" + tempUser.getUsername());
                                System.out.println("password :" + tempUser.getPassword());
                                programState = ProgramState.USER_PROFILE;

                                //-- persist information to the file to the database.
                            }
                        }
                    }
                }catch (Exception ex){
                    System.out.println("\n\n\n\n\n");
                    System.out.println("The input you entered is invalid please enter another input");
                }
            }

            //-- user profile
            else if (programState==ProgramState.USER_PROFILE){
                System.out.println("---------------------------------------");
                System.out.println("hello username: " + username);
                System.out.println("---------------------------------------");
                System.out.println("0 - see amount");
                System.out.println("1 - deposit amount");
                System.out.println("2 - withdraw amount");
                System.out.println("3 - exit program");
                System.out.println("---------------------------------------");
                try{
                    Scanner scanner = new Scanner(System.in);
                    int selection = scanner.nextInt();
                    User tempUser = bankAccounts.get(username);

                    switch(selection){
                        case 0:{
                            System.out.println(username + " current amount :" + tempUser.getAmount());
                            System.out.println("\n\n");
                            break;
                        }
                        case 1:{
                            System.out.println(username + " current amount :" + tempUser.getAmount());
                            System.out.print("enter a deposit amount: ");
                            int amountDeposit = scanner.nextInt();
                            //-- add and create new value.

                            if(amountDeposit > 0){
                                tempUser.setAmount(amountDeposit + tempUser.getAmount());
                                System.out.println(username + " new amount :" + bankAccounts.get(username).getAmount());
                            }else{
                                System.out.println("Please enter a valid amount");
                            }
                            System.out.println("\n\n");
                            break;
                        }
                        case 2:{
                            System.out.println(username + " current amount :" + tempUser.getAmount());
                            System.out.print("enter a withdrawal amount: ");
                            int withdrawalAmount = scanner.nextInt();
                            if(withdrawalAmount > 0 && tempUser.getAmount() >= withdrawalAmount){
                                tempUser.setAmount(tempUser.getAmount() - withdrawalAmount);
                                bankAccounts.replace(username, tempUser);
                                System.out.println(username + " new amount :" + tempUser.getAmount());
                            }else{
                                System.out.println("Please enter a valid amount");
                            }
                            System.out.println("\n\n");
                            break;
                        }
                        case 3:{
                            programState = ProgramState.USER_LOGOUT;
                            System.out.println("\n\n");
                            break;
                        }
                        default:{
                            System.out.println("\n\n\n\n\n");
                            System.out.println("The input you entered is invalid please enter another input");
                        }
                    }
                }catch (Exception ex){
                    System.out.println("\n\n\n\n\n");
                    System.out.println("The input you entered is invalid please enter another input");
                }
            }


            else if (programState == ProgramState.USER_LOGIN){
                PrintUserCreationPrompt();
                try{
                    Scanner scanner = new Scanner(System.in);
                    username = scanner.next();

                    if(username.toLowerCase().contains("exit")){
                        programState = ProgramState.USER_LOGOUT;
                    }else{
                        System.out.print("Please enter your password :");
                        password = scanner.next();
                    }

                    if(password.toLowerCase().contains("exit")){
                        programState = ProgramState.USER_LOGOUT;
                    }else{
                        //-- check if you are able to login if have right username and password.
                        if(bankAccounts.containsKey(username) && bankAccounts.get(username).getPassword().contains(password)){
                            System.out.println("successfully logged in");
                            programState =ProgramState.USER_PROFILE;
                        }else{
                            System.out.println("You entered the wrong combination, please try again");
                        }
                    }
                }catch (Exception ex){
                    System.out.println("\n\n\n\n\n");
                    System.out.println("The input you entered is invalid please enter another input");
                }
            }
            else{
                programState = ProgramState.USER_LOGOUT;
            }
        }
        Save(bankAccounts);
        System.out.println("Thank you for banking with us. Have a wonderful day!!!");
    }
}
