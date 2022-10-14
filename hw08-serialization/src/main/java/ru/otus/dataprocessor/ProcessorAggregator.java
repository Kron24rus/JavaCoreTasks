package ru.otus.dataprocessor;

import ru.otus.model.Measurement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ProcessorAggregator implements Processor {

    @Override
    public Map<String, Double> process(List<Measurement> data) {
        //группирует выходящий список по name, при этом суммирует поля value
        Map<String, Double> processedData = new TreeMap<>();

        data.forEach(m -> {
            if (processedData.containsKey(m.getName())) {
                processedData.put(m.getName(), processedData.get(m.getName()) + m.getValue());
            } else {
                processedData.put(m.getName(), m.getValue());
            }
        });

        return processedData;
    }
}
