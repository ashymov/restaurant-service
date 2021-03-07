package com.sashymov.restaurantservice.services.impl;

import com.sashymov.restaurantservice.dao.FileRepo;
import com.sashymov.restaurantservice.models.entities.File;
import com.sashymov.restaurantservice.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileRepo fileRepo;
    @Override
    public File save(File file) {
        fileRepo.save(file);
        return file;
    }
}
