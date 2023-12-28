package com.aptech.spring01.service;

import com.aptech.spring01.models.Media;
import com.aptech.spring01.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediaService {
    @Autowired
    MediaRepository mediaRepository;

    public Media save(Media media) {
        try {
            mediaRepository.save(media);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return media;
    }
}
