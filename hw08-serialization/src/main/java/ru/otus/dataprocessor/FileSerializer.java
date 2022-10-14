package ru.otus.dataprocessor;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class FileSerializer implements Serializer {

    private final String fileName;

    public FileSerializer(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void serialize(Map<String, Double> data) {
        ObjectMapper mapper = new ObjectMapper();
        try (var fileWriter = new BufferedWriter(new FileWriter(fileName))) {
            mapper.writeValue(fileWriter, data);
        } catch (IOException e) {
            throw new FileProcessException("Error during serializing data", e);
        }
        //формирует результирующий json и сохраняет его в файл
    }
}
