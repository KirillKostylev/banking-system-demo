package com.intexsoft.demo.repository;

import com.intexsoft.demo.repository.exception.JsonStorageException;

import java.util.Map;

public interface Repository<K, V> {
    V create(V value) throws JsonStorageException;

    V findById(K key) throws JsonStorageException;

    Map<K, V> findAll() throws JsonStorageException;

    boolean delete(K key) throws JsonStorageException;

    V update(V value) throws JsonStorageException;

}
