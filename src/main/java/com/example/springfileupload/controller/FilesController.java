package com.example.springfileupload.controller;

import com.example.springfileupload.model.FileInfo;
import com.example.springfileupload.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class FilesController {

    private final FileStorageService storageService;

    @PostMapping("/save-local")
    public String saveLocal(@RequestParam("file") MultipartFile file) throws IOException {
        return storageService.saveLocal(file);
    }

    @PostMapping("/save-db")
    public String saveDB(@RequestParam("file") MultipartFile file) throws IOException {
        return storageService.saveDB(file);
    }

    @GetMapping("/read-url")
    public File readFile(@RequestParam String fileUrl) throws IOException {
        return  storageService.readFileUrl(fileUrl);
    }

    @GetMapping("/read-info")
    public FileInfo readFile1(@RequestParam String fileUrl) throws IOException {
        return  storageService.readFileResource(fileUrl);
    }

    @GetMapping("/show")
    public ResponseEntity<Resource> showFileResource(@RequestParam String fileUrl) throws IOException {
        FileInfo file = storageService.readFileResource(fileUrl);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getFileType()))
                .body(new ByteArrayResource(file.getFileData()));
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFileResource(@RequestParam String fileUrl) throws IOException {
        FileInfo file = storageService.readFileResource(fileUrl);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+file.getFileName())
                .body(new ByteArrayResource(file.getFileData()));
    }






}
