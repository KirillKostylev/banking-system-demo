package com.intexsoft.demo.entity;


import java.util.UUID;

public abstract class AbstractEntity {
    protected UUID id;

    public UUID getId() {
        return id;
    }

    protected AbstractEntity() {
        this.id = UUID.randomUUID();
    }

    protected AbstractEntity(UUID id) {
        this.id = id;
    }
}
