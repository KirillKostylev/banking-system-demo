package com.intexsoft.demo.repository;

import com.intexsoft.demo.core.config.JsonStorage;
import com.intexsoft.demo.entity.AbstractEntity;
import com.intexsoft.demo.repository.exception.EntityNotFound;
import com.intexsoft.demo.repository.exception.JsonStorageException;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public abstract class JsonAbstractRepository<V extends AbstractEntity> implements Repository<UUID, V> {
    protected JsonStorage storage = JsonStorage.INSTANCE;

    protected Map<UUID, V> cache = new ConcurrentHashMap<>();

    abstract Class<V> getStorageValueClass();

    @Override
    public V create(V value) throws JsonStorageException {
        updateCacheIfRequired();
        cache.put(value.getId(), value);
        storage.saveJson(cache, getStorageValueClass());
        return value;
    }

    @Override
    public V findById(UUID key) throws JsonStorageException {
        updateCacheIfRequired();
        return Optional
                .ofNullable(cache.get(key))
                .orElseThrow(() -> new EntityNotFound("Object of " + getStorageValueClass() + " with id + " + key + " not found. "));
    }

    @Override
    public Map<UUID, V> findAll() throws JsonStorageException {
        updateCacheIfRequired();
        return cache;
    }

    @Override
    public boolean delete(UUID key) throws JsonStorageException {
        findById(key);
        cache.remove(key);
        storage.saveJson(cache, getStorageValueClass());
        return true;
    }

    @Override
    public V update(V value) throws JsonStorageException {
        findById(value.getId());
        cache.put(value.getId(), value);
        storage.saveJson(cache, getStorageValueClass());
        return value;
    }

    protected void updateCacheIfRequired() throws JsonStorageException {
        if (cache.isEmpty()) {
            cache = storage.readJson(getStorageValueClass());
        }
    }
}
