package com.backend.datapipeline;

import com.backend.datapipeline.property.KafkaProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableConfigurationProperties(KafkaProperties.class)
@EnableAspectJAutoProxy
public class DatapipelineApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatapipelineApplication.class, args);
	}

}
