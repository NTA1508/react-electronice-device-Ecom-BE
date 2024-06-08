package com.backend_project.service;

import com.backend_project.model.FileModel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Service
public interface FileService {
    FileModel uploadImg (String path, MultipartFile file) throws IOException;

    InputStream getImgFile(String path, String fileName, int id) throws FileNotFoundException;
}
