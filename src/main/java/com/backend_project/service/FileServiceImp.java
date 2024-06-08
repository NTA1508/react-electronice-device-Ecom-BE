package com.backend_project.service;

import com.backend_project.model.FileModel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImp implements FileService{

    @Override
    public FileModel uploadImg(String path, MultipartFile file) throws IOException {
        FileModel fileModel = new FileModel();
        String fileName = file.getOriginalFilename();

        String randomId = UUID.randomUUID().toString();
        String finalName = randomId.concat(fileName.substring(fileName.indexOf(".")));

        String filePath = path + File.separator + finalName;
        File f = new File(path);
        if(!f.exists()){
            f.mkdir();
        }
        Files.copy(file.getInputStream(), Paths.get(filePath));
        fileModel.setImageFileName(finalName);
        return fileModel;
    }

    @Override
    public InputStream getImgFile(String path, String fileName, int id) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        return new FileInputStream(fullPath);
    }
}
