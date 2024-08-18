package com.backend.datapipeline.controller;

import com.backend.datapipeline.service.IDataPipelineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class DataPipelineController {

    private final IDataPipelineService iDataPipelineService;

    public DataPipelineController(IDataPipelineService iDataPipelineService) {
        this.iDataPipelineService = iDataPipelineService;
    }

    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        iDataPipelineService.processFile(file);
        return ResponseEntity.ok().body("File is processing");
    }
}
