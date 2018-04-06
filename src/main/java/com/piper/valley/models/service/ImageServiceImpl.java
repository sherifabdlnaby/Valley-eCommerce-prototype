package com.piper.valley.models.service;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


@Service
public class ImageServiceImpl implements ImageService {

    private String absPath="C:\\Users\\Refaie\\Documents\\GitHub\\SWE-2-spring-boot-web-app\\src\\main\\resources\\static\\images";

    @Override
    public void createImage(MultipartFile file) throws IOException {
        if(!file.isEmpty())
        {
            Files.copy(file.getInputStream(), Paths.get(absPath,file.getOriginalFilename()));
        }
    }
}
