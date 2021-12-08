package com.example.springfileupload.service;

import com.example.springfileupload.model.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface FileStorageService {

    String saveLocal(MultipartFile file) throws IOException;
    String saveDB(MultipartFile file) throws IOException;

    File readFileUrl(String path) throws IOException;
    FileInfo readFileResource(String path) throws IOException;
}
