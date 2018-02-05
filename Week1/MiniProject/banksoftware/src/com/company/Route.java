package com.company;

public interface Route <T> {
    public Main.ProgramState processResponse(T response, Main.ProgramState currentState);
}
