package com.aptech.spring01.repository;

import com.aptech.spring01.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    public Category findByName(String name);

    @Query("select id, name,tag from Category where name LIKE %:keyword% OR tag LIKE %:keyword%")
    public List<Category> search(String keyword);
}
