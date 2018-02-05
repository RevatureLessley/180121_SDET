package com.management;

import java.util.List;

public interface Prompt<T> {
    public T display(List<String> promptMessage);
}
