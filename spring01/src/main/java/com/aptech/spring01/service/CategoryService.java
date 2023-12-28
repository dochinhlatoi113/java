package com.aptech.spring01.service;

import com.aptech.spring01.models.Category;
import com.aptech.spring01.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public List<Category> search(String keyword) {
        return categoryRepository.search(keyword);
    }

    public Category detail(Integer id) {
        return categoryRepository.findById(id).get();
    }

    public Category findCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    public Category create(Category category) {
        try {
            categoryRepository.save(category);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return category;
    }

    /**
     * Update category
     *
     * @param category
     * @return
     */
    public boolean update(Category category) {
        try {
            categoryRepository.save(category);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public boolean delete(Integer categoryId) {
        try {
            categoryRepository.deleteById(categoryId);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }
}
