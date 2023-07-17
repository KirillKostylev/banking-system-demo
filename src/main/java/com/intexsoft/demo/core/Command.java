package com.intexsoft.demo.core;

import com.intexsoft.demo.repository.exception.JsonStorageException;

public interface Command {
    Object execute(String[] args) throws JsonStorageException;

    String getName();

    String getDescription();
}
