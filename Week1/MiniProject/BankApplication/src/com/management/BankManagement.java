package com.management;

import com.company.Driver;
import com.connection.BankConnection;
import com.connection.User;

import java.util.*;

public class BankManagement {
    //-- tells the bank what state the user is in.
    private Prompt<ArrayList<String>> usernameAndPasswordPrompt;
    private Prompt<Integer> simpleSelectionPrompt;
    private Route<Integer> userPromptRouter;
    private Route<ArrayList<String>> userAndPasswordRouter;
    private User user;
    BankConnection bankConnection = new BankConnection();

    public ArrayList<String> ExitProgram(ArrayList<String> arr){
        arr.clear();
        arr.add("exit");
        return arr;
    }

    //-- by default place the bank into the prompt state
    public BankManagement(){

        user = new User();

        usernameAndPasswordPrompt = (List<String> promptMessage)->{

            ArrayList<String>  usernameAndPassword = new ArrayList<>(2);
            String userInput;

            //-- display message to users
            for(String line: promptMessage){
                System.out.println(line);
            }

            try{
                Scanner scanner = new Scanner(System.in);
                System.out.print("enter your username: ");
                userInput = scanner.next();
                System.out.println(userInput);
                if(!userInput.toLowerCase().contains("exit")){
                    usernameAndPassword.add(userInput);
                    System.out.print("enter your password: ");
                    userInput = scanner.next();
                    if(!userInput.toLowerCase().contains("exit")) {
                        usernameAndPassword.add(userInput);
                    }else{
                        return ExitProgram(usernameAndPassword);
                    }
                }else{
                    return ExitProgram(usernameAndPassword);
                }
            }catch (Exception ex){
                // ex.printStackTrace();
                System.out.println("Not a valid input.");
            }
            return usernameAndPassword;
        };

        userAndPasswordRouter = (ArrayList<String> arr, Driver.ProgramState state)->{

            if(arr.get(0).toLowerCase().contains("exit")){
                return Driver.ProgramState.USER_LOGOUT;
            }else if(arr.size() <= 1){
                return Driver.ProgramState.USER_PROMPT;
            }else{
                return Driver.ProgramState.USER_PROFILE;
            }
        };

        simpleSelectionPrompt = (List<String> promptMessage)->{
            //-- hold user input
            int userInput = 0;

            //-- display message to users
            for(String line: promptMessage){
                System.out.println(line);
            }

            //-- take the users input
            try{
                Scanner scanner = new Scanner(System.in);
                System.out.print("user input: ");
                userInput = scanner.nextInt();
            }catch(Exception ex){
                // ex.printStackTrace();
                System.out.println("Not a valid input.");
            }

            return userInput;
        };

        userPromptRouter = (Integer response, Driver.ProgramState state) ->{
            if(response == 0){
                return Driver.ProgramState.USER_CREATE;
            }else if(response == 1){
                return Driver.ProgramState.USER_LOGIN;
            } else if(response == 2){
                return Driver.ProgramState.USER_LOGOUT;
            }else{
                System.out.println("Not a valid input.");
            }
            return state;
        };

    }

    public Driver.ProgramState process(Driver.ProgramState currentState){
        switch (currentState){
            case USER_PROFILE:
                //-- reroutes user based on the selection they choose.
                Integer userProfileInput =  simpleSelectionPrompt.display(Arrays.asList(
                        "---------------------------------------",
                        "             USER PROFILE              ",
                        "---------------------------------------",
                        "0 -                      DISPLAY AMOUNT",
                        "1 -                      DEPOSIT AMOUNT",
                        "2 -                     WITHDRAW AMOUNT",
                        "3 -                        EXIT PROGRAM",
                        "---------------------------------------"));

                if(userProfileInput == 0){
                        System.out.println("Current Amount :" + user.getAmount());
                }else if(userProfileInput == 1){
                        int depositAmount = simpleSelectionPrompt.display(
                                Arrays.asList("Current Amount :" + user.getAmount()));
                        if(depositAmount > 0){
                            user.setAmount(user.getAmount() + depositAmount);
                        }else{
                            System.out.println("you cannot deposit a negative amount");
                        }

                        System.out.println("New Amount :" + user.getAmount());
                }else if(userProfileInput == 2){
                    int withdrawAmount = simpleSelectionPrompt.display(
                            Arrays.asList("Current Amount :" + user.getAmount()));

                    if(withdrawAmount < user.getAmount()){
                        user.setAmount(user.getAmount() - withdrawAmount);
                    }else{
                        System.out.println("your withdrawal amount can not be greater than the amount in your bank account.");
                    }

                    System.out.println("New Amount :" + user.getAmount());
                }else if(userProfileInput == 3){
                    System.out.println("saving all transactions");
                    System.out.println("processing..............");
                    bankConnection.insertUser(user);
                    return Driver.ProgramState.USER_LOGOUT;
                }
                return Driver.ProgramState.USER_PROFILE;
            case USER_CREATE:
                //-- reroutes user based on the selection they choose.
                ArrayList<String> usercreationInput = usernameAndPasswordPrompt.display(Arrays.asList(
                        "---------------------------------------",
                        "       CREATE AN ACCOUNT SCREEN        ",
                        "---------------------------------------",
                        "please enter your username and password",
                        " or enter exit to exit the application ",
                        "---------------------------------------"));

                if(usercreationInput.contains("exit")){
                    return userAndPasswordRouter.processResponse(usercreationInput,
                            currentState);
                }else {
                    //-- check if the account exist in the database.
                    System.out.println("loading...............");
                    user = bankConnection.getUser(usercreationInput.get(0), usercreationInput.get(1));
                    if(user != null){
                        System.out.println("the username entered is already taken.");
                    }else{
                        //-- add the user to the database.
                        System.out.println("processing...............");
                        user = new User(usercreationInput.get(0), 0f, usercreationInput.get(1));
                        bankConnection.insertUser(user);
                        user = bankConnection.getUser(user.getUsername(), user.getPassword());
                        user.setAmount(0f);
                        System.out.println("Account saved");
                        return userAndPasswordRouter.processResponse(usercreationInput,
                                currentState);
                    }
                }
                break;
            case USER_LOGIN:
                //-- reroutes user based on the selection they choose.
                ArrayList<String> userInput = usernameAndPasswordPrompt.display(Arrays.asList(
                        "---------------------------------------",
                        "             LOGIN SCREEN              ",
                        "---------------------------------------",
                        "please enter your username and password",
                        " or enter exit to exit the application ",
                        "---------------------------------------"));

                if(userInput.contains("exit")){
                    return userAndPasswordRouter.processResponse(userInput,
                            currentState);
                }else{
                    //-- check if the account exist in the database.
                    user = bankConnection.getUser(userInput.get(0), userInput.get(1));
                    if(user == null){
                        System.out.println("your account does not exit or it was not created. please contact" +
                                " your bank at " + " 1-357-465-4739");
                    }else{
                        return userAndPasswordRouter.processResponse(userInput,
                                currentState);
                    }
                }

            case USER_PROMPT:
                //-- reroutes user based on the selection they choose.
                return userPromptRouter.processResponse(
                        simpleSelectionPrompt.display(Arrays.asList(
                                "---------------------------------------",
                                "            WELCOME SCREEN             ",
                                "---------------------------------------",
                                "0 -                   CREATE AN ACCOUNT",
                                "1 -               LOGIN TO YOUR ACCOUNT",
                                "2 -                EXIT THE APPLICATION",
                                "---------------------------------------")),
                        currentState);
            default:{

            }
        }
        return  currentState;
    }
}
