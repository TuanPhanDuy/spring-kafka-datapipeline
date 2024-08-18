package com.backend.datapipeline.service.impl;

import com.backend.datapipeline.property.KafkaProperties;
import com.backend.datapipeline.service.IDataPipelineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DataPipelineServiceImpl implements IDataPipelineService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final KafkaProperties kafkaProperties;

    public DataPipelineServiceImpl(KafkaTemplate<String, String> kafkaTemplate, KafkaProperties kafkaProperties) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaProperties = kafkaProperties;
    }


    /**
     * @param file - import csv file
     * @throws IOException
     */
    @Override
    public void processFile(MultipartFile file) throws IOException {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            List<String> batches = new ArrayList<>();
            while ((line = reader.readLine()) != null){
                String[] parts = line.split(",");
                if (parts.length == 2){
                    String userId = parts[0];
                    String segmentType = parts[1];
                    String message = userId + "," + segmentType;
                    batches.add(message);
                    if (batches.size() >= kafkaProperties.getBatchSize()) {
                        batches.stream().map(batch -> kafkaTemplate.send(kafkaProperties.getTopic(), batch));
                        batches.clear();
                    }
                }
            }

            if(!batches.isEmpty())
                batches.stream().map(batch -> kafkaTemplate.send(kafkaProperties.getTopic(), batch));
        }
    }
}
