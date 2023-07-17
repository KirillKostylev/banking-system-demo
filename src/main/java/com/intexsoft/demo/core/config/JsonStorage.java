package com.intexsoft.demo.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intexsoft.demo.repository.exception.JsonStorageException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public enum JsonStorage {
    INSTANCE;

    private static final String DEFAULT_PATH = "db" + File.separator;
    public static final String JSON_EXTENSION = ".json";
    private final ObjectMapper mapper;

    JsonStorage() {
        mapper = new ObjectMapper();
    }


    public <V> void saveJson(Object o, Class<V> clazz) throws JsonStorageException {
        try {
            var name = JsonStorage.DEFAULT_PATH + clazz.getName() + JSON_EXTENSION;
            createIfNotExists(name);

            mapper.writeValue(new File(name), o);
        } catch (IOException e) {
            throw new JsonStorageException(e.getMessage(), e);
        }
    }

    public <V> Map<UUID, V> readJson(Class<V> clazz) throws JsonStorageException {
        try {
            var name = JsonStorage.DEFAULT_PATH + clazz.getName() + JSON_EXTENSION;
            var file = new File(name);

            if (file.length() == 0) {
                return new ConcurrentHashMap<>();
            }

            var type = mapper.getTypeFactory().constructMapType(ConcurrentHashMap.class, UUID.class, clazz);
            return mapper.readValue(file, type);
        } catch (IOException e) {
            throw new JsonStorageException(e.getMessage(), e);
        }
    }

    private void createIfNotExists(String name) throws IOException {
        var filePath = Paths.get(name);
        var directoryPath = Paths.get(DEFAULT_PATH);

        if (!Files.exists(directoryPath)) {
            Files.createDirectory(directoryPath);
        }

        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
    }
}
