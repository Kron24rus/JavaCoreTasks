package ru.otus.dataprocessor;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.otus.model.Measurement;
import ru.otus.model.MeasurementMixIn;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ResourcesFileLoader implements Loader {

    private final String fileName;

    public ResourcesFileLoader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Measurement> load() {
        //читает файл, парсит и возвращает результат
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.addMixIn(Measurement.class, MeasurementMixIn.class);
        try (InputStream inputStream = ResourcesFileLoader.class.getClassLoader().getResourceAsStream(fileName)) {
            return List.of(objectMapper.readValue(inputStream, Measurement[].class));
        } catch (IOException e) {
            throw new FileProcessException("Error during processing input file", e);
        }
    }
}
