package com.example.springfileupload;

import com.example.springfileupload.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class SpringFileuploadApplication{

    @Resource
    FileStorageService storageService;

    public static void main(String[] args) {
        SpringApplication.run(SpringFileuploadApplication.class, args);
    }
}
