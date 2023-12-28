package com.aptech.spring01.controllers;

import com.aptech.spring01.exception.StorageFileNotFoundException;
import com.aptech.spring01.models.Media;
import com.aptech.spring01.service.MediaService;
import com.aptech.spring01.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/uploads")
public class UploadController {
    @Autowired
    private StorageService storageService;

    @Autowired
    MediaService mediaService;

    @GetMapping("")
    public String index() throws IOException {
        return "upload/index";
    }


    @PostMapping("")
    public ResponseEntity<Media> upload(@RequestParam("file") MultipartFile file,
                                        RedirectAttributes redirectAttributes) {

        String path = storageService.store(file);
        if (path == "") {
            return ResponseEntity.badRequest().build();
        }
        Media media = new Media();
        media.setMimetype(file.getOriginalFilename());
        media.setName(file.getName());
        media.setPath(path);
        media.setType("local");
        mediaService.save(media);

        return ResponseEntity.ok(media);

    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
