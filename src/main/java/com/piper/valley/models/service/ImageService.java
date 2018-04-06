package com.piper.valley.models.service;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;

public interface ImageService {


    public void createImage(MultipartFile file) throws IOException;
}
