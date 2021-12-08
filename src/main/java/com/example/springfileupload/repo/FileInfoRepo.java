package com.example.springfileupload.repo;

import com.example.springfileupload.model.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileInfoRepo extends JpaRepository<FileInfo,Long> {
}
