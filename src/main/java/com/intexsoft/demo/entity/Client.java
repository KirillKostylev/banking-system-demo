package com.intexsoft.demo.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Client extends AbstractEntity {
    private ClientType clientType;

    private Collection<UUID> accountIds = new ArrayList<>();

    public Client() {
    }

    public Client(ClientType clientType) {
        this.clientType = clientType;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public Collection<UUID> getAccountIds() {
        return accountIds;
    }

    public void setAccountIds(Collection<UUID> accountIds) {
        this.accountIds = accountIds;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientType=" + clientType +
                ", accountIds=" + accountIds +
                ", id=" + id +
                '}';
    }
}
