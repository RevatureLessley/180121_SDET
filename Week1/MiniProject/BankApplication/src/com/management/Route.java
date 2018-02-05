package com.management;

import com.company.Driver;

public interface Route <T> {
    public Driver.ProgramState processResponse(T response, Driver.ProgramState currentState);
}
