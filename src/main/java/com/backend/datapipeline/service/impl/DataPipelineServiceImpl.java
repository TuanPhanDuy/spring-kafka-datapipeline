package com.backend.datapipeline.service.impl;

import com.backend.datapipeline.exception.BadRequestException;
import com.backend.datapipeline.property.KafkaProperties;
import com.backend.datapipeline.service.DataPipelineService;
import com.backend.datapipeline.service.KafkaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DataPipelineServiceImpl implements DataPipelineService {

    private final KafkaService kafkaService;

    private final KafkaProperties kafkaProperties;

    public DataPipelineServiceImpl(KafkaService kafkaService, KafkaProperties kafkaProperties) {
        this.kafkaService = kafkaService;
        this.kafkaProperties = kafkaProperties;
    }


    /**
     * @param file - import csv file
     * @throws IOException
     */
    @Override
    public void processFile(MultipartFile file) throws IOException {
        if (!file.getOriginalFilename().endsWith(".csv"))
            throw new BadRequestException("File type is not correct");

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
                    if (batches.size() >= kafkaProperties.getBatchSize()){
                        batches.parallelStream().forEach(batch -> kafkaService.sendMessage(kafkaProperties.getTopic(), batch));
                        log.info("push batch message: {}", batches.toString());
                        batches.clear();
                    }

                }
            }
            if(!batches.isEmpty())
                batches.parallelStream().forEach(batch -> kafkaService.sendMessage(kafkaProperties.getTopic(), batch));
        }
    }
}
