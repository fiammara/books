package com.example.demo.service;


import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;


    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public Category saveCategory(@Valid Category category) {
        return categoryRepository.save(category);

    }

    public Optional<Category> findCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }

    public Category updateCategory(Category categoryToUpdate) {
        if (categoryToUpdate.getCategoryId() == 0) {

            throw new IllegalArgumentException("Category id cannot be 0");
        } else if (!findCategoryById(categoryToUpdate.getCategoryId()).isPresent()) {

            throw new IllegalArgumentException("Category was not found by parameters provided");
        }
        return categoryRepository.save(categoryToUpdate);
    }
}
