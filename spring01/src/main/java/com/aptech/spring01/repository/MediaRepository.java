package com.aptech.spring01.repository;

import com.aptech.spring01.models.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface MediaRepository extends JpaRepository<Media, Integer> {
}
