package com.backend.datapipeline.service;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface DataPipelineService {

    /**
     *
     * @param file - import csv file
     * @throws IOException
     */
    void processFile(MultipartFile file) throws IOException;
}
