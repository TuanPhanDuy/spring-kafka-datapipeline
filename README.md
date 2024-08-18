# Spring Kafka Data Pipeline

This project is a Spring Boot application that processes large CSV files containing user segmentation information and pushes the data to Kafka. The service is designed to handle large files efficiently, allow customization of Kafka configurations, and includes robust error handling and logging. The project is built using JDK 21 and can be run using Docker Compose.

Backend using spring boot to support upload csv file.
Kafka is the streaming platform to perform store and process the large data in real time, event 
Inside source code using BufferedReader to perform read line of file effectly. Config batch size to send the message to Kafka at the same time
## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Running the Application](#running-the-application)
- [Using the API](#using-the-api)
- [Validating](#validating)

## Features

- **CSV File Processing:** Efficiently processes large CSV files for user segmentation data.
- **Kafka Integration:** Pushes processed data to Kafka topics with customizable configurations.
- **Error Handling:** Comprehensive error handling for file processing and Kafka interactions.
- **Logging:** Detailed logging for monitoring and troubleshooting.
- **Docker Compose:** Easily run the service in a containerized environment using Docker Compose.

## Prerequisites

Before you begin, ensure you have the following installed on your machine:

- **JDK 21**: Download and install [JDK 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html).
- **Maven**: Ensure that Maven is installed. You can download it from [here](https://maven.apache.org/download.cgi).
- **Docker**: Download and install Docker from [here](https://www.docker.com/products/docker-desktop).
- **Git**: Download and install Git from [here](https://git-scm.com/downloads).
- **Git**: Download and install Docker from [here](https://www.postman.com/downloads/).
  
## Installation

### 1. Clone the Repository

  First, clone the repository to your local machine:

  Open git bash
  
  git clone https://github.com/TuanPhanDuy/spring-kafka-datapipeline.git
  
  cd spring-kafka-datapipeline

## Running the Application

### 2. Build project

  Run maven command to build project.
  mvn clean install

### 3. Run docker compose

  Run docker desktop

  Run docker-compose.yml file to pull images and start containers.
  docker compose up -d

## Using the API

  Open and import data-pipeline.postman_collection.json into Postman
  POST method: http://localhost:8080/api/upload-file
  Body file : csv file

## Validating

  Open browser then access http://localhost:9021/clusters to validate
