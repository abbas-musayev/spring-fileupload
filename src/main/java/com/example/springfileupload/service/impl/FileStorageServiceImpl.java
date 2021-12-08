package com.example.springfileupload.service.impl;

import com.example.springfileupload.repo.FileInfoRepo;
import com.example.springfileupload.model.FileInfo;
import com.example.springfileupload.service.FileStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
public class FileStorageServiceImpl implements FileStorageService {

    private final FileInfoRepo repo;

    public FileStorageServiceImpl(FileInfoRepo repo) {
        this.repo = repo;
    }

    @Override
    public String saveLocal(MultipartFile file) throws IOException {

        String filename = file.getOriginalFilename();
        log.info(filename);
        Path path = Paths.get("F:/" + filename);
        log.info(String.valueOf(path));
        Files.write(path, file.getBytes());
        return "File";
    }

    @Override
    public String saveDB(MultipartFile file) throws IOException {
        FileInfo uploadFile = new FileInfo();

        uploadFile.setFileName(file.getOriginalFilename());
        uploadFile.setFileData(file.getBytes());
        uploadFile.setFileType(file.getContentType());
        repo.save(uploadFile);
        return "File Save Olundu";
    }

    @Override
    public File readFileUrl(String fileUrl) throws IOException {

        File file1 = ResourceUtils.getFile(fileUrl);
        return file1;
    }

    @Override
    public FileInfo readFileResource(String fileUrl) throws IOException {
        File file = ResourceUtils.getFile(fileUrl);

        byte[] bytes = Files.readAllBytes(file.toPath());
        String type = Files.probeContentType(file.getAbsoluteFile().toPath());

        FileInfo info = new FileInfo();
        info.setFileName(file.getName());
        info.setFileType(type);
        info.setFileData(bytes);
        return info;
    }
}
