package com.intexsoft.demo.repository;

import com.intexsoft.demo.entity.Client;
import com.intexsoft.demo.repository.exception.JsonStorageException;

import java.util.Collection;
import java.util.UUID;

public class ClientRepository extends JsonAbstractRepository<Client> {

    @Override
    Class<Client> getStorageValueClass() {
        return Client.class;
    }

    public Collection<Client> findClientsByIds(Collection<UUID> clientIds) throws JsonStorageException {
        updateCacheIfRequired();
        return cache.values()
                .stream()
                .filter(client -> clientIds.contains(client.getId()))
                .toList();
    }
}
